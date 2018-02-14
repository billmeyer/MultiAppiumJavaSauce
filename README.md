## LoanCalcAppiumTest

Demonstrates running Appium tests with the TestNG testing framework on Sauce Labs across multiple mobile device combinations in parallel.

This code is provided on an "AS-IS” basis without warranty of any kind, either express or implied, including without limitation any implied warranties of condition, uninterrupted use, merchantability, fitness for a particular purpose, or non-infringement. Your tests and testing environments may require you to modify this framework. Issues regarding this framework should be submitted through GitHub. For questions regarding Sauce Labs integration, please see the Sauce Labs documentation at https://wiki.saucelabs.com/. This framework is not maintained by Sauce Labs Support.

### Environment Setup

1. Global Dependencies

    * [Install Maven](https://maven.apache.org/install.html)
    * Or Install Maven with [Homebrew](http://brew.sh/)
    
            $ brew install maven

2. App Upload

    * Upload the `app-release.apk` from this project to [https://testobject.com]().
    
    * A new app named __Car-Loan-Calculator__ will be available.  Click on it and select __Automated Test__ &rarr; __Appium__.
    
    * In the upper right corner, click on __Setup Instructions__.
    
    * Copy the value of the `testobject_api_key` entry and do either of the following:
    
        1. Create an environment variable named `TESTOBJECT_API_KEY` and set its value from TestObject console.
        2. Change the first line of the __TestBase__ class and hard code the value:

                public class TestBase
                {
                    private static final String testobjectApiKey = "<INSERT_API_KEY_HERE>";
    	        
3. Project Dependencies

	* Check that Packages are available
	
	        $ cd MultiAppiumJavaSauce
	        $ mvn test-compile
	
	* You may also want to run the command below to check for outdated dependencies. Please be sure to verify and review updates before editing your `pom.xml` file. The updated packages may or may not be compatible with your code.
	
	        $ mvn versions:display-dependency-updates
	    
### Running Tests

* Tests in Parallel:

    	$ mvn test

[Sauce Labs Dashboard](https://saucelabs.com/beta/dashboard/)

### Advice/Troubleshooting

1. It may be useful to use a Java IDE such as IntelliJ or Eclipse to help troubleshoot potential issues. 

2. There may be additional latency when using `RemoteWebDriver` to run tests on Sauce Labs. Timeouts or Waits may need to be increased.

    * [Selenium tips regarding explicit waits](https://wiki.saucelabs.com/display/DOCS/Best+Practice%3A+Use+Explicit+Waits)

### Resources

##### [Sauce Labs Documentation](https://wiki.saucelabs.com/)

##### [SeleniumHQ Documentation](http://www.seleniumhq.org/docs/)

##### [TestNg Documentation](http://testng.org/javadocs/index.html)

##### [Java Documentation](https://docs.oracle.com/javase/7/docs/api/)

##### Stack Overflow:

* [Related Stack Overflow Threads](http://stackoverflow.com/questions/27355003/advise-on-hierarchy-for-element-locators-in-selenium-webdriver)
