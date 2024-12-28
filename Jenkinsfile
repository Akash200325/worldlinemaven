pipeline {
    agent any

    tools {
        jdk 'jdk17'           // Use Java 17 configured in Jenkins Global Tool Configuration
        maven 'Maven 3.8.5'   // Use Maven configured in Jenkins Global Tool Configuration
    }

    environment {
        SONARQUBE_SERVER = 'sonar-cube' // SonarQube server name in Jenkins
        SONARQUBE_TOKEN = credentials('sonar-token') // Replace 'sonar-token' with your SonarQube token credential ID
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonar-cube') {
                    sh 'mvn sonar:sonar -Dsonar.projectKey=mavenproject -Dsonar.login=$SONARQUBE_TOKEN'
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 1, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/*.jar', allowEmptyArchive: true
            cleanWs()
        }
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
