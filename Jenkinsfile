pipeline {
    agent any
    tools{
        jdk '8'
    }
    options {
        skipStagesAfterUnstable()
    }

    stages {
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
		withSonarQubeEnv('My SonarQube Server') {
      			sh 'mvn clean package sonar:sonar'
    		} 
	}

	stage('Quality gate') {
		timeout(time: 1, unit: 'HOURS') { // Just in case something goes wrong, pipeline will be killed after a timeout
    		def qg = waitForQualityGate() // Reuse taskId previously collected by withSonarQubeEnv
    		if (qg.status != 'OK') {
      			error "Pipeline aborted due to quality gate failure: ${qg.status}"	
	}

        stage('Package') {
            steps {
                sh 'mvn -f ./Backend/pom.xml -B -DskipTests package'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build . -t michellebroens/kwetter_backend:test'
            }
        }
        stage('Docker publish') {
            steps {
                withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId:'dockerhub', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
                  sh 'docker login -u $USERNAME -p $PASSWORD'
                  sh 'docker push michellebroens/kwetter_backend:test'
                  sh 'docker logout'
                }

            }
        }
    }
}
