pipeline {
    agent any

    tools {
        jdk 'JDK11'
        maven 'Maven3'
    }

    parameters {
        string(name: 'suite', defaultValue: 'api', description: 'TestNG suite name')
        string(name: 'env', defaultValue: 'STAGE', description: 'Environment')
    }

    environment {
        SUITE = "${params.suite}"
        ENV = "${params.env}"
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/BlazejKanczkowski/jenkins'
            }
        }
        stage('Run Tests') {
            steps {
                sh 'mvn clean test -Dsuite=${SUITE} -Denv=${ENV}'
            }
        }
    }
}
