pipeline {
  agent any

  stages {
    stage('Unit Tests') {
      steps {

          sh 'ant -f text.xml -v'

      }
    }
    stage('build') {
      steps {
        sh 'ant -f build.xml -v'
      }
    }
  }

  post {
    always {
      archiveArtifacts artifacts: 'dist/*.jar', fingerprint: true
    }

    steps{
      junit 'reports/result.xml'
    }
  }
}
