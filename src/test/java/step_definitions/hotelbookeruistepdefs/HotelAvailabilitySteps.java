package step_definitions.hotelbookeruistepdefs;

import io.cucumber.java.en.And;
import pageobjects.hotelbookerui.HotelAvailabilityPageObjects;

public class HotelAvailabilitySteps {

    public HotelAvailabilityPageObjects hotelAvailabilityPageObjects;

    public HotelAvailabilitySteps(HotelAvailabilityPageObjects hotelAvailabilityPageObjects) {
        this.hotelAvailabilityPageObjects = hotelAvailabilityPageObjects;
    }

    @And("Select the Rate Plan from {string} with refundable {string}")
    public void selected_client_should_display_on_header(String Provider, String RateType) {
        hotelAvailabilityPageObjects.SelectHotelRatesForProvider(Provider, RateType);
    }
}
