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
                script{
                    withEnv(['JENKINS_NODE_COOKIE=dontkill']) {
                        //sh 'chmod +x ./Server/target/kill.sh'
                        //sh './Server/target/kill.sh'
                        //sh 'nohup java -jar ./Server/target/BibliothekWS2017Server-1.0-SNAPSHOT-jar-with-dependencies.jar &'
                    }
                    //sh 'chmod +x ./Server/target/deploy_EE.sh'
                    //sh './Server/target/deploy_EE.sh'
                    sh '/opt/glassfish5/glassfish/bin/asadmin --user admin --passwordfile /opt/glassfish5/glassfish/bin/password.txt undeploy BibliothekWS2017Server || true'
                    sh 'mv ./Server/target/BibliothekWS2017Server-1.0-SNAPSHOT.war ./Server/target/BibliothekWS2017Server.war'
                    sh '/opt/glassfish5/glassfish/bin/asadmin --user admin --passwordfile /opt/glassfish5/glassfish/bin/password.txt deploy ./Server/target/BibliothekWS2017Server.war'
                }
            }
        }
    }
    post {
        always {
            archive 'Server/target/*.jar, Server/target/hibernate.cfg.xml, Server/target/log4j.properties, Server/target/kill.sh'
            archive 'Client/target/*.jar, Client/target/Config/'
        }
    }
}
