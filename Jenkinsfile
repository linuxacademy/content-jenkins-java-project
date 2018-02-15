pipeline {

  agent none


  environment{

    MAJOR_VERSION=1

  }


  stages {
    stage('Unit Tests') {

      agent{
        label 'apache'
      }

      steps {


        sh 'ant -f test.xml -v'
        //
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
         sh "mkdir -p /var/www/html/rectangles/all/${env.BRANCH_NAME}"
         sh "cp dist/rectangle_${env.BUILD_NUMBER}.jar  /var/www/html/rectangles/all/${env.BRANCH_NAME}"
      }

    }


    stage('functional check on debian'){

            agent{
              label 'agentOne'
            }

            steps{
                sh "wget http://35.230.114.105/rectangles/all/${env.BRANCH_NAME}/rectangle_${env.BUILD_NUMBER}.jar"
                sh "java -jar rectangle_${env.BUILD_NUMBER}.jar 54 321"
            }
        }

    stage('promote to green' ){

          agent{
             label 'apache'
          }

          when{
            branch 'Amar02'
          }

          steps{

              sh "cp /var/www/html/rectangles/all/${env.BRANCH_NAME}/rectangle_${env.BUILD_NUMBER}.jar /var/www/html/rectangles/green"

          }
    }

 //  Added this stage for automatic branch promotions ...

    stage('Promote Amar03 to Amar02'){

          agent{
            label 'apache'
          }

          when {
            branch 'Amar03'
          }

          steps{
            echo "Stashing any local changes.."
            sh "git stash"
            echo "Checking Out Amar03"
            sh "git checkout Amar03"
            sh "git pull"
            echo "Checking Out Amar02"
            sh "git checkout Amar02"
            echo "Merging  Amar03"
            sh "git merge Amar03"
            echo "Pushing to Origin Amar02"
            sh  'git push origin Amar02'
            echo 'NO PROBLEMS 16'
            sh 'git tag -a ${env.MAJOR_VERSION}.${env.BUILD_NUMBER}  -m ${env.GIT_COMMIT}'
            sh 'git push origin ${env.MAJOR_VERSION}.${env.BUILD_NUMBER}'
          }

    }


  }



}
