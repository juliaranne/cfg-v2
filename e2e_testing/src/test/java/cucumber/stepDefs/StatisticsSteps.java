package cucumber.stepDefs;

import cucumber.util.Context;
import cucumber.webHelpers.StatisticsActions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.Map;

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

    @And("the workout data is visible in the format:")
    public void theWorkoutDataIsVisibleInTheFormat(DataTable dataTable) {
        Map<String, String> singleExpectedWorkoutData = dataTable.asMaps(String.class, String.class).getFirst();

        String expectedHeader = singleExpectedWorkoutData.get("header").replace("<ExerciseType>", Context.get(Context.KEY_EXERCISE_TYPE).toString());
        System.out.println(expectedHeader);

        String expectedContents = singleExpectedWorkoutData.get("contents").replace("<X>", Context.get(Context.KEY_EXERCISE_DURATION).toString());

        statisticsActions.assertCorrectSingleExercisePresent(expectedHeader, expectedContents);
    }
}
