package cucumber.stepDefs;

import cucumber.config.WebDriverManager;
import cucumber.util.Context;
import cucumber.webHelpers.WebActions;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;

public class SeleniumSteps {

    @Given("a web connection is established")
    public void aWebConnectionIsEstablished() {
        WebDriver chromeDriver = WebDriverManager.getDriver();
        WebActions webActions = new WebActions(chromeDriver);
        Context.set(Context.KEY_WEB_ACTIONS, webActions);
        Context.set(Context.KEY_CHROME_DRIVER, chromeDriver);
    }
}
