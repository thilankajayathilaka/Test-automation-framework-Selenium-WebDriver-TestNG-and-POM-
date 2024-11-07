Puma Selenium Test Suite
This project is a Selenium-based test suite for automating functional tests on the Puma website. The suite includes tests for logging in, searching products, selecting items, and verifying error messages, with support for multiple browsers and parallel execution.

Table of Contents
Project Overview
Technologies Used
Project Structure
Setup Instructions
Running Tests
Writing Tests
Troubleshooting
Project Overview
The Puma Selenium Test Suite is built using Selenium WebDriver with TestNG for test management. It supports Chrome, Firefox, and Edge browsers and uses data-driven testing with TestNG's DataProvider to read login credentials from an Excel file.

Features:
Automated login testing with data from Excel files
Search functionality testing with assertions for accurate results
Product selection and validation of error messages
Parallel browser testing for faster execution
Technologies Used
Java - Programming language for writing the tests.
Selenium WebDriver - For browser automation.
TestNG - Testing framework to manage and execute tests.
Apache POI - For reading data from Excel files.
WebDriverManager - For managing WebDriver binaries.
Project Structure
bash
Copy code
src/
├── main/
│   └── java/
│       └── puma/
│           ├── pageobjects/     # Page Object classes for different web pages
│           ├── utilities/       # Utilities for browser management, data reading, etc.
└── test/
    └── java/
        └── puma/
            ├── tests/           # Test classes for login, search, and product selection
            └── resources/       # Excel data files (e.g., loginData.xlsx)
Setup Instructions
Clone the repository:

bash
Copy code
git clone https://github.com/your-username/puma-selenium-test-suite.git
cd puma-selenium-test-suite
Install dependencies: Make sure you have Java and Maven installed. Run the following command to install required dependencies:

bash
Copy code
mvn clean install
Configure WebDriverManager: Ensure that WebDriverManager is set up to download the appropriate WebDriver binaries automatically.

Add test data: Update loginData.xlsx under src/test/resources with valid and invalid test credentials for the login tests.

Running Tests
Running All Tests
To run all tests, execute the following Maven command:

bash
Copy code
mvn test
Running Tests with TestNG Suite File
You can specify different configurations using a TestNG XML suite file (testng.xml). To run with testng.xml:

bash
Copy code
mvn -Dsurefire.suiteXmlFiles=testng.xml test
The suite file will execute tests in the specified order (e.g., running login tests first and then search functionality tests across browsers).

Writing Tests
Page Object Pattern: Page classes are created under puma.pageobjects. Follow this pattern to encapsulate page interactions within specific classes.
Test Data: Use DataProvider for data-driven testing (e.g., login credentials).
Exception Handling: Handle StaleElementReferenceException and other common errors using retry logic within page methods.
Adding a New Test
Create a new test class in puma.tests.
Add methods to puma.pageobjects for any new page actions.
Use assertions in tests to validate expected behavior.
Update testng.xml to include the new test class if necessary.
Troubleshooting
Common Errors
StaleElementReferenceException: This occurs when an element is no longer attached to the DOM. Add retry logic as demonstrated in the acceptCookies method of HomePage.
WebDriver Version Mismatch: Ensure you’re using the correct version of WebDriverManager and drivers for your browser.
Debugging
To debug tests, use the @Optional parameter to specify the browser type directly within IntelliJ or your IDE. Breakpoints can be set in test methods or page classes for step-by-step debugging.

License
This project is licensed under the MIT License. See the LICENSE file for details.
