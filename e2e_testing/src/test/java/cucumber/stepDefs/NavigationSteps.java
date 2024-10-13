package cucumber.stepDefs;

import cucumber.util.Context;
import cucumber.webHelpers.WebActions;
import io.cucumber.java.en.When;

public class NavigationSteps {

    private WebActions webActions = Context.get(Context.KEY_WEB_ACTIONS, WebActions.class);


    @When("I go to the {string} page")
    public void iGoToThePage(String page) {
        webActions.clickOnButtonByLinkText(page);
    }
}
