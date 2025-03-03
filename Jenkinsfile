pipeline {

  agent { label 'java17' }

  options {
	buildDiscarder(logRotator(numToKeepStr: '5'))
	timeout(time: 1, unit: 'HOURS')
  }

  triggers { cron('@daily') }

  stages {
    stage('deploy') {
      steps {
        sh "mvn clean -U -B deploy"
      }
    }
  }
}
