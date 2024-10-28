package cucumber.stepDefs;

import cucumber.util.Context;
import cucumber.webHelpers.LoginActions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.util.UUID.randomUUID;

@Slf4j
public class LoginSteps {

    private final ChromeDriver chromeDriver = Context.get(Context.KEY_CHROME_DRIVER, ChromeDriver.class);
    private final LoginActions loginActions = new LoginActions(chromeDriver);

    @Given("I create account")
    public void iCreateAccount() {
        String username1 = randomUUID().toString();
        String password1 = randomUUID().toString();

        Context.set(Context.KEY_USERNAME, username1);

        loginActions.clickOnButtonByLinkText("Sign up");
        loginActions.populateField("formBasicEmail", username1);
        loginActions.populateField("formBasicPassword", password1);
        loginActions.clickOnButtonByPath("//button[@type='submit']");
    }


    @And("I am logged in")
    public void iAmLoggedIn() {
        loginActions.assertLogoutButtonVisible();
    }
}
