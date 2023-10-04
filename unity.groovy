pipeline {
    agent any

    environment {
        UnityCodePath = "${WORKSPACE}"
    }

    options {
        timestamps()
        disableConcurrentBuilds()
    }

    stages {
        stage('Clean Workspace') {
            steps {
                deleteDir()
                echo "Cleaned workspace"
                echo "UnityCodePath -> $UnityCodePath"
            }
        }

        stage('Build HoYoCore') {
            steps {
                dir('hoyocore') {
                    sh '''
                    echo "$ConfigurationJSON" > ~/workspace/CI/configuration.json
                    echo "ConfigurationJSON -> $ConfigurationJSON"
                    echo Current PATH = $PATH
                    cmake --version
                    '''
                }
            }
        }
    }
}