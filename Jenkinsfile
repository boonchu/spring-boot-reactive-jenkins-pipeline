pipeline {
    
    agent any  

    stages {

        stage('Init'){
            steps {
                echo 'Init'
                echo '******************************'
            }
        }

        stage('Mvn Install') {
            steps {
                echo 'Mvn Install'
                echo '******************************'
                sh 'docker run --rm -v $(pwd):/app -v /root/.m2:/root/.m2 maven:3.6.2-jdk-11 mvn clean install -DskipTests=true -f /app/pom.xml'
                sh "ls -l" 
            }
        }
        
        stage('Mvn Test') {
            steps {
                echo 'Mvn Test'
                echo '******************************'
                sh 'docker run --rm -v $(pwd):/app -v /root/.m2:/root/.m2 maven:3.6.2-jdk-11 mvn test -f /app/pom.xml'
            }
        }
    }    
}
