package step_definitions.agencyadminstepdefs;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageobjects.agencyadmin.TravelPolicyPageObjects;


public class TravelPolicySteps {
    
    public TravelPolicyPageObjects travelPolicyPageObjects;

    public TravelPolicySteps(TravelPolicyPageObjects travelPolicyPageObjects) {
        this.travelPolicyPageObjects = travelPolicyPageObjects;
    }

    @When("user clicks on Tools button")
    public void user_clicks_on_tools_button() {
        travelPolicyPageObjects.clickToolsButton();
    }

    @And("user clicks on Agency Admin link")
    public void user_clicks_on_agency_admin_link() {
        travelPolicyPageObjects.clickAgencyAdminLink();
    }

    @And("user gets the page title")
    public void user_gets_the_page_title() {
        String pageTitle = travelPolicyPageObjects.getPageTitle();
        Assert.assertTrue(pageTitle != null && !pageTitle.isEmpty(),"Page title should not be empty");
    }

    @And("user clicks on Travel Policy link")
    public void user_clicks_on_travel_policy_link() {
        
        travelPolicyPageObjects.clickTravelPolicyLink();
    }

    @And("user unchecks Policy Active checkbox")
    public void user_unchecks_policy_active_checkbox() {
        travelPolicyPageObjects.uncheckPolicyActiveCheckbox();
    }

    @And("user clicks on Update Policy button")
    public void user_clicks_on_update_policy_button() {
        
        travelPolicyPageObjects.clickUpdatePolicyButton();
    }

    @Then("user should see Update Successful message")
    public void user_should_see_update_successful_message() {
        
        boolean isMessageDisplayed = travelPolicyPageObjects.isUpdateSuccessfulMessageDisplayed();
        Assert.assertTrue(isMessageDisplayed,"Update Successful message should be displayed");
    }

    @When("user closes Agency Admin window")
    public void user_closes_agency_admin_window() {
        travelPolicyPageObjects.closeAgencyAdminWindow();
    }

    // =====================================================
    // COMPOSITE STEP - Disable Travel Policy
    // =====================================================
    /**
     * Composite step that executes the complete flow to disable Travel Policy.
     * This step can be reused in any feature file by simply calling:
     * "When user disables Travel Policy"
     *
     * Internally executes:
     * 1. Clicks Tools button
     * 2. Clicks Agency Admin link
     * 3. Switches to Agency Admin window
     * 4. Clicks Travel Policy link
     * 5. Unchecks Policy Active checkbox
     * 6. Clicks Update Policy button
     * 7. Verifies Update Successful message
     * 8. Closes Agency Admin window
     */
    @When("disable Travel Policy In Agency Admin")
    public void user_disables_travel_policy() {
        user_clicks_on_tools_button();
        user_clicks_on_agency_admin_link();
        user_clicks_on_travel_policy_link();
        user_unchecks_policy_active_checkbox();
        user_clicks_on_update_policy_button();
        user_should_see_update_successful_message();
        user_closes_agency_admin_window();
    }
}
