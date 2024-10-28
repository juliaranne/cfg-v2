package cucumber.webHelpers;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

}
