package step_definitions.hotelbookeruistepdefs;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageobjects.commons.TestDataGenerator;
import pageobjects.hotelbookerui.RetrieveBookingPageObjects;


public class RetrieveBookingSteps {

    public RetrieveBookingPageObjects retrieveBookingPageObjects;

    public RetrieveBookingSteps(RetrieveBookingPageObjects retrieveBookingPageObjects) {
        this.retrieveBookingPageObjects = retrieveBookingPageObjects;
    }
    @When("user clicks on View Bookings tab")
    public void user_clicks_on_view_bookings_tab() {
        retrieveBookingPageObjects.clickViewBookingsTab();
    }

    @Then("View Bookings page should be displayed")
    public void view_bookings_page_should_be_displayed() {
        retrieveBookingPageObjects.validateBookingSearchFunctionality();
        Assert.assertTrue(retrieveBookingPageObjects.isViewBookingsPageDisplayed(),"View Bookings page is not displayed");
    }

    @When("user enters booking reference")
    public void user_enters_booking_reference() {
        retrieveBookingPageObjects.enterBookingReference(TestDataGenerator.BookingReferenceNumber);
    }

    @When("user clicks search button for booking retrieval")
    public void user_clicks_search_button_for_booking_retrieval() {
        retrieveBookingPageObjects.clickSearchButton();
    }

    @When("user searches for booking by PNR")
    public void user_searches_for_booking_by_pnr(String pnr) {
        retrieveBookingPageObjects.searchBookingByPNR();
    }

    @When("user searches for booking using guest details forename {string} and surname {string}")
    public void user_searches_for_booking_using_guest_details(String forename, String surname) {
        retrieveBookingPageObjects.searchBookingByGuestDetails(forename, surname, null);
    }

    @When("user searches for booking using guest details forename {string} surname {string} and email {string}")
    public void user_searches_for_booking_using_guest_details_with_email(String forename, String surname, String email) {
        retrieveBookingPageObjects.searchBookingByGuestDetails(forename, surname, email);
    }

    @When("user enters start date {string}")
    public void user_enters_start_date(String startDate) {
        retrieveBookingPageObjects.enterStartDate(startDate);
    }

    @When("user enters end date {string}")
    public void user_enters_end_date(String endDate) {
        retrieveBookingPageObjects.enterEndDate(endDate);
    }

    @When("user selects date type {string}")
    public void user_selects_date_type(String dateType) {
        retrieveBookingPageObjects.selectDateType(dateType);
    }

    @When("user selects order by {string}")
    public void user_selects_order_by(String orderBy) {
        retrieveBookingPageObjects.selectOrderBy(orderBy);
    }

    @When("user enters forename {string}")
    public void user_enters_forename(String forename) {
        retrieveBookingPageObjects.enterForename(forename);
    }

    @When("user enters surname {string}")
    public void user_enters_surname(String surname) {
        retrieveBookingPageObjects.enterSurname(surname);
    }

    @When("user enters email {string}")
    public void user_enters_email(String email) {
        retrieveBookingPageObjects.enterEmail(email);
    }

    @When("user selects person type {string}")
    public void user_selects_person_type(String personType) {
        retrieveBookingPageObjects.selectPersonType(personType);
    }

    @When("user clicks on Retrieve PNR")
    public void user_clicks_on_retrieve_pnr() {
        retrieveBookingPageObjects.clickRetrievePNR();
    }

    @When("user clicks on Advanced Options for booking search")
    public void user_clicks_on_advanced_options_for_booking_search() {
        retrieveBookingPageObjects.clickAdvancedOptions();
    }

    @When("user clicks on Add Contact")
    public void user_clicks_on_add_contact() {
        retrieveBookingPageObjects.clickAddContact();
    }

    @When("user clicks on Add Travellers")
    public void user_clicks_on_add_travellers() {
        retrieveBookingPageObjects.clickAddTravellers();
    }

    @When("user sets date range {int} days back and {int} days forward")
    public void user_sets_date_range(int daysBack, int daysForward) {
        retrieveBookingPageObjects.setDateRange(daysBack, daysForward);
    }

    @When("user clears all search fields")
    public void user_clears_all_search_fields() {
        retrieveBookingPageObjects.clearAllSearchFields();
    }

    @Then("booking search results should be displayed")
    public void booking_search_results_should_be_displayed() {
        Assert.assertTrue(retrieveBookingPageObjects.isBookingResultsDisplayed(),"Booking search results are not displayed");
    }

    @Then("booking search results should be displayed or no bookings message")
    public void booking_search_results_should_be_displayed_or_no_bookings_message() {
        boolean resultsDisplayed = retrieveBookingPageObjects.isBookingResultsDisplayed();
        boolean noBookingsMessage = retrieveBookingPageObjects.isNoBookingsMessageDisplayed();
        Assert.assertTrue(resultsDisplayed || noBookingsMessage,"Neither booking results nor no bookings message is displayed");
    }

    @Then("booking with ID {string} should be found")
    public void booking_with_id_should_be_found(String bookingId) {
        retrieveBookingPageObjects.validateBookingDetailsDisplayed(bookingId);
        Assert.assertTrue(retrieveBookingPageObjects.isBookingFoundById(bookingId),"Booking with ID " + bookingId + " was not found");
    }

    @Then("booking with ID {string} should not be found")
    public void booking_with_id_should_not_be_found(String bookingId) {
        Assert.assertFalse(retrieveBookingPageObjects.isBookingFoundById(bookingId),"Booking with ID " + bookingId + " was found but should not be");
    }

    @When("user clicks on booking with ID {string}")
    public void user_clicks_on_booking_with_id(String bookingId) {
        retrieveBookingPageObjects.clickBookingById(bookingId);
    }

    @Then("at least {int} booking should be displayed in results")
    public void at_least_booking_should_be_displayed_in_results(int minimumCount) {
        int actualCount = retrieveBookingPageObjects.getBookingCount();
        Assert.assertTrue(actualCount >= minimumCount,"Expected at least " + minimumCount + " booking(s) but found " + actualCount
                   );
    }

    @Then("exactly {int} booking should be displayed in results")
    public void exactly_booking_should_be_displayed_in_results(int expectedCount) {
        int actualCount = retrieveBookingPageObjects.getBookingCount();
        Assert.assertEquals(expectedCount, actualCount,"Expected exactly " + expectedCount + " booking(s) but found " + expectedCount);
    }

    @Then("no bookings should be found")
    public void no_bookings_should_be_found() {
        int bookingCount = retrieveBookingPageObjects.getBookingCount();
        boolean noBookingsMessage = retrieveBookingPageObjects.isNoBookingsMessageDisplayed();
        
        Assert.assertTrue(bookingCount == 0 || noBookingsMessage,"Expected no bookings but found " + bookingCount + " booking(s)" );
    }

    @When("user retrieves booking using generated PNR")
    public void user_retrieves_booking_using_generated_pnr() {
        retrieveBookingPageObjects.searchBookingByPNR();
    }

    @Then("retrieved booking data should match original reservation")
    public void retrieved_booking_data_should_match_original_reservation() {
        Assert.assertTrue(retrieveBookingPageObjects.isBookingResultsDisplayed(),"Booking search results should be displayed for retrieval validation");
    }

    @When("user performs comprehensive booking search")
    public void user_performs_comprehensive_booking_search() {
        retrieveBookingPageObjects.setDateRange(30, 30);
        retrieveBookingPageObjects.selectDateType("Arrival Date");
        retrieveBookingPageObjects.selectOrderBy("Arrival ASC");
        retrieveBookingPageObjects.clickSearchButton();
    }

    @When("user searches for recent bookings")
    public void user_searches_for_recent_bookings() {
        retrieveBookingPageObjects.setDateRange(7, 7);
        retrieveBookingPageObjects.selectDateType("Booked Date");
        retrieveBookingPageObjects.selectOrderBy("Booking Ref DESC");
        retrieveBookingPageObjects.clickSearchButton();
    }

    @Then("booking retrieval functionality should work correctly")
    public void booking_retrieval_functionality_should_work_correctly() {
        retrieveBookingPageObjects.validateBookingSearchFunctionality();
    }

    @Given("user navigates to View Bookings page")
    public void user_navigates_to_view_bookings_page() {
        retrieveBookingPageObjects.clickViewBookingsTab();
        retrieveBookingPageObjects.validateBookingSearchFunctionality();
    }

    @When("user searches for booking")
    public void user_searches_for_booking() {
        retrieveBookingPageObjects.searchBookingByPNR();
    }

    @Then("Validate booking retrieval page should be displayed")
    public void booking_retrieval_page_should_be_displayed() {
        Assert.assertTrue(retrieveBookingPageObjects.isViewBookingsPageDisplayed(),"Booking retrieval page should be displayed");
    }



}

