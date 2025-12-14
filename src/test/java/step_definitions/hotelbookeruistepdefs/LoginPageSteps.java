package step_definitions.hotelbookeruistepdefs;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pageobjects.hotelbookerui.LoginPageObjects;

public class LoginPageSteps {
    
    public LoginPageObjects loginPageObjects;

    public LoginPageSteps(LoginPageObjects loginPageObjects) {
        this.loginPageObjects = loginPageObjects;
    }

    @Given("Open Browser and Navigate to HotelBooker")
    public void user_is_on_login_page() {
        loginPageObjects.navigateToLoginPage();
    }

    @When("user enters username and password")
    public void user_enters_username_and_password() {
        loginPageObjects.enterUserName();
        loginPageObjects.enterPassword();
    }

    @When("user clicks login button")
    public void user_clicks_login_button() {
        loginPageObjects.clickLoginButton();
    }

}