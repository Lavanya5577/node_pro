pipeline {
    agent any

    tools {
        // This must match the NodeJS installation name you configure in Jenkins
        nodejs "Node16"
    }

    stages {
        stage('Checkout Code') {
            steps {
                // Checkout from main branch of your repo
                git branch: 'main',
                    url: 'https://github.com/Lavanya5577/node_pro.git'
            }
        }

        stage('Install Dependencies') {
            steps {
                // Install project dependencies from package.json
                sh 'npm install'
            }
        }

        stage('Run Tests') {
            steps {
                // Run tests defined in package.json (Jest or other framework)
                sh 'npm test'
            }
        }

        stage('Archive Test Results') {
            steps {
                // Archive test results if generated (e.g., by Jest reporters)
                junit '**/test-results.xml'
            }
        }
    }

    post {
        always {
            echo "Pipeline completed. Cleaning up workspace."
            cleanWs()
        }
    }
}
