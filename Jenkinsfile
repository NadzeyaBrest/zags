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
        allure([
            includeProperties: false,
            jdk: '',
            commandline: 'allure',
            properties: [],
            reportBuildPolicy: 'ALWAYS',
            results: [[path: 'target/allure-results']]
        ])
    }

    environment {
        ZAGS_BASE_URL    = credentials('zags-base-url')
        ZAGS_USERNAME    = credentials('zags-username')
        ZAGS_PASSWORD    = credentials('zags-password')
        ZAGS_DB_URL      = credentials('zags-db-url')
        ZAGS_DB_USER     = credentials('zags-db-user')
        ZAGS_DB_PASSWORD = credentials('zags-db-password')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Prepare .env') {
            steps {
                script {
                    def envContent = """\
BASE_URL=${ZAGS_BASE_URL}
TEST_USERNAME=${ZAGS_USERNAME}
TEST_PASSWORD=${ZAGS_PASSWORD}
DB_URL=${ZAGS_DB_URL}
DB_USER=${ZAGS_DB_USER}
DB_PASSWORD=${ZAGS_DB_PASSWORD}
"""
                    writeFile file: '.env', text: envContent
                }
            }
        }

        stage('Run tests') {
            steps {
                catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
                    script {
                        if (isUnix()) {
                            sh 'mvn -B clean test -Dmaven.test.failure.ignore=true'
                        } else {
                            bat 'mvn -B clean test -Dmaven.test.failure.ignore=true'
                        }
                    }
                }
            }
        }
    }

    post {
        always {
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