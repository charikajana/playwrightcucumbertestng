package org.sabre.basefactory;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.sabre.Browserfactory.BrowserFactory;
import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;

public class PlaywrightActions {
    private final BrowserFactory browserFactory;

    public PlaywrightActions(BrowserFactory browserFactory) {
        this.browserFactory = browserFactory;
    }

    //In Java, "protected" means that the member can be accessed by classes in the same package or subclasses.
    protected BrowserFactory getBrowserManager() {
        return browserFactory;
    }

    public void navigate(String url) {
        browserFactory.getPage().navigate(url);
    }

    public Locator Locator(String locator) {
        return browserFactory.getPage().locator(locator);
    }

    public void waitAndClickByRole(String role, String name) {
        Locator element = browserFactory.getPage().getByRole(AriaRole.valueOf(role.toUpperCase()), new Page.GetByRoleOptions().setName(name));
        element.click();
    }

    public void waitAndClickBySelector(String selector) {
        browserFactory.getPage().waitForSelector(selector, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
        browserFactory.getPage().click(selector);
    }

    public void waitAndClick(String locator)
    {
        Locator(locator).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        Locator(locator).click();
    }
    public void waitAndEnterText(String locator, String text) {
        Locator(locator).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        Locator(locator).fill(text);
    }

    public void switchToNewWindowAfterClick(String Locator) {
        Page newPage = browserFactory.getContext().waitForPage(() -> {
            browserFactory.getPage().click(Locator);
        });
        browserFactory.setPage(newPage);
        browserFactory.getPage().bringToFront();
    }

    public void selectDropdownByValue(String locator, String value) {
        browserFactory.getPage().selectOption(locator, new SelectOption().setLabel(value));
    }

    public void waitAndUncheckCheckbox(String locator) {
        Locator(locator).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        Locator(locator).setChecked(false);
    }

    public void waitAndCheckCheckbox(String locator) {
        Locator(locator).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        Locator(locator).setChecked(true);
    }

    public void closeCurrentPageAndSwitch(){
        Page currentPage = browserFactory.getPage();
        currentPage.close();
        Page firstPage = browserFactory.getContext().pages().get(0);
        browserFactory.setPage(firstPage);
        firstPage.bringToFront();
    }
    public void takeScreenshot(String fileName) {
        browserFactory.getPage().screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get("screenshots/" + fileName + ".png")));
    }

    public void getScreenshot() {
        byte[] screenshot = browserFactory.getPage().screenshot(new Page.ScreenshotOptions().setFullPage(true));
        Allure.addAttachment("Screenshot", new ByteArrayInputStream(screenshot));
    }
    public String getContent() {
        return browserFactory.getPage().content();
    }

}
