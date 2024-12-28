pipeline {
    agent any

    environment {
        SONAR_HOST_URL = 'http://localhost:9000'
        SONAR_PROJECT_KEY = 'worldlinemaven'
        SONAR_PROJECT_NAME = 'worldlinemaven'
        SONAR_TOKEN = 'sqp_6d679f0454286a0a8dfc13d75ba8b9985edc9792'
    }

    tools {
        maven 'Maven 3.8.5' // Replace 'Maven 3' with the name of your Maven installation from Global Tool Configuration
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Verify') {
            steps {
                bat 'mvn clean verify'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                bat """
                mvn sonar:sonar ^
                    -Dsonar.projectKey=${env.SONAR_PROJECT_KEY} ^
                    -Dsonar.projectName="${env.SONAR_PROJECT_NAME}" ^
                    -Dsonar.host.url=${env.SONAR_HOST_URL} ^
                    -Dsonar.token=${env.SONAR_TOKEN}
                """
            }
        }
    }

    post {
        always {
            echo 'This runs regardless of the result.'
        }

        success {
            echo 'Pipeline completed successfully!'
        }

        failure {
            echo 'Pipeline failed'
        }
    }
}
