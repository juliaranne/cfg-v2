package cucumber.stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginSteps {
// todo have chrome driver config in a spring config file
    private WebDriver driver;

    @Given("I have an account for {string} with password {string}")
    public void iHaveAnAccountForWithPassword(String username, String password) {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:/Users/baron/Code/chromedriver-win64/chromedriver-win64/chromedriver.exe");

        // Initialize the Chrome WebDriver
        driver = new ChromeDriver();

        driver.get("https://www.google.com");

        Assertions.assertEquals(1, 1);
//        Todo in next PR: implementation
    }

    @And("I login to the app using the username {string} and password {string}")
    public void iLoginToTheAppUsingTheUsernameAndPassword(String arg0, String arg1) {
        Assertions.assertEquals(1, 1);
//        Todo in next PR: implementation
    }
}
