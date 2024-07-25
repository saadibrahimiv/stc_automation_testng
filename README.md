# Subscription Package Validation Framework

## Overview
This project is an automated test framework designed to validate subscription packages on the STC TV subscription page. 

## Technology Stack
Java / Selenium / TestNG / Extent Reports / Maven

## Key Approaches

### Dynamic Element Handling
Approach: Dynamically locate elements based on their types and structure rather than hardcoding specific locators. 
Implementation: Used flexible locators and dynamic methods to fetch package titles, prices, and currencies.

```
public List<WebElement> getSubscriptionPackages() {
    return driver.findElements(By.className("plan-title"));
}

public String getPackagePrice(WebElement packageElement) {
    return packageElement.findElement(By.tagName("b")).getText();
}

public String getPackageCurrency(WebElement packageElement) {
    return packageElement.findElement(By.tagName("i")).getText().split("/")[0];
}
```

### Parameterized Tests with TestNG

Approach: Utilize TestNG's data provider feature to run tests with multiple sets of data.
Implementation: Defined a data provider method that supplies country-specific subscription package details to the test method.

### Cross-Browser Testing with Factory Pattern

Approach: Implement the factory pattern to support multiple browsers.
Implementation: Parameterized browser choice in TestNG and managed WebDriver instances using a factory pattern.



## Getting Started
### Prerequisites
Java 11 or higher
Maven
web browser 

## Installation
### Clone the repository:
```
git clone <repo>
```

### Install the dependencies:

```
 mvn clean install
```


### Execute the test cases using Maven:

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

