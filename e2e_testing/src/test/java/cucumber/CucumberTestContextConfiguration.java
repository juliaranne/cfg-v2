package cucumber;

import cucumber.config.WebDriverConfig;
import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;

@CucumberContextConfiguration
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {WebDriverConfig.class})  // Add this line
public class CucumberTestContextConfiguration {
//    @Autowired
//    private WebDriver driver;

    @Before(value = "@chrome_driver")
    public void setupChromeDriver(){
        System.setProperty("webdriver.chrome.driver", "C:/Users/baron/Code/chromedriver-win64/chromedriver-win64/chromedriver.exe");
    }
}