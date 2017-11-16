pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean -DskipTests package -U'
            }
        }
        stage('Test'){
            steps {
                sh 'mvn test pmd:pmd'
                junit 'Server/target/surefire-reports/*.xml'
                pmd canRunOnFailed: true, pattern: 'Server/target/pmd.xml'

                junit 'Client/target/surefire-reports/*.xml'
                pmd canRunOnFailed: true, pattern: 'Client/target/pmd.xml'

            }
        }
        stage('Deploy') {
            steps {
                //sh 'kill $(ps aux | grep -F BibliothekWS2017Server | grep -v -F 'grep' | awk '{ print $2 }') || true'
                sh 'nohup java -jar ./Server/target/BibliothekWS2017Server-1.0-SNAPSHOT-jar-with-dependencies.jar &'
                //sh 'chmod +x ./Server/target/start.sh'
                //sh './Server/target/start.sh'
            }
        }
    }
    post {
        always {
            archive 'Server/target/*.jar, Server/target/hibernate.cfg.xml, Server/target/log4j.properties, Server/target/start.sh'
            archive 'Client/target/*.jar'
        }
    }
}
