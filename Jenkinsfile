pipeline {
  agent any
  stages {
    stage('Checkout') {
      steps { checkout scm }
    }
    stage('Build & Test') {
      steps {
        sh 'mvn -B -DskipTests=false clean test'
      }
    }
    stage('Archive Reports') {
      steps {
        archiveArtifacts artifacts: 'reports/**', allowEmptyArchive: true
      }
    }
  }
}
