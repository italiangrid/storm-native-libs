@Library('sd')_
def kubeLabel = getKubeLabel()

pipeline {

  agent {
    kubernetes {
      label "${kubeLabel}"
      cloud 'Kube mwdevel'
      defaultContainer 'runner'
      inheritFrom 'ci-template'
    }
  }

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

  post {
    failure {
      slackSend color: 'danger', message: "${env.JOB_NAME} - #${env.BUILD_NUMBER} Failure (<${env.BUILD_URL}|Open>)"
    }
    unstable {
      slackSend color: 'warning', message: "${env.JOB_NAME} - #${env.BUILD_NUMBER} Unstable (<${env.BUILD_URL}|Open>)"
    }
    changed {
      script {
	if('SUCCESS'.equals(currentBuild.currentResult)) {
	  slackSend color: 'good', message: "${env.JOB_NAME} - #${env.BUILD_NUMBER} Back to normal (<${env.BUILD_URL}|Open>)"
	}
      }
    }
  }
}
