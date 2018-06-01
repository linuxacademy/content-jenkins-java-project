pipeline { 
    agent {
      label 'apache'
    }
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
