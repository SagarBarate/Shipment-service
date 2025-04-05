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
        stage('Maven Build & Test') {
            steps {
                // Run clean, test, package and generate JaCoCo coverage report.
                sh 'mvn clean verify'
                echo 'Maven Build and Tests Completed'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withCredentials([string(credentialsId: 'SONAR_TOKEN', variable: 'SONAR_AUTH_TOKEN')]) {
                    withSonarQubeEnv('myMicroservice') {
                        // Note: When tests are run, the coverage report is generated and will be picked up by SonarQube
                        sh "mvn sonar:sonar -Dsonar.projectKey=myMicroservice -Dsonar.login=${SONAR_AUTH_TOKEN}"
                    }
                }
            }
        }
    }

    post {
        always {
            // Archive the generated artifact and reports.
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            archiveArtifacts artifacts: 'target/site/jacoco/**/*.*', allowEmptyArchive: true
            junit 'target/surefire-reports/*.xml'
        }
    }
}
