pipeline {
    agent any

    tools {
        maven 'Maven 3.8.5'  // Use the name configured in Jenkins for Maven
        jdk 'jdk17'          // Use the name configured in Jenkins for JDK 17
    }

    environment {
        SONAR_HOST_URL = 'http://localhost:9000'
        SONAR_PROJECT_KEY = 'worldlinemaven'
        SONAR_PROJECT_NAME = 'worldlinemaven'
        SONAR_TOKEN = 'sqp_6d679f0454286a0a8dfc13d75ba8b9985edc9792'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Akash200325/worldlinemaven.git'
            }
        }

        stage('Build and Analyze') {
            steps {
                script {
                    bat """
                    mvn clean verify sonar:sonar ^
                        -Dsonar.projectKey=${env.SONAR_PROJECT_KEY} ^
                        -Dsonar.projectName=${env.SONAR_PROJECT_NAME} ^
                        -Dsonar.host.url=${env.SONAR_HOST_URL} ^
                        -Dsonar.token=${env.SONAR_TOKEN}
                    """
                }
            }
        }
    }

    post {
        success {
            echo 'Build and SonarQube analysis succeeded.'
        }
        failure {
            echo 'Build or SonarQube analysis failed.'
        }
    }
}
