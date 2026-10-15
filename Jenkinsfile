pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    triggers {
        cron('H 3 * * *')
    }

    options {
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '30', artifactNumToKeepStr: '10'))
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

       stage('Prepare .env') {
                   steps {
                       withCredentials([file(credentialsId: 'n-env-file', variable: 'ENV_FILE')]) {
                           script {
                               if (isUnix()) {
                                   sh 'cp "$ENV_FILE" .env'
                               } else {
                                   bat 'copy "%ENV_FILE%" .env'
                               }
                           }
                       }
                   }
               }

        stage('Run tests') {
            steps {
                    script {
                        if (isUnix()) {
                            sh 'mvn -B clean test'
                        } else {
                            bat 'mvn -B clean test'
                        }
                    }
            }
        }
    }

    post {
        always {
                allure([
                    includeProperties: false,
                    jdk: '',
                    commandline: 'allure',
                    properties: [],
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: 'target/allure-results']]
                ])
            archiveArtifacts artifacts: 'target/logs/**', allowEmptyArchive: true
            archiveArtifacts artifacts: 'target/cucumber-reports/**', allowEmptyArchive: true
            emailext(
                subject: "ZAGS: прогон #${BUILD_NUMBER} — ${currentBuild.currentResult}",
                mimeType: 'text/html',
                body: """
                    <h3>Отчёт автотестов ZAGS</h3>
                    <p>Статус: <b>${currentBuild.currentResult}</b></p>
                    <p>
                      <a href="${BUILD_URL}allure">Allure-отчёт</a> ·
                      <a href="${BUILD_URL}artifact/target/logs/">Логи</a> ·
                      <a href="${BUILD_URL}console">Консоль</a>
                    </p>
                """,
                to: 'nadzeyaq@gmail.com'
            )
        }
    }
}