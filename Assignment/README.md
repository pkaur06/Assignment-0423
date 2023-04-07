# # Web Assignment
An End to End Java based UI test framework for XYZ Bank, Customer (CRD operations) as Bank Manager and Transactions as Customer.

# Description 
Selenium Framework project using Java, Maven, TestNG, Page Object Model and Behavior Driven Development with Extent Reports.

## Getting Started
These instructions will get you a copy of the framework up and running up and running on your local machine for testing process.

### Prerequisites

## Setting up you local environment
An IDE e.g. Eclipse is preferable.
After cloning the framework, you will need to run the following steps:
1. Ensure that Java_Home and Maven_Home is set in environment variables.
2. TestNG plugin is installed to run the cases.
In your local working directory:


# Project Packages
Project consists of 2 sections of packages as the following:

src/main/java: it includes following packages:

- "AbstractComponents": includes all the util methods such as waitforelement() etc, which are commonly used thorughout the framework pages.
- "pageobject":  contains locators using PageObject and Page Factory design patterns and also has the methods written specific to actions specific to page with an inheritance of "BaseTest" superclass
- "resources": contains Extent report class 
              GlobalData.prorperties file is used to provide the global data such as browser name is defined here, is used to run the test suites on.

src/test/java :it includes following packages:
- "data": contains two JSON files. DataReader.json and CustomerData.json. DataReader.json reads data from CustomerData.json file to provide test data in to test scripts for customer creation, verification and deletion.
- "TestComponents": contains "BaseTest" class which starts and stops the WebDriver for each <Test> tag in TestNG xml file. 
The Listeners.java  file contains methods for Extent reports for all the tests pass/faiure results as well as screenshot for failed tests functionality.
Retry.java, contains the mechanism to rerun the failed testcases.
- "tests": This package contains  all the feature testcases scritps(CustomerTesta.java,TransactionsTest.java)
  Besides , Test Classes which have the Independent Test scenarios mapped with the methods from page objects , with an inheritance from "BaseTest" superclass 

- Every test class in "tests" package contains one or more test methods that deals with one or more page object to make the steps for the test and then after complete the steps verifying the results with assertions. Most of the tests are grouped as "Suite"


# Project Additional Folders
 
- "drivers" : contains Chrome Driver for Windows OS.
- "reports" : contains Extent report of the tests run named as "index.html" and screenshots taken on failure or on purpose.
- "target" :  contains reports from /surefire-reports : as "index.html" file for testng test running results,

- "test-output" : contains "index.html" file for TestNG tests running results.
- "testSuites" :  contians XML files for testng suites such as Article.xml,testng.xml,ErrorValidation.xml 


# Project Deliverables 

- Screenshots taken in case of failure of test cases.
- Extent Reports will be in  "reports" folder after running the tests in "Article.xml" or "testng.xml' or 'ErrorValidationTests.xml'Suite 
 

# Dependencies/Plug-ins used

- Selenium WebManager: To initiate the driver and handle with the web elements.
- TestNG: Used for test annotations, asserting on the results and parallel execution.
- json-simple: This used to parse  and read the JSON test data file.
- Extent Report: Used for reports in more readble format
- Maven-surefire-plugin: used for configuring the testng and suite xml 'testSuites/testng.xml'
- Maven-compiler-plugin: used to compile the project to  java version 19.0.2


### Run tests
After setting up the environment variables, now you are ready to run the tests.

There are several ways to run the code after importing the project:
In your IDE: 

```
1. Under root directory, double click on  testSuites folder
2. Right click on 'testng.xml' and select option as 'TestNG suite' to run all the testcases for all features.you can change the browser parameter or keep the default "Chromeheadless" value in GlobalProperties class;By deafult, it will run the tests with chrome in headless mode.
- Run each Test Class separately from "tests" by right click on the class and select "run as testNG test".

```
Note: The current implementation supports execution of cases on Browser.
In order to run the cases in headless mode, please follow these steps:

```
1. Under src/main/java directory, Go to GlobalData.properties  file under assignment.resources directory
2. changes this value 'browser=chrome' to browser=chromeheadless

```
4.Run through the command line by navigating to the project folder in the command line then write 
`mvn test`
e.g:   \Assignment\Assignment>mvn test



### Test report
After the test suite is fully executed in the terminal you will be able to view the generated test report.
Under the root directory, you can  go to "reports" folder and open the index html report e.g.

```
index.html
```
A sceenshot is also shown for false failure, to ensure that failure test cases end up with screenshot captured of the state.
