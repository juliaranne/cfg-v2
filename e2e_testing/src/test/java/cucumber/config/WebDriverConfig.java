package cucumber.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@Configuration
public class WebDriverConfig {
//    @Value("${chromedriver_path}")
//    private String chromeDriverPath;

    @Bean
    public WebDriver chromeDriver() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/baron/Code/chromedriver-win64/chromedriver-win64/chromedriver.exe");
        return new ChromeDriver();  // Ensure ChromeDriver is instantiated properly
    }
}
