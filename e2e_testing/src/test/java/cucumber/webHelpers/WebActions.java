package cucumber.webHelpers;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class WebActions {

    protected final WebDriver chromeDriver;
    protected WebDriverWait wait;

    public WebActions(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(5));
    }

    public void clickOnButtonByLinkText(String button) {
        clickElement(By.linkText(button));
    }

    public void clickOnButtonByPath(String path) {
        clickElement(By.xpath(path));
    }

    public void populateField(String fieldId, String contents) {
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(By.id(fieldId)));
        field.sendKeys(contents);
    }

    public void assertMessageAppears(String contents) {
        String successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//p[contains(text(),'%s')]", contents)))).getText();
        assertEquals(contents, successMessage, String.format("Actual message: %s, expected: %s", successMessage, contents));
    }

    public void assertHeaderPresent(String headerType, String contents) {
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//%s[contains(text(), '%s')]", headerType, contents))));
        assertEquals(contents, header.getText(), String.format("Header does not contain: %s", contents));
    }

    protected WebElement findByClassName(String classname){
        return chromeDriver.findElement(By.className(classname));
    }

    protected void clickElement(By locator, String elementDescription) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            log.info("{} clicked successfully.", elementDescription);
        } catch (Exception e) {
            log.error("Failed to click on {}", elementDescription, e);
            throw e;
        }
    }

    private void clickElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }
}
