package cucumber.stepDefs;

import cucumber.util.Context;
import cucumber.webHelpers.StatisticsActions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chrome.ChromeDriver;

@Slf4j
public class StatisticsSteps {

    private final ChromeDriver chromeDriver = Context.get(Context.KEY_CHROME_DRIVER, ChromeDriver.class);
    private final StatisticsActions statisticsActions = new StatisticsActions(chromeDriver);

    @Then("I see \"Well done, <username>! This is your overall effort:\"")
    public void iSeeWellDoneUsernameThisIsYourOverallEffort() {
        statisticsActions.assertWellDoneHeaderPresent(Context.get(Context.KEY_USERNAME).toString());
    }

    @And("there is no data available")
    public void thereIsNoDataAvailable() {
        statisticsActions.assertNoDataAvailableMessagePresent();
    }
}
