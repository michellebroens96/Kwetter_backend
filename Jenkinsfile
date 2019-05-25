pipeline {
    agent any
    tools{
        jdk '8'
    }
    options {
        skipStagesAfterUnstable()
    }

    stages {
        stage('Test') {
            steps {
                sh 'mvn -f Overheid/Backend/pom.xml clean test'
            }
            post {
                always {
                    junit 'Overheid/Backend/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                sh 'mvn -f Overheid/Backend/pom.xml -B -DskipTests package'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build ./Overheid/Backend/. -t kevinverkuijlenfontys/overheid:test'
                withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId:'dockerhub', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
                  sh 'docker login -u $USERNAME -p $PASSWORD'
                }
                sh 'docker push kevinverkuijlenfontys/overheid:test'
            }
        }

        stage('Kubernetes pod') {
             steps {
                 sh 'kubectl config view'
                 sh 'kubectl apply -f ./Overheid/Backend/overheid-dep.yaml'
             }
        }
    }
}
