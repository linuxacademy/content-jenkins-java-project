pipeline { 
    agent any
    stages { 
       stage('build') {
          steps {
             sh 'javac -d . src/*.java'
             sh 'jar -cvf rectangle.jar *.class'
            } 
        }
    } 
 }
