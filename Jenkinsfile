pipeline {
  agent none
  
  environment {
  MAJOR_VERSION = 1
  }
    options {
     buildDiscarder(logRotator(numToKeepStr: '2', artifactNumToKeepStr: '1'))
      }    


  stages {
	stage('Unit Tests') {
		agent {
    	    label'apache'
    	}

        steps {
        sh 'ant -f test.xml -v'
          junit 'reports/result.xml'
        }
      }
 
	stage('Build') {
		agent {
    	    label 'apache'
    	}

        steps {
        sh 'ant -f build.xml -v'
        }
        post {
         	success {
         	    always {
      			 archiveArtifacts artifacts: 'dist/*.jar', fingerprint: true
    		 }
     	}
   }  
 }
	stage('Deploy') {
		agent {
    	    label 'apache'
    	}

        steps {	
            sh "mkdir /var/www/html/rectangles/all/${env.BRANCH_NAME}_${env.BUILD_NUMBER}"
            sh "cp dist/rectangle_${env.MAJOR_VERSION}.${env.BUILD_NUMBER}.jar /var/www/html/rectangles/all/${env.BRANCH_NAME}_${env.BUILD_NUMBER}/"

			}
		}
		
	stage("Running on CentOS") {
        agent {
            label 'CentOS'
        }
      	steps {

        sh "wget http://tpavan-d69ca7ed1.mylabserver.com/rectangles/all/${env.BRANCH_NAME}_${env.BUILD_NUMBER}/rectangle_${env.MAJOR_VERSION}.${env.BUILD_NUMBER}.jar"
        sh "java -jar rectangle_${env.MAJOR_VERSION}.${env.BUILD_NUMBER}.jar 3 4"

        

       }
     } 
     stage("Running on Docker Debian"){
         agent{
             docker 'openjdk:10.0-jre'
         }
		steps {

		sh "wget http://tpavan-d69ca7ed1.mylabserver.com/rectangles/all/${env.BRANCH_NAME}_${env.BUILD_NUMBER}/rectangle_${env.MAJOR_VERSION}.${env.BUILD_NUMBER}.jar"
        sh "java -jar rectangle_${env.MAJOR_VERSION}.${env.BUILD_NUMBER}.jar 3 4"

		

		}
     }
     stage('Promote to Green') {
     	agent {
         label 'apache'   
     }
     	when {
     	    branch 'dev'
     	}
         steps {

             sh "cp /var/www/html/rectangles/all/${env.BRANCH_NAME}_${env.BUILD_NUMBER}/rectangle_${env.MAJOR_VERSION}.${env.BUILD_NUMBER}.jar /var/www/html/rectangles/green/rectangle_${env.MAJOR_VERSION}.${env.BUILD_NUMBER}.jar"
        }
    }
    stage('Promote Dev branch to Master'){
  		agent {
		 label 'apache'
  		}
  		when {
  		    branch 'dev'
  		}
  		steps {
  		    echo 'Stashing any local changes'
  		    sh 'git stash'
  		    echo "Checking out dev branch"
  		    sh 'git checkout dev'
  		    echo "Cheking out master branch"
  		    sh 'git checkout master'
  		    echo "Merging dev into master branch"
  		    sh 'git merge dev'
  		    echo "Pushing to origin master"
  		    sh 'git push origin master'
  		}
    }
  }
}