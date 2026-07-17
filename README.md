# Test Automation Framework

This is a **Java-based Test Automation Framework** built using **Selenium WebDriver and TestNG**.

The framework leverages various libraries and tools to support **parallel execution**, **headless testing**, and **integration with cloud-based testing platforms like LambdaTest**.

---

## 🚀 About This Framework

This framework is designed to automate web applications efficiently and reliably.  
It supports execution on both **local machines** and **LambdaTest cloud**, making it suitable for real-world automation projects and CI/CD pipelines.

The framework follows industry best practices to ensure **scalability**, **maintainability**, and **reusability**.

---

## Prerequisites

Before running this framework, ensure the following software is installed on your system:

- **Java 8 or above**  
  Make sure Java is installed and the `JAVA_HOME` environment variable is set.

- **Maven**  
  Ensure Maven is installed and added to the system path.  
  Download link: https://maven.apache.org/download.cgi

---

## Features

- **Parallel Execution**  
  Supports parallel test execution using TestNG to reduce execution time.

- **Cross-Browser Testing**  
  Tests can be executed on different browsers.

- **Headless Mode**  
  Faster execution by running tests in headless mode.

- **Cloud Testing**  
  Integrated with **LambdaTest** to execute tests on the cloud.

- **Config-Driven Execution**  
  Easily switch between local and cloud execution using system properties.

---

## Technologies Used

- Java  
- Selenium WebDriver  
- TestNG  
- Maven  
- LambdaTest  

---

## Setup Instructions

## Clone the Repository
```bash
git clone https://github.com/Dhanush-776/Test_Automation_Framework.git
cd <Test_Automation_Framework>
```

## Running Tests

## Run Tests on LambdaTest
```bash
mvn test -Dbrowser=chrome -DisLambdaTest=true -DisHeadless=false
```

## Run Tests on Local Machine in Headless Mode
```bash
mvn test -Dbrowser=chrome -DisLambdaTest=false -DisHeadless=true
```

## Reports & Logs

## Reports
After execution, test results will be generated in the form of execution output and logs.
(Reporting tools can be integrated as an enhancement if required.)

## Logs
Logs are created during test execution to help with debugging and analysis.


