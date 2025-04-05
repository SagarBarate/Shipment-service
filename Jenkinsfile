pipeline {
    agent any

    tools {
        maven 'maven'
    }

    stages {
        stage('Git Checkout') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], 
                                userRemoteConfigs: [[url: 'https://github.com/SagarBarate/Shipment-service.git']])
                echo 'Git Checkout Completed'
            }
        }
        stage('Maven Build') {
            steps {
                sh 'mvn clean package -DskipTests'
                echo 'Maven Build Completed'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                // Bind the SONAR token from Jenkins credentials to an environment variable
                withCredentials([string(credentialsId: 'SONAR_TOKEN', variable: 'SONAR_AUTH_TOKEN')]) {
                    withSonarQubeEnv('MySonarQubeServer') {
                        sh "mvn sonar:sonar -Dsonar.projectKey=myMicroservice -Dsonar.login=${SONAR_AUTH_TOKEN} -DskipTests"
                    }
                }
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
    }
}
