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

        WebElement header = statsContainer.findElement(By.tagName("h4"));

        assertTrue(header.getText().contains("Well done, " + username + "! This is your overall effort:"));
    }

    public void assertNoDataAvailableMessagePresent() {
        WebElement statsContainer = findByClassName("stats-container");

        List<WebElement> exerciseDataList = statsContainer.findElements(By.className("exercise-data"));

        assertTrue(exerciseDataList.isEmpty(), "Exercise data not empty: " + exerciseDataList);
        WebElement noDataMessage = statsContainer.findElement(By.tagName("p"));

        assertEquals("No data available", noDataMessage.getText(), "Unexpected message: " + noDataMessage);
    }

}
