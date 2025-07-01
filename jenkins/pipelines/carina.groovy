pipeline {
    agent any
    stages {
        stage('Example') {
            steps {
                echo 'Hello from Carina pipeline!'
            }
        }
    }
}
