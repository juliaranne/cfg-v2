package cucumber.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverManager {
    private static WebDriver driver;
    private static String baseUrl = "http://localhost";

    public static WebDriver getDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "C:/Users/baron/Code/chromedriver-win64/chromedriver-win64/chromedriver.exe"); // Todo get from app.yml or something
            driver = new ChromeDriver();
            driver.get(baseUrl);
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}