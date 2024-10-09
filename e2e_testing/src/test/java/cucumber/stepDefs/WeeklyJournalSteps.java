package cucumber.stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeeklyJournalSteps {
    private WebDriver driver;

    @Then("^I can see a daily breakdown of my workout week in the journal$")
    public void iCanSeeADailyBreakdownOfMyWorkoutWeekInTheJournal() {
        assertEquals(1, 1);
//        Todo in next PR: implementation
        // add the expected text
        // using selenium, navigate to the weekly journal button/area the info lives
        // assert this info is there
    }

    @And("^I can see the full details of each workout$")
    public void iCanSeeTheFullDetailsOfEachWorkout() {
        assertEquals(1, 1);
//        Todo in next PR: implementation
        //        at the moment, this just shows the type & duration, not the details added when tracking new exercise
    }

    @Then("I can see my workout in the journal")
    public void iCanSeeMyWorkoutInTheJournal() {
        driver = new ChromeDriver();

        String actualTitle = driver.getTitle();
        System.out.println(actualTitle);

        assertEquals("1", 2);
        assertEquals("Gogle", actualTitle);

        // Close the browser
        driver.quit();


//        assertEquals(1, 1);
//        Todo in next PR: implementation
    }
}
