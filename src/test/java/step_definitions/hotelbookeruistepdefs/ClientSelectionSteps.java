package step_definitions.hotelbookeruistepdefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageobjects.hotelbookerui.ClientSelectionPageObjects;

public class ClientSelectionSteps {
    public ClientSelectionPageObjects clientSelectionPageObjects;

    public ClientSelectionSteps(ClientSelectionPageObjects clientSelectionPageObjects) {
        this.clientSelectionPageObjects = clientSelectionPageObjects;
    }

    @When("selects client {string}")
    public void selects_client(String clientName) {
        clientSelectionPageObjects.selectClient(clientName);
    }
    @Then("Validate selected client should display on header")
    public void selected_client_should_display_on_header() {
        Assert.assertTrue(clientSelectionPageObjects.isClientDisplayedOnHeader());
    }

}
