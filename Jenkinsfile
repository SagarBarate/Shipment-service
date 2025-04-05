pipeline {
    agent any

    tools {
        maven 'maven'   // Ensure this matches the Maven tool configured in Jenkins
    }

    stages {
        stage('Git Checkout') {
            steps {
                // Clones the repository and checks out the main branch
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
                // Inject the SonarQube token stored in Jenkins (update 'SONAR_TOKEN' to your credentials ID)
                withCredentials([string(credentialsId: 'SONAR_TOKEN', variable: 'SONAR_AUTH_TOKEN')]) {
                    // Reference the SonarQube server as defined in Jenkins (name must be exactly "myMicroservice")
                    withSonarQubeEnv('myMicroservice') {
                        // Pass the token via -Dsonar.login; adjust sonar.projectKey as needed
                        sh "mvn sonar:sonar -Dsonar.projectKey=myMicroservice -Dsonar.login=${SONAR_AUTH_TOKEN} -DskipTests"
                    }
                }
            }
        }
    }
    post {
        always {
            // Archive the generated artifact so you can reference it later
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
    }
}
