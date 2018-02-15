pipeline {

  agent none

  stages {
    stage('Unit Tests') {

      agent{
        label 'apache'
      }
      steps {
        sh 'ant -f test.xml -v'
        junit 'reports/result.xml'
      }
    }
    stage('build') {

      agent{
        label 'apache'
        
      }

      steps {
        sh 'ant -f build.xml -v'
      }

      post {
        success {
          archiveArtifacts artifacts: 'dist/*.jar', fingerprint: true
        }
      }


    }

    stage('deploy'){

      agent{
        label 'apache'
      }

      steps{
         sh "cp dist/rectangle_${env.BUILD_NUMBER}.jar  /var/www/html/rectangles/all/"
      }

    }


    stage('functional check on debian'){

            agent{
              label 'agentOne'
            }

            steps{
                sh "wget http://35.230.114.105/rectangles/all/rectangle_${env.BUILD_NUMBER}.jar"
                sh "java -jar rectangle_${env.BUILD_NUMBER}.jar 54 321"
            }
        }

    stage('promote to green' ){

          agent{
             label 'apache'
          }

          when{
            branch 'Amar03'
          }

          steps{

              sh "cp /var/www/html/rectangles/all/rectangle_${env.BUILD_NUMBER}.jar /var/www/html/rectangles/green"

          }
    }


  }



}
