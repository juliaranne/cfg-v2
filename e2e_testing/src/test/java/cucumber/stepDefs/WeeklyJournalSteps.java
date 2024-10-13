package cucumber.stepDefs;

import cucumber.util.Context;
import cucumber.webHelpers.WeeklyJournalActions;
import io.cucumber.java.en.Then;
import org.openqa.selenium.chrome.ChromeDriver;

public class WeeklyJournalSteps {

    private final ChromeDriver chromeDriver = Context.get(Context.KEY_CHROME_DRIVER, ChromeDriver.class);
    private final WeeklyJournalActions weeklyJournalActions = new WeeklyJournalActions(chromeDriver);

    @Then("I can see the workout type and duration in the journal")
    public void iCanSeeTheWorkoutTypeAndDurationInTheJournal() {
        String duration = Context.get(Context.KEY_EXERCISE_DURATION).toString();
        String exerciseType = Context.get(Context.KEY_EXERCISE_TYPE).toString();

        String expectedRecord = String.format(Context.WEEKLY_JOURNAL_RECORD_TEXT, exerciseType, duration);
        weeklyJournalActions.assertExerciseRecordIsPresent(expectedRecord);
    }
}
