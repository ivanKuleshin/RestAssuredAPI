pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "3.6.3"
    }

    stages {
        stage('VCS checkout'){
            steps{
                // Get some code from a GitHub repository
                git 'https://github.com/ivanKuleshin/RestAssuredAPI.git'
            }
        }

        stage('Run tests') {
            steps {
                // To run Maven on a Windows agent, use
                bat "mvn -Dsurefire.suiteXmlFiles=src/test/resources/runners/mainSuit.xml clean test"
            }
        }
        stage('Execute script'){
            steps{
                script {
                    for(int i = 0; i < 100; i++){
                    println(i)
                }
                }
            }
        }
    }
}