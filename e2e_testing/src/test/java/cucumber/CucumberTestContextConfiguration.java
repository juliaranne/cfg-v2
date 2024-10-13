package cucumber;

import cucumber.config.WebDriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;

import org.springframework.test.context.junit4.SpringRunner;

@CucumberContextConfiguration
@RunWith(SpringRunner.class)
public class CucumberTestContextConfiguration {

    @Before
    public void setUp() {
        WebDriverManager.getDriver();
    }

    @After
    public void tearDown() {
        WebDriverManager.closeDriver();
    }
}