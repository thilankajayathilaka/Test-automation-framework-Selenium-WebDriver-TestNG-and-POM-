# Puma Selenium Test Suite

This project is a Selenium-based test suite for automating functional tests on the Puma website. The suite includes tests for logging in, searching products, selecting items, and verifying error messages, with support for multiple browsers and parallel execution.

## Table of Contents

- [Project Overview](#project-overview)
- [Technologies Used](#technologies-used)
- [Setup Instructions](#setup-instructions)
- [Running Tests](#running-tests)
- [Writing Tests](#writing-tests)
- [Troubleshooting](#troubleshooting)

## Project Overview

The Puma Selenium Test Suite is built using Selenium WebDriver with TestNG for test management. It supports Chrome, Firefox, and Edge browsers and uses data-driven testing with TestNG's `DataProvider` to read login credentials from an Excel file.

### Features:
- Automated login testing with data from Excel files
- Search functionality testing with assertions for accurate results
- Product selection and validation of error messages
- Parallel browser testing for faster execution

## Technologies Used

- **Java** - Programming language for writing the tests.
- **Selenium WebDriver** - For browser automation.
- **TestNG** - Testing framework to manage and execute tests.
- **Apache POI** - For reading data from Excel files.
- **WebDriverManager** - For managing WebDriver binaries.

## Setup Instructions

   git clone https://github.com/thilankajayathilaka/Test-automation-framework-Selenium-WebDriver-TestNG-and-POM-.git
   cd puma-selenium-test-suite
   mvn clean install
