pipeline {

  agent { label 'java17' }

  options {
	buildDiscarder(logRotator(numToKeepStr: '5'))
	timeout(time: 1, unit: 'HOURS')
  }

  triggers { cron('@daily') }

  stages {
    stage('analysis') {
      steps {
        script {
          def cobertura_opts = 'cobertura:cobertura -Dmaven.test.failure.ignore -DfailIfNoTests=false -Dcobertura.report.format=xml'
          def checkstyle_opts = 'checkstyle:check -Dcheckstyle.config.location=google_checks.xml'
          sh "mvn clean -U ${cobertura_opts} ${checkstyle_opts}"
        }
      }
    }
    stage('deploy') {
      steps {
        sh "mvn clean -U -B deploy"
      }
    }
  }
}
