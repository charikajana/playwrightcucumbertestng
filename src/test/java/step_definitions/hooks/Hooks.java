package step_definitions.hooks;

import io.cucumber.java.*;
import io.qameta.allure.Allure;
import org.sabre.Browserfactory.BrowserManager;

public class Hooks {
    private final BrowserManager browserManager;

    public Hooks(BrowserManager browserManager) {
        this.browserManager = browserManager;
    }

    //Runs once before all tests start
    @BeforeAll
    public static void beforeAll() {
        System.out.println("\nExecuting test suite....");
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
        // Add dynamic Allure labels/parameters
        Allure.label("browser", "Chrome");
        Allure.label("os", System.getProperty("os.name"));
        Allure.label("env", "QA");
        browserManager.setUp();
    }

    //Runs after each test
    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = browserManager.takeScreenshot();
            scenario.attach(screenshot, "image/png", "screenshot");
        }
        browserManager.tearDown();
    }
}
