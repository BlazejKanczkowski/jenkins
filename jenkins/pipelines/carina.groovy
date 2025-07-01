pipeline {
    agent any
    parameters {
        string(name: 'suite', defaultValue: 'api', description: 'TestNG suite name')
        string(name: 'env', defaultValue: 'STAGE', description: 'Test environment')
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Run Tests') {
            steps {
                echo "Running tests with suite: ${params.suite} on environment: ${params.env}"
                sh "mvn clean test -Dsuite=${params.suite} -Denv=${params.env}"
            }
        }
    }
    post {
        always {
            junit 'target/surefire-reports/*.xml'
            archiveArtifacts 'target/*.log'
        }
    }
}
