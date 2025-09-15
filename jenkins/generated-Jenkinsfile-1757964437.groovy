pipeline {
    agent any
    tools {
        nodejs 'Node16'  // Name you configured in Jenkins
    }
    stages {
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
