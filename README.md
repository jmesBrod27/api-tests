API Testing Coding Challenge

This Java application demonstrates how to perform API testing using RestAssured, a popular Java library for testing RESTful APIs. The project includes a set of test cases for testing a REST API.



Getting Started

Software Requirements
Before you can run the API tests, ensure that you have the following software installed on your system:
* Java
* Maven
* Integrated Development Environment (IDE) - Feel free to use IntelliJ, Eclipse, or Visual Studio Code for running this application.

Dependencies Used
The project relies on several Java libraries, and Maven will automatically download these dependencies based on the pom.xml configuration:
* io.rest-assured: Used for REST API testing.
* org.testng: TestNG testing framework.
* org.slf4j: Used for logging purposes.
* com.fasterxml.jackson.core: Used for JSON data binding i.e. parsing JSON responses.

Installation
* Clone this repository to your local machine: git clone https://github.com/jmesBrod27/api-tests
* Navigate to the project directory: cd src/test/java/



Usage

This application demonstrates how to perform the following types of API testing using RestAssured:
* GET Request: Retrieve data from the API and validate the response.
* POST Request: Create a new resource using a POST request and validate the response.
* PUT Request: Update an existing resource using a PUT request and validate the response.
* PATCH Request: Partially update an existing resource using a PATCH request and validate the response.
* DELETE Request: Delete a resource using a DELETE request and validate the response.

This application is also set up to run tests in parallel.  The TestNG suite configuration in the testing.xml file, allows TestNG to execute the test methods in the ApiTest class concurrently using two threads allowing for parallel execution of the test methods. Here's the relevant setup for this in the testing.xml file:
<suite name="Parallel Test Suite" thread-count="2" parallel="methods">
<test name="Parallel Test API">
<classes>
<class name="ApiTest"/>
</classes>
</test>
</suite>



Project Structure

The project structure is as follows:
* src/test/java: Contains the Java test classes.
* src/test/resources: Contains configuration properties for the tests.
* pom.xml: Maven configuration file specifying project dependencies.


Test Cases

The main test class is ApiTest. It contains various test methods, each focusing on a specific API testing scenario. These test methods are self-contained and can be run independently.



Configuration

The configuration properties for the tests are stored in the data.properties file.
This file includes:
* baseUri: The base URL of the API under test.
* listLength: The expected length of the list in the API response.
* requestBodyToPost: JSON payload for POST requests.
* requestBodyToPut: JSON payload for PUT requests.
* requestBodyToPatch: JSON payload for PATCH requests.

You can customize the tests by modifying the configuration properties in the data.properties and dataCopy.properties files, and create new test methods or modify existing ones. I added the dataCopy.properties file so as the user could make use of alternative configuration properties for testing with different data.



Running the Tests

To run the tests, follow these steps:
* Ensure that you have cloned the repository and navigated to the project directory (as mentioned in the Installation section).
* Open a terminal or command prompt in the project directory.
* Run the tests using Maven: mvn test (Maven will download the required dependencies and execute the test cases specified in the ApiTest class using TestNG.)

Alternative steps to run the Application:
1. Navigate to the test file in this directory src/test/java/ApiTest in your chosen IDE.
2. To run, select option to run the test file within ApiTest test class or right-click on the testing.xml file which will provide run options also.

