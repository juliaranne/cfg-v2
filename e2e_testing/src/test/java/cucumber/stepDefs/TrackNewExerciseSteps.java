package cucumber.stepDefs;

import cucumber.util.Context;
import cucumber.webHelpers.WebActions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class TrackNewExerciseSteps {
    private WebActions webActions = Context.get(Context.KEY_WEB_ACTIONS, WebActions.class);

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

    @And("I am on the track exercise page")
    public void iAmOnTheTrackExercisePage() {
        webActions.assertHeaderPresent("h3", "Track exercise");
    }
}
