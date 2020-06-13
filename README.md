# Applitools Cross Browser Testing Hackathon 2020

I have created this Hackathon project to participate in the **Cross Browser Testing Hackathon 2020** challenge. 
This project is based on Selenium WenDriver using Java language.

This README includes information about the project structure and instructions to execute both Traditional and Modern tests suites.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for evaluating my effort on this challenge.   

### Technologies

* Selenium WebDriver v3.141.59 - for Web Based UI Automation Testing  
* Applitool Eyes RELEASE (latest) version - for Visual Testing  
* Java JDK 11 - for Development   
* Apache Maven v3.6.3 - as Build Tool   
* TestNG v7.1.0 - as Test Runner   

### External Libraries Used

* WebDriverManager v4.0.0 - for the automatic management of the binary drivers (i.e. chromedriver, msedgedriver, geckodriver) required by Selenium WebDriver.   
* AssertJ v3.15.0 - for implementing assertions in TraditionalTests.java   
* Awaitility v4.0.2 - for implementing SmartWaits to handle asynchronous calls if any   

### Plugins

* Maven Compiler v3.8.0 - for compiling the project   
* Maven FailSafe v3.0.0-M4 - to execute tests with specified configurations and goals   

## Hackathon Requirements

### Browsers Selection

* Google Chrome v83.0.4103.97  
* Microsoft Edge Chromium v83.0.478.45  
* Mozila Firefox v77.01   

### Viewports Selection

* Laptop - 1200x700   
* Tablet - 800x700   
* Mobile - 500x700   


## Project Structure

### src/main/java/com/hackathon/base

- contains the **AutomationBase.java** which is the singleton base class all our tests inherit   
- contains the **AutomationFactory.java** which is developed on factory design pattern for automation drivers instantiation    
- contains the **DriverOptions.java** which handles browser capabilities and options  
- contains the **DriverTypes.java** which handles viewports based on device selection  
                                   
### src/main/java/com/hackathon/constants 

- contains **GLOBAL.Java** which has all the required constant variables used in the project   

### src/main/java/com/hackathon/pages 

- contains the **page object classes** implemented using the very famous design pattern **Page Object Model**.   

### src/main/java/com/hackathon/utils 

- contains all the utility methods needed to support the automation of this challenge   

### src/resources/configs 

- contains the **secrets.properties** file to securely hold the API key in case we forget to provide through Maven   

### src/test/java/com/hackathon/suites 

- contains TestNG xml suite files:
  **traditional.xml** - responsible for executing **TraditionalTests.java** locally   
  **modern.xml** - responsible for executing **ModernTests.java** on Applitools Ultrafast Grid   

### src/test/java/com/hackathon/tests 

- contains **TraditionalTests.java** for both V1 and V2 of **AppliFashion app**  
- contains **ModernTests.java** for both V1 and V2 of **AppliFashion app**  

### pom.xml 

- contains the information of the project and configurations to build the project such as dependencies, build directory, source directory, 
  test source directory, plugins, goals etc.  
  
## Hackathon Traditional Tests Results Reporter files

- **Traditional-V1-TestResults.txt** : resides at the root of the project and contains the results of the all possible test
  scenarios performed on the V1 of the AppliFasion app  
  
- **Traditional-V2-TestResults.txt** : resides at the root of the project and contains the results of the all possible test
  scenarios performed on the V2 of the AppliFasion app    

## Guide to Execute Test Suites

Please either clone this project or download it on your local machine. Once done, please follow below steps to execute   
traditional as well as modern tests.

### Executing the Traditional Tests Suite

Open **CMD** or any terminal . Go to the project root directory and run the following commands.  

Note: Recommended option is to use **IntelliJ Idea Maven runner.**  

Use below Maven command to run tests (with default settings):  

mvn clean verify **-D**testSuite=**traditional.xml**  

However, to execute tests for Hackathon AppliFashion App V1 for different viewports, you need to run below commands one by one.  

#### Execute Traditional Tests on CHROME with 1200x700 viewport
mvn clean verify **-D**testSuite=**traditional.xml** **-D**appVersion=**V1** **-D**device=**laptop** **-D**browser=**chrome**  

#### Execute Traditional Tests on CHROME with 800x700 viewport
mvn clean verify **-D**testSuite=**traditional.xml** **-D**appVersion=**V1** **-D**device=**tablet** **-D**browser=**chrome**  

#### Execute Traditional Tests on CHROME with 500x700 viewport
mvn clean verify **-D**testSuite=**traditional.xml** **-D**appVersion=**V1** **-D**device=**mobile** **-D**browser=**chrome**  

#### Execute Traditional Tests on EDGE with 1200x700 viewport
mvn clean verify **-D**testSuite=**traditional.xml** **-D**appVersion=**V1** **-D**device=**laptop** **-D**browser=**edge**  

#### Execute Traditional Tests on EDGE with 800x700 viewport
mvn clean verify **-D**testSuite=**traditional.xml** **-D**appVersion=**V1** **-D**device=**tablet** **-D**browser=**edge**  

#### Execute Traditional Tests on EDGE with 500x700 viewport
mvn clean verify **-D**testSuite=**traditional.xml** **-D**appVersion=**V1** **-D**device=**mobile** **-D**browser=**edge**  

#### Execute Traditional Tests on FIREFOX with 1200x700 viewport
mvn clean verify **-D**testSuite=**traditional.xml** **-D**appVersion=**V1** **-D**device=**laptop** **-D**browser=**firefox**  

#### Execute Traditional Tests on FIREFOX with 800x700 viewport
mvn clean verify **-D**testSuite=**traditional.xml** **-D**appVersion=**V1** **-D**device=**tablet** **-D**browser=**firefox**  

#### Execute Traditional Tests on FIREFOX with 500x700 viewport
mvn clean verify **-D**testSuite=**traditional.xml** **-D**appVersion=**V1** **-D**device=**mobile** **-D**browser=**firefox**  

**For executing tests on Hackathon AppliFashion App V2 with above browser-viewport configurations:**   

Just replace the **-D**appVersion=**V1** to **-D**appVersion=**V2** in all of above mentioned Maven commands.   

**Note:** My framework does support **parallel execution**, but I was unable to launch the same browser with different viewports parallel.   

### Executing the Modern Tests Suite

Open **CMD** or any terminal . Go to the project root directory and run the following commands.  

Note: Recommended option is to use **IntelliJ Idea Maven runner.**  

**For executing tests on Hackathon AppliFashion App V1:**   

mvn clean verify **-D**testSuite=**modern.xml** **-D**appVersion=**V1** **-D**enableEyes=**true**   

**For executing tests on Hackathon AppliFashion App V2:**   

mvn clean verify **-D**testSuite=**modern.xml** **-D**appVersion=**V2** **-D**enableEyes=**true**   

## Tests Stats

### Traditional Tests Suite Execution Time

Time to install dependencies, create framework, create tests, write reports, and file bugs, etc. = **10 hours**  
Time to execute the tests in all three browsers with three different viewports configurations = **20 minutes**  

### Modern Tests Suite Execution Time

Time to install dependencies, create framework, create tests, write reports, and file bugs, etc. = **2 hours**  
Time to execute the tests in all three browsers with three different viewports configurations = **2.10 minutes**  

## Applitools Eyes Ultrafast Grid Test Manager Results

Please visit the below link to see the results of my execution of the Modern Tests Suite.   

https://eyes.applitools.com/app/test-results/00000251810316634156/?accountId=-ddgbIeGjkqgZtJaL9f3Eg~~   

## Author

* MUHAMMAD TAFSEER HAIDER    
  Principal SQA Automation Engineer, Developer & DevOps Advocate - Tester by Profession, Developer by Passion

## Contact

In case you are stuck and not able to run my tests for any reason, please contact me on:   

E-mail: mtafseer.haider@gmail.com OR tafseer.haider@netsoltech.com, WhatsApp: +923008359570  
