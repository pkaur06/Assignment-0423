# API Assignment
An API RestAssured testing framework for User API (CRUD operations).

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

\src\test\java\apiAssignment: it includes following packages:

- "utilities": This package contains  all the utility functions used in APIs testcases  such as Payload.
- "APIs": contains Automated Test scripts for API testcases for Articles and Comments API .
- "setUp": contais super class file for config data used in APIs.
- "testcases" : contains Automated Test class for API testcases assertions with TestNG for User API .
- "listeners" : contains Extent Html listeners for reporting purpose.

src\test\resources :it includes following packages:
- "excel": contains excel data file to pass data input in API scripts in run time.
- "properties": contains config.properties file to pass configurations related to API endpoints, credentials and Data sheet name for excel
- "runner" : contians XML files to run testsuites such as testng.xml for all regression cases and testStripe.xml for feature specific cases.


# Project Additional Folders
 
- "target" :  contains reports from /surefire-reports : as "index.html" file for testng test running results,
- "test-output" : contains "index.html" file for TestNG tests running results.
- "reports" :"target": contains extent report for api testcases run.


# Project Deliverables 

- Extent Reports will be in  "reports" folder after running the tests.
 

# Dependencies/Plug-ins used

- RestAssured
- TestNG: Used for test annotations, asserting on the results and parallel execution.
- Extent Report: Used for reports in more readble format
- Maven-surefire-plugin: used for configuring the testng and suite xml 'testSuites/testng.xml'
- Maven-compiler-plugin: used to compile the project to  java.


### Run tests
After setting up the environment variables, now you are ready to run the tests.

There are several ways to run the code after importing the project:
In your IDE: 

```
1. Under src/test/resources/runner directory, right click on 'testng.xml' and select option as 'TestNG suite' to run all the testcases for all features.
2. You can run testStripe.xml the same way to run the testsuite specific to feature cases related to Articles API.
3. Run each Test Class separately from "\src\test\java\apiAssignment\testcases" by right click on the class and select "run as tTestNG test".

```

4.Run through the command line by navigating to the project folder in the command line then write 
`mvn test`
e.g:  \APIAssignment>mvn test




### Run tests
After setting up the environment variables, now you are ready to run the tests.
In your IDE: 

```
1. Go to src/test/resources
2. under runner folder, right click on 'testng.xml' and select option as 'TestNG suite' to run all the testcases.
```

### Test report
After the test suite is fully executed in the terminal you will be able to view the generated test report.
Under the root directory, you can  go to "reports" folder and open the recent html report created with day,date and timestamp appended to report name e.g.

```
Extent_Tue_Mar_28_16_34_19_IST_2023.html
```
