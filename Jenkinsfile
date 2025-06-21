pipeline {
  agent any

  environment {
    SONARQUBE_ENV = 'sonarqube default'
    SONAR_LOGIN = credentials('sonar-token')
  }

  stages {

    stage('Clone repository') {
      steps {
        git url: 'https://github.com/eseo-angers/twic-2025-s1-rattrapage-alidou-yasmina.git', branch: 'main'
      }
    }

    stage('Build Backend') {
      steps {
        dir('server') {
          bat 'call mvn clean install'
        }
      }
    }

    stage('SonarQube Analysis') {
      steps {
            withSonarQubeEnv('sonarqube default') {
              withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
                dir('server') {
                  bat "mvn sonar:sonar -Dsonar.login=%SONAR_TOKEN%"
                }
              }
            }
        }
    }


    stage('Build Frontend') {
      steps {
        dir('client') {
          bat 'npm install'
          bat 'ng build --configuration production'
        }
      }
    }

    stage('Archive Artifacts') {
      steps {
        archiveArtifacts artifacts: '**/target/*.jar', allowEmptyArchive: true
      }
    }
  }
}
