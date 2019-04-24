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
        sh "mkdir /var/www/html/rectangles/all/${env.BRANCH_NAME}"
        sh "cp dist/rectangle_${env.BUILD_NUMBER}.jar /var/www/html/rectangles/all/${env.BRANCH_NAME}/"
      }
    }
    stage ('Running on CentOs'){
      agent {
      label 'CentOs'
      }
      steps {
      sh "wget http://cprakas011d.mylabserver.com/rectangles/all/${env.BRANCH_NAME}/rectangle_${env.BUILD_NUMBER}.jar"
      sh "java -jar rectangle_${env.BUILD_NUMBER}.jar 3 4"
      }
    }
  stage ('Ruuning on debian'){
    agent {
         docker 'openjdk:8u121-jre'
    }
    steps {
    sh "wget http://cprakas011d.mylabserver.com/rectangles/all/${env.BRANCH_NAME}/rectangle_${env.BUILD_NUMBER}.jar"
    sh "java -jar rectangle_${env.BUILD_NUMBER}.jar 3 4"
    }
  }
    stage('Promote to green'){
      agent {
      label 'apache'
      }
      when {
      branch 'master'
      }
      steps {
        sh "cp /var/www/html/rectangles/all/${env.BRANCH_NAME}/rectangle_${env.BUILD_NUMBER}.jar /var/www/html/rectangles/green/"
      }
    }
    stage('Promote developement to master'){
    agent {
      label 'apache'
      }
      when {
      branch 'developement'
      }
      steps {
        echo "Stash any local changes"
        sh 'git stash'
        echo "Checking out developement branch"
        sh 'git checkout developement'
        echo "Checking out the master"
        sh 'git checkout master'
        echo 'Merging developement to master'
        sh 'git merge developement'
        echo "Pusing to origin master"
        sh 'git push origin master'
        echo "Y"
        echo "z"
      }
    }
  }
 }
