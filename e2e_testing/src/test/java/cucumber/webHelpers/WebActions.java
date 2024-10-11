package cucumber.webHelpers;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.util.Context;

import java.time.Duration;

@RequiredArgsConstructor
public class WebActions {

    private final WebDriver chromeDriver;

    public void clickOnButtonToRedirect(String button) {
        WebDriverWait wait = wait(chromeDriver);

        WebElement signUpLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(button)));
        signUpLink.click();
    }

    public void populateField(String field, String contents) {
        WebDriverWait wait = wait(chromeDriver);
        WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.id(field)));
        usernameField.sendKeys(contents);

    }

    private WebDriverWait wait(WebDriver chromeDriver) {
        return new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
    }
}
