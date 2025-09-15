pipeline {
    agent any
    tools {
        nodejs 'Node16'  // must match exactly the name in Jenkins Tools
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Lavanya5577/node_pro.git'
            }
        }
        stage('Install Dependencies') {
            steps {
                sh 'npm install'
            }
        }
        stage('Build and Start Application') {
            steps {
                sh 'npm run build'
                sh 'npm start &'
            }
        }
        stage('Run Tests') {
            steps {
                sh 'npm test'
            }
        }
    }
}
