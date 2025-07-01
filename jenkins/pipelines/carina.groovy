pipeline {
    agent any

    tools {
        maven 'maven'
    }

    parameters {
        string(name: 'suite', defaultValue: 'api', description: 'TestNG suite name')
        string(name: 'env', defaultValue: '', description: 'Environment (leave empty if not needed)')
    }

    stages {
        stage('Run Tests') {
            steps {
                echo "Running tests with suite: ${params.suite} and environment: '${params.env}'"
                script {
                    def envOption = params.env?.trim()
                    def cmd = "mvn clean test -Dsuite=${params.suite}"
                    if (envOption && envOption.length() > 0) {
                        cmd += " -Denv=${envOption}"
                    }
                    echo "Executing command: ${cmd}"
                    sh "${tool 'maven'}/bin/${cmd}"
                }
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }
    }
}
