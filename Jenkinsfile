pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
       sh 'ant -f build.xml -v'
      }
    } 
  }

  post {
    always {
      archiveArtifacts artifacts: 'dist/*.jar', fingerprint: true
      }
   }
}
