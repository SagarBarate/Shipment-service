pipeline {
    agent any
    tools {
        maven 'maven'
    }
    stages {
        stage('Git Checkout') {
            steps {
                // Clones the repository and checks out the specified branch
                checkout scmGit(branches: [[name: '*/main']], userRemoteConfigs: [[url: 'https://github.com/SagarBarate/Shipment-service.git']])
                echo 'Git Checkout Completed'
            }
        }
        stage('Maven Build') {
            steps {
                // Clean and package the project, generating a deployable artifact (JAR/WAR)
                sh 'mvn clean package -DskipTests'
                echo 'Maven Build Completed'
            }
        }
    }
    post {
        always {
            // Archive the generated artifact if needed for later stages or review
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
    }
}
