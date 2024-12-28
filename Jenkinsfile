pipeline {
    agent any
    
    environment {
        SONAR_HOST_URL = 'http://localhost:9000'  // Make sure the URL is correct
        SONAR_PROJECT_KEY = 'worldlinemaven'
        SONAR_PROJECT_NAME = 'worldlinemaven'
        SONAR_TOKEN = credentials('sonar-token')
    }
    
    tools {
        maven 'Maven 3.8.5'  // Ensure Maven is configured correctly
        jdk 'jdk17'          // Ensure JDK 17 is configured correctly
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
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
