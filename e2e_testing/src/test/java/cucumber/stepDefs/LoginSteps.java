package cucumber.stepDefs;

import cucumber.config.WebDriverManager;
import cucumber.webHelpers.WebActions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.time.Duration;
import java.util.UUID;

@Slf4j
public class LoginSteps {

    private WebDriver chromeDriver = WebDriverManager.getDriver(); // Get the shared WebDriver

    private String baseUrl = "http://localhost";

    private WebActions webActions = new WebActions(chromeDriver);

    @Given("I have an account for {string} with password {string}")
    public void iHaveAnAccountForWithPassword(String username, String password) { // remove the fields
        // have to either:
        // try logging in and see if it lets, and if not signup
        // or signup and see what happens
        // or create a new account
        chromeDriver.get(baseUrl);
        webActions.clickOnButtonToRedirect("Sign up");
        webActions.populateField("formUsername", UUID.randomUUID().toString());
        webActions.populateField("formPassword", UUID.randomUUID().toString());

    }

    @And("I login to the app using the username {string} and password {string}")
    public void iLoginToTheAppUsingTheUsernameAndPassword(String arg0, String arg1) {
//        WebDriverWait wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10)); // change to awaitility perhaps
//
//        // Locate the username field by controlId and enter the username
//        WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("formUsername")));
//        usernameField.sendKeys("testUsername");  // Replace with actual test username
//
//        // Locate the password field by controlId and enter the password
//        WebElement passwordField = chromeDriver.findElement(By.id("formPassword"));
//        passwordField.sendKeys("testPassword");  // Replace with actual test password
//
//        // Click the login button
//        WebElement loginButton = chromeDriver.findElement(By.xpath("//button[@type='submit']"));
//        loginButton.click();
//        Todo in next PR: implementation
    }

}
