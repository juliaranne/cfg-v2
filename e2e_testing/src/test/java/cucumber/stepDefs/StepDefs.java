package cucumber.stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class StepDefs {
    @Given("^I have entered my completed workout$")
    public void iHaveEnteredMyCompletedWorkout() {
        System.out.println("hi");
        Assertions.assertEquals(1,1);
    // login
    //
    }

    @Given("^I have an account$")
    public void iHaveAnAccount() {
        // creates an account
        // perhaps using selenium
        // and some seeded data for the user
    }

    @Given("^I have logged into the app$")
    public void iHaveLoggedIntoTheApp() {
        // using the seeded data to login
    }

    @When("^I go to the statistics page$")
    public void iGoToTheStatisticsPage() {
        // click on the button to navigate
    }

    @Then("^I can see a daily breakdown of my workout week in the journal$")
    public void iCanSeeADailyBreakdownOfMyWorkoutWeekInTheJournal() {
        System.out.println("hi");
        Assertions.assertEquals(1,1);

        // add the expected text
        // using selenium, navigate to the weekly journal button/area the info lives
        // assert this info is there
    }

    @And("^I can see the full details of each workout$")
    public void iCanSeeTheFullDetailsOfEachWorkout() {
        //
    }
}
