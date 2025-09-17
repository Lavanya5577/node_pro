pipeline {
    agent any

    environment {
        REPO_URL = 'https://github.com/Lavanya5577/node_pro.git'
    }

    stages {
        stage('Checkout Code') {
            steps {
                // Clone the repository using Jenkins git plugin
                git branch: 'main', url: "${REPO_URL}"
            }
        }

        stage('Install Dependencies') {
            steps {
                // Install required dependencies using npm
                sh 'npm install'
            }
        }

        stage('Testing') {
            steps {
                // Run tests using Jest framework
                sh 'npm test'
            }
        }
    }

    post {
        always {
            // Cleanup workspace after build
            cleanWs()
        }
        success {
            // Notify success
            echo 'Build and tests succeeded!'
        }
        failure {
            // Notify failure
            echo 'Build or tests failed!'
        }
    }
}