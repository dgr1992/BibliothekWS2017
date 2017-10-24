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
                sh 'echo "No docker, no cry ;)"'
            }
        }
    }
    post {
        always {
            archive 'Server/target/*.jar'
            archive 'Client/target/*.jar'
        }
    }
}
