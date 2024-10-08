package cucumber.stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.junit.jupiter.api.Assertions;

public class LoginSteps {


    @Given("I have an account for {string} with password {string}")
    public void iHaveAnAccountForWithPassword(String username, String password) {
        System.out.println("hi");
        Assertions.assertEquals(1,1);
    }

    @And("I login to the app using the username {string} and password {string}")
    public void iLoginToTheAppUsingTheUsernameAndPassword(String arg0, String arg1) {
        System.out.println("hi");
        Assertions.assertEquals(1,1);
    }
}
