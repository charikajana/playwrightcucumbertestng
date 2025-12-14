# playwrightcucumbertestng

## Overview
This project demonstrates automated testing using Playwright, Cucumber, and TestNG in a Java Maven environment. It supports Allure reporting and is configured for easy execution of tests.

## Prerequisites
- Java 11 or higher
- Maven 3.6+
- Node.js (for Playwright browser installation, if required)

## Project Structure
- `src/test/java/runner/RunCucumberTest.java`: Main test runner class
- `src/test/resources/features/`: Cucumber feature files
- `pom.xml`: Maven configuration with all dependencies and plugins

## How to Run the Tests

### 1. Run with Main Method (Recommended for this Project)
This project is set up to run the `main` method of `RunCucumberTest` using the Maven Exec Plugin.

**Command:**
```
mvn clean test-compile exec:java@RunCucumberTest

if you want to pass additional arguments, you can do so by appending them after the command, like this:
mvn clean test-compile exec:java@RunCucumberTest -DBrowserName=chrome -DThreadCount=2 -DEnv=DEV
```
This will compile your tests and execute the `main` method in `runner.RunCucumberTest` using the test classpath.

### 2. Run as TestNG/Cucumber Test (If Refactored)
If you refactor your runner to use TestNG or JUnit annotations (e.g., `@Test`), you can use:
```
mvn clean test
```
> **Note:** By default, `mvn test` does not run `main` methods. It only runs test methods annotated with `@Test`.

### 3. Generate Allure Report
After running your tests, you can generate and view the Allure report:
```
mvn allure:report
```
The report will be available in the `target/site/allure-maven-plugin` directory.

## Troubleshooting
- If you see SLF4J warnings, they are safe to ignore unless you require logging output.
- Ensure all dependencies are downloaded by running `mvn clean install` if you encounter missing classes.
- For Playwright browser dependencies, you may need to run `npx playwright install` if not already set up.

## Customization
- To change the test runner or add more features, edit the files in `src/test/java/runner/` and `src/test/resources/features/`.
- Update `pom.xml` to add or change dependencies and plugins as needed.

## Contact
For questions or issues, please contact the project maintainer.
