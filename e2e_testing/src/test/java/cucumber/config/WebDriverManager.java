package cucumber.config;

import cucumber.util.Context;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverManager {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");

             options.addArguments("--headless");
            options.addArguments("--disable-gpu");
            options.addArguments("window-size=1920,1080");

            System.setProperty("DISPLAY", ":99");
            driver = new ChromeDriver(options);

            driver.get(Context.BASE_URL);
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