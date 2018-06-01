pipeline { 
    agent any
    stages {
       stage('Unit Tests') {
           steps {
              sh 'ant -f test.xml'
              junit 'reports/result.xml'
           }
       }
       stage('build') {
          steps {
             sh 'ant -f build.xml -v'
            } 
        }
      stage('deploy') {
         agent {
           label 'apache'
         }
         steps {
             echo 'Deploying to Apache Web Server'
             sh "cp dist/rectangle_${env.BUILD_NUMBER}.jar /var/www/html/rectangles/all/"
         }
     }
    }
    post {
      success {
         archive 'dist/*.jar'
       }
   }
    
 }
