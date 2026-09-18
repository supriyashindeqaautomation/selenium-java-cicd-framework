# SeleniumFrameworkDesign

A Selenium WebDriver + Java hybrid automation framework built with the Page Object Model, BDD (Cucumber), TestNG, and Maven for testing an e-commerce web application flow (Landing → Product Catalogue → Cart → Checkout → Order → Confirmation).

## Tech Stack

- **Language:** Java
- **Build Tool:** Maven
- **Test Framework:** TestNG
- **BDD:** Cucumber (Feature files + Step Definitions)
- **Design Pattern:** Page Object Model (POM) with Abstract Components
- **Reporting:** ExtentReports (ExtentReportNG), Cucumber HTML reports
- **Data Handling:** JSON-based test data (PurchaseOrder.json), custom DataReader utility
- **Other:** Custom Listeners, Retry Analyzer for flaky test handling

## Project Structure

```
SeleniumFrameworkDesign
├── src/main/java
│   ├── SupriyaShinde.AbstractComponents
│   │   └── AbstractComponent.java        # Common reusable component actions
│   ├── SupriyaShinde.pageobjects
│   │   ├── CartPage.java
│   │   ├── CheckOutPage.java
│   │   ├── ConfirmationPage.java
│   │   ├── LandingPage.java
│   │   ├── OrderPage.java
│   │   └── ProductCatalogue.java
│   └── SupriyaShinde.Resources
│       ├── ExtentReporterNG.java         # Extent report configuration
│       └── GlobalData.properties         # Global config/constants
│
├── src/test/java
│   ├── Cucumber
│   │   ├── TestNGTestRunner.java         # Cucumber-TestNG runner
│   │   ├── ErrorValidation.feature
│   │   └── SubmitOrder.feature
│   ├── SupriyaShinde.data
│   │   ├── DataReader.java               # Utility to read test data
│   │   └── PurchaseOrder.json            # Test data
│   ├── SupriyaShinde.stepDefinitions
│   │   └── StepDefinitionImpl.java       # Cucumber step implementations
│   ├── SupriyaShinde.TestComponents
│   │   ├── BaseTest.java                 # Test setup/teardown, driver init
│   │   ├── Listeners.java                # TestNG listeners for logging/reports
│   │   └── Retry.java                    # Retry analyzer for failed tests
│   └── SupriyaShinde.Tests
│       ├── ErrorValidationsTest.java
│       ├── StandAloneTest.java
│       └── SubmitOrderTest.java
│
├── reports/                              # Generated Extent/screenshot reports
├── target/                               # Maven build output, Cucumber reports
├── testSuites/
│   ├── ErrorValidationTest.xml
│   ├── purchase.xml
│   └── testng.xml
└── pom.xml
```

## Features

- **Page Object Model** with an `AbstractComponent` base class for shared UI interactions across pages
- **BDD support** via Cucumber feature files (`ErrorValidation.feature`, `SubmitOrder.feature`) mapped to step definitions
- **Data-driven testing** using JSON test data and a custom `DataReader`
- **Custom TestNG Listeners** for enhanced logging and reporting
- **Retry mechanism** (`Retry.java`) to automatically re-run flaky/failed tests
- **ExtentReports integration** for rich HTML test reports with screenshots on failure
- **Multiple TestNG suite XMLs** for running targeted test subsets (error validation, purchase flow, full suite)

## Prerequisites

- Java (JDK 8 or higher)
- Maven
- Eclipse / IntelliJ IDEA (or any Java IDE)
- Chrome/Firefox browser + matching WebDriver (managed via Maven dependencies)

## How to Run

Run the full suite via Maven:
```bash
mvn clean test
```

Run a specific TestNG suite:
```bash
mvn clean test -DsuiteXmlFile=testSuites/testng.xml
```

Run via a specific suite file (e.g., purchase flow only):
```bash
mvn clean test -DsuiteXmlFile=testSuites/purchase.xml
```

## Reports

After execution, reports are generated at:
- `reports/index.html` — ExtentReports HTML report (with failure screenshots)
- `target/Cucumber.html` — Cucumber HTML report
- `test-output/` — Default TestNG output

## Author

**Supriya Shinde**
QA Automation Engineer

---
*This is a personal practice/portfolio project built to demonstrate Selenium + Java + Cucumber + TestNG framework design skills.*
