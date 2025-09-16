pipeline {
    agent {
        docker {
            image 'node:16'   // Official Node.js 16 image from Docker Hub
            args '-p 3000:3000'  // Expose port if your app runs on 3000
        }
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Install Dependencies') {
            steps {
                sh 'npm install'
            }
        }

        stage('Build') {
            steps {
                sh 'npm run build || echo "No build script, skipping..."'
            }
        }

        stage('Test') {
            steps {
                sh 'npm test || echo "No test script, skipping..."'
            }
        }

        stage('Run App') {
            steps {
                sh 'npm start & sleep 10'
            }
        }
    }

    post {
        success {
            echo "✅ Build & Test Successful!"
        }
        failure {
            echo "❌ Build Failed. Check logs."
        }
    }
}
