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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@RequiredArgsConstructor
public class WebActions { // todo remove anything unused

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

    public void clickOnRunningButton(){ // todo maybe split by webpage here
        WebDriverWait wait = wait(chromeDriver);

        WebElement runningButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("runningButton"))
        );

        runningButton.click();
    } // todo have this as an enum of different types // maybe have in separate classes for the different webpages

    public void clickSubmit(){
        WebDriverWait wait = wait(chromeDriver);
        try {
            WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit' and contains(text(), 'Save activity')]")));

            saveButton.click();

            System.out.println("Save activity button clicked successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void assertMessageAppears(String contents){
        WebDriverWait wait = wait(chromeDriver);

        String successMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//p[contains(text(),'%s')]", contents)))
        ).getText();

        assertTrue(successMessage.equals(contents), String.format("Actual message contents: %s, expected: %s", successMessage, contents));

    }

    public void assertLogoutButtonVisible(){
        WebDriverWait wait = wait(chromeDriver);
        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Logout']")));
        assertTrue(logoutButton.isDisplayed(), "Logout button is not visible");

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

    public void assertExerciseRecordIsPresent(String expectedRecord){

        WebElement exerciseList = chromeDriver.findElement(By.tagName("ul"));

        List<WebElement> exercises = exerciseList.findElements(By.tagName("li"));

        boolean recordFound = false;

        for (WebElement exercise : exercises) {
            if (exercise.getText().equals(expectedRecord)) {
                recordFound = true;
                break;
            }
        }

        assertTrue(recordFound, String.format("%s record wasn't found", expectedRecord));

    }

    private WebDriverWait wait(WebDriver chromeDriver) {
        return new WebDriverWait(chromeDriver, Duration.ofSeconds(5));
    } // can this be turned into a variable for the class instead
}
