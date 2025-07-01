pipeline {
    agent any

    environment {
        SUITE = "${params.suite}"
        ENV = "${params.env}"
    }

    parameters {
        string(name: 'suite', defaultValue: 'api', description: 'TestNG suite name')
        string(name: 'env', defaultValue: 'STAGE', description: 'Environment')
    }

    stages {
        stage('Git checkout') {
            steps {
                git url: 'https://github.com/BlazejKanczkowski/jenkins', branch: 'main'
            }
        }
        stage('Run tests') {
            steps {
                sh './mvnw clean test -Dsuite=${SUITE} -Denv=${ENV}'
            }
        }
    }
}
