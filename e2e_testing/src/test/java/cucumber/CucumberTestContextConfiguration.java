package cucumber;

import cucumber.config.WebDriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@CucumberContextConfiguration
@RunWith(SpringRunner.class)
public class CucumberTestContextConfiguration {

//    @Before(value = "@chrome_driver")
//    public void setupChromeDriver() {
//        System.setProperty("webdriver.chrome.driver", "C:/Users/baron/Code/chromedriver-win64/chromedriver-win64/chromedriver.exe");
//    }

//    @Autowired
//    private WebDriver chromeDriver;

    private static String baseUrl = "http://localhost"; // get from context perhaps

    @Before
    public void setUp() {
        // Initialize WebDriver before each scenario
        WebDriverManager.getDriver();
    }

    @After
    public void tearDown() {
//        WebDriverManager.closeDriver();
    }
}