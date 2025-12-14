package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.sabre.applicationConstants.ApplicationConstant;
import org.sabre.util.EnvPropertyLoader;
import org.testng.TestNG;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.Collections;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


@CucumberOptions(
        features = "src/test/resources/features",
        glue = "step_definitions",
        tags = "@TestOne",
        dryRun = false,
        plugin = {"pretty", "json:target/cucumber.json",
                "html:target/cucumber-report.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
)
public class RunCucumberTest extends AbstractTestNGCucumberTests {
    private static final Logger logger = Logger.getLogger(RunCucumberTest.class.getName());
    private static final Properties properties = EnvPropertyLoader.getProperties();

    public static void main(String[] args) {
        //Create an instance of TestNG
        TestNG testNG = new TestNG();

        //Create a new TestNG Suite
        XmlSuite suite = new XmlSuite();

        //Get the thread count from the properties file
        int threadCount = getThreadCount();
        System.out.println("Configured thread count value: " + threadCount);

        // Set the number of threads for the data provider
        suite.setDataProviderThreadCount(threadCount);

        // Create a new TestNG test and add it to the suite
        XmlTest test = new XmlTest(suite);
        test.setName("Cucumber Tests"); //Setting the name of our test
        test.setXmlClasses(Collections.singletonList(new XmlClass(RunCucumberTest.class))); // Add the test class to the test

        // Disable default listeners (Will disable TestNG reports from being generated)
        testNG.setUseDefaultListeners(false);

        // Add the suite to the TestNG instance
        testNG.setXmlSuites(Collections.singletonList(suite));

        // Run TestNG with the configured suite
        testNG.run();
    }

    //Method to get thread count from system property or properties file
    private static int getThreadCount() {
        // 1. Check system property (highest priority)
        String sysProp = System.getProperty(ApplicationConstant.THREAD_COUNT);
        if (sysProp != null && !sysProp.trim().isEmpty()) {
            try {
                int val = Integer.parseInt(sysProp.trim());
                if (val > 0) return val;
            } catch (NumberFormatException ignored) {}
        }
        // 2. Check property file (if system property not set or invalid)
        String propFileVal = properties.getProperty(ApplicationConstant.THREAD_COUNT);
        if (propFileVal != null && !propFileVal.trim().isEmpty()) {
            try {
                int val = Integer.parseInt(propFileVal.trim());
                if (val > 0) return val;
            } catch (NumberFormatException ignored) {}
        }
        // 3. Default (if neither is set or both are invalid)
        return 1;
    }

    //DataProvider Method
    //used for parallel execution, allowing multiple tests to run simultaneous
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
