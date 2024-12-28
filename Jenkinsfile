pipeline {
    agent any

    environment {
        // Define SonarQube server and token as environment variables
        SONAR_HOST_URL = 'http://localhost:9000'
        SONAR_TOKEN = 'sqp_6d679f0454286a0a8dfc13d75ba8b9985edc9792'
        SONAR_PROJECT_KEY = 'worldlinemaven'
        SONAR_PROJECT_NAME = 'worldlinemaven'
    }

    tools {
        // Use Maven tool configured in Jenkins
        maven 'M3'  // Replace 'M3' with the name of your Maven installation in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from your repository
                checkout scm
            }
        }

        stage('Build & Verify') {
            steps {
                // Clean and verify the Maven project
                bat 'mvn clean verify'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                // Run SonarQube analysis using the bat command for Windows
                bat """
                    mvn sonar:sonar ^
                      -Dsonar.projectKey=${SONAR_PROJECT_KEY} ^
                      -Dsonar.projectName=${SONAR_PROJECT_NAME} ^
                      -Dsonar.host.url=${SONAR_HOST_URL} ^
                      -Dsonar.token=${SONAR_TOKEN}
                """
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully.'
        }

        failure {
            echo 'Pipeline failed.'
        }
    }
}
