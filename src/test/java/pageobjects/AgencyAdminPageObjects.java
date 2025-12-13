package pageobjects;

import org.sabre.Browserfactory.BrowserFactory;
import org.sabre.basefactory.PlaywrightActions;

import java.util.logging.Logger;

public class AgencyAdminPageObjects extends PlaywrightActions {
    private static final Logger logger = Logger.getLogger(LoginPageObjects.class.getName());

    private final String TRAVEL_POLICY_LINK = "#ctl00_travelPolicyNav_TravelPolicyNavigationTitle";
    private final String SELECT_CLIENT_DROPDOWN = "#ctl00_ClientDropdownlist";
    private final String POLICY_ACTIVE_CHECKBOX = "#ctl00_cphMainContent_policyActiveChk";
    private final String UPDATE_POLICY_BUTTON = "#ctl00_cphMainContent_btnUpdate";

    public AgencyAdminPageObjects(BrowserFactory browserFactory) {
        super(browserFactory);
    }

    public void clickOnTravelPolicy(){
        waitAndClick(TRAVEL_POLICY_LINK);
        selectDropdownByValue(SELECT_CLIENT_DROPDOWN, "Test QA Client(Sabre)");
    }

    public void uncheckPolicyActiveCheckbox(){
        waitAndUncheckCheckbox(POLICY_ACTIVE_CHECKBOX);
    }

    public void clickOnUpdateButton(){
        waitAndClick(UPDATE_POLICY_BUTTON);
    }

    public void closeAgencyAdminPage(){
        closeCurrentPageAndSwitch();
    }




}
