pipeline {
    agent any
    tools {
        maven 'Maven_3_5_2'
    }
    stages{
        stage('CompileandRunSonarAnalysis'){
            steps {
		        sh 'mvn clean verify sonar:sonar -Dsonar.projectKey=mpuertao_apirestmvc -Dsonar.organization=mpuertao -Dsonar.host.url=https://sonarcloud.io -Dsonar.login=0d65fb8398478f2e42adaab2d3033cbea1fd9957'
            }
        }
        stage('RunSCAAnalysisUsingSnyk') {
            steps {
    		    withCredentials([string(credentialsId: 'SNYK_TOKEN', variable: 'SNYK_TOKEN')]) {
    			    sh 'mvn snyk:test -fn'
    			}
    		}
        }
        stage('Build Image') {
            steps {
                withDockerRegistry([credentialsId: "dockerlogin", url: ""]){
                    script{
                        app = docker.build("mao-ecr-eks")
                    }
                }
            }
        }
        stage('Push Image') {
            steps{
                script{
                    docker.withRegistry('https://732629678930.dkr.ecr.us-east-1.amazonaws.com', 'ecr:us-east-1:aws-credentials') {
                        app.push("latest")
                    }
                }
            }
        }
        stage('Deploy k8s - API Rest'){
            steps{
                withKubeConfig([credentialsId: 'kubeconfig']){
                    sh('kubectl delete all --all -n devsecops')
                    sh('kubectl apply -f deployment.yml --namespace=devsecops')
                }
            }
        }
        stage ('wait_for_testing'){
        	   steps {
        		   sh 'pwd; sleep 90; echo "Application Has been deployed on K8S"'
        	   	}
        	   }

        stage('RunDASTUsingZAP') {
               steps {
        	        withKubeConfig([credentialsId: 'kubeconfig']) {
        				sh('zap.sh -cmd -quickurl http://$(kubectl get services/apirest --namespace=devsecops -o json| jq -r ".status.loadBalancer.ingress[] | .hostname") -quickprogress -quickout ${WORKSPACE}/zap_report.html')
        				archiveArtifacts artifacts: 'zap_report.html'
        		    }
        	   }
        }
    }
}