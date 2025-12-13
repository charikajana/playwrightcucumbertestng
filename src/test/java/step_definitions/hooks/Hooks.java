package step_definitions.hooks;

import com.microsoft.playwright.Page;
import io.cucumber.java.*;
import io.qameta.allure.Allure;
import org.sabre.Browserfactory.BrowserManager;
import org.sabre.util.ExecutionSummary;
import org.sabre.util.EmailNotifier;
import org.sabre.util.SummaryReportGenerator;
import org.sabre.util.AllureDirectorySetup;

import jakarta.mail.MessagingException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.stream.Collectors;

public class Hooks {
    private final BrowserManager browserManager;

    public Hooks(BrowserManager browserManager) {
        this.browserManager = browserManager;
    }

    //Runs once before all tests start
    @BeforeAll
    public static void beforeAll() {
        System.out.println("\nExecuting test suite....");
        AllureDirectorySetup.setup();
        ExecutionSummary.markStart();
        // Set environment and browser (replace with actual logic if needed)
        ExecutionSummary.setEnvironment("QA"); // Or read from config
        ExecutionSummary.setBrowser("Chrome"); // Or read from config
        // Create environment.properties in allure-results
        String allureResultsPath = System.getProperty("user.dir") + File.separator + "/allure-results";
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
        ExecutionSummary.markEnd();
        AllureDirectorySetup.copyResultsAndGenerateReport();
        // Only send summary email if running in Jenkins
        String jenkinsHome = System.getenv("JENKINS_HOME");
        if (jenkinsHome != null && !jenkinsHome.isEmpty()) {
            try {
                // Load mail config (replace with your config loading logic)
                Properties config = new Properties();
                // config.load(new FileInputStream("path/to/config.properties"));
                // Set these properties as needed or load from file
                config.setProperty("mail.smtp.host", "smtp.yourserver.com");
                config.setProperty("mail.smtp.port", "587");
                config.setProperty("mail.smtp.username", "your_email@domain.com");
                config.setProperty("mail.smtp.password", "your_password");
                config.setProperty("mail.smtp.auth", "true");
                config.setProperty("mail.smtp.starttls.enable", "true");
                EmailNotifier notifier = new EmailNotifier(config);
                String recipients = "stakeholder1@domain.com,stakeholder2@domain.com"; // comma-separated
                String allureReportLink = "http://your-allure-server/report"; // or local file path
                String htmlSummary = SummaryReportGenerator.generateHtmlSummary(allureReportLink);
                notifier.sendHtmlMail(recipients, "Automation Execution Summary", htmlSummary);
            } catch (MessagingException e) {
                System.out.println("Failed to send summary email: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error in summary email logic: " + e.getMessage());
            }
        } else {
            System.out.println("Not running in Jenkins. Skipping summary email notification.");
        }
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
        ExecutionSummary.incrementTotal();
        // Collect tags for this scenario
        ExecutionSummary.addTags(scenario.getSourceTagNames().stream().map(tag -> tag.replace("@", "")).collect(Collectors.toSet()));
        if (scenario.isFailed()) {
            ExecutionSummary.incrementFailed();
            byte[] screenshot = browserManager.getPage().screenshot(new Page.ScreenshotOptions().setFullPage(true));
            Allure.addAttachment("Screenshot", new ByteArrayInputStream(screenshot));
        } else {
            ExecutionSummary.incrementPassed();
        }
        browserManager.tearDown();
    }
}
