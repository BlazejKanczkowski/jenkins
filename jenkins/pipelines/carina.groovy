pipeline {
    agent {
        docker {
            image 'maven:3.9.6-eclipse-temurin-11'
            args '-v $HOME/.m2:/root/.m2' 
        }
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
