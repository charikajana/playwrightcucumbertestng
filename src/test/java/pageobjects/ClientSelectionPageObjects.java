package pageobjects;


import org.sabre.Browserfactory.BrowserFactory;
import org.sabre.basefactory.PlaywrightActions;

public class ClientSelectionPageObjects extends PlaywrightActions {

    private final String SELECT_CLIENT_DROPDOWN = "#ctl00_ClientDropdownlist";
    public ClientSelectionPageObjects(BrowserFactory browserFactory) {
        super(browserFactory);
    }

    public void selectClient(String clientName) {
       selectDropdownByValue(SELECT_CLIENT_DROPDOWN,clientName);
    }
}
