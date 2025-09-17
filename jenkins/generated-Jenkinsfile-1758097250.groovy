pipeline {
    agent any

    environment {
        NODE_ENV = 'production' // Example environment variable
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo 'Checking out code...'
                checkout scm // Uses Jenkins Git plugin to check out code
            }
        }

        stage('Install Dependencies') {
            steps {
                echo 'Installing dependencies...'
                sh 'npm install' // Runs npm install to install dependencies
            }
        }

        stage('Linting') {
            steps {
                echo 'Running ESLint...'
                sh 'npm run lint' // Runs ESLint for linting
            }
            post {
                failure {
                    echo 'Linting failed. Please fix the issues and retry.'
                    error('Pipeline failed due to linting errors.')
                }
            }
        }

        stage('Unit Testing') {
            steps {
                echo 'Running Unit Tests...'
                sh 'npm test' // Runs Jest or Mocha for unit testing
            }
            post {
                failure {
                    echo 'Unit tests failed. Please fix the issues and retry.'
                    error('Pipeline failed due to unit testing errors.')
                }
            }
        }

        stage('Integration Testing') {
            steps {
                echo 'Running Integration Tests...'
                sh 'npm run integration-test' // Runs integration tests using an appropriate framework
            }
            post {
                failure {
                    echo 'Integration tests failed. Please fix the issues and retry.'
                    error('Pipeline failed due to integration testing errors.')
                }
            }
        }

        stage('Build') {
            steps {
                echo 'Building the application...'
                sh 'npm run build' // Runs the build process
            }
        }
    }

    post {
        always {
            echo 'Pipeline completed.'
        }
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed. Check logs for details.'
        }
    }
}