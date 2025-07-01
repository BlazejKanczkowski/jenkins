pipeline {
    agent any

    tools {
        maven 'maven'
    }

    parameters {
        string(name: 'suite', defaultValue: 'api', description: 'TestNG suite name')
        string(name: 'env', defaultValue: 'STAGE', description: 'Environment')
    }

    stages {
        stage('Run Tests') {
            steps {
                echo "Running tests with suite: ${params.suite} on environment: ${params.env}"
                sh "${tool 'maven'}/bin/mvn clean test -Dsuite=${params.suite} -Denv=${params.env}"
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }
    }
}
