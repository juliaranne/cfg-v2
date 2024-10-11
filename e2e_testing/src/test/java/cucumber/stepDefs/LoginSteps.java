package cucumber.stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class LoginSteps {
    @Autowired
    @Qualifier("chromeDriver")
    private WebDriver chromeDriver;

    @Given("I have an account for {string} with password {string}")
    public void iHaveAnAccountForWithPassword(String username, String password) {

        chromeDriver.get("https://www.google.com");

        Assertions.assertEquals(1, 1);
//        Todo in next PR: implementation
    }

    @And("I login to the app using the username {string} and password {string}")
    public void iLoginToTheAppUsingTheUsernameAndPassword(String arg0, String arg1) {
        Assertions.assertEquals(1, 1);
//        Todo in next PR: implementation
    }
}
