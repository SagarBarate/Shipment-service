pipeline {
    agent any

    tools {
        maven 'maven'   // Make sure 'maven' is defined in Jenkins Global Tool Configuration
    }

    stages {
        stage('Git Checkout') {
            steps {
                // Clone the repository and check out the main branch
                checkout scmGit(branches: [[name: '*/main']], 
                                userRemoteConfigs: [[url: 'https://github.com/SagarBarate/Shipment-service.git']])
                echo 'Git Checkout Completed'
            }
        }

        stage('Maven Build') {
            steps {
                // Clean and package the project, generating a deployable artifact (JAR)
                sh 'mvn clean package -DskipTests'
                echo 'Maven Build Completed'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                // Use the SonarQube server configured in Jenkins (replace 'MySonarQubeServer' with your server name)
                withSonarQubeEnv('MySonarQubeServer') {
                    // Run Sonar analysis; update -Dsonar.projectKey to suit your naming
                    // If you want to run tests for coverage, remove "-DskipTests"
                    sh "mvn sonar:sonar -Dsonar.projectKey=ShipmentService -DskipTests"
                }
            }
        }
    }

    post {
        always {
            // Archive the generated .jar artifact
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
    }
}
