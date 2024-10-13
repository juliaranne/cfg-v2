package cucumber.stepDefs;

import cucumber.util.Context;
import cucumber.webHelpers.WebActions;
import io.cucumber.java.en.Then;

public class WeeklyJournalSteps {
    private WebActions webActions = Context.get(Context.KEY_WEB_ACTIONS, WebActions.class);

    @Then("I can see the workout type and duration in the journal")
    public void iCanSeeTheWorkoutTypeAndDurationInTheJournal() {
        String duration = Context.get(Context.KEY_EXERCISE_DURATION).toString();
        String exerciseType = Context.get(Context.KEY_EXERCISE_TYPE).toString();

        String expectedRecord = String.format(Context.WEEKLY_JOURNAL_RECORD_TEXT, exerciseType, duration);
        webActions.assertExerciseRecordIsPresent(expectedRecord);
    }
}
