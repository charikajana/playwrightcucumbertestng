package org.sabre.basefactory;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.sabre.Browserfactory.BrowserFactory;
import io.qameta.allure.Allure;
import org.sabre.util.ThreadLocalManager;

import java.io.ByteArrayInputStream;

public class PlaywrightActions {
    private final BrowserFactory browserFactory;

    public PlaywrightActions(BrowserFactory browserFactory) {
        this.browserFactory = browserFactory;
    }

    protected BrowserFactory getBrowserManager() {
        return browserFactory;
    }

    public void navigate(String url) {
        browserFactory.getPage().navigate(url);
    }

    public Locator getLocator(String locator) {
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
        browserFactory.getPage().waitForSelector(locator, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
        browserFactory.getPage().click(locator);
    }
    public void waitAndEnterText(String locator, String text) {
        browserFactory.getPage().waitForSelector(locator, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
        browserFactory.getPage().fill(locator, text);
    }

    public void switchToNewWindowAfterClick(String Locator) {
        Page newPage = browserFactory.getContext().waitForPage(() -> {
            browserFactory.getPage().click(Locator);
        });
        browserFactory.setPage(newPage);
        browserFactory.getPage().bringToFront();
        browserFactory.getPage().setDefaultTimeout(120000);
    }
    public void switchToOldWindow() {
        int pagesCount = browserFactory.getContext().pages().size();
        Page lastPage = browserFactory.getContext().pages().get(pagesCount - 1);
        browserFactory.setPage(lastPage);
        browserFactory.getPage().bringToFront();
        browserFactory.getPage().setDefaultTimeout(120000);
    }

    public void selectDropdownByValue(String locator, String value) {
        browserFactory.getPage().selectOption(locator, new SelectOption().setLabel(value));
    }

    public void waitAndUncheckCheckbox(String locator) {
        browserFactory.getPage().waitForSelector(locator, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
        browserFactory.getPage().setChecked(locator,false);
    }

    public void waitAndCheckCheckbox(String locator) {
        browserFactory.getPage().waitForSelector(locator, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
        browserFactory.getPage().setChecked(locator,true);
    }

    public void closeCurrentPageAndSwitch(){
        Page currentPage = browserFactory.getPage();
        currentPage.close();
        Page firstPage = browserFactory.getContext().pages().get(0);
        browserFactory.setPage(firstPage);
        firstPage.bringToFront();
    }
    public void takeScreenshot() {
        byte[] screenshot = browserFactory.getPage().screenshot(new Page.ScreenshotOptions().setFullPage(true));
        Allure.addAttachment("Screenshot", new ByteArrayInputStream(screenshot));
    }

    public void getScreenshot() {
        byte[] screenshot = browserFactory.getPage().screenshot(new Page.ScreenshotOptions().setFullPage(true));
        Allure.addAttachment("Screenshot", new ByteArrayInputStream(screenshot));
    }
    public String getContent() {
        return browserFactory.getPage().content();
    }
    public String getText(String locator) {
        return getLocator(locator).textContent();
    }

    public boolean isVisible(String locator) {
        try {
            return getLocator(locator).isVisible();
        } catch (Exception e) {
            return false;
        }
    }
     public int elementCount(String selector) {
        return browserFactory.getPage().locator(selector).count();
    }

    public boolean isChecked(String locator) {
        try {
            return getLocator(locator).isChecked();
        } catch (Exception e) {
            return false;
        }
    }
    public void Check(String locator) {
        getLocator(locator).check();
    }
    public void UnCheck(String locator) {
        getLocator(locator).uncheck();
    }

    public void waitForLoadState(){
        browserFactory.getPage().waitForLoadState();
    }
    public String getTitle() {
        return browserFactory.getPage().title();
    }
    public void reload() {
        browserFactory.getPage().reload();
    }

    public String getCurrentUrl() {
        return browserFactory.getPage().url();
    }
    public void closeCurrentWindow(){
        browserFactory.getPage().close();
    }
    public void waitForPageLoad(){
        browserFactory.getPage().waitForLoadState(LoadState.LOAD);
        browserFactory.getPage().waitForFunction("() => document.readyState === 'complete'", null,
                new Page.WaitForFunctionOptions().setTimeout(120000));
        browserFactory.getPage().waitForFunction("() => typeof jQuery === 'undefined' || jQuery.active === 0", null,
                new Page.WaitForFunctionOptions().setTimeout(120000));
    }
    public void waitForElementVisible(String selector) {
        browserFactory.getPage().waitForSelector(selector, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
    }
    /**
     * Waits until either of the two locators is visible on the page.
     * Returns the selector that became visible first, or null if timeout.
     */
    public String waitForEitherVisible(String selector1, String selector2, int timeoutMillis) {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < timeoutMillis) {
            if (isVisible(selector1)) {
                return selector1;
            }
            if (isVisible(selector2)) {
                return selector2;
            }
            try {
                Thread.sleep(200); // Polling interval
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        return null;
    }

}
