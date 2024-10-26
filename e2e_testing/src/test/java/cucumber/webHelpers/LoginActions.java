package cucumber.webHelpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginActions extends WebActions{

    private String LOGOUT_XPATH = "//a[text()='Logout']";

    public LoginActions(WebDriver chromeDriver) {
        super(chromeDriver);
    }

    public void assertLogoutButtonVisible() {
        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOGOUT_XPATH)));
        assertTrue(logoutButton.isDisplayed(), "Logout button is not visible");
    }

    public void logout() {
        clickOnButtonByPath(LOGOUT_XPATH);
    }

    public void assertFailedLoginAlertMessageAppears() {
        String message = "Failed to login";
        WebElement alertElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@role='alert' and contains(@class, 'alert-danger')]")));

        assertEquals(message, alertElement.getText(),
                String.format("Expected alert message: '%s', but found: '%s'", message, alertElement.getText()));
    }
}
