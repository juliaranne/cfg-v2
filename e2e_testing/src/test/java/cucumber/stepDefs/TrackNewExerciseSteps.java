package cucumber.stepDefs;

import cucumber.util.Context;
import cucumber.webHelpers.TrackExerciseActions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.openqa.selenium.Alert;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrackNewExerciseSteps {

    private final ChromeDriver chromeDriver = Context.get(Context.KEY_CHROME_DRIVER, ChromeDriver.class);
    private final TrackExerciseActions trackExerciseActions = new TrackExerciseActions(chromeDriver);

    @Given("^I have entered a completed workout$")
    public void iHaveEnteredACompletedWorkout() {

        String exerciseType = "Running";
        String exerciseDuration = "90";
        String exerciseDescription = "This is my exercise description";
        Context.set(Context.KEY_EXERCISE_DESCRIPTION, exerciseDescription);
        Context.set(Context.KEY_EXERCISE_TYPE, exerciseType);
        Context.set(Context.KEY_EXERCISE_DURATION, exerciseDuration);

        trackExerciseActions.clickOnRunningButton();
        trackExerciseActions.populateField("description", exerciseDescription);
        trackExerciseActions.populateField("duration", exerciseDuration);
        trackExerciseActions.clickSubmit();
        trackExerciseActions.assertAnyMotivationalAlertShows();
        trackExerciseActions.assertMessageAppears("Activity logged successfully! Well done!");
    }

    @And("I am on the track exercise page")
    public void iAmOnTheTrackExercisePage() {
        trackExerciseActions.assertHeaderPresent("h3", "Track exercise");
    }
}
