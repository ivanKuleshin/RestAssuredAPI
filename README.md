# RestAssuredAPI Project

## Overview

This project is a Java-based API testing framework that utilizes various technologies and libraries to facilitate API testing. It includes dependencies for logging, test execution, and reporting, ensuring a robust and maintainable testing environment.

## Technologies and Libraries

### Build and Dependency Management

- **Maven**: A build automation tool used primarily for Java projects. It helps in managing project dependencies and build lifecycle.

### Testing Frameworks

- **TestNG**: A testing framework inspired by JUnit and NUnit. It is designed to cover all categories of tests: unit, functional, end-to-end, integration, etc.
- **Cucumber**: A tool for running automated tests written in plain language. It is used for behavior-driven development (BDD).

### API Testing

- **Rest-Assured**: A Java library for testing RESTful web services. It simplifies the validation of REST APIs and helps in writing readable and maintainable tests.

### Logging

- **Log4j**: A logging library for Java. It provides flexible and robust logging capabilities.
- **SLF4J**: A simple facade for various logging frameworks, allowing the end user to plug in the desired logging framework at deployment time.
- **Logback**: A reliable, generic, fast, and flexible logging framework.

### Reporting

- **Allure**: A framework designed to create test execution reports that are clear to everyone in the team. It is used for generating comprehensive test reports.
- **ExtentReports**: A reporting library that generates interactive and detailed test reports.

### Data Binding

- **Jackson**: A suite of data-processing tools for Java (and the JVM platform), including the flagship `streaming` JSON parser/generator library.
- **JSON**: A library to work with JSON data.

### Excel Handling

- **Apache POI**: A Java library for reading and writing Microsoft Office documents. It is used for manipulating Excel files.

### Code Quality

- **Lombok**: A Java library that automatically plugs into your editor and build tools, spicing up your Java with boilerplate code reduction.

## Project Structure and Configuration

### Maven Plugins

- **Maven Compiler Plugin**: Configured to compile Java source code with a specific version.
- **Maven Surefire Plugin**: Used for running tests with support for JUnit and TestNG.
- **Allure Maven Plugin**: Used to generate Allure reports after running the tests.

### Maven Profiles

- **cucumber-test**: A profile for running Cucumber tests.
    - **Dependencies**:
        - `allure-cucumber4-jvm`
- **testng-test**: A profile for running TestNG tests.
    - **Dependencies**:
        - `allure-testng`

## Usage

To run the tests, use the following Maven commands based on the profile you want to activate:

```sh
# To run Cucumber tests
mvn test -P cucumber-test

# To run TestNG tests
mvn test -P testng-test

# To run Cucumber test cases (by default)
mvn test

# To run TestNG test cases
mvn -Ptestng-test test

# To generate and serve Allure report
mvn allure:serve
```
