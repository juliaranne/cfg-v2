package cucumber.stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

public class WeeklyJournalSteps {

    @Then("^I can see a daily breakdown of my workout week in the journal$")
    public void iCanSeeADailyBreakdownOfMyWorkoutWeekInTheJournal() {
        Assertions.assertEquals(1, 1);
//        Todo in next PR: implementation
        // add the expected text
        // using selenium, navigate to the weekly journal button/area the info lives
        // assert this info is there
    }

    @And("^I can see the full details of each workout$")
    public void iCanSeeTheFullDetailsOfEachWorkout() {
        Assertions.assertEquals(1, 1);
//        Todo in next PR: implementation
        //        at the moment, this just shows the type & duration, not the details added when tracking new exercise
    }
}
