// Uses Declarative syntax to run commands inside a container.
pipeline {

    environment {
        def CONFIG_FILE_UUID   = '8ac4e324-359d-4b24-9cc3-04893a7d56ce'
    }

    agent {
        kubernetes {
            yaml '''
apiVersion: v1
kind: Pod
spec:
  containers:
  - name: openjdk11
    image: adoptopenjdk/openjdk11:alpine
    command:
    - sleep
    args:
    - infinity
'''
            defaultContainer 'openjdk11'
        }
    }
    stages {
        stage('Checkout SCM') {
            steps {
                git url: 'https://github.com/cyrille-leclerc/multi-module-maven-project'
            }
        }
        stage('Build') {
            steps {
                configFileProvider([configFile(fileId: "${CONFIG_FILE_UUID}", variable: 'MAVEN_GLOBAL_SETTINGS')]) {
                    withMaven( maven: 'maven-3') {
                       sh 'echo "Maven Build step"'
                       sh 'mvn clean install -DskipTests=true -f pom.xml'
                    }
                }
            }
        }
        stage('Test') {
            steps {
                configFileProvider([configFile(fileId: "${CONFIG_FILE_UUID}", variable: 'MAVEN_GLOBAL_SETTINGS')]) {
                    withMaven( maven: 'maven-3') {
                       sh 'echo "Maven Testing step"'
                       sh 'mvn test -f pom.xml'
                    }
                }
           }
        }
        stage('Deploy') {
            steps {
                sh 'hostname'
                sh 'echo "Deploy step"'
            }
        }
    }
    post {
        always {
            sh 'echo "always run regardless of the completion status"'
        }
        success {
            sh 'echo "message shows after jobs is successful"'
        }
        failure {
            sh 'echo "only when the Pipeline is currently in a "failed" state run, usually expressed in the Web UI with the red indicator."'
        }
    }
}
