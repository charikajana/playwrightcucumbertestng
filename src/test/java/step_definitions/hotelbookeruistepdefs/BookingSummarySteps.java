package step_definitions.hotelbookeruistepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pageobjects.hotelbookerui.BookingSummaryPageObjects;

public class BookingSummarySteps {

    public BookingSummaryPageObjects bookingSummaryPageObjects;

    public BookingSummarySteps(BookingSummaryPageObjects bookingSummaryPageObjects) {
        this.bookingSummaryPageObjects = bookingSummaryPageObjects;
    }

    @And("Add Booking Contact details")
    public void add_booking_contact_details() {
        bookingSummaryPageObjects.selectContactFromPopup();
    }

    @And("Add Traveller details")
    public void add_traveller_details() {
        bookingSummaryPageObjects.selectTravellerDetails();
    }

    @And("Click on Fax Communication Preference and Add Custom Data Fields")
    public void Click_on_Fax_Communication_Preference_and_add_Custom_Data_Fields() {
        bookingSummaryPageObjects.CustomDataFields();
        bookingSummaryPageObjects.clickOneFaxCommunicationCheckbox();
    }

    @And("Select Payment Method as {string}")
    public void select_payment_method(String paymentMethod) {
        bookingSummaryPageObjects.selectPaymentMethod(paymentMethod);
    }

    @And("Click on Full Rate Information")
    public void click_on_full_rate_information() {
        
        bookingSummaryPageObjects.selectFullRateInformation();
    }

    @And("Validate the Price amount from HotelAvailability Page in Total section")
    public void validate_price_amount_from_hotel_availability_in_total_section() {
        

    }
    @Then("Click on Finish button")
    public void click_on_finish_button() {
        bookingSummaryPageObjects.clickOnFinishButton();
        
    }

    @Then("Validate Booking Reference Number to be display")
    public void validate_booking_reference_number_displayed() {
        String BookingReference=bookingSummaryPageObjects.getBookingReferenceNumber();
        Assert.assertTrue(BookingReference!=null && !BookingReference.isEmpty(),"Booking Reference Number is not generated");
    }
    @And("Click on Home button")
    public void click_on_home_button() {
        bookingSummaryPageObjects.clickOnHomeButton();
    }
}
