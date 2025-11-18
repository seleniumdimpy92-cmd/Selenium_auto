pipeline {
  agent any

  // Run every 2 minutes
  triggers {
    // H/2 is better for distributed scheduling; use "*/2 * * * *" if you prefer exact every-2-min.
    cron('H/2 * * * *')
  }

  options {
    // Do not run concurrent builds - avoids overlap if a previous run is still executing
    disableConcurrentBuilds()
    // Keep build logs for a week (adjust as needed)
    buildDiscarder(logRotator(daysToKeepStr: '7'))
    // Timeout long-running jobs (avoid hung executors)
    timeout(time: 10, unit: 'MINUTES')
  }

  stages {
    stage('Checkout') {
      steps { checkout scm }
    }

    stage('Run Tests') {
      steps {
        // run headless to reduce resource usage
        sh 'mvn -B -Denv=ci -DbrowserMode=headless clean test'
      }
    }

    stage('Archive Reports') {
      steps {
        archiveArtifacts artifacts: 'reports/**', allowEmptyArchive: true
      }
    }
  }

  post {
    always {
      // optional: publish junit xml if you produce surefire results
      junit '**/target/surefire-reports/*.xml'
    }
  }
}
