package pageobjects.hotelbookerui;


import org.sabre.Browserfactory.BrowserFactory;
import org.sabre.basefactory.PlaywrightActions;
import org.sabre.util.ThreadLocalManager;

import java.util.logging.Logger;

/**
 * ClientSelectionPageObjects - Focused Page Object for Client Selection Modal
 * Contains only client selection specific functionality
 */
public class ClientSelectionPageObjects extends PlaywrightActions {
    private static final Logger logger = Logger.getLogger(ClientSelectionPageObjects.class.getName());
    
    // Client Selection Modal
    // Use the visible header as anchor for the modal
    private static final String CLIENT_MODAL = "div.modal-dialog";
    private static final String CLIENT_MODAL_HEADER = "h4";
    private static final String MODAL_CLOSE_BUTTON = "button.close, button[aria-label='Close']";
    private static final String HEADER_CLIENT = "#lnkClientSelect";
    // Client Filter
    private static final String CLIENT_FILTER_INPUT = "input[placeholder*='Filter Client List']";
    private static final String CLIENT_GROUPS_BUTTON = "button:has-text('Client Groups')";
    private static final String ACTION_LIST_BUTTON = "button:has-text('Action List')";
    private static final String ALL_CLIENTS_BUTTON = "button:has-text('All Clients')";
    // Available Clients (use h2 for headings)
    private static final String CLIENT_HEADING = "h2";

    public ClientSelectionPageObjects(BrowserFactory browserFactory) {
        super(browserFactory);
    }

    /**
     * Select client by name
     */
    public void selectClient(String clientName) {
        waitForPageLoad();
        logger.info("Selecting client: " + clientName);
        for (com.microsoft.playwright.Locator locator : getLocator(CLIENT_HEADING).all()) {
            String text = locator.textContent().trim();
            if (text.equalsIgnoreCase(clientName) || text.replaceAll("\\s+", "").equalsIgnoreCase(clientName.replaceAll("\\s+", ""))) {
                locator.click();
                logger.info("Client selected: " + clientName);
                return;
            }
        }
        throw new RuntimeException("Client not found: " + clientName);
    }

    public boolean isClientDisplayedOnHeader() {
        return isVisible(HEADER_CLIENT);
    }

    /**
     * Filter client list using search
     */
    public void filterClients(String filterText) {
        waitAndEnterText(CLIENT_FILTER_INPUT, filterText);
        // Optionally, wait for results to update if needed
    }

    /**
     * Click on client groups filter
     */
    public void clickClientGroups() {
        waitAndClick(CLIENT_GROUPS_BUTTON);
    }

    /**
     * Click on action list filter
     */
    public void clickActionList() {
        waitAndClick(ACTION_LIST_BUTTON);
    }

    /**
     * Click on all clients filter
     */
    public void clickAllClients() {
        waitAndClick(ALL_CLIENTS_BUTTON);
    }

    /**
     * Close client selection modal
     */
    public void closeModal() {
        waitAndClick(MODAL_CLOSE_BUTTON);
    }

    /**
     * Check if client modal is visible
     */
    public boolean isClientModalVisible() {
        return isVisible(CLIENT_MODAL);
    }

    /**
     * Check if specific client is available
     */
    public boolean isClientAvailable(String clientName) {
        for (com.microsoft.playwright.Locator locator : getLocator(CLIENT_HEADING).all()) {
            String text = locator.textContent().trim();
            if (text.equalsIgnoreCase(clientName) || text.replaceAll("\\s+", "").equalsIgnoreCase(clientName.replaceAll("\\s+", ""))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get all available client names
     */
    public String[] getAvailableClients() {
        return getLocator(CLIENT_HEADING).allTextContents().toArray(new String[0]);
    }
}