package pageobjects.hotelbookerui;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.SelectOption;
import org.sabre.Browserfactory.BrowserFactory;
import org.sabre.basefactory.PlaywrightActions;
import org.sabre.util.ThreadLocalManager;
import pageobjects.commons.TestDataGenerator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

@SuppressWarnings("unused")
public class RetrieveBookingPageObjects extends PlaywrightActions {
    private static final Logger logger = Logger.getLogger(RetrieveBookingPageObjects.class.getName());
    // Page Elements Locators - Following ID > CSS > XPath priority order
    // Quick Booking Search (Top section)
    private static final String BOOKING_REFERENCE_INPUT = "#txtClientBookingReference"; // ID: txtClientBookingReference
    private static final String FIND_BOOKING_BUTTON = "button:has-text('Find Booking')"; // No ID - using CSS with text
    private static final String MAKE_BOOKING_LINK = "a:has-text('Make a booking')"; // No ID - using CSS with text
    private static final String VIEW_BOOKINGS_LINK = "a:has-text('View bookings')"; // No ID - using CSS with text
    
    // Advanced Booking Search (View Bookings section)  
    private static final String BOOKING_ARRIVAL_DATE_INPUT = "#ctl00_txtBookingArrivalDate"; // ID: ctl00_txtBookingArrivalDate
    private static final String BOOKING_DEPARTURE_DATE_INPUT = "#ctl00_txtBookingDepartureDate"; // ID: ctl00_txtBookingDepartureDate
    private static final String DATE_TYPE_DROPDOWN = "#ctl00_BookingDateTypeList"; // ID: ctl00_BookingDateTypeList
    private static final String ORDER_BY_DROPDOWN = "#ctl00_BookingOrderByList"; // ID: ctl00_BookingOrderByList
    private static final String FORENAME_INPUT = "#ctl00_txtBookingForename"; // ID: ctl00_txtBookingForename
    private static final String SURNAME_INPUT = "#ctl00_txtBookingSurname"; // ID: ctl00_txtBookingSurname
    private static final String EMAIL_INPUT = "#ctl00_txtBookingEmail"; // ID: ctl00_txtBookingEmail
    private static final String PERSON_TYPE_DROPDOWN = "#ctl00_BookingPersonTypeList"; // ID: ctl00_BookingPersonTypeList
    private static final String BOOKING_REF_INPUT = "#ctl00_txtBookingRef"; // ID: ctl00_txtBookingRef
    private static final String HOTEL_NAME_INPUT = "#ctl00_HotelSearchNameLit"; // ID: ctl00_HotelSearchNameLit
    private static final String THIRD_PARTY_REF_INPUT = "#ctl00_txtThirdPartyBookingRef"; // ID: ctl00_txtThirdPartyBookingRef
    private static final String ADVANCED_SEARCH_BUTTON = "#ctl00_btnBookingSearch"; // ID: ctl00_btnBookingSearch
    private static final String ADVANCED_OPTIONS_LINK = "#openAdvancedBookingSearchOptions"; // ID: openAdvancedBookingSearchOptions
    private static final String BOOKING_STATUS="#BookingStatus";
    // Hotel Search section (Make a booking)
    private static final String HOTEL_SEARCH_ARRIVAL_DATE = "#ctl00_txtArrivalDate"; // ID: ctl00_txtArrivalDate
    private static final String HOTEL_SEARCH_DEPARTURE_DATE = "#ctl00_txtHiddenDepartureDate"; // ID: ctl00_txtHiddenDepartureDate
    private static final String HOTEL_SEARCH_ADVANCED_OPTIONS = "#openAdvancedSearchOptions"; // ID: openAdvancedSearchOptions
    private static final String HOTEL_SEARCH_BUTTON = "#ctl00_btnSearch"; // ID: ctl00_btnSearch
    
    // Legacy locators (keeping for backward compatibility)
    private static final String VIEW_BOOKINGS_TAB = "a:has-text('View bookings')"; // Updated to use proper CSS
    private static final String BOOKING_REFERENCE_FIELD = BOOKING_REF_INPUT; // Alias
    private static final String SEARCH_BUTTON = ADVANCED_SEARCH_BUTTON; // Alias
    private static final String START_DATE_FIELD = BOOKING_ARRIVAL_DATE_INPUT; // Alias
    private static final String END_DATE_FIELD = BOOKING_DEPARTURE_DATE_INPUT; // Alias
    private static final String FORENAME_FIELD = FORENAME_INPUT; // Alias
    private static final String SURNAME_FIELD = SURNAME_INPUT; // Alias
    private static final String EMAIL_FIELD = EMAIL_INPUT; // Alias
    
    // Result elements (no specific IDs found - using generic selectors)
    private static final String RETRIEVE_PNR_LINK = "a:has-text('Retrieve PNR')";
    private static final String BOOKING_RESULTS_SECTION = ".booking-result, [class*='booking']";
    private static final String BOOKING_LINK = "a[href*='Booking.aspx?Id=']";
    private static final String ADD_CONTACT_LINK = "a:has-text('Add Contact')";
    private static final String ADD_TRAVELLERS_LINK = "a:has-text('Add Travellers')";
    private static final String OPEN_BOOKINGS_SECTION = "button:has-text('Open Bookings')";
    private static final String NO_BOOKINGS_MESSAGE = "text*='No bookings found'";
    private static final String BOOKING_DETAILS_SECTION = ".booking-details, [class*='booking-detail']";
    private static final String ERROR_MESSAGE = ".error-message, .alert-danger";
    private static final String SUCCESS_MESSAGE = ".success-message, .alert-success";

    public RetrieveBookingPageObjects(BrowserFactory browserFactory) {
        super(browserFactory);
    }

    /**
     * Quick booking search using the top booking reference field
     */
    public void searchQuickBookingByPNR(String pnr) {
        waitAndEnterText(BOOKING_REFERENCE_INPUT, pnr);
        waitAndClick(FIND_BOOKING_BUTTON);
    }

    /**
     * Click on View Bookings tab
     */
    public void clickViewBookingsTab() {
        waitAndClick(VIEW_BOOKINGS_TAB);
    }

    /**
     * Enter booking reference for search
     */
    public void enterBookingReference(String bookingRef) {
        waitAndEnterText(BOOKING_REFERENCE_FIELD,bookingRef);
    }

    /**
     * Click search button to retrieve booking
     */
    public void clickSearchButton() {
        waitAndClick(SEARCH_BUTTON);
    }

    /**
     * Enter start date for booking search
     */
    public void enterStartDate(String startDate) {
        waitAndEnterText(START_DATE_FIELD, startDate);
    }

    /**
     * Enter end date for booking search
     */
    public void enterEndDate(String endDate) {
        waitAndEnterText(END_DATE_FIELD, endDate);
    }

    /**
     * Select date type from dropdown
     */
    public void selectDateType(String dateType) {
        selectDropdownByValue(DATE_TYPE_DROPDOWN,dateType);
    }

    /**
     * Select order by option from dropdown
     */
    public void selectOrderBy(String orderBy) {
        selectDropdownByValue(ORDER_BY_DROPDOWN,orderBy);
    }

    /**
     * Enter forename for guest search
     */
    public void enterForename(String forename) {
        waitAndEnterText(FORENAME_FIELD, forename);
    }

    /**
     * Enter surname for guest search
     */
    public void enterSurname(String surname) {
        waitAndEnterText(SURNAME_FIELD, surname);
    }

    /**
     * Enter email for guest search
     */
    public void enterEmail(String email) {
        waitAndEnterText(EMAIL_FIELD, email);
    }

    /**
     * Select person type from dropdown
     */
    public void selectPersonType(String personType) {
        selectDropdownByValue(PERSON_TYPE_DROPDOWN,personType);
    }

    /**
     * Click on Retrieve PNR link
     */
    public void clickRetrievePNR() {
        waitAndClick(RETRIEVE_PNR_LINK);
    }

    /**
     * Click on Advanced Options link
     */
    public void clickAdvancedOptions() {
        waitAndClick(ADVANCED_OPTIONS_LINK);
    }

    /**
     * Click on Add Contact link
     */
    public void clickAddContact() {
        waitAndClick(ADD_CONTACT_LINK);
    }

    /**
     * Click on Add Travellers link
     */
    public void clickAddTravellers() {
        waitAndClick(ADD_TRAVELLERS_LINK);
    }

    /**
     * Validate that booking results are displayed
     */
    public boolean isBookingResultsDisplayed() {
        try {
            return isVisible(BOOKING_RESULTS_SECTION);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Validate that View Bookings page is displayed
     */
    public boolean isViewBookingsPageDisplayed() {
        waitForPageLoad();
        try {
            return isVisible(BOOKING_STATUS);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get booking reference from the first booking in results
     */
    public String getFirstBookingReference() {
        waitForPageLoad();
        Locator firstBookingLink = getLocator(BOOKING_LINK).first();
        if (firstBookingLink.isVisible()) {
            String href = firstBookingLink.getAttribute("href");
            if (href != null && href.contains("Id=")) {
                String bookingId = href.substring(href.lastIndexOf("Id=") + 3);
                return bookingId;
            }
        }
        return null;
    }

    /**
     * Click on specific booking by booking ID
     */
    public void clickBookingById(String bookingId) {
        String bookingLinkSelector = "a[href*='Booking.aspx?Id=" + bookingId + "']";
        waitAndClick(bookingLinkSelector);
    }

    /**
     * Validate specific booking exists in results
     */
    public boolean isBookingFoundById(String bookingId) {
        String bookingLinkSelector = "a[href*='Booking.aspx?Id=" + bookingId + "']";
        try {
            return isVisible(bookingLinkSelector);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get total number of bookings displayed
     */
    public int getBookingCount() {
        try {
            return getLocator(BOOKING_LINK).count();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Validate no bookings message is displayed
     */
    public boolean isNoBookingsMessageDisplayed() {
        try {
            return isVisible(NO_BOOKINGS_MESSAGE);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Clear all search fields
     */
    public void clearAllSearchFields() {
        if (isVisible(BOOKING_REFERENCE_FIELD)) {
            waitAndEnterText(BOOKING_REFERENCE_FIELD, "");
        }
        if (isVisible(FORENAME_FIELD)) {
            waitAndEnterText(FORENAME_FIELD, "");
        }
        if (isVisible(SURNAME_FIELD)) {
            waitAndEnterText(SURNAME_FIELD, "");
        }
        if (isVisible(EMAIL_FIELD)) {
            waitAndEnterText(EMAIL_FIELD, "");
        }
    }

    /**
     * Validate booking details are displayed for specific booking
     */
    public void validateBookingDetailsDisplayed(String bookingId) {
        boolean isBookingFound = isBookingFoundById(bookingId);
        if (!isBookingFound) {
            throw new RuntimeException("CRITICAL FAILURE: Booking with ID " + bookingId + " not found in search results");
        }
    }

    /**
     * Validate booking search functionality works
     */
    public void validateBookingSearchFunctionality() {
        if (!isViewBookingsPageDisplayed()) {
            throw new RuntimeException("CRITICAL FAILURE: View Bookings page is not displayed properly");
        }
        if (!isVisible(SEARCH_BUTTON)) {
            throw new RuntimeException("CRITICAL FAILURE: Search button is not visible on booking retrieval page");
        }
    }

    /**
     * Set date range for booking search
     */
    public void setDateRange(int daysBack, int daysForward) {
        LocalDate startDate = LocalDate.now().minusDays(daysBack);
        LocalDate endDate = LocalDate.now().plusDays(daysForward);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        enterStartDate(startDate.format(formatter));
        enterEndDate(endDate.format(formatter));
    }

    /**
     * Search booking by PNR or Booking Reference
     */
    public void searchBookingByPNR() {
        clearAllSearchFields();
        enterBookingReference(TestDataGenerator.Booking_Pnr);
        clickSearchButton();
    }

    /**
     * Search booking by guest details
     */
    public void searchBookingByGuestDetails(String forename, String surname, String email) {
        clearAllSearchFields();
        if (forename != null && !forename.isEmpty()) {
            enterForename(forename);
        }
        if (surname != null && !surname.isEmpty()) {
            enterSurname(surname);
        }
        if (email != null && !email.isEmpty()) {
            enterEmail(email);
        }
        clickSearchButton();
    }


}
