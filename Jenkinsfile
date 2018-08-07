pipeline {
  agent none
  
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
        sh "cp dist/rectangle_${env.BUILD_NUMBER}.jar /var/www/html/rectangles/all"
			}
		}
		
	stage("Running on CentOS") {
        agent {
            label 'CentOS'
        }
        
      	steps {
        sh "wget http://tpavan-d69ca7ed1.mylabserver.com/rectangles/all/rectangle_${env.BUILD_NUMBER}.jar"
        sh "java -jar rectangle_${env.BUILD_NUMBER}.jar 3 4"
       }
     } 
   } 
}