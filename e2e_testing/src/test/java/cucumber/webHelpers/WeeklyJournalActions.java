package cucumber.webHelpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeeklyJournalActions extends WebActions {

    public WeeklyJournalActions(WebDriver chromeDriver) {
        super(chromeDriver);
    }

    public void assertExerciseRecordIsPresent(String expectedRecord) {
        boolean recordFound = getListFromPage().stream().anyMatch(exercise -> exercise.getText().contains(expectedRecord));
        assertTrue(recordFound, String.format("%s record wasn't found", expectedRecord));
    }

    public void assertExerciseRecordContainsDescription(String expectedDescription) {
        boolean recordFound = getListFromPage().stream().anyMatch(exercise -> exercise.getText().contains(expectedDescription));
        assertTrue(recordFound, String.format("Description wasn't found: ", expectedDescription));
    }

    private List<WebElement> getListFromPage() {
        WebElement exerciseList = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("ul")));
        return exerciseList.findElements(By.tagName("li"));
    }
}
