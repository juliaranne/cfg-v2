# Testing application for the MLA fitness app 
An app to test the MLA fitness app in a black box manner, from a user's perspective.

## How to use 
1. Install Java (21+)
2. Install the relevant plugins for your IDE for Cucumber & Java 
3. Follow the instructions in the MLA-app README to run the application 
4. Run the CucumberTest class (`src/test/java/cucumber/CucumberTest.java`)
5. The tests run in headless mode (without a UI), to disable this - remove the options in WebDriverManager 

### Cucumber Tags
Add tags in `src/test/resources/junit-platform.properties`
* Currently, have `@ignore` configured to not run a test 


### Plugins 
_Some useful plugins for different IDEs_

**Visual Studio** 
* redhat language support for java 
* Cucumber 
* requires version 21+ 
* test runner for java 

**IntelliJ**
* Cucumber