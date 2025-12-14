package step_definitions.hotelbookeruistepdefs;


import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.hotelbookerui.CancellationBookingPageObjects;

public class CancellationBookingSteps {

    public CancellationBookingPageObjects cancellationBookingPageObjects;

    public CancellationBookingSteps(CancellationBookingPageObjects cancellationBookingPageObjects) {
        this.cancellationBookingPageObjects = cancellationBookingPageObjects;
    }

    @When("clicks Cancel Booking and confirms cancellation")
    public void user_clicks_cancel_booking_button() {
        
        cancellationBookingPageObjects.clickCancelButton();
    }
    @Then("booking cancellation should be completed")
    public void booking_cancellation_should_be_completed() {
        cancellationBookingPageObjects.booking_cancellation_should_be_completed();
    }
    @Then("booking status should be displayed as Cancelled")
    public void booking_status_should_be_displayed_as_cancelled() {
        cancellationBookingPageObjects.booking_status_should_be_displayed_as_cancelled();
    }

    @When("clicks Resend Confirmation button")
    public void user_clicks_resend_confirmation_button() {
        cancellationBookingPageObjects.clickResendConfirmationButton();
    }

    @When("clicks Cancel Room button")
    public void user_clicks_cancel_room_button() {
        cancellationBookingPageObjects.clickCancelRoomButton();
    }

    @When("clicks Amend Room button")
    public void user_clicks_amend_room_button() {
        cancellationBookingPageObjects.clickAmendRoomButton();
    }

    @When("user clicks user menu")
    public void user_clicks_user_menu() {
        cancellationBookingPageObjects.clickUserMenu();
    }
    @When("user clicks logout button")
    public void user_clicks_logout_button() {
        cancellationBookingPageObjects.clickLogoutButton();
    }


}
