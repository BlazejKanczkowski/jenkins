pipeline {
    agent any

    tools {
        maven 'maven'
    }

    parameters {
        string(name: 'suite', defaultValue: 'api', description: 'TestNG suite name')
        string(name: 'browser', defaultValue: 'chrome', description: 'Browser to run tests on')
    }

    stages {
        stage('Run Tests') {
            steps {
                echo "Running tests with suite: ${params.suite} and environment: '${params.env}' and browser: '${params.browser}'"
                script {
                    def envOption = params.env?.trim()
                    def cmd = "mvn clean test -Dsuite=${params.suite}"
                    if (envOption && envOption.length() > 0) {
                        cmd += " -Denv=${envOption}"
                    }
                    if (params.browser && params.browser.trim()) {
                        cmd += " -Dbrowser=${params.browser.trim()}"
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
