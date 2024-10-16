package cucumber.webHelpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginActions extends WebActions{

    public LoginActions(WebDriver chromeDriver) {
        super(chromeDriver);
    }

    public void assertLogoutButtonVisible() {
        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Logout']")));
        assertTrue(logoutButton.isDisplayed(), "Logout button is not visible");
    }

}
