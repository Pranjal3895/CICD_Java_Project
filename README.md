# CICD_Java_Project

# DevOps Learning Blog (Spring Boot + MySQL + Jenkins CI/CD)

A lightweight Java web application built with Spring Boot, Thymeleaf, and MySQL. Designed specifically to practice version control with GitHub, automated testing, Maven builds, and CI/CD pipelines using Jenkins.

---

## Tech Stack
* **Backend:** Java 17, Spring Boot 3.2.5 (Spring Web, Spring Data JPA)
* **Frontend:** HTML5, CSS3, Thymeleaf (Server-side rendering, zero JavaScript required)
* **Database:** MySQL 8+
* **Build Tool:** Apache Maven
* **CI/CD & Version Control:** Git, GitHub, Jenkins

---

## Prerequisites
Before running the project, ensure the following are installed on your machine:
1. **Java Development Kit (JDK) 17** or higher
2. **Apache Maven** (3.8+)
3. **MySQL Server** running locally on port `3306`

---

## Local Setup & Running Instructions

### 1. Database Setup
Log into your MySQL server and create the required database:
```sql
CREATE DATABASE blog_db;

*(Spring Boot JPA is configured with `ddl-auto=update`, so the `posts` table will be generated automatically when the app starts.)*

### 2. Configure Credentials

Open `src/main/resources/application.properties` and update your local MySQL username and password:

```properties
spring.datasource.username=root
spring.datasource.password=your_mysql_password

```

### 3. Build and Run Locally

Open a terminal in the project root directory and run:

```bash
mvn spring-boot:run

```

Once you see `Started BlogApplication` in the console, open your browser and visit:

* **Home Feed:** `http://localhost:8080`
* **Create Article:** `http://localhost:8080/new`

---

## Running Automated Tests

To execute the JUnit test suite manually (the same command Jenkins runs during CI):

```bash
mvn clean test

```

---

## CI/CD Pipeline Workflow (Jenkins)

This repository includes a declarative `Jenkinsfile` at the root that automates the following stages on every GitHub push:

1. **Checkout:** Pulls the latest source code from the GitHub repository.
2. **Test:** Executes `mvn clean test` to verify application context and logic.
3. **Package:** Runs `mvn package -DskipTests` to compile the executable `.jar` artifact (`target/blog-app-0.0.1-SNAPSHOT.jar`).
4. **Deploy:** Stops any previously running instance of the application and launches the newly built `.jar` in the background.

```

<ElicitationsGroup message="Ready to move your code to GitHub and Jenkins?">
  <Elicitation label="Show git commands to push this project to GitHub" query="Show me the exact git commands to initialize this repository and push all files to GitHub."/>
  <Elicitation label="Walk through setting up this pipeline in Jenkins" query="Give me step-by-step instructions to create a Jenkins Pipeline job connected to my GitHub repository."/>
</ElicitationsGroup>

```
