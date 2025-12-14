package step_definitions.hotelbookeruistepdefs;


import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageobjects.hotelbookerui.HotelSearchPageObjects;


public class HotelSearchSteps {
    
    public HotelSearchPageObjects hotelSearchPageObjects;

    public HotelSearchSteps(HotelSearchPageObjects hotelSearchPageObjects) {
        this.hotelSearchPageObjects = hotelSearchPageObjects;
    }

    @Then("selected client should display on header")
    public void selected_client_should_display_on_header() {
        Assert.assertTrue(hotelSearchPageObjects.isClientDisplayedOnHeader());
    }

    @When("user selects country {string}")
    public void user_selects_country(String country) {
        hotelSearchPageObjects.selectCountry(country);
    }

    @When("enters location {string} from suggestion")
    public void enters_location_from_suggestion(String location) {
        hotelSearchPageObjects.enterLocation(location);
    }

    @When("enters hotel name {string}")
    public void enters_hotel_name(String hotelName) {
        hotelSearchPageObjects.enterHotelName(hotelName);
    }

    @When("selects distance {string}")
    public void selects_distance(String distance) {
        hotelSearchPageObjects.selectDistance(distance);
    }

    @When("selects arrival date {int} days from today")
    public void selects_arrival_date_days_from_today(int days) {
        hotelSearchPageObjects.setArrivalDateDaysFromToday(days);
    }

    @When("enters number of nights as {string}")
    public void enters_number_of_nights_as(String nights) {
        hotelSearchPageObjects.setNights(nights);
    }

    @When("selects number of rooms as {string}")
    public void selects_number_of_rooms_as(String rooms) {
        hotelSearchPageObjects.selectRooms(rooms);
    }

    @When("selects number of guests as {string}")
    public void selects_number_of_guests_as(String guests) {
        hotelSearchPageObjects.selectGuests(guests);
    }

    @When("clicks on search button")
    public void clicks_on_search_button() {
        hotelSearchPageObjects.clickSearchButton();
        
    }

    @Then("hotel search results should be displayed or a message if no hotels found")
    public void hotel_search_results_should_be_displayed_or_a_message_if_no_hotels_found() {
        Assert.assertTrue(hotelSearchPageObjects.isSearchResultDisplayedOrNoHotelMessage());
    }
}