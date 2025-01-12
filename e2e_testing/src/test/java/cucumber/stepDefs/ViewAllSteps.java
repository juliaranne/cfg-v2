package cucumber.stepDefs;

import cucumber.util.Context;
import cucumber.webHelpers.WebActions;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewAllSteps {

    private WebActions webActions = Context.get(Context.KEY_WEB_ACTIONS, WebActions.class);

    @Then("I can see the details of my workout")
    public void iCanSeeTheDetailsOfMyWorkout() {
        String expectedDuration = Context.get(Context.KEY_EXERCISE_DURATION).toString();
        String expectedType = Context.get(Context.KEY_EXERCISE_TYPE).toString();
        String expectedDescription = Context.get(Context.KEY_EXERCISE_DESCRIPTION).toString();

        WebElement exercisesList = webActions.findElementByCssSelector("ul[data-testid='viewAllList']");

        List<WebElement> exerciseItems = exercisesList.findElements(By.cssSelector("li.exercise-view-all-data"));
        assertEquals(1, exerciseItems.size());
        WebElement myExercise = exerciseItems.getFirst();
        String exerciseType = myExercise.findElement(By.tagName("strong")).getText();
        String duration = myExercise.getText().split("\n")[1];
        String description = myExercise.getText().split("\n")[2];
        assertEquals(expectedDescription, description);
        assertEquals(expectedDuration, duration);
        assertEquals(expectedType, exerciseType);
    }
}
