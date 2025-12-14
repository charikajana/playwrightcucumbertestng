package pageobjects.agencyadmin;


import org.sabre.Browserfactory.BrowserFactory;
import org.sabre.applicationConstants.ApplicationConstant;
import org.sabre.basefactory.PlaywrightActions;
import org.sabre.util.EnvPropertyLoader;
import org.sabre.util.ThreadLocalManager;

public class TravelPolicyPageObjects extends PlaywrightActions {


    private static final String USERNAME_FIELD = "#txtUserName";
    private static final String PASSWORD_FIELD = "#txtPassword";
    private static final String LOGIN_BUTTON = "#btnLogin";
    private static final String TOOLS_BUTTON = "#ctl00_ucNavigation_liTools";
    private static final String AGENCY_ADMIN_LINK = "//a[text()='Agency Admin']";
    private static final String TRAVEL_POLICY_LINK = "#ctl00_travelPolicyNav_TravelPolicyNavigationTitle";
    private static final String POLICY_ACTIVE_CHECKBOX = "#ctl00_cphMainContent_policyActiveChk";
    private static final String UPDATE_POLICY_BUTTON = "#ctl00_cphMainContent_btnUpdate";
    private static final String SELECT_CLIENT_DROPDOWN = "#ctl00_ClientDropdownlist";
    private static final String UPDATE_SUCCESSFUL_MESSAGE = "#ctl00_cphMainContent_panelError";

    public TravelPolicyPageObjects(BrowserFactory browserFactory) {
        super(browserFactory);
    }


    /**
     * Click Tools button in navigation menu
     */
    public void clickToolsButton() {
        getLocator(TOOLS_BUTTON).click();
    }

    /**
     * Click Agency Admin link from Tools dropdown
     */
    public void clickAgencyAdminLink() {
        switchToNewWindowAfterClick(AGENCY_ADMIN_LINK);
        waitForPageLoad();
        if(isVisible(USERNAME_FIELD)){
            enterUserName();
            enterPassword();
            clickLoginButton();
        }
    }

    public void closeAgencyAdminWindow(){
        closeCurrentWindow();
        switchToOldWindow();
    }
    public String getPageTitle(){
        return getBrowserManager().getPage().title();
    }



    /**
     * Click Travel Policy link in Agency Admin menu
     */
    public void clickTravelPolicyLink() {
        getLocator(TRAVEL_POLICY_LINK).click();
        waitForLoadState();
        String ClientDropdown = EnvPropertyLoader.getProperty("SELECT_CLIENT_DROPDOWN_IN_ADMIN");
        selectDropdownByValue(SELECT_CLIENT_DROPDOWN,ClientDropdown);
    }

    /**
     * Uncheck Policy Active checkbox
     */
    public void uncheckPolicyActiveCheckbox() {
        if (isChecked(POLICY_ACTIVE_CHECKBOX)) {
            getLocator(POLICY_ACTIVE_CHECKBOX).click();
        }
    }

    /**
     * Click Update Policy button
     */
    public void clickUpdatePolicyButton() {
        getLocator(UPDATE_POLICY_BUTTON).click();
        waitForLoadState();
    }

    /**
     * Check if Update Successful message is displayed
     * @return true if message is displayed, false otherwise
     */
    public boolean isUpdateSuccessfulMessageDisplayed() {
        return getLocator(UPDATE_SUCCESSFUL_MESSAGE).isVisible();
    }


    public void enterUserName() {
        String UserName = EnvPropertyLoader.getProperty(ApplicationConstant.USER_NAME);
        waitAndEnterText(USERNAME_FIELD, UserName);
    }

    public void enterPassword() {
        String Password = EnvPropertyLoader.getProperty(ApplicationConstant.PASSWORD);
        waitAndEnterText(PASSWORD_FIELD, Password);
    }
    public void clickLoginButton() {
        waitAndClick(LOGIN_BUTTON);

    }
}
