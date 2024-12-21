package cucumber.webHelpers;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Slf4j
public class TrackExerciseActions extends WebActions{

    public TrackExerciseActions(WebDriver chromeDriver) {
        super(chromeDriver);
    }

    public void clickOnRunningButton() {
        clickElement(By.id("runningButton"), "Running Button");
    }

    public void clickSubmit() {
        clickElement(By.xpath("//button[@type='submit' and contains(text(), 'Save activity')]"), "Save activity button");
    }

    public void assertAnyMotivationalAlertShows(){
        try {
            Alert alert =  wait.until(ExpectedConditions.alertIsPresent());
            alert.dismiss();
        } catch (Exception e) {
            log.warn("no motivational alert shown");
        }
    }

}
