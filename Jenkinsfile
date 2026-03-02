pipeline {
    agent any

    tools {
        maven 'Maven'     // name configured in Jenkins → Global Tool Config
        jdk 'JDK17'       // name configured in Jenkins
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

        stage('Run Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Generate Reports') {
            steps {
                bat 'mvn verify'
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