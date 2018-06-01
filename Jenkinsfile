pipeline { 
    agent any
    stages {
       stage('Unit Tests') {
           steps {
              sh 'ant -f test.xml'
              junit 'reports/*.xml'
           }
       }
       stage('build') {
          steps {
             sh 'ant -f build.xml -v'
            } 
        }
       stage('Execute') {
          steps {
             sh 'java -jar dist/rectangle.jar 5 6'
          }
       }
    }
    post {
      success {
         archive 'dist/*.jar'
       }
   }
    
 }
