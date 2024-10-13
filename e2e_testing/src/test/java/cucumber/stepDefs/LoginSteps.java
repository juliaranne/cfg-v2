package cucumber.stepDefs;

import cucumber.util.Context;
import cucumber.webHelpers.WebActions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class LoginSteps {

    @Given("I create account")
    public void iCreateAccount() {
        WebActions webActions = Context.get(Context.KEY_WEB_ACTIONS, WebActions.class); // maye have as global for the class, and ensure to cleardown in hooks


        String username1 = UUID.randomUUID().toString();
        String password1 = UUID.randomUUID().toString();

        webActions.clickOnButtonByLinkText("Sign up");
        webActions.populateField("formBasicEmail", username1); // highlight this to team - wording is different
        webActions.populateField("formBasicPassword", password1);
        webActions.clickOnButtonByPath("//button[@type='submit']");
    }


    @And("I am logged in")
    public void iAmLoggedIn() {
        WebActions webActions = Context.get(Context.KEY_WEB_ACTIONS, WebActions.class);
        webActions.assertLogoutButtonVisible();
    }
}
