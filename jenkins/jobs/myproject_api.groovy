pipelineJob('MyProject_API') {
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/BlazejKanczkowski/jenkins')
                    }
                    branches('main')
                }
            }
            scriptPath('jenkins/pipelines/carina.groovy')
        }
    }
}
