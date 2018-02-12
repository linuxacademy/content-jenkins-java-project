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




    stage('functional check on docker debian'){

      agent{
        docker 'openjdk:8u151-jre'
      }

      steps{
          sh "wget http://srinuamar91.mylabserver.com/rectangles/all/rectangle_${env.BUILD_NUMBER}.jar"
          sh "java -jar rectangle_${env.BUILD_NUMBER}.jar 54 321"
      }

    stage('functional check on centos'){

        agent{
          label 'centos'
        }

        steps{
            sh "wget http://srinuamar91.mylabserver.com/rectangles/all/rectangle_${env.BUILD_NUMBER}.jar"
            sh "java -jar rectangle_${env.BUILD_NUMBER}.jar 54 321"
        }




    }


  }


}
