pipeline {
  agent none
  stages {
    stage ('Unit Tests') {
      agent {
      label 'apache'
      }
      steps {
      sh 'ant -f test.xml -v'
        junit 'reports/result.xml'
      }
    }
    stage ('build') {
      agent {
      label 'apache'
      }
      steps{
        sh 'ant -f build.xml -v'
      }
      post {
  success {
  archiveArtifacts artifacts: 'dist/*.jar', fingerprint: true
  }
}
    }
    stage ('deploy'){
      agent {
      label 'apache'
      }
      steps {
        sh "cp dist/rectangle_${env.BUILD_NUMBER}.jar /var/www/html/rectangles/all"
      }
    }
    stage ('Running on CentOs'){
      agent {
      label 'CentOs'
      }
      steps {
      sh "wget http://cprakas011d.mylabserver.com/rectangles/all/rectangle_${env.BUILD_NUMBER}.jar"
      sh "java -jar rectangle_${env.BUILD_NUMBER}.jar 3 4"
      }
    }
  stage ('Ruuning on debian'){
    agent {
         docker 'openjdk:8u121-jre'
    }
    steps {
    sh "wget http://cprakas011d.mylabserver.com/rectangles/all/rectangle_${env.BUILD_NUMBER}.jar"
    sh "java -jar rectangle_${env.BUILD_NUMBER}.jar 3 4"
    }
  }
    stage('Promote to green'){
      agent {
      label 'apache'
      }
      steps {
        sh "cp /var/www/html/rectangles/all/rectangle_${env.BUILD_NUMBER}.jar /var/www/html/rectangles/green/"
      }
    }
  }
 }
