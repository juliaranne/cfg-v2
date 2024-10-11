package cucumber.webHelpers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Slf4j
@RequiredArgsConstructor
public class WebActions {

    private final WebDriver chromeDriver;

    public void clickOnButtonByLinkText(String button) {
        WebDriverWait wait = wait(chromeDriver);
wait.until(ExpectedConditions.elementToBeClickable(By.linkText(button))).click();
    }

    public void populateField(String field, String contents) {
        WebDriverWait wait = wait(chromeDriver);
        WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.id(field)));
        usernameField.sendKeys(contents);

    }

    public void clickOnButtonByPath(String path){
        chromeDriver.findElement(By.xpath(path)).click();

    }

    public void assertHeader(String headerType, String contents){
        WebDriverWait wait = wait(chromeDriver);

        try {
            WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//%s[contains(text(), '%s')]", headerType, contents))));

            String headerText = header.getText();
            Assert.assertEquals("Header does not contain 'Track exercise'", "Track exercise", headerText);

            System.out.println("The header contains the correct text: 'Track exercise'");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void awaitAndAssertRedirection(String endpoint){
        WebDriverWait wait = wait(chromeDriver);
        String currentUrl = chromeDriver.getCurrentUrl();
        log.info("ADDI" + currentUrl);


        wait.until(ExpectedConditions.urlToBe("http://localhost/trackExercise" +endpoint));



        Assert.assertEquals(currentUrl, "http://localhost/trackExercise" +endpoint, "The user was not redirected to /trackExercise, currentURL: " + currentUrl);




    }

    private WebDriverWait wait(WebDriver chromeDriver) {
        return new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
    } // can this be turned into a variable for the class instead
}
