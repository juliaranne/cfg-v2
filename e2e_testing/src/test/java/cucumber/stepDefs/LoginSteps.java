package cucumber.stepDefs;

import cucumber.config.WebDriverManager;
import cucumber.util.Context;
import cucumber.webHelpers.WebActions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import java.util.UUID;

import static cucumber.util.Context.KEY_PASSWORD;
import static cucumber.util.Context.KEY_USERNAME;

@Slf4j
public class LoginSteps { // maybe change the name if having all steps within

    private WebDriver chromeDriver = WebDriverManager.getDriver(); // Get the shared WebDriver

    private String baseUrl = "http://localhost";

    private WebActions webActions = new WebActions(chromeDriver);

    // maye change the below to be like 'i sign up and i am logged in with a random username and password' or something simiarl
// todo sort out the timeouts
    @Given("I have an account for {string} with password {string}")
    public void iHaveAnAccountForWithPassword(String username, String password) { // remove the fields
        String username1 = UUID.randomUUID().toString();
        String password1 = UUID.randomUUID().toString();

        Context.set(KEY_USERNAME, username1);
        Context.set(KEY_PASSWORD, password1);

        chromeDriver.get(baseUrl);
        webActions.clickOnButtonByLinkText("Sign up");
        webActions.populateField("formBasicEmail", username1); // highlight this to team
        webActions.populateField("formBasicPassword", password1); // double check as this seems to be double populating
        webActions.clickOnButtonByPath("//button[@type='submit']");
        webActions.assertHeader("h3", "Track exercise");

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

    @Given("^I have entered a completed workout$")
    public void iHaveEnteredACompletedWorkout() {
        String exerciseType = "Running";
        String exerciseDuration = "90";
        Context.set(Context.KEY_EXERCISE_TYPE, exerciseType);
        Context.set(Context.KEY_EXERCISE_DURATION, exerciseDuration);

    webActions.clickOnRunningButton(); // SOMEHOW USE THE EXERCISEtYPE FROM ABOVE
    webActions.populateField("description", "This is my exercise description");
    webActions.populateField("duration", exerciseDuration);
    webActions.clickSubmit();
    webActions.assertMessageAppears("Activity logged successfully! Well done!");
    }

    @When("I go to the {string} page")
    public void iGoToThePage(String page) {
        webActions.clickOnButtonByLinkText(page);
    }

    @Then("I can see the workout type and duration in the journal")
    public void iCanSeeTheWorkoutTypeAndDurationInTheJournal() {
       String duration = Context.get(Context.KEY_EXERCISE_DURATION).toString();
       String exerciseType = Context.get(Context.KEY_EXERCISE_TYPE).toString();

       String expectedRecord = String.format(Context.WEEKLY_JOURNAL_RECORD_TEXT, exerciseType, duration);
        webActions.assertExerciseRecordIsPresent(expectedRecord);
    }
}
