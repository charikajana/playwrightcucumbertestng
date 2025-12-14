package pageobjects.hotelbookerui;


import org.sabre.Browserfactory.BrowserFactory;
import org.sabre.basefactory.PlaywrightActions;
import org.sabre.util.ThreadLocalManager;
import pageobjects.commons.TestDataGenerator;

import java.time.LocalDate;
import java.util.logging.Logger;

public class HotelSearchPageObjects extends PlaywrightActions {
    private static final Logger logger = Logger.getLogger(HotelSearchPageObjects.class.getName());
    private static final String CLIENT_HEADING = "h2:has-text('Test QA Client(Sabre)')";
    private static final String COUNTRY_DROPDOWN = "#ctl00_lstCountry";
    private static final String LOCATION_FIELD = "input[placeholder*='Place']";
    private static final String HOTEL_NAME_FIELD = "#ctl00_txtHotelName";
    private static final String DISTANCE_DROPDOWN = "#ctl00_lstDistance";
    private static final String ARRIVAL_DATE_FIELD = "#ctl00_txtArrivalDate";
    private static final String NIGHTS_FIELD = "#ctl00_txtNights";
    private static final String ROOMS_DROPDOWN = "#ctl00_lstRooms";
    private static final String GUESTS_DROPDOWN = "#ctl00_lstOccupancy";
    private static final String SEARCH_BUTTON = "#ctl00_btnSearch";
    private static final String HEADER_CLIENT = "#lnkClientSelect";
    private static final String NO_HOTEL_MESSAGE = "text=Sorry, we weren't able to find any hotels for the criteria you specified.";
    private static final String SEARCH_RESULTS = "#ctl00_cphMainContent_pnlHotels";


    public HotelSearchPageObjects(BrowserFactory browserFactory) {
        super(browserFactory);
    }

    public void selectClient() {
        waitAndClick(CLIENT_HEADING);
    }

    public boolean isClientDisplayedOnHeader() {
        return isVisible(HEADER_CLIENT);
    }

    public void selectCountry(String country) {
        selectDropdownByValue(COUNTRY_DROPDOWN, country);
    }

    public void enterLocation(String location) {
        waitAndEnterText(LOCATION_FIELD, location);
    }

    public void enterHotelName(String hotelName) {
        waitAndEnterText(HOTEL_NAME_FIELD, hotelName);
    }

    public void selectDistance(String distance) {
        selectDropdownByValue(DISTANCE_DROPDOWN, distance);
    }

    public void setArrivalDateDaysFromToday(int days) {
        getLocator(ARRIVAL_DATE_FIELD).clear();
        selectArrivalDateInCalendar(days);
    }

    public void setNights(String nights) {
        waitAndEnterText(NIGHTS_FIELD, nights);
    }

    public void selectRooms(String rooms) {
        TestDataGenerator.setNoOfRooms(rooms);
        selectDropdownByValue(ROOMS_DROPDOWN, rooms);
    }

    public void selectGuests(String guests) {
        TestDataGenerator.setNoOfGuests(guests);
        if(!getLocator(GUESTS_DROPDOWN).isDisabled()){
            selectDropdownByValue(GUESTS_DROPDOWN,guests);
        }
    }

    public void clickSearchButton() {
        waitAndClick(SEARCH_BUTTON);
    }

    public boolean isSearchResultDisplayedOrNoHotelMessage() {
        waitForEitherVisible(SEARCH_RESULTS,NO_HOTEL_MESSAGE,30000);
        boolean flag=false;
        if(elementCount(SEARCH_RESULTS)>0){
            flag=true;
            logger.info("Search results are displayed.");
        } else if(elementCount(NO_HOTEL_MESSAGE)>0){
            flag=false;
            logger.info("No hotel message is displayed.");
        } else {
            logger.warning("Neither search results nor no hotel message is displayed.");
        }
        return flag;
    }
    public void selectArrivalDateInCalendar(int daysFromToday) {
        LocalDate targetDate = LocalDate.now().plusDays(daysFromToday);
        int targetDay = targetDate.getDayOfMonth();
        int targetMonth = targetDate.getMonthValue();
        int targetYear = targetDate.getYear();
        waitAndClick(ARRIVAL_DATE_FIELD);
        String monthLabelSelector = ".datepicker-days .datepicker-switch";
        String nextMonthButtonSelector = ".datepicker-days th.next";
        while (true) {
            String monthYearText = getText(monthLabelSelector).trim();
            java.time.format.DateTimeFormatter calFmt = java.time.format.DateTimeFormatter.ofPattern("MMMM yyyy");
            java.time.YearMonth displayedMonthYear = java.time.YearMonth.parse(monthYearText, calFmt);
            if (displayedMonthYear.getMonthValue() == targetMonth && displayedMonthYear.getYear() == targetYear) {
                break;
            }
            waitAndClick(nextMonthButtonSelector);
        }
        String dayCellSelector = String.format(".datepicker-days td.day:not(.old):not(.new):text-is('%d')", targetDay);
        waitAndClick(dayCellSelector);
    }
}
