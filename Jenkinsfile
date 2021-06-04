// Uses Declarative syntax to run commands inside a container.
pipeline {
    agent {
        kubernetes {
            // Rather than inline YAML, in a multibranch Pipeline you could use: yamlFile 'jenkins-pod.yaml'
            // Or, to avoid YAML:
            // containerTemplate {
            //     name 'shell'
            //     image 'ubuntu'
            //     command 'sleep'
            //     args 'infinity'
            // }
            yaml '''
apiVersion: v1
kind: Pod
spec:
  containers:
  - name: shell
    image: ubuntu
    command:
    - sleep
    args:
    - infinity
'''
            // Can also wrap individual steps:
            // container('shell') {
            //     sh 'hostname'
            // }
            defaultContainer 'shell'
        }
    }
    stages {
        stage('Build') {
            git url: 'https://github.com/cyrille-leclerc/multi-module-maven-project'

            withMaven(
                // Maven installation declared in the Jenkins "Global Tool Configuration"
                maven: 'maven-3', // (1)
                // Use `$WORKSPACE/.repository` for local repository folder to avoid shared repositories
                mavenLocalRepo: '.repository', // (2)
                // Maven settings.xml file defined with the Jenkins Config File Provider Plugin
                // We recommend to define Maven settings.xml globally at the folder level using
                // navigating to the folder configuration in the section "Pipeline Maven Configuration / Override global Maven configuration"
                // or globally to the entire master navigating to  "Manage Jenkins / Global Tools Configuration"
                mavenSettingsConfig: 'my-maven-settings' // (3)
            ){
                sh 'echo "Maven Build step"'
                sh 'mvn clean install -DskipTests=true -f /app/pom.xml'
            }
        }
        stage('Test') {
            git url: 'https://github.com/cyrille-leclerc/multi-module-maven-project'

            withMaven(
                // Maven installation declared in the Jenkins "Global Tool Configuration"
                maven: 'maven-3', // (1)
                // Use `$WORKSPACE/.repository` for local repository folder to avoid shared repositories
                mavenLocalRepo: '.repository', // (2)
                // Maven settings.xml file defined with the Jenkins Config File Provider Plugin
                // We recommend to define Maven settings.xml globally at the folder level using
                // navigating to the folder configuration in the section "Pipeline Maven Configuration / Override global Maven configuration"
                // or globally to the entire master navigating to  "Manage Jenkins / Global Tools Configuration"

            ){
                sh 'echo "Maven Testing step"'
                sh 'mvn test -f /app/pom.xml'
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
