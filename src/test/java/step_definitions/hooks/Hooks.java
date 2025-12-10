package step_definitions.hooks;

import com.microsoft.playwright.Page;
import io.cucumber.java.*;
import io.qameta.allure.Allure;
import org.sabre.Browserfactory.BrowserManager;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Hooks {
    private final BrowserManager browserManager;

    public Hooks(BrowserManager browserManager) {
        this.browserManager = browserManager;
    }

    //Runs once before all tests start
    @BeforeAll
    public static void beforeAll() {
        System.out.println("\nExecuting test suite....");
        // Create environment.properties in allure-results
        String allureResultsPath = System.getProperty("user.dir") + File.separator + "allure-results";
        File envFile = new File(allureResultsPath, "environment.properties");
        try {
            envFile.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(envFile);
            writer.write("Browser=Chrome\n");
            writer.write("OS=" + System.getProperty("os.name") + "\n");
            writer.write("Env=QA\n");
            writer.write("ThreadCount=1\n"); // You can make this dynamic if needed
            writer.close();
        } catch (IOException e) {
            System.out.println("Failed to create environment.properties: " + e.getMessage());
        }
    }

    //Runs once after all tests are done
    @AfterAll
    public static void afterAll() {
        System.out.println("\nFinished executing the test suite!");
    }

    //Runs before each test
    @Before
    public void setup(Scenario scenario) {
        System.out.println("\nStarted Before executing the test!");
        browserManager.setUp();
    }

    //Runs after each test
    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = browserManager.getPage().screenshot(new Page.ScreenshotOptions().setFullPage(true));
            Allure.addAttachment("Screenshot", new ByteArrayInputStream(screenshot));
        }
        browserManager.tearDown();
    }
}
