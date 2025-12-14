package pageobjects.hotelbookerui;

import org.sabre.Browserfactory.BrowserFactory;
import org.sabre.basefactory.PlaywrightActions;
import org.sabre.util.EnvPropertyLoader;
import org.sabre.util.ThreadLocalManager;
import org.testng.Assert;
import pageobjects.commons.TestDataGenerator;
import java.util.logging.Logger;


@SuppressWarnings("unused")
public class BookingSummaryPageObjects extends PlaywrightActions {
    private static final Logger logger = Logger.getLogger(BookingSummaryPageObjects.class.getName());
    // Booking Contact Card locators
    private static final String BOOKING_CONTACT_CARD = "div:has(h4:text-is('Booking Contact'))";
    private static final String BOOKING_CONTACT_HEADING_LABEL = "h4:text-is('Booking Contact')";
    private static final String BOOKING_CONTACT_REQUIRED = "em:has-text('* Contact Required')";
    private static final String BOOKING_CONTACT_MESSAGE = "p:has-text(We can't find a contact associated with this booking)";
    private static final String BOOKING_CONTACT_SELECT_LINK = "a:text-is('Select Contact')";
    private static final String BOOKING_CONTACT_EMAIL_CHECKBOX = "#e164";
    private static final String BOOKING_CONTACT_EMAIL_LABEL = "label:has-text('Send Email Notification')";
    private static final String BOOKING_SUMMARY_HEADING = "h1:text-is('Booking Summary')";
    private static final String EDIT_BOOKING_HEADING = "h4:text-is('Edit your booking?')";
    private static final String BOOKING_CONTACT_HEADING = "h4:text-is('Booking Contact')";
    private static final String TRAVELLERS_HEADING = "h4:text-is('Travellers')";
    private static final String PAYMENT_METHOD_HEADING = "h4:text-is('Payment Method')";
    private static final String PAYMENT_CARD_LINK= "#lnkPaymentCardSummary";
    private static final String NAME_ON_CARD= "#ctl00_cphMainContent_ucPaymentCard_NameTextBox";
    private static final String CARD_ISSUER="#ctl00_cphMainContent_ucPaymentCard_CardIssuerList";
    private static final String CARD_NUMBER="#ctl00_cphMainContent_ucPaymentCard_CardNumberTextBox";
    private static final String EXPIRY_MONTH="#ctl00_cphMainContent_ucPaymentCard_ExpiryMonthList";
    private static final String EXPIRY_YEAR="#ctl00_cphMainContent_ucPaymentCard_ExpiryYearList";
    private static final String SINGLE_PNR_HEADING = "h4:text-is('Single PNR')";
    private static final String ADD_CARD_BUTTON= "#AddCardBtn";
    private static final String CUSTOM_DATA_FIELDS_HEADING = "h4:text-is('Custom Data Fields')";
    private static final String SPECIAL_INSTRUCTIONS_HEADING = "h4:text-is('Special Instructions')";
    private static final String HOTEL_NAME = "#e109";
    private static final String HOTEL_ADDRESS = "#e109 + xpath=following-sibling::text()[1]";
    private static final String HOTEL_PHONE = "#e109 + xpath=following-sibling::text()[2]";
    private static final String PRICE_USD = "#e111 >> text=/USD/";
    private static final String PRICE_GBP = "#e111 >> text=/GBP/";
    private static final String PER_ROOM_PER_NIGHT_LABEL = "#e111 + xpath=following-sibling::text()[1]";
    private static final String STAY_DETAILS = "#e116 + xpath=..//following-sibling::text()[1]";
    private static final String ROOM_TYPE = "#e118 + xpath=..//following-sibling::text()[1]";
    private static final String GUEST_COUNT = "#e120 + xpath=..//following-sibling::text()[1]";
    private static final String FULL_RATE_INFO_LINK = "#ctl00_cphMainContent_rateInfoLink";
    private static final String LESS_RATE_INFO_LINK = "#ctl00_cphMainContent_rateInfoLink";
    private static final String ARRIVAL_DATE_INPUT = "input[aria-label='Arrival Date']";
    private static final String NIGHTS_INPUT = "input[aria-label='Nights']";
    private static final String ROOMS_DROPDOWN = "select[aria-label='Rooms']";
    private static final String GUESTS_DROPDOWN = "select[aria-label='Guests']";
    private static final String UPDATE_BUTTON = "a:text-is('Update'), button:text-is('Update')";
    private static final String SELECT_CONTACT_LINK = "#ctl00_cphMainContent_selectContactLinkLit";
    private static final String SEND_EMAIL_CONTACT_CHECKBOX = "#e164";
    private static final String SELECT_TRAVELLER_LINK = "#ctl00_cphMainContent_selectTravellersLinkText";
    private static final String BOOKING_CONTACT_AS_TRAVELLER ="//a[text()='Add Booking Contact As Traveller']";
    private static final String ADD_TBA_TRAVELLER = "//a[text()='Add TBA Traveller']";
    private static final String SEND_EMAIL_TRAVELLER_CHECKBOX = "#e182";
    private static final String PAYMENT_METHOD_OPTION = "#e196";
    private static final String SINGLE_PNR_BUTTON = "button:text-is('Single PNR')";
    private static final String TO_BOOKER_TEXTBOX = "input[aria-label^='To Booker']";
    private static final String CUSTOMER_NAME_TEXTBOX = "input[aria-label='Customer Name']";
    private static final String CUSTOM_DATA_FIELD_LABEL = "#ControlR1T1";
    private static final String CUSTOM_DATA_FIELD_LABEL_2 = "#ControlR1T29";
    private static final String FAX_COMMUNICATION_CHECKBOX = "#ctl00_cphMainContent_chkForceEmail";
    private static final String TOTAL_PRICE_LABEL = "text=Total:";
    private static final String TOTAL_PRICE_VALUE = "#e227";
    private static final String FINISH_BUTTON = "#ctl00_cphMainContent_FinishButton";
    private static final String BOOKING_REF_TITLE= "#bookingRefTitle";
    private static final String SELECT_CSP_FOP= "//div[text()='Conferma Settlement Platform']";
    private static final String SELECT_AGENCY_FOP= "//div[text()='Guarantee to Agency IATA Number']";
    private static final String SELECT_CC_FOP= "//div[text()='test']";
    private static final String SELECT_PAYMENT_METHOD="//div[text()='TestUser']";
    private static final String BOOKING_FAILED_MESSAGE="//div[@class='alert alert-danger alert-dismissible']";
    private static final String VIEW_BOOKINGS_TAB = "a:has-text('View bookings')";
    private static final String HOTEL_CONFIRMATION_NUMBER = "#ctl00_cphMainContent_HotelConfirmationNumberTxt";
    private static final String CONFIRM_BUTTON="#ctl00_cphMainContent_confirmBtn";
    private static final String BOOKING_SUCCESS_MESSAGE="#ctl00_cphMainContent_confirmationContainer";
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



    public BookingSummaryPageObjects(BrowserFactory browserFactory) {
        super(browserFactory);
    }

    public void waitForBookingSummaryVisible() {
        isVisible(BOOKING_SUMMARY_HEADING);
    }
    private void addBookingContactDetails(){
        isVisible(CONTACT_POPUP);
        waitAndEnterText(FIRST_NAME, TestDataGenerator.FirstName);
        waitAndClick(CONTACT_FIND_BUTTON);
        if(getLocator(CONTACT_SEARCH_RESULTS_CONTAINER).count()>0){
            waitAndClick(CONTACT_SEARCH_RESULT_SELECT_LINK);
            waitForPageLoad();
            waitAndClick(CONTACT_OK_BUTTON);
        }else{
            waitAndClick(ADD_NEW_EMPLOYEE);
            selectDropdownByValue(SALUTATION,"Mr");
            logger.info("Entering First Name: " + TestDataGenerator.FirstName);
            waitAndEnterText(CONTACT_FIRST_NAME_INPUT, TestDataGenerator.FirstName);
            logger.info("Entering Last Name: " + TestDataGenerator.LastName);
            waitAndEnterText(CONTACT_LAST_NAME_INPUT, TestDataGenerator.LastName);
            logger.info("Entering PhoneNumber: " + TestDataGenerator.PhoneNumber);
            waitAndEnterText(CONTACT_PHONE_INPUT, TestDataGenerator.PhoneNumber);
            logger.info("Entering Email: " + TestDataGenerator.Email);
            waitAndEnterText(CONTACT_EMAIL_INPUT, TestDataGenerator.Email);
            selectDropdownByValue(SELECT_OFFICE_DROPDOWN, "Test QA Client Sabre office");
            waitAndClick(ADD_EMPY_CONBUTTON);
            waitForPageLoad();
            waitAndClick(CONTACT_OK_BUTTON);
        }
    }


    public void selectContactFromPopup() {
        waitAndClick(SELECT_CONTACT_LINK);
        addBookingContactDetails();
        waitForPageLoad();
    }
    public void selectFullRateInformation(){
        isVisible(FULL_RATE_INFO_LINK);
        waitAndClick(FULL_RATE_INFO_LINK);
        waitForPageLoad();
    }

    public void selectTravellerDetails(){
        boolean flag=false;
        try{
            waitAndClick(SELECT_TRAVELLER_LINK);
            Thread.sleep(5000);
            flag=true;
            int noOfGeust=Integer.parseInt(TestDataGenerator.getNoOfGuests());
            if(noOfGeust>1){
                for(int i=0;i<noOfGeust;i++){
                    Thread.sleep(1000);
                    waitAndClick(ADD_TBA_TRAVELLER);
                    Thread.sleep(1000);
                }
            }else if(noOfGeust==1 && Integer.parseInt(TestDataGenerator.getNoOfRooms())>1){
                for(int i=0;i<Integer.parseInt(TestDataGenerator.getNoOfRooms());i++){
                    Thread.sleep(1000);
                    waitAndClick(ADD_TBA_TRAVELLER);
                    Thread.sleep(1000);
                }
            }else{
                Thread.sleep(1000);
                waitAndClick(BOOKING_CONTACT_AS_TRAVELLER);
                Thread.sleep(1000);
            }
            waitAndClick(TRAVELLER_OK_BUTTON);
        } catch (Exception e) {
            Assert.assertTrue(flag,"Failed to select traveller details: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void addNewPaymentCardDetails(){
        String nameOnCard ="TestUser";
        String cardIssuer = EnvPropertyLoader.getProperty("CardIssuer");
        String cardNumber = EnvPropertyLoader.getProperty("CardNumber");
        String expiryMonth = EnvPropertyLoader.getProperty("ExpiryMonth");
        String expiryYear = EnvPropertyLoader.getProperty("ExpiryYear");
        waitAndClick(PAYMENT_CARD_LINK);
        waitAndEnterText(NAME_ON_CARD, nameOnCard);
        selectDropdownByValue(CARD_ISSUER,cardIssuer);
        waitAndEnterText(CARD_NUMBER, cardNumber);
        selectDropdownByValue(EXPIRY_MONTH,expiryMonth);
        selectDropdownByValue(EXPIRY_YEAR, expiryYear);
        waitAndClick(ADD_CARD_BUTTON);
    }
    public void selectPaymentMethod(String FOP){
        waitForPageLoad();
        if(FOP.equalsIgnoreCase("CSP")){
            waitAndClick(SELECT_CSP_FOP);
        } else if(FOP.equalsIgnoreCase("Agency")){
            waitAndClick(SELECT_AGENCY_FOP);
        } else if(FOP.equalsIgnoreCase("Credit Card")){
            addNewPaymentCardDetails();
            waitAndClick(SELECT_PAYMENT_METHOD);
        }
    }

    public void waitAndClickOnFinishButton(){
        isVisible(FINISH_BUTTON);
        waitAndClick(FINISH_BUTTON);
        try{
            isVisible(BOOKING_FAILED_MESSAGE);
            if(getLocator(BOOKING_FAILED_MESSAGE).count()>0){
                String failedMessage = getLocator(BOOKING_FAILED_MESSAGE).textContent();
                if(failedMessage.contains("This booking failed to book with the third party")){
                    RetrieveBookingPageObjects retrieveBookingPageObjects = new RetrieveBookingPageObjects(getBrowserManager());
                    String bookingRefNumber = getText("#bookingRefTitle").trim();
                    TestDataGenerator.setBookingReferenceNumber(bookingRefNumber.split(" ")[1]);
                    retrieveBookingPageObjects.enterBookingReference(bookingRefNumber.split(" ")[1].trim());
                    waitAndEnterText(HOTEL_CONFIRMATION_NUMBER,bookingRefNumber);
                    waitAndClick(CONFIRM_BUTTON);
                    String SuccessMessage=getLocator(BOOKING_SUCCESS_MESSAGE).textContent();
                }
            }
        } catch (Exception e) {
            // Ignore
        }
    }
    public void waitAndClickOnHomepageLogo(){
        waitAndClick("#ctl00_ucNavigation_navLogo");
    }
    public void CustomDataFields(){
        waitForPageLoad();
        if(getLocator(CUSTOM_DATA_FIELD_LABEL).count()>0){
            waitAndEnterText(CUSTOM_DATA_FIELD_LABEL, TestDataGenerator.FirstName);
            if(getLocator(CUSTOM_DATA_FIELD_LABEL_2).count()>0){
                waitAndEnterText(CUSTOM_DATA_FIELD_LABEL_2, TestDataGenerator.FirstName);
            }
        }
    }
    public String getBookingReferenceNumber(){
        waitForPageLoad();
        isVisible(BOOKING_REF_TITLE);
        String bookingRefNumber = getText("#bookingRefTitle").trim();
        TestDataGenerator.setBookingReferenceNumber(bookingRefNumber.split(" ")[1]);
        return bookingRefNumber.split(" ")[1].trim();
    }
    public void waitAndClickOneFaxCommunicationCheckbox(){
        waitForPageLoad();
        if(getLocator(FAX_COMMUNICATION_CHECKBOX).count()>0){
            if(!isChecked(FAX_COMMUNICATION_CHECKBOX)){
                Check(FAX_COMMUNICATION_CHECKBOX);
            }
        }
    }
    public void clickOnFinishButton() {
        waitAndClickOnFinishButton();
    }
    public void clickOnHomeButton() {
        waitAndClickOnHomepageLogo();
    }
    public void clickOneFaxCommunicationCheckbox(){
        waitAndClickOneFaxCommunicationCheckbox();
    }

}
