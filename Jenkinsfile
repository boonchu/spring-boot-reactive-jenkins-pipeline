// Uses Declarative syntax to run commands inside a container.
pipeline {
    agent {
        kubernetes {
            yaml '''
apiVersion: v1
kind: Pod
spec:
  containers:
  - name: shell
    image: adoptopenjdk/openjdk11:alpine
    command:
    - sleep
    args:
    - infinity
'''
            defaultContainer 'shell'
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
                withMaven( maven: 'maven-3') {
                    configFileProvider([configFile(fileId: 'nexus-maven-settings.xml', variable: 'MAVEN_SETTINGS_XML')]) {
                       sh 'echo "Maven Build step"'
                       sh 'mvn clean install -DskipTests=true -f pom.xml'
                    }
                }
            }
        }
        stage('Test') {
            steps {
                withMaven( maven: 'maven-3') {
                    configFileProvider([configFile(fileId: 'nexus-maven-settings.xml', variable: 'MAVEN_SETTINGS_XML')]) {
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
