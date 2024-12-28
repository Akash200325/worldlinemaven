pipeline {
    agent any
    tools {
        jdk 'jdk11'           // Ensure the JDK is configured in Jenkins Global Tool Configuration
        maven 'Maven 3.8.5'   // Ensure Maven is configured in Jenkins Global Tool Configuration
    }
    environment {
        SONARQUBE_URL = 'http://localhost:9000' // Update to your SonarQube server URL
        SONAR_TOKEN = credentials('sonar-token') // SonarQube token stored in Jenkins credentials
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                // Clean and package the Maven project
                sh 'mvn clean package'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    // Run SonarQube analysis using Maven
                    sh '''
                    mvn sonar:sonar \
                       -Dsonar.projectKey=worldlinemaven \
                       -Dsonar.projectName="worldlinemaven" \
                       -Dsonar.host.url=$SONARQUBE_URL \
                       -Dsonar.token=$SONAR_TOKEN
                    '''
                }
            }
        }
        stage('Quality Gate') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    // Wait for the Quality Gate result
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
    post {
        success {
            echo 'Pipeline completed successfully!'
            archiveArtifacts artifacts: '**/target/*.jar', allowEmptyArchive: true
        }
        failure {
            echo 'Pipeline failed. Check the logs for details.'
        }
        always {
            // Clean the workspace to free space
            cleanWs()
        }
    }
}
