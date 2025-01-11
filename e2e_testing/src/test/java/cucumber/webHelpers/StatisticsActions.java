package cucumber.webHelpers;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class StatisticsActions extends WebActions {

    public StatisticsActions(WebDriver chromeDriver) {
        super(chromeDriver);
    }

    public void assertWellDoneHeaderPresent(String username) {
        WebElement statsContainer = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("stats-container")));

        WebElement header = wait.until(ExpectedConditions.visibilityOf(statsContainer.findElement(By.tagName("h4"))));

        assertTrue(header.getText().contains("Well done, " + username + "! This is your overall effort:"));
    }

    public void assertNoDataAvailableMessagePresent() {
        WebElement container = chromeDriver.findElement(By.cssSelector("div[data-testid='stats']"));

        String containerText = container.getText();
        assertTrue(containerText.contains("No data available"));
   }

    public void assertCorrectSingleExercisePresent(String exerciseType, String contents){
        List<WebElement> exerciseDataList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("exercise-data")));

        WebElement singleExercise = exerciseDataList.getFirst();

        assertEquals(exerciseType, singleExercise.findElement(By.tagName("strong")).getText());
        assertEquals(contents, singleExercise.findElement(By.xpath("./div[2]")).getText());
    }

    private List<WebElement> getExerciseDataList(){
        WebElement statsContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("stats-container")));

        return wait.until(ExpectedConditions.visibilityOfAllElements(statsContainer.findElements(By.className("exercise-data"))));
    }
}
