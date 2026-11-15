pipeline {
    agent any

    tools {
        maven 'Maven-3.9.x'  // Match your Jenkins Global Tool Configuration name for Maven
        jdk 'JDK-17'         // Match your Jenkins Global Tool Configuration name for JDK 17
    }

    stages {
        stage('1. Checkout from GitHub') {
            steps {
                // Pulls latest repository changes
                checkout scm
            }
        }

        stage('2. Run Tests') {
            steps {
                // Executes JUnit tests (Pipeline fails automatically if tests fail)
                sh 'mvn clean test'
            }
        }

        stage('3. Build Jar Package') {
            steps {
                // Compiles and packs the executable web app jar file
                sh 'mvn package -DskipTests'
            }
        }

        stage('4. Automated Deploy') {
            steps {
                echo 'Deploying application locally...'
                // Safely kills any prior running instance of the app, then spins up the new jar
                sh '''
                    pkill -f 'blog-app-0.0.1-SNAPSHOT.jar' || true
                    nohup java -jar target/blog-app-0.0.1-SNAPSHOT.jar > app.log 2>&1 &
                '''
            }
        }
    }

    post {
        success {
            echo 'SUCCESS: CI/CD Pipeline executed successfully! Blog is updated & live.'
        }
        failure {
            echo 'FAILURE: Pipeline failed during testing or building. Check logs.'
        }
    }
}