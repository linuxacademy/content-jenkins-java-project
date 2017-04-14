pipeline{
  agent any

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
