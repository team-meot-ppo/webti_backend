pipeline {
    agent any

    environment {
        IMAGE_NAME = "jeyongsong/webti_batch"
        CONTAINER_NAME = "webti_batch"
        BRANCH = "main"
        EXECUTE_PROFILE = "dev"
        SERVICE = "batch"
    }

    stages {
        stage('Git Clone') {
            steps {
                git branch: env.BRANCH, url: 'https://github.com/team-meot-ppo/webti_backend.git'
                withCredentials([GitUsernamePassword(credentialsId: 'submodule_security_token', gitToolName: 'Default')]) {
                    sh 'git submodule update --init --recursive'
                }
            }
        }

         stage('Display Directory Structure') {
             steps {
                 sh 'ls -R'
             }
         }

        stage('Docker Image Build') {
            steps {
                sh "docker build --build-arg SERVICE=${env.SERVICE} -t ${env.IMAGE_NAME}:latest ."
            }
        }

        stage('Run Docker Container') {
            steps {
                sh "docker run -e PROFILE=${env.EXECUTE_PROFILE} ${env.IMAGE_NAME}:latest"
            }
        }
    }
}
