=====================================================
� MANDATORY EXECUTION-FIRST POLICY
=====================================================
When the user provides a scenario:
1. First, execute the scenario step-by-step in the browser using Playwright MCP or similar tools.
2. During execution, record the best/most stable locators for each UI action (ID > CSS > XPath).
3. Do NOT generate any code (feature file, step definitions, or page objects) until the scenario has been executed and the user has confirmed the actions and locators are correct.
4. Only after user confirmation, proceed to generate the corresponding classes and files.
5. befor e generating code, validate that all locators work correctly in the live application context.
6. use the already EXISTING feature file Steps and Step Definitions wherever possible to avoid duplication.
7. If any step fails during execution, troubleshoot and fix the issue first (adjust locators, waits, etc.) before generating code.

This ensures all generated code is based on validated, robust selectors and real user flows, and prevents unnecessary or incorrect code generation.
=====================================================
⚡ CRITICAL: READ THIS FILE FOR EVERY USER REQUEST
=====================================================
🚨 **MANDATORY FIRST ACTION FOR EVERY USER PROMPT:**

Before responding to ANY user request related to test automation, feature files, 
step definitions, or page objects, you MUST:

1. **Read this entire copilot-instructions.md file** to refresh context
2. **Execute the MANDATORY STEP REUSE ENFORCEMENT WORKFLOW** (below)
3. **Follow ALL guidelines** outlined in this document

This ensures:
✅ Latest guidelines are always followed
✅ No outdated approaches are used
✅ Maximum code reuse and consistency
✅ Zero duplication across all scenarios

**NEVER skip reading this file - it contains critical mandatory workflows!**

=====================================================
🔧 PROJECT STRUCTURE
=====================================================
- Feature files:
  src/test/resources/features/
  Temporary AI-generated feature files:
  src/test/resources/features/aibasedfeaturefiles/

- Step Definitions:
  src/test/java/com/sabre/hotelbooker/stepdefinitions/
  Temporary AI-generated step definitions:
  src/test/java/com/sabre/hotelbooker/stepdefinitions/aibasedstepdefinitions/

- Page Objects:
  src/test/java/com/sabre/hotelbooker/pageobjects/
  Temporary AI-generated page objects:
  src/test/java/com/sabre/hotelbooker/pageobjects/aibasedpageobjects/

=====================================================
🚨 MANDATORY STEP REUSE ENFORCEMENT WORKFLOW
=====================================================
**CRITICAL: BEFORE generating ANY code, you MUST execute this validation workflow:**

**PHASE 1: STEP DISCOVERY (MANDATORY - NO EXCEPTIONS)**
Before creating ANY new Step Definition or Page Object:

1. **Search ALL existing steps** using grep_search:
   ```
   grep_search: "user enters username|user clicks login|selects client"
   grep_search: "user selects country|enters location|clicks on search button"
   ```

2. **Identify existing step definitions** in these locations:
   - src/test/java/com/sabre/hotelbooker/stepdefinitions/hotelbookeruistepdefs/
   - src/test/java/com/sabre/hotelbooker/stepdefinitions/aibasedstepdefinitions/

3. **Identify existing page objects** in these locations:
   - src/test/java/com/sabre/hotelbooker/pageobjects/hotelbookerui/
   - src/test/java/com/sabre/hotelbooker/pageobjects/aibasedpageobjects/

**PHASE 2: REUSE DECISION (MANDATORY)**

✅ **IF ALL STEPS EXIST** → 
   - Create ONLY TestFeature.feature
   - DO NOT create TestSteps.java
   - DO NOT create TestPageObjects.java
   - DO NOT modify HotelBookerUtility.java
   - Use existing steps verbatim in feature file

✅ **IF SOME STEPS EXIST** →
   - Reuse ALL existing steps in feature file
   - Create TestSteps.java ONLY for missing steps
   - Create TestPageObjects.java ONLY for missing page actions
   - Update HotelBookerUtility.java ONLY if new PageObject created

✅ **IF NO STEPS EXIST** (Rare) →
   - Create TestFeature.feature with new steps
   - Create TestSteps.java with step implementations
   - Create TestPageObjects.java with page actions
   - Update HotelBookerUtility.java to include TestPageObjects

**PHASE 3: VALIDATION (MANDATORY)**

After generating code:
1. Run: `mvn test -Dcucumber.filter.tags="@AI"`
2. If test passes → DONE
3. If test fails → Fix locators/waits, re-run until pass
4. **NEVER** create duplicate steps - delete any duplicates immediately

**EXAMPLE ENFORCEMENT:**

❌ **WRONG APPROACH:**
```gherkin
# User says: "Navigate to HotelBooker, login, and search Dallas"
# AI creates:
Given navigate to hotelbooker
When enter credentials
And click login
```

✅ **CORRECT APPROACH:**
```bash
# AI runs grep_search first:
Found: "Open Browser and Navigate to HotelBooker" (LoginPageSteps.java)
Found: "user enters username and password" (LoginPageSteps.java)
Found: "user clicks login button" (LoginPageSteps.java)

# AI creates feature file with existing steps:
Given Open Browser and Navigate to HotelBooker
When user enters username and password
And user clicks login button
```

**🚨 ZERO TOLERANCE RULE:**
- If you create a duplicate step when an existing one is available → DELETE IT IMMEDIATELY
- If you create TestSteps.java when all steps exist → DELETE IT IMMEDIATELY
- If you create TestPageObjects.java when all actions exist → DELETE IT IMMEDIATELY

**CLEANUP COMMANDS:**
```bash
# Delete duplicate step definitions
rm src/test/java/com/sabre/hotelbooker/stepdefinitions/aibasedstepdefinitions/TestSteps.java

# Delete duplicate page objects
rm src/test/java/com/sabre/hotelbooker/pageobjects/aibasedpageobjects/TestPageObjects.java

# Remove from HotelBookerUtility.java:
# - Remove: import com.sabre.hotelbooker.pageobjects.aibasedpageobjects.TestPageObjects;
# - Remove: public TestPageObjects testPageObjects;
# - Remove: this.testPageObjects = new TestPageObjects(page);
```

**VALIDATION CHECKLIST (Run BEFORE finalizing):**
```bash
✅ grep_search executed for ALL potential existing steps
✅ Feature file uses ONLY existing canonical steps
✅ No duplicate StepDef methods created
✅ No duplicate PageObject methods created
✅ TestSteps.java deleted if all steps exist
✅ TestPageObjects.java deleted if all actions exist
✅ HotelBookerUtility.java cleaned of unused references
✅ Test passes: mvn test -Dcucumber.filter.tags="@AI"
```

=====================================================
🎯 OBJECTIVE
=====================================================
Your role is to generate Cucumber + Playwright tests based on natural language scenarios given by the user.

DO NOT directly generate Playwright test code from the scenario alone.

Instead, you must:
1. **FIRST: Execute MANDATORY STEP REUSE ENFORCEMENT WORKFLOW (above)**
2. Interpret the user prompt as a test scenario description.
3. Using Playwright MCP or similar tools, execute each step in the browser context
   (open the browser, locate elements, interact, and verify outcomes).
4. After validating selectors and actions, generate these three files (ONLY if needed per Phase 2):

   ✅ 1. Feature file (.feature)
       - Path: src/test/resources/features/aibasedfeaturefiles/
       - Written in Gherkin syntax using Given / When / Then.
	   - use ScenarioOutline with Examples when user pass the Test data in single 'string' or multiple "strings" format.
       - Should include @AI tag.
       - 🎯 TEST DATA IDENTIFICATION: When user provides test data within single quotes 'data' or double quotes "data", consider this as test data and implement using Scenario Outline with Examples table.
       - 🎯 SCENARIO NAMING: Use descriptive scenario names that clearly indicate the GDS flow, booking type, and key characteristics (e.g., "Sabre vs BCOM Single Night Single Room Single Guest Standard Booking")

   ✅ 2. Step Definition file (.java)
       - Path: src/test/java/com/sabre/hotelbooker/stepdefinitions/aibasedstepdefinitions/
       - Implements Cucumber steps using Playwright Java APIs.
       - Each step should delegate UI actions to the corresponding PageObject class.
       - Reuse existing step definitions from other step classes if the step already exists. No duplicate step code should be generated.

   ✅ 3. Page Object file (.java)
       - Path: src/test/java/com/sabre/hotelbooker/pageobjects/aibasedpageobjects/
       - Contains Playwright locators and methods for each UI action.
       - Must use Playwright’s Page and Locator classes.
       - No test logic — only element actions and getters.


4. Ensure all newly generated AI-based classes use the following exact names and locations:
    - PageObject: TestPageObjects.java (in the appropriate aibasedpageobjects folder)
    - StepDef: TestSteps.java (in the appropriate aibasedstepdefinitions folder)
    - Feature: TestFeature.feature (in the appropriate aibasedfeaturefiles folder)
    - Do not use any prefixes or suffixes—use only these names for new AI-generated classes.
    - Once the scenario/code is validated and working, the user will move these files to their final location and rename as needed.

5. After generating, save files to the respective directories.

6. Execute the generated test using Maven:
   mvn test -Dcucumber.filter.tags="@AI"

7. If the test fails:
   - Adjust locators, waits, or assertions automatically.
   - Regenerate until the test passes.

8. Once a scenario is stable, the user will manually promote files to the main framework.

=====================================================
� MANDATORY EXECUTION-FIRST POLICY
=====================================================
When the user provides a scenario:
1. First, execute the scenario step-by-step in the browser using Playwright MCP or similar tools.
2. During execution, record the best/most stable locators for each UI action (ID > CSS > XPath).
3. Do NOT generate any code (feature file, step definitions, or page objects) until the scenario has been executed and the user has confirmed the actions and locators are correct.
4. Only after user confirmation, proceed to generate the corresponding classes and files.
5. befor e generating code, validate that all locators work correctly in the live application context.
6. use the already EXISTING feature file Steps and Step Definitions wherever possible to avoid duplication.
7. If any step fails during execution, troubleshoot and fix the issue first (adjust locators, waits, etc.) before generating code.

This ensures all generated code is based on validated, robust selectors and real user flows, and prevents unnecessary or incorrect code generation.

=====================================================

🧩 LOCATOR & WAIT STRATEGY (MANDATORY PRIORITY ORDER)
======================================================
🚨 **MANDATORY LOCATOR PRIORITY ORDER - STRICTLY ENFORCED:**

**1. ID SELECTORS (HIGHEST PRIORITY)**
- Always use element ID first if available: `#elementId`
- Example: `#txtClientBookingReference`, `#ctl00_btnSearch`

**2. CSS SELECTORS (SECOND PRIORITY)**  
- Use CSS class selectors, attribute selectors, or pseudo-selectors
- Example: `.form-control`, `button[type='submit']`

**3. XPATH (LAST RESORT ONLY)**
- Only use XPath when ID and CSS options are exhausted
- Must be stable and not brittle
- Example: `//input[@placeholder='Booking Ref']`

**🚨 NEVER USE: NAME, LINKTEXT - These are deprecated and unreliable**

**MANDATORY IMPLEMENTATION RULES:**
- Always inspect actual DOM elements to identify real IDs
- Comment each locator with the actual element ID found: `// ID: txtClientBookingReference`
- Use meaningful locator variable names in camelCase (e.g., `bookingReferenceInput`)
- Prefer stable, semantic selectors over generic ones
- Test all locators with live application before finalizing

**LOCATOR VALIDATION CHECKLIST:**
✅ ID selector used if element has ID attribute
✅ CSS selector used if no ID available  
✅ XPath used only as absolute last resort
✅ All locators tested with actual application
✅ Comments added showing actual element attributes

🚨 MANDATORY: All new Page Object classes MUST use ExplicitWaitUtility for all waits before interacting with elements. Do not use Playwright's native waitForSelector or similar methods directly in PageObjects. Always call the appropriate ExplicitWaitUtility method (e.g., waitForElementVisible, waitForElementClickable, waitForDropdownOptionsToLoad) before any element interaction. This ensures robust, stable, and maintainable automation.

=====================================================
♻️ STEP REUSE & DEDUPLICATION (MANDATORY)
=====================================================
🚨 CRITICAL RULE: DO NOT CREATE NEW STEPS IF EXISTING ONES ARE AVAILABLE

**MANDATORY WORKFLOW:**
1. **ALWAYS search ALL existing .feature files first**
2. **ALWAYS search ALL existing stepdefinitions files**
3. **Reuse ANY existing step or glue code if a match (partial or exact) is found**
4. **ONLY generate new steps if NO equivalent step exists ANYWHERE in the framework**
5. **Maintain consistency in phrasing and capitalization for Given/When/Then steps**

🎯 **STEP DISCOVERY PROCESS:**
Before writing ANY new step:
- Search feature files: `src/test/resources/features/`
- Search step definitions: `src/test/java/stepdefinitions/`
- Look for similar actions, validations, or UI interactions
- Use EXACT same wording if step exists
- Use EXACT same parameter patterns if step exists

🚨 **ZERO TOLERANCE FOR DUPLICATE STEPS:**
- **NEVER create**: "user clicks login button" if "user clicks login button" exists
- **NEVER create**: "user enters username and password" if it exists elsewhere  
- **NEVER create**: "hotel availability should be displayed" if it exists
- **NEVER create**: Any shopping step variations - use the EXACT 9-step pattern
- **NEVER create**: Any background step variations - use existing login flow

✅ **STEP REUSE EXAMPLES:**
```gherkin
# EXISTING STEP - REUSE THIS:
Given user selects client "Test QA Client(Sabre)"

# WRONG - DON'T CREATE:
Given user chooses client "Test QA Client(Sabre)"
Given user picks client "Test QA Client(Sabre)"
```

🎯 **Framework Integrity**: This prevents code duplication, reduces maintenance, and ensures consistent test behavior across all scenarios.

=====================================================
🛒 MANDATORY SHOPPING STEPS PATTERN
=====================================================
🎯 CRITICAL: For ALL hotel search scenarios, use this EXACT shopping step sequence:

```gherkin
When user selects country "<country>"
And enters location "<location>" from suggestion
And enters hotel name "<hotelName>"
And selects distance "<distance>"
And enters number of nights as "<nights>"
And selects number of rooms as "<rooms>"
And selects number of guests as "<guests>"
And selects arrival date <days> days from today
And clicks on search button
Then hotel search results should be displayed or a message if no hotels found
And Select the Rate Plan from "<provider>" with refundable "<refundable>"
```

🚨 NEVER create consolidated or alternative shopping steps. This 11-step pattern is mandatory for:
- Standard booking scenarios
- Same-day booking scenarios  
- Contractual rates scenarios
- Multi-CDT scenarios
- ANY hotel search functionality

✅ Required Examples Table Parameters:
| country | location | hotelName | distance | days | nights | rooms | guests | [scenario_specific_params] |

✅ Location Format: "City - Location" (e.g., "New York - Location")
✅ Distance Format: "X Miles" (e.g., "20 Miles")  
✅ Hotel Name: "Holiday" (standard test data)
✅ Days: Future dates (30, 45, 60, 90, 120) or 0 for same-day

🎯 This pattern ensures step reuse and framework consistency across all feature files.

=====================================================
📊 TEST DATA HANDLING GUIDELINES
=====================================================
🎯 CRITICAL RULE: When user provides test data within quotes, always use Scenario Outline with Examples:

1. **Single Quote Data**: 'username' → Treat as test data parameter
2. **Double Quote Data**: "password" → Treat as test data parameter
3. **Multiple Data Values**: 'user1', 'user2', "pass1", "pass2" → Create Examples table with multiple rows
4. **Mixed Quotes**: Any combination of single/double quotes → All are test data

📝 IMPLEMENTATION PATTERN:
- Convert quoted values to parameterized steps: "login with username 'admin'" → "login with username <username>"
- Create Examples table with all quoted values
- Use descriptive column headers in Examples table
- Always use Scenario Outline (not Scenario) when test data is present

✅ EXAMPLE TRANSFORMATION:
User Input: "Login with username 'testuser' and password 'testpass'"
Generated:
```gherkin
Scenario Outline: Login functionality
When user logs in with username "<username>" and password "<password>"

Examples:
| username | password |
| testuser | testpass |
```

=====================================================

🧩 DEVELOPMENT GUIDELINES
=====================================================
- Always import: com.microsoft.playwright.Page and com.microsoft.playwright.Locator
- Use Cucumber annotations: @Given, @When, @Then
- Follow Page Object Model design.
- Avoid hard-coded waits; Use ExplicitWaitUtility.java with appropriate wait statements
- Ensure readable, maintainable, and reusable steps.

🚨 MANDATORY STEPDEF & PAGEOBJECT STRUCTURE 🚨
-----------------------------------------------------
For every new Step Definition and PageObject class, you MUST:

1. Declare all utility fields (e.g., PageObject, ExplicitWaitUtility, HotelBookerUtility) as private class-level fields.
2. Use a @Before method to initialize all required utilities before each scenario (not lazily in each step).
3. All step methods must only delegate to utility or PageObject methods—no test logic or direct UI code in step methods.
4. No duplicate or redundant step logic—reuse existing steps and glue code wherever possible.
5. Maintain clean, modular, and reusable code consistent with the framework’s standards.

Example StepDef pattern:
```java
public class ExampleSteps {
    private HotelBookerUtility hotelBookerUtility;
     private ExplicitWaitUtility explicitWaitUtility;

     @Before
    public void assignUtility() {
        hotelBookerUtility = new HotelBookerUtility(PlayWrightBaseTest.page);
        explicitWaitUtility = new ExplicitWaitUtility(PlayWrightBaseTest.page);
    }

    @And("user does something")
    public void user_does_something() {
        hotelBookerUtility.examplePage.doSomething();
    }
}
```

Example PageObject pattern:
```java
public class ExamplePageObjects {
    
    private final Page page;
    private final ExplicitWaitUtility waitUtility;
    
    // Client Selection Modal
    // Use the visible header as anchor for the modal
    private static final String CLIENT_MODAL = "div.modal-dialog";
    private static final String CLIENT_MODAL_HEADER = "h4";
    private static final String MODAL_CLOSE_BUTTON = "button.close, button[aria-label='Close']";
    private static final String HEADER_CLIENT = "#lnkClientSelect";
    // Client Filter
    private static final String CLIENT_FILTER_INPUT = "input[placeholder*='Filter Client List']";
    private static final String CLIENT_GROUPS_BUTTON = "button:has-text('Client Groups')";
    private static final String ACTION_LIST_BUTTON = "button:has-text('Action List')";
    private static final String ALL_CLIENTS_BUTTON = "button:has-text('All Clients')";
    // Available Clients (use h2 for headings)
    private static final String CLIENT_HEADING = "h2";
    
    public ExamplePageObjects(Page page) {
        this.page = page;
        this.waitUtility = new ExplicitWaitUtility(page);
    }
    
    /**
     * Wait for client selection modal to load completely
     */
    public void waitForClientModalToLoad() {
    LoggerUtil.logStep("Waiting for client selection modal to load");
    waitUtility.waitForModalToLoad(CLIENT_MODAL);
    LoggerUtil.logStep("Client modal is loaded");
    waitUtility.waitForElementVisible(CLIENT_MODAL_HEADER);
    LoggerUtil.logStep("Client modal header is visible");
    // Wait for at least one client heading to be visible
    waitUtility.waitForElementVisible(CLIENT_HEADING);
    LoggerUtil.logStep("Client headings are visible and modal is ready");
    }
}
```
Example TestPageObject pattern:
```java
public class ExampleSteps {
    private HotelBookerUtility hotelBookerUtility;
     private ExplicitWaitUtility explicitWaitUtility;

     @Before
    public void assignUtility() {
        hotelBookerUtility = new HotelBookerUtility(PlayWrightBaseTest.page);
        explicitWaitUtility = new ExplicitWaitUtility(PlayWrightBaseTest.page);
    }

    @And("user does something")
    public void user_does_something() {
        hotelBookerUtility.examplePage.doSomething();
    }
}
```
insert TestPageObjects object in HotelBookerUtility.java as shown below:
don't change any other existing code in HotelBookerUtility.java
```javapackage com.sabre.hotelbooker.hotelbookerutility;
public class HotelBookerUtility {

    public TestPageObjects testPageObjects;

    

    public HotelBookerUtility(Page page) {
	
        this.testPageObjects = new TestPageObjects(page);

    }

}
```

This structure is mandatory for all new StepDef and PageObject classes.

=====================================================
📘 🎯 CRITICAL EXPECTED OUTPUT FILES EXAMPLES *** Mandatory Format 
=====================================================

📄 src/test/resources/features/aibasedfeaturefiles/Example.feature
-----------------------------------------------------
@AI
Feature: Sabre vs BCOM Single Night Single Room Single Guest Booking

Background:
Given Open Browser and Navigate to HotelBooker
When user enters username and password
And user clicks login button
And selects client "Test QA Client(Sabre)"
Then Validate selected client should display on header

Scenario Outline: Sabre vs BCOM Single Night Single Room Single Guest Standard Booking
# SHOP Phase
When user selects country "<country>"
And enters location "<location>" from suggestion
And selects distance "<distance>"
And enters number of nights as "<nights>"
And selects number of rooms as "<rooms>"
And selects number of guests as "<guests>"
And selects arrival date <days> days from today
And clicks on search button
Then hotel search results should be displayed or a message if no hotels found
And Select the Rate Plan from "<provider>" with refundable "<refundable>"
# BOOK Phase
And Click on Full Rate Information
And Add Booking Contact details
And Add Traveller details
And Click on Fax Communication Preference and Add Custom Data Fields
And Select Payment Method as "<payment_method>"
And Click on Finish button
Then Validate Booking Reference Number to be display
And Click on Home button
# RETRIVE Phase
And user clicks on View Bookings tab
And user enters booking reference
And user clicks search button for booking retrieval
Then Validate booking retrieval page should be displayed
# CANCEL Phase
When clicks Cancel Booking and confirms cancellation
Then booking status should be displayed as Cancelled

    Examples:
      | country | location   | hotelName | distance | days | nights | rooms | guests | provider    | refundable |  payment_method | cancel_method    | email_recipients    |
      | USA     | Dallas     | Holiday   | 20 Miles | 25   | 1      | 1     | 1      | Sabre       | No         |  Credit Card    | Cancel by Room   | Booker,Agent,Client |


📄 src/test/java/stepdefinitions/aibasedstepdefinitions/ExampleSteps.java
-----------------------------------------------------
package com.sabre.hotelbooker.stepdefinitions;

import com.sabre.hotelbooker.hotelbookerutility.ExplicitWaitUtility;
import com.sabre.hotelbooker.hotelbookerutility.HotelBookerUtility;
import com.sabre.hotelbooker.playwrightbase.PlayWrightBaseTest;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import static org.junit.Assert.assertTrue;

public class TestScenarioSteps {

     private HotelBookerUtility hotelBookerUtility;
     private ExplicitWaitUtility explicitWaitUtility;

    @Before
    public void assignUtility() {
        hotelBookerUtility = new HotelBookerUtility(PlayWrightBaseTest.page);
        explicitWaitUtility = new ExplicitWaitUtility(PlayWrightBaseTest.page);
    }


    @And("Add Booking Contact details")
    public void add_booking_contact_details() {
        explicitWaitUtility.waitForPageLoad();
        PlayWrightBaseTest.captureScreenshotWithInfo(PlayWrightBaseTest.page, "Booking Summary Page", Hooks.test.get());
        hotelBookerUtility.bookingSummaryPage.selectContactFromPopup();
    }

    @And("Add Traveller details")
    public void add_traveller_details() {
        explicitWaitUtility.waitForPageLoad();
        hotelBookerUtility.bookingSummaryPage.selectTravellerDetails();
    }

    @And("Click on Fax Communication Preference and Add Custom Data Fields")
    public void Click_on_Fax_Communication_Preference_and_add_Custom_Data_Fields() {
        explicitWaitUtility.waitForPageLoad();
        hotelBookerUtility.bookingSummaryPage.CustomDataFields();
        hotelBookerUtility.bookingSummaryPage.clickOneFaxCommunicationCheckbox();
    }

    @And("Select Payment Method as {string}")
    public void select_payment_method(String paymentMethod) {
        explicitWaitUtility.waitForPageLoad();
        hotelBookerUtility.bookingSummaryPage.selectPaymentMethod(paymentMethod);
    }

    @And("Click on Full Rate Information")
    public void click_on_full_rate_information() {
        explicitWaitUtility.waitForPageLoad();
        hotelBookerUtility.bookingSummaryPage.selectFullRateInformation();
    }

    @And("Validate the Price amount from HotelAvailability Page in Total section")
    public void validate_price_amount_from_hotel_availability_in_total_section() {
        explicitWaitUtility.waitForPageLoad();

    }
    @Then("Click on Finish button")
    public void click_on_finish_button() {
        explicitWaitUtility.waitForPageLoad();
        hotelBookerUtility.bookingSummaryPage.clickOnFinishButton();
        explicitWaitUtility.waitForPageLoad();
    }

    @Then("Validate Booking Reference Number to be display")
    public void validate_booking_reference_number_displayed() {
        explicitWaitUtility.waitForPageLoad();
        String BookingReference=hotelBookerUtility.bookingSummaryPage.getBookingReferenceNumber();
        assertTrue("Booking Reference Number is not generated",BookingReference!=null && !BookingReference.isEmpty());
        PlayWrightBaseTest.captureScreenshotWithInfo(PlayWrightBaseTest.page, "Booking Confirmation Page", Hooks.test.get());
    }
    @And("Click on Home button")
    public void click_on_home_button() {
        explicitWaitUtility.waitForPageLoad();
        hotelBookerUtility.bookingSummaryPage.clickOnHomepageLogo();
    }
}


📄 src/test/java/pageobjects/aibasedpageobjects/ExamplePageObjects.java
-----------------------------------------------------
package com.sabre.hotelbooker.pageobjects;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import com.sabre.hotelbooker.configreaderutils.ConfigReader;
import com.sabre.hotelbooker.hotelbookerutility.ExplicitWaitUtility;
import com.sabre.hotelbooker.loggerutils.LoggerUtil;

@SuppressWarnings("unused")
public class TestScenarioPageObjects {


    // Select Contact Popup locators
    private static final String CONTACT_POPUP = "//h4[text()='Select your booking contact ']";
    private static final String CONTACT_FIND_BUTTON = "#searchContact";
    private static final String ADD_NEW_EMPLOYEE = "#addNewEmployeeContactLinkText";
    private static final String SALUTATION = "#ctl00_ucContactSelect_SalutationDdl";
    private static final String CONTACT_FIRST_NAME_INPUT = "#ctl00_ucContactSelect_ForenameTxt";
    private static final String CONTACT_LAST_NAME_INPUT = "//input[@id='ctl00_ucContactSelect_SurNameTxt']";
    private static final String CONTACT_PHONE_INPUT = "//input[@id='ctl00_ucContactSelect_TelephoneNumberTxt']";
    private static final String CONTACT_EMAIL_INPUT = "//input[@id='ctl00_ucContactSelect_EmailAddressTxt']";
    private static final String ADD_EMPY_CONBUTTON = "#addEmployeeAsContactButton";
    private static final String CONTACT_SEARCH = "#addNewEmployeeContactLinkText";
    private static final String FIRST_NAME= "#ctl00_ucContactSelect_txtFirstName";
    private static final String SELECT_OFFICE_DROPDOWN = "#ctl00_ucContactSelect_OfficeDdl";
    
    // Contact Search Results locators
    private static final String CONTACT_SEARCH_RESULTS_CONTAINER = "//strong[text()='Search Results']";
    private static final String CONTACT_SEARCH_RESULT_ROW = "#contactSearchResults tr.data";
    private static final String CONTACT_SEARCH_RESULT_NAME_CELL = "td:nth-child(1)";
    private static final String CONTACT_SEARCH_RESULT_EMAIL_CELL = "td:nth-child(2)";
    private static final String CONTACT_SEARCH_RESULT_PHONE_CELL = "td:nth-child(3)";
    private static final String CONTACT_SEARCH_RESULT_SELECT_LINK = "//table[@class='table table-hover table-striped']/tbody/tr[1]/td[4]/a";
    private static final String CONTACT_OK_BUTTON = "//div[@id='contactSelectModal']//button[contains(text(),'OK')]";
    private static final String TRAVELLER_OK_BUTTON = "//div[@id='travellerSelectModal']//button[contains(text(),'OK')]";

    private final Page page;
    private final ExplicitWaitUtility waitUtility;
    private final TestDataGenerator testDataGenerator;

    public TestScenarioPageObjects(Page page) {
        this.page = page;
        this.waitUtility = new ExplicitWaitUtility(page);
        this.testDataGenerator = new TestDataGenerator();
    }
    public void waitForBookingSummaryVisible() {
        waitUtility.waitForElementVisible(BOOKING_SUMMARY_HEADING);
    }
    private void addBookingContactDetails(){
        waitUtility.waitForElementVisible(CONTACT_POPUP);
        page.fill(FIRST_NAME, testDataGenerator.FirstName);
        page.click(CONTACT_FIND_BUTTON);
        if(page.locator(CONTACT_SEARCH_RESULTS_CONTAINER).count()>0){
            page.click(CONTACT_SEARCH_RESULT_SELECT_LINK);
            waitUtility.waitForPageLoad();
            page.click(CONTACT_OK_BUTTON);
        }else{
            page.click(ADD_NEW_EMPLOYEE);
            page.selectOption(SALUTATION, new SelectOption().setLabel("Mr"));
            page.fill(CONTACT_FIRST_NAME_INPUT, testDataGenerator.FirstName);
            LoggerUtil.logStep("FirstName "+ testDataGenerator.FirstName);
            page.fill(CONTACT_LAST_NAME_INPUT, testDataGenerator.LastName);
            LoggerUtil.logStep("SurName "+ testDataGenerator.LastName);
            page.fill(CONTACT_PHONE_INPUT, testDataGenerator.PhoneNumber);
            LoggerUtil.logStep("PhoneNumber "+ testDataGenerator.PhoneNumber);
            page.fill(CONTACT_EMAIL_INPUT, testDataGenerator.Email);
            LoggerUtil.logStep("Email "+ testDataGenerator.Email);
            page.selectOption(SELECT_OFFICE_DROPDOWN, new SelectOption().setLabel("Test QA Client Sabre office"));
            page.click(ADD_EMPY_CONBUTTON);
            page.click(CONTACT_OK_BUTTON);
        }
    }


    public void selectContactFromPopup() {
        waitUtility.waitForPageLoad();
        page.click(SELECT_CONTACT_LINK);
        addBookingContactDetails();


    }
    public void selectFullRateInformation(){
        waitUtility.waitForElementClickable(FULL_RATE_INFO_LINK);
        page.click(FULL_RATE_INFO_LINK);
        waitUtility.waitForPageStability();
    }

    public void selectTravellerDetails(){
        waitUtility.waitForPageLoad();
        page.click(SELECT_TRAVELLER_LINK);
        page.click(BOOKING_CONTACT_AS_TRAVELLER);
        page.click(TRAVELLER_OK_BUTTON);
        waitUtility.waitForPageStability();
    }

    public void addNewPaymentCardDetails(){
        String nameOnCard ="TestUser";
        String cardIssuer = ConfigReader.getProperty("CardIssuer");
        String cardNumber = ConfigReader.getProperty("CardNumber");
        String expiryMonth = ConfigReader.getProperty("ExpiryMonth");
        String expiryYear = ConfigReader.getProperty("ExpiryYear");
        page.click(PAYMENT_CARD_LINK);
        page.fill(NAME_ON_CARD, nameOnCard);
        page.selectOption(CARD_ISSUER, new SelectOption().setLabel(cardIssuer));
        page.fill(CARD_NUMBER, cardNumber);
        page.selectOption(EXPIRY_MONTH, new SelectOption().setLabel(expiryMonth));
        page.selectOption(EXPIRY_YEAR, new SelectOption().setLabel(expiryYear));
        page.click(ADD_CARD_BUTTON);
        waitUtility.waitForPageStability();
    }
    public void selectPaymentMethod(String FOP){
        waitUtility.waitForPageLoad();
        if(FOP.equalsIgnoreCase("CSP")){
            page.click(SELECT_CSP_FOP);
        } else if(FOP.equalsIgnoreCase("Agency")){
            page.click(SELECT_AGENCY_FOP);
        } else if(FOP.equalsIgnoreCase("Credit Card")){
            addNewPaymentCardDetails();
            waitUtility.waitForPageStability();
            page.click(SELECT_PAYMENT_METHOD);
        }
        waitUtility.waitForPageStability();
    }

    public void clickOnFinishButton(){
        waitUtility.waitForElementClickable(FINISH_BUTTON);
        page.click(FINISH_BUTTON);
        waitUtility.waitForPageLoad();
    }
    public void clickOnHomepageLogo(){
        page.click("#ctl00_ucNavigation_navLogo");
        waitUtility.waitForPageLoad();
    }
    public void CustomDataFields(){
        if(page.locator(CUSTOM_DATA_FIELD_LABEL).count()>0){
            waitUtility.waitForElementVisible(CUSTOM_DATA_FIELD_LABEL);
            page.fill(CUSTOM_DATA_FIELD_LABEL, testDataGenerator.FirstName);
        }
    }
    public String getBookingReferenceNumber(){
        waitUtility.waitForElementVisible(BOOKING_REF_TITLE);
        String bookingRefNumber = page.textContent("#bookingRefTitle").trim();
        LoggerUtil.logStep("Booking Reference Number: "+bookingRefNumber);
        testDataGenerator.setBookingReferenceNumber(bookingRefNumber);
        return bookingRefNumber;
    }

    public void clickOneFaxCommunicationCheckbox(){
        if(page.locator(FAX_COMMUNICATION_CHECKBOX).count()>0){
            if(!page.isChecked(FAX_COMMUNICATION_CHECKBOX)){
                page.check(FAX_COMMUNICATION_CHECKBOX);
            }
        }
    }

}

📄 src/test/java/com/sabre/hotelbookerutility/HotelBookerUtility.java
-----------------------------------------------------
''''
TestPageObjects.java Object has to created as given as example below.
''''

package com.sabre.hotelbooker.hotelbookerutility;

import com.microsoft.playwright.Page;
import com.sabre.hotelbooker.configreaderutils.TestDataGenerator;
import com.sabre.hotelbooker.pageobjects.*;

import com.sabre.hotelbooker.pageobjects.RetrieveBookingPageObjects;
import com.sabre.hotelbooker.pageobjects.aibasedpageobjects.TestPageObjects;

public class HotelBookerUtility {

    public LoginPageObjects loginPageObjects;
    public TestDataGenerator testDataGenerator;
    public HotelSearchPageObjects hotelSearchPage;
    public HotelAvailabilityPageObjects hotelAvailabilityPage;
    public BookingSummaryPageObjects bookingSummaryPageObjects;
    public RetrieveBookingPageObjects retrieveBookingPage;
    public ClientSelectionPageObjects clientSelectionPageObjects;
    public TestPageObjects testPageObjects;
    public CancellationBookingPageObjects cancellationBookingPageObjects;
    

    public HotelBookerUtility(Page page) {
        this.loginPageObjects = new LoginPageObjects(page);
        this.clientSelectionPageObjects = new ClientSelectionPageObjects(page);
        this.hotelSearchPage = new HotelSearchPageObjects(page);
        this.hotelAvailabilityPage = new HotelAvailabilityPageObjects(page);
        this.bookingSummaryPageObjects = new BookingSummaryPageObjects(page);
        this.retrieveBookingPage = new RetrieveBookingPageObjects(page);
        this.testDataGenerator =new TestDataGenerator();
        this.clientSelectionPageObjects = new ClientSelectionPageObjects(page);
        this.testPageObjects = new TestPageObjects(page);
        this.cancellationBookingPageObjects = new CancellationBookingPageObjects(page);
    }

}





=====================================================
🏁 FINAL NOTE
=====================================================
Only after validating all actions via Playwright MCP should you emit and save these files.

Never generate standalone test code — always respect this framework's 3-layer structure:
- Feature file (Gherkin)
- Step Definitions (Glue)
- Page Objects (Locators + Methods)

Save all AI-generated files in their corresponding AIBased folders.

✅ Always respect the three-layer BDD structure:
Feature → Step Definitions → Page Objects

✅ Always ensure:
**MANDATORY ID > CSS > XPath locator order priority is strictly followed.**
Existing steps are reused (no duplication).
All feature files remain modular, clean, and reusable.
Save all AI-generated files inside their AIBased folders.
**CRITICAL**: All DOM validations are robust and check for content, not just presence.

=====================================================
🎯 CRITICAL SUCCESS CRITERIA
=====================================================
✅ **Every generated test MUST**:
1. **Follow MANDATORY ID > CSS > XPath locator priority order**
2. Use HardAssertUtil for critical validations
3. Include TestExecutionState checks in step definitions
4. Implement robust DOM validation (existence + visibility + content)
5. Follow proper Extent reporting patterns
6. Handle dynamic content loading with appropriate waits
7. Stop execution immediately on critical failures
8. Show clear PASS/FAIL/SKIP status in reports
9. Avoid duplicate error messages in reports
10. Provide comprehensive validation coverage
11. Maintain clean, professional test execution flow

=====================================================
🚦 LOGIN & NAVIGATION STEP REUSE POLICY (MANDATORY)
=====================================================
Always map any user-provided login, navigation, or credential entry steps—regardless of their wording—to the canonical Background steps below:

```
Background:
    Given Open Browser and Navigate to HotelBooker
    When user enters username and password
    And user clicks login button
    And selects client "Test QA Client(Sabre)"
    Then Validate selected client should display on header
    And disable Travel Policy In Agency Admin
```

🚨 **MANDATORY**: All feature files MUST include "disable Travel Policy In Agency Admin" step in Background section after the client validation step. This step is required for ALL test scenarios across all provider combinations (Sabre, BCOM, Amadeus, Galileo).

No matter how the user phrases these actions (e.g., "open browser and navigate", "enter URL with credentials", "go to login page", etc.), always reuse these steps and never create new login or navigation steps or glue code. This ensures consistency, avoids duplication, and leverages the robust, reusable login flow already defined in the framework.

=====================================================
📝 FEATURE FILE CREATION: MANDATORY PHASE-BASED STEP REUSE
=====================================================
🚨 **CRITICAL**: When creating feature files, you MUST search and reuse existing steps for ALL phases:

**PHASE 1: LOGIN/BACKGROUND (100% REUSE - NO NEW STEPS ALLOWED)**
```bash
# MANDATORY grep_search BEFORE feature creation:
grep_search: "Open Browser and Navigate|user enters username|user clicks login|selects client"
```

**Canonical Background Steps (ALWAYS USE THESE):**
```gherkin
Background:
    Given Open Browser and Navigate to HotelBooker
    When user enters username and password
    And user clicks login button
    And selects client "Test QA Client(Sabre)"
    Then Validate selected client should display on header
    And disable Travel Policy In Agency Admin
```

**PHASE 2: SHOPPING (100% REUSE - NO NEW STEPS ALLOWED)**
```bash
# MANDATORY grep_search BEFORE adding shopping steps:
grep_search: "user selects country|enters location.*from suggestion|enters hotel name|selects distance|enters number of nights|selects number of rooms|selects number of guests|selects arrival date.*days from today|clicks on search button"
```

**Canonical Shopping Steps (ALWAYS USE THESE):**
```gherkin
When user selects country "<country>"
And enters location "<location>" from suggestion
And enters hotel name "<hotelName>"
And selects distance "<distance>"
And enters number of nights as "<nights>"
And selects number of rooms as "<rooms>"
And selects number of guests as "<guests>"
And selects arrival date <days> days from today
And clicks on search button
Then hotel search results should be displayed or a message if no hotels found
And Select the Rate Plan from "<provider>" with refundable "<refundable>"
```

**PHASE 3: BOOKING (100% REUSE - NO NEW STEPS ALLOWED)**
```bash
# MANDATORY grep_search BEFORE adding booking steps:
grep_search: "Click on Full Rate Information|Add Booking Contact details|Add Traveller details|Click on Fax Communication|Select Payment Method|Click on Finish button|Validate Booking Reference|Click on Home button"
```

**Canonical Booking Steps (ALWAYS USE THESE):**
```gherkin
And Click on Full Rate Information
And Add Booking Contact details
And Add Traveller details
And Click on Fax Communication Preference and Add Custom Data Fields
And Select Payment Method as "<payment_method>"
And Click on Finish button
Then Validate Booking Reference Number to be display
And Click on Home button
```

**PHASE 4: RETRIEVE (100% REUSE - NO NEW STEPS ALLOWED)**
```bash
# MANDATORY grep_search BEFORE adding retrieve steps:
grep_search: "user clicks on View Bookings tab|user enters booking reference|user clicks search button for booking retrieval|Validate booking retrieval page"
```

**Canonical Retrieve Steps (ALWAYS USE THESE):**
```gherkin
And user clicks on View Bookings tab
And user enters booking reference
And user clicks search button for booking retrieval
Then Validate booking retrieval page should be displayed
```

**PHASE 5: CANCEL (100% REUSE - NO NEW STEPS ALLOWED)**
```bash
# MANDATORY grep_search BEFORE adding cancel steps:
grep_search: "clicks Cancel Booking|confirms cancellation|booking status should be displayed as Cancelled"
```

**Canonical Cancel Steps (ALWAYS USE THESE):**
```gherkin
When clicks Cancel Booking and confirms cancellation
Then booking status should be displayed as Cancelled
```

**🚨 FEATURE FILE CREATION WORKFLOW:**

1. **User Request**: User provides scenario (e.g., "Book hotel in Dallas, then cancel")
2. **Identify Phases**: Determine which phases are needed (Login → Shopping → Booking → Retrieve → Cancel)
3. **Execute grep_search**: Search for existing steps for EACH phase
4. **Build Feature File**: Assemble feature using ONLY existing canonical steps
5. **NO NEW STEPS**: If ANY step from these phases is missing, it's a framework issue - DO NOT create new steps
6. **Validate**: Run test with `mvn test -Dcucumber.filter.tags="@AI"`

**✅ COMPLETE FEATURE FILE EXAMPLE:**
```gherkin
@AI
Feature: Sabre vs BCOM Single Night Single Room Single Guest Booking with Cancellation

Background:
    Given Open Browser and Navigate to HotelBooker
    When user enters username and password
    And user clicks login button
    And selects client "Test QA Client(Sabre)"
    Then Validate selected client should display on header
    And disable Travel Policy In Agency Admin

Scenario Outline: Complete Booking Flow with Cancellation
    # SHOP Phase
    When user selects country "<country>"
    And enters location "<location>" from suggestion
    And enters hotel name "<hotelName>"
    And selects distance "<distance>"
    And enters number of nights as "<nights>"
    And selects number of rooms as "<rooms>"
    And selects number of guests as "<guests>"
    And selects arrival date <days> days from today
    And clicks on search button
    Then hotel search results should be displayed or a message if no hotels found
    And Select the Rate Plan from "<provider>" with refundable "<refundable>"
    # BOOK Phase
    And Click on Full Rate Information
    And Add Booking Contact details
    And Add Traveller details
    And Click on Fax Communication Preference and Add Custom Data Fields
    And Select Payment Method as "<payment_method>"
    And Click on Finish button
    Then Validate Booking Reference Number to be display
    And Click on Home button
    # RETRIEVE Phase
    And user clicks on View Bookings tab
    And user enters booking reference
    And user clicks search button for booking retrieval
    Then Validate booking retrieval page should be displayed
    # CANCEL Phase
    When clicks Cancel Booking and confirms cancellation
    Then booking status should be displayed as Cancelled

    Examples:
      | country | location | hotelName | distance | days | nights | rooms | guests | provider | refundable | payment_method |
      | USA     | Dallas   | Holiday   | 20 Miles | 30   | 1      | 1     | 1      | Sabre    | No         | Credit Card    |
```

**🎯 KEY PRINCIPLE:**
- Feature files should be **100% composition of existing steps**
- If user scenario needs different phases → use different combinations of canonical steps
- **NEVER create new steps for standard phases (Login/Shopping/Booking/Retrieve/Cancel)**
- Only create new steps for genuinely NEW functionality not covered by framework

=====================================================
🎯 CRITICAL AI AUTOMATION COMPLIANCE CHECKLIST
=====================================================
All AI-generated automation MUST strictly follow these rules:

1. **Execution-First**: Validate all actions and locators in the live browser before generating code. Only generate PageObject, StepDef, and Feature files after confirming selectors and flows work as expected.
2. **File Naming & Structure**: All new AI-generated files must be named exactly:
   - `TestPageObjects.java` in `pageobjects/aibasedpageobjects/`
   - `TestSteps.java` in `stepdefinitions/aibasedstepdefinitions/`
   - `TestFeature.feature` in `features/aibasedfeaturefiles/`
3. **Locator Priority**: Use ID selectors first, then CSS, then XPath (last resort). Add comments with the actual element ID for each locator.
4. **Waits**: All waits must use `ExplicitWaitUtility`—never Playwright’s native waits directly.
5. **Step Reuse**: Always search and reuse existing steps and glue code. Never create new steps if an equivalent already exists.
6. **Mandatory Step Patterns**: For login, navigation, and shopping, always use the canonical Background and shopping steps as described.
7. **Scenario Outline for Test Data**: If the scenario uses quoted test data, always use Scenario Outline with Examples.
8. **No Hardcoded Logic**: All UI actions must be delegated to PageObject methods; no direct Playwright code in StepDefs.
9. **Validation**: After generating code, run the test (`mvn test -Dcucumber.filter.tags='@AI'`). If failures occur, automatically fix locators, waits, or logic and rerun until the test passes.
10. **Reporting**: Output the ordered list of user actions performed and ensure all files are saved in the correct AIBased folders.

These rules are mandatory and must be enforced for every AI-generated automation scenario.
=====================================================
🚨 FINAL ENFORCEMENT: FILE DELETION POLICY
=====================================================
**CRITICAL**: After test execution passes, you MUST perform this cleanup:

**STEP 1: Analyze What Was Created**
- List all files created in aibasedfeaturefiles/, aibasedstepdefinitions/, aibasedpageobjects/

**STEP 2: Determine What Should Remain**
```bash
✅ TestFeature.feature → ALWAYS KEEP (this is the goal)
❓ TestSteps.java → DELETE if all steps already exist in framework
❓ TestPageObjects.java → DELETE if all page actions already exist in framework
```

**STEP 3: Execute Cleanup Commands**
```bash
# If all steps already existed:
rm src/test/java/com/sabre/hotelbooker/stepdefinitions/aibasedstepdefinitions/TestSteps.java
rm src/test/java/com/sabre/hotelbooker/pageobjects/aibasedpageobjects/TestPageObjects.java

# Then clean HotelBookerUtility.java:
# Remove: import com.sabre.hotelbooker.pageobjects.aibasedpageobjects.TestPageObjects;
# Remove: public TestPageObjects testPageObjects;
# Remove: this.testPageObjects = new TestPageObjects(page);
```

**STEP 4: Validate Final State**
```bash
✅ Only TestFeature.feature exists in aibasedfeaturefiles/
✅ No duplicate step definitions exist
✅ No duplicate page object methods exist
✅ Test still passes: mvn test -Dcucumber.filter.tags="@AI"
✅ Framework remains clean and maintainable
```

**WHY THIS MATTERS:**
- Prevents code duplication across the framework
- Ensures maximum step reuse and maintainability
- Keeps framework lean and efficient
- Follows DRY (Don't Repeat Yourself) principle
- Makes future maintenance easier

**REMINDER**: The goal is to create ONLY the feature file if all required steps already exist!
=====================================================


