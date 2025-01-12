package cucumber.stepDefs;

import cucumber.util.Context;
import cucumber.webHelpers.WebActions;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class ViewAllSteps {

    private WebActions webActions = Context.get(Context.KEY_WEB_ACTIONS, WebActions.class);

    @Then("I can see the details of my workout")
    public void iCanSeeTheDetailsOfMyWorkout() {
        String expectedDuration = Context.get(Context.KEY_EXERCISE_DURATION).toString();
        String expectedType = Context.get(Context.KEY_EXERCISE_TYPE).toString();
        String expectedDescription = Context.get(Context.KEY_EXERCISE_DESCRIPTION).toString();

        WebElement myExercise = getSingleExercise();
        log.info("addi");
        log.info(myExercise.getText());
        String myExerciseInfo = myExercise.getText();
        assertTrue(myExerciseInfo.contains(expectedDuration));
    }

    @When("I select the delete button")
    public void iSelectTheDeleteButton() {
        WebElement myExercise = getSingleExercise();
        WebElement deleteButton = webActions.findElementByCssSelector("button.close-button");
        deleteButton.click();
    }

    @Then("the exercise is no longer visible on the View All page")
    public void theExerciseIsNoLongerVisibleOnTheViewAllPage() {
        WebElement exercisesList = webActions.findElementByCssSelector("ul[data-testid='viewAllList']");

        List<WebElement> exerciseItems = exercisesList.findElements(By.cssSelector("li.exercise-view-all-data"));
        assertEquals(0, exerciseItems.size());
    }

    private WebElement getSingleExercise(){
        WebElement exercisesList = webActions.findElementByCssSelector("ul[data-testid='viewAllList']");

        List<WebElement> exerciseItems = exercisesList.findElements(By.cssSelector("li.exercise-view-all-data"));
        assertEquals(1, exerciseItems.size());

        return exerciseItems.getFirst();
    }
}
