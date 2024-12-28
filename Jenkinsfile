pipeline {
    agent any
    
    environment {
        SONAR_HOST_URL = 'http://localhost:9000'
        SONAR_PROJECT_KEY = 'worldlinemaven'
        SONAR_PROJECT_NAME = 'worldlinemaven'
        SONAR_TOKEN = credentials('sonar-token')  // Accessing the SonarQube token stored in Jenkins credentials
    }
    
    tools {
        maven 'Maven 3.8.5'  // Use the name configured in Jenkins for Maven
        jdk 'jdk17'          // Use the name configured in Jenkins for JDK 17
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm  // This will checkout the source code from the repository
            }
        }

        stage('Build and Verify') {
            steps {
                bat '''
                mvn clean verify
                '''
            }
        }

        stage('SonarQube Analysis') {
            steps {
                bat '''
                mvn sonar:sonar ^
                    -Dsonar.projectKey=${SONAR_PROJECT_KEY} ^
                    -Dsonar.projectName=${SONAR_PROJECT_NAME} ^
                    -Dsonar.host.url=${SONAR_HOST_URL} ^
                    -Dsonar.token=${SONAR_TOKEN}
                '''
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully'
        }
        failure {
            echo 'Pipeline failed'
        }
        always {
            echo 'This runs regardless of the result.'
        }
    }
}
