package pageobjects;


import org.sabre.Browserfactory.BrowserFactory;
import org.sabre.applicationConstants.ApplicationConstant;
import org.sabre.basefactory.PlaywrightActions;
import org.sabre.util.EnvPropertyLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

/**
 * LoginPageObjects - Simple Page Object for HotelBooker Login Page
 */
public class LoginPageObjects extends PlaywrightActions {
    private static final Logger logger = Logger.getLogger(LoginPageObjects.class.getName());

    private  final String USERNAME_FIELD = "#ctl00_cphMainContent_txtUserName";
    private  final String PASSWORD_FIELD = "#ctl00_cphMainContent_txtPassword";
    private  final String LOGIN_BUTTON = "#ctl00_cphMainContent_btnLogin";
    private  final String USER_MENU_BUTTON_SELECTOR = "#lstUserMenu";
    private  final String LOGOUT_LINK_SELECTOR = "a:has-text('Log Out')";

    public LoginPageObjects(BrowserFactory browserFactory) {
        super(browserFactory);
    }

    public void navigateToLoginPage() {
        navigate(EnvPropertyLoader.getProperty(ApplicationConstant.URL));
    }

    public void enterUserName() {
        waitAndEnterText(USERNAME_FIELD, EnvPropertyLoader.getProperty(ApplicationConstant.USER_NAME));
    }

    public void enterPassword() {
        waitAndEnterText(PASSWORD_FIELD, EnvPropertyLoader.getProperty(ApplicationConstant.PASSWORD));

    }

    public void clickLoginButton() {
        waitAndClick(LOGIN_BUTTON);
        getScreenshot();
    }

    public String getLocator(String htmlpage,String Lablel) throws IOException, InterruptedException {
        String locator ="";
        try{
            ProcessBuilder pb = new ProcessBuilder("python", "gpt_locator.py", "--html", htmlpage, "--description", Lablel);
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
             locator = reader.readLine();
            process.waitFor();
        }catch(Exception e){
            logger.severe("Error in getting locator: " + e.getMessage());
        }
        return locator;

    }



}