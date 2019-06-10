pipeline {
    agent any
    tools{
        jdk '8'
    }
    options {
        skipStagesAfterUnstable()
    }
	environment {
		PATH = '$PATH:/usr/bin/'
	}
	
    stages {
		stage('Setting environment') {
			steps {
				echo "PATH is: $PATH"
			} 
		}
		
        stage('Cleanup'){
            steps{
                sh '''
                docker rmi $(docker images -f 'dangling=true' -q) || true
                docker rmi $(docker images | sed 1,2d | awk '{print $3}') || true
                '''
            }
         }
        stage('Test') {
            steps {
                sh 'mvn -f ./Backend/pom.xml clean test'
            }
        }

	stage('Sonar analysis') {
	    agent any
	    steps{
		withSonarQubeEnv('SonarQube') {
      			sh 'mvn -f ./Backend/pom.xml clean package sonar:sonar'
    		} 
	    }
	}

	stage('Quality gate') {
	    steps {
		    timeout(time: 2, unit: 'MINUTES') {
    		    waitForQualityGate abortPipeline: true
	        }
	    }
	}

    stage('Package') {
        steps {
            sh 'mvn -f ./Backend/pom.xml -B -DskipTests package'
        }
    }

    stage('Docker Build') {
        steps {
            sh 'docker build . -t michellebroens/kwetter_backend:production'
        }
    }
	
    stage('Docker publish') {
        steps {
            withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId:'dockerhub', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
            sh 'docker login -u $USERNAME -p $PASSWORD'
            sh 'docker push michellebroens/kwetter_backend:production'
            sh 'docker logout'
            }
        }
    }
		
	stage('Docker compose') {
		steps {
			sh 'docker-compose --version'
			sh 'docker-compose build'
			sh 'docker-compose up -d'
		}
	}	
    }
}
