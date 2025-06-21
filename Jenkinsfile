pipeline {
  agent any

  environment {
    SONARQUBE_ENV = 'sonarqube default'

    }

  stages {
    stage('Build Backend') {
      steps {
        dir('server') {
          sh 'mvn clean install'
        }
      }
    }

    stage('SonarQube Analysis') {
        steps {
            withSonarQubeEnv("${SONARQUBE_ENV}") {
                dir('server') {
                    sh 'mvn sonar:sonar'
                }
            }
        }
    }

    stage('Build Frontend') {
      steps {
        dir('client') {
          sh 'npm install'
          sh 'ng build --configuration production'
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
