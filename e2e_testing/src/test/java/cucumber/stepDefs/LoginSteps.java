package cucumber.stepDefs;

import cucumber.util.Context;
import cucumber.webHelpers.LoginActions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class LoginSteps {

    private final ChromeDriver chromeDriver = Context.get(Context.KEY_CHROME_DRIVER, ChromeDriver.class);
    private final LoginActions loginActions = new LoginActions(chromeDriver);

    @Given("I create account")
    public void iCreateAccount() {
        String username = randomUUID().toString();
        String password = randomUUID().toString();

        Context.set(Context.KEY_USERNAME, username);
        Context.set(Context.KEY_PASSWORD, password);

        loginActions.clickOnButtonByLinkText("Sign up");
        loginActions.populateField("formBasicEmail", username);
        loginActions.populateField("formBasicPassword", password);
        loginActions.clickOnButtonByPath("//button[@type='submit']");

        log.info("Test account created and logged in");
    }


    @And("I am logged in")
    public void iAmLoggedIn() {
        loginActions.assertLogoutButtonVisible();
    }

    @Given("I have an account")
    public void iHaveAnAccount() {
        iCreateAccount();
    }

    @Given("I navigate to the login page")
    public void iNavigateToTheLoginPage() {
        boolean onLogin = loginActions.assertOnPage("/login");

        if (!onLogin) {
            if (loginActions.assertOnPage("/signup")) {
                loginActions.clickOnButtonByLinkText("Sign up");
            } else {
                loginActions.assertLogoutButtonVisible();
                loginActions.logout();
            }
        }

        assertTrue(loginActions.assertOnPage("/login"));
    }

    @When("I login")
    public void iLogin() {
        loginActions.populateField("formUsername", Context.get(Context.KEY_USERNAME).toString());
        loginActions.populateField("formPassword", Context.get(Context.KEY_PASSWORD).toString());
        loginActions.clickOnButtonByPath("//button[@type='submit']");
    }
}
