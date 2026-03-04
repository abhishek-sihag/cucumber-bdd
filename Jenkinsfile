pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK17'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Clean & Build') {
            steps {
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Run Smoke Tests') {
            steps {
                bat 'mvn test -Dcucumber.filter.tags="@smoke"'
            }
        }

        stage('Run Sanity Tests') {
            steps {
                bat 'mvn test -Dcucumber.filter.tags="@sanity"'
            }
        }

        stage('Run Regression Tests') {
            steps {
                bat 'mvn test -Dcucumber.filter.tags="@regression"'
            }
        }

        stage('Run API Tests') {
            steps {
                bat 'mvn test -Dcucumber.filter.tags="@api"'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/target/*.html', allowEmptyArchive: true
        }
        failure {
            echo 'Build Failed ❌'
        }
        success {
            echo 'Build Successful ✅'
        }
    }
}