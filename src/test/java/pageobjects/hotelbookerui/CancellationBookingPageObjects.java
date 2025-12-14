package pageobjects.hotelbookerui;


import org.sabre.Browserfactory.BrowserFactory;
import org.sabre.basefactory.PlaywrightActions;
import org.testng.Assert;
import pageobjects.commons.TestDataGenerator;
import org.sabre.util.ThreadLocalManager;

import java.util.logging.Logger;


public class CancellationBookingPageObjects extends PlaywrightActions {
    private static final Logger logger = Logger.getLogger(CancellationBookingPageObjects.class.getName());
    //CANCEL BOOKING
    private static final String CANCEL_BUTTON="#ctl00_cphMainContent_globalBookingCancelBtn";
    private static final String RESEND_CONFIRMATION_BUTTON="#ctl00_cphMainContent_resendFaxesBtn";
    private static final String CANCEL_ROOM_BUTTON="#ctl00_cphMainContent_ctl26_cancelLnkBtn";
    private static final String AMEND_ROOM_BUTTON="#ctl00_cphMainContent_ctl26_amendLnkBtn";
    private static final String CONFIRM_CANCEL_BUTTON="#ctl00_cphMainContent_globalBookingContinueBtn";
    private static final String CANCEL_CONFIRMATION_MESSAGE="#ctl00_cphMainContent_confirmationContainer";
    private static final String CANCEL_TEXTBOX="#ctl00_cphMainContent_globalManualCancellationTxt";
    private static final String CONTINUE_BUTTON="#ctl00_cphMainContent_globalBookingContinueBtn";
    private static final String BOOKING_STATUS="#BookingStatus";
    private static final String LOGIN_BUTTON = "#ctl00_cphMainContent_btnLogin";
    private static final String USER_MENU_BUTTON_SELECTOR = "#lstUserMenu";
    private static final String LOGOUT_LINK_SELECTOR = "a:has-text('Log Out')";


    public CancellationBookingPageObjects(BrowserFactory browserFactory) {
        super(browserFactory);
    }

    // ============= CANCEL BOOKING METHODS =============

    /**
     * Click the global cancel booking button
     */
    public void clickCancelButton() {
        waitAndClick(CANCEL_BUTTON);
        if(getLocator(CANCEL_TEXTBOX).count()>0){
            waitAndEnterText(CANCEL_TEXTBOX, TestDataGenerator.getBookingReferenceNumber());
            waitAndClick(CONTINUE_BUTTON);
        }else{
            waitAndClick(CONFIRM_CANCEL_BUTTON);
        }

    }
    public void booking_cancellation_should_be_completed(){
        waitForPageLoad();
        String cancelConfirmationMessage = getText(CANCEL_CONFIRMATION_MESSAGE).trim();
        Assert.assertTrue(cancelConfirmationMessage.contains("Your cancellation request was successful"));
    }
    public void booking_status_should_be_displayed_as_cancelled(){
        waitForPageLoad();
        String bookingStatus = getText(BOOKING_STATUS).trim();
        Assert.assertTrue(bookingStatus.contains("Accommodation Cancellation"));
    }

    /**
     * Click the resend confirmation button
     */
    public void clickResendConfirmationButton() {
        waitAndClick(RESEND_CONFIRMATION_BUTTON);
    }

    /**
     * Click the cancel room button
     */
    public void clickCancelRoomButton() {
        waitAndClick(CANCEL_ROOM_BUTTON);
    }

    /**
     * Click the amend room button
     */
    public void clickAmendRoomButton() {
        waitAndClick(AMEND_ROOM_BUTTON);
    }

    public void clickUserMenu() {
        waitAndClick(USER_MENU_BUTTON_SELECTOR);
    }
    public void clickLogoutButton() {
        waitForPageLoad();
        waitAndClick(LOGOUT_LINK_SELECTOR);
    }
}
