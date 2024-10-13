package cucumber.webHelpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeeklyJournalActions extends WebActions {

    public WeeklyJournalActions(WebDriver chromeDriver) {
        super(chromeDriver);
    }

    public void assertExerciseRecordIsPresent(String expectedRecord) {
        WebElement exerciseList = chromeDriver.findElement(By.tagName("ul"));
        List<WebElement> exercises = exerciseList.findElements(By.tagName("li"));

        boolean recordFound = exercises.stream().anyMatch(exercise -> exercise.getText().equals(expectedRecord));

        assertTrue(recordFound, String.format("%s record wasn't found", expectedRecord));
    }
}
