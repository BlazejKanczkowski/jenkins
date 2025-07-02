def suites = ['api']

suites.each { suite ->
    pipelineJob("MyProject_${suite.toUpperCase()}") {
        description("Run ${suite} suite tests")
        parameters {
            stringParam('suite', suite, 'TestNG suite name')
            stringParam('env', 'STAGE', 'Environment')
        }
        definition {
            cpsScm {
                scm {
                    git {
                        remote {
                            url('https://github.com/BlazejKanczkowski/jenkins')
                            credentials('Github-token-carina-demo')
                        }
                        branches('*/main')
                    }
                }
                scriptPath('jenkins/pipelines/carina.groovy') 
            }
        }
    }
}
