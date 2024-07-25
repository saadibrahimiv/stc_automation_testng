# Subscription Package Validation Framework

## Overview
This project is an automated test framework designed to validate subscription packages on the STC TV subscription page. 

## Technology Stack
### Java:
The programming language used for writing the automation scripts.
### Selenium: 
For browser automation.
### TestNG:
For managing test cases.
### Extent Reports:
For generating test reports.
### Maven:
For project management and dependency management.



## Getting Started
### Prerequisites
Java 11 or higher
Maven
web browser 

### Installation
Clone the repository:
```
git clone <repo>
```

Install the dependencies:

```
 mvn clean install
```


Execute the test cases using Maven:

```
mvn test
```

### View the report: 
(Sample Successful Test Run)
![image](https://github.com/user-attachments/assets/5dbac7da-deca-4b82-90f7-4112dbd741ba)

The report will be generated in the target directory.

## Customizing Test Execution

### Change Browser and URL:

Modify the testng.xml file to specify different browser types and URLs for the test execution.

### Update Test Data:

The test data for different countries and their respective packages can be updated in the testdata.json.

