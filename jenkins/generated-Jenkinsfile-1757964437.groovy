pipeline {
    agent any

    environment {
        NODE_VERSION = '16'
    }

    stages {
        stage('Checkout') {
            steps {
                // Clone the repository
                git url: 'https://github.com/Lavanya5577/node_pro.git', branch: 'main'
            }
        }

        stage('Setup Node.js') {
            steps {
                // Install Node.js
                sh '''
                curl -fsSL https://deb.nodesource.com/setup_${NODE_VERSION}.x | sudo -E bash -
                sudo apt-get install -y nodejs
                node -v
                npm -v
                '''
            }
        }

        stage('Install Dependencies') {
            steps {
                // Install npm dependencies
                sh 'npm install'
            }
        }

        stage('Build and Start Application') {
            steps {
                // Verify application starts successfully
                sh 'npm run start'
            }
        }

        stage('Run Tests') {
            steps {
                // Execute unit and integration tests
                sh 'npm test'
            }
        }
    }

    post {
        always {
            // Clean up workspace
            cleanWs()
        }
    }
}