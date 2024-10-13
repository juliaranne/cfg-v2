package cucumber.webHelpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TrackExerciseActions extends WebActions{

    public TrackExerciseActions(WebDriver chromeDriver) {
        super(chromeDriver);
    }

    public void clickOnRunningButton() {
        clickElement(By.id("runningButton"), "Running Button");
    }

}
