pipeline {

  agent {
    label 'Slave_Induccion'
  }

  options {
    buildDiscarder(logRotator(numToKeepStr: '3'))
 	 disableConcurrentBuilds()
  }

  tools {
    jdk 'JDK11_Centos'
    gradle 'Gradle6.0.1_Centos'
  }

  stages{
    stage('Checkout') {
      steps{
        echo "------------>Checkout<------------"
        checkout([
			$class: 'GitSCM',
			branches: [[name: '*/develop']],
			doGenerateSubmoduleConfigurations: false,
			extensions: [],
			gitTool: 'Default',
			submoduleCfg: [],
			userRemoteConfigs: [[
				credentialsId: 'GitHub_Franklinceiba',
				url:'https://github.com/Franklinceiba/ADN.git'
			]]
		])
      }
    }



    stage('Clean') {
      steps{
        echo "------------>Clean<------------"
        sh 'gradle --b ./consultoriofv/build.gradle clean'

      }
    }

    stage('Unit Tests') {
      steps{
        
	echo "------------>Compile project<------------"
        sh 'gradle --b ./consultoriofv/build.gradle compileJava'
        
	echo "------------>Unit Tests<------------"
        sh 'gradle --b ./consultoriofv/build.gradle clean'
	sh 'gradle --b ./consultoriofv/build.gradle test'
        sh 'gradle --b ./consultoriofv/build.gradle jacocoTestReport' 
      }
    }

    stage('Static Code Analysis') {
      steps{
          echo '------------>Análisis de código estático<------------'

		withSonarQubeEnv('Sonar') {
                  sh "${tool name: 'SonarScanner', type:'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner -Dproject.settings=sonar-project.properties" 
                }
	    }     
       
    }

    stage('Build') {
      steps {
        echo "------------>Build<------------"
	    sh 'gradle --b ./consultoriofv/build.gradle build -x test'
      }
    }
  }

  post {
    always {
      echo 'This will always run'
    }
    success {
      echo 'This will run only if successful'
    }
    failure {
      echo 'This will run only if failed'
      mail (to: 'franklin.vasquez@ceiba.com.co',subject: "Failed Pipeline:${currentBuild.fullDisplayName}",body: "Something is wrong with ${env.BUILD_URL}")
    }
	unstable {
      echo 'This will run only if the run was marked as unstable'
    }
    changed {
      echo 'This will run only if the state of the Pipeline has changed'
      echo 'For example, if the Pipeline was previously failing but is now successful'
    }
  }
}
