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
        stage('Build') {
            steps {
                withDockerRegistry([credentialsId: "dockerlogin", url: ""]){
                    script{
                        app = docker.build("mao-ecr-eks")
                    }
                }
            }
        }
        stage('Push') {
            steps{
                script{
                    docker.withRegistry('https://732629678930.dkr.ecr.us-east-1.amazonaws.com', 'ecr:us-east-1:aws-credentials') {
                        app.push("latest")
                    }
                }
            }
        }
    }
}