pipeline {
    agent {
      label "jenkins-gradle"
    }
    environment {
      ORG               = 'mumayuan'
      APP_NAME          = 'samplekotlin'
      CHARTMUSEUM_CREDS = credentials('jenkins-x-chartmuseum')
    }
    stages {
      stage('CI Build and push snapshot') {
        when {
          branch 'PR-*'
        }
        environment {
          PREVIEW_VERSION = "0.0.0-SNAPSHOT-$BRANCH_NAME-$BUILD_NUMBER"
          PREVIEW_NAMESPACE = "$APP_NAME-$BRANCH_NAME".toLowerCase()
          HELM_RELEASE = "$PREVIEW_NAMESPACE".toLowerCase()
        }
        steps {
          echo '-- outside of container'  
          sh 'hostname; whoami'
            
          container('gradle') {
              echo '-- inside of container'
            sh 'hostname; whoami'  
            // TODO 
            //sh "mvn versions:set -DnewVersion=$PREVIEW_VERSION"
            sh "gradle clean build"
            sh 'export VERSION=$PREVIEW_VERSION && skaffold build -f skaffold.yaml'

            sh "jx step post build --image $DOCKER_REGISTRY/$ORG/$APP_NAME:$PREVIEW_VERSION"
          }

          dir ('./charts/preview') {
           container('gradle') {
             sh "make preview"
             sh "jx preview --app $APP_NAME --dir ../.."
           }
          }
        }
      }
      stage('Build Release') {
        when {
          branch 'master'
        }
        steps {
                          echo '-Build Release- outside of container'
            sh 'hostname; whoami;pwd'  
          container('gradle') {
                            echo '-Build Release- inside of container'
            sh 'hostname; whoami;pwd'  
            // ensure we're not on a detached head
            sh "git checkout master"
            sh "git config --global credential.helper store"

            sh "jx step git credentials"
            // so we can retrieve the version in later steps
            sh "echo \$(jx-release-version) > VERSION"
            // TODO
            //sh "mvn versions:set -DnewVersion=\$(cat VERSION)"
          }
          dir ('./charts/samplekotlin') {
            container('gradle') {
              sh "make tag"
            }
          }
          container('gradle') {
            sh 'gradle clean build'

            sh 'export VERSION=`cat VERSION` && skaffold build -f skaffold.yaml'

            sh "jx step post build --image $DOCKER_REGISTRY/$ORG/$APP_NAME:\$(cat VERSION)"
          }
        }
      }
      stage('Promote to Environments') {
        when {
          branch 'master'
        }
        steps {
              echo '-Promote to Environments- outside of container'
            sh 'hostname; whoami;pwd'  
          dir ('./charts/samplekotlin') {
            container('gradle') {
                  echo '-Promote to Environments- in side of container'
            sh 'hostname; whoami;pwd'  
              sh 'jx step changelog --version v\$(cat ../../VERSION)'

              // release the helm chart
              sh 'jx step helm release'

              // promote through all 'Auto' promotion Environments
              sh 'jx promote -b --all-auto --timeout 1h --version \$(cat ../../VERSION)'
            }
          }
        }
      }
    }
    post {
        always {
            cleanWs()
        }
    }
  }
