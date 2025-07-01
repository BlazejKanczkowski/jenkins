pipelineJob('MyProject_API') {
    description('Run API test suite from carina-demo project')
    parameters {
        stringParam('suite', 'api', 'TestNG suite name')
        stringParam('env', 'STAGE', 'Environment')
    }
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/BlazejKanczkowski/jenkins')
                        credentials('github-token-carina-demo')
                    }
                    branches('*/main')
                }
            }
            scriptPath('jenkins/pipelines/carina.groovy')
        }
    }
}
