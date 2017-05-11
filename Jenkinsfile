pipeline{
  agent any

  options{
    buildDiscaarder(logRotation(numToKeep '2', archive '1'))
  }

  stages {
    stage('build'){
      steps{

       sh 'ant -f build.xml'
      }
    }
  }
  post{
    always{
      archive 'dist/*.jar'
    }
  }
}
