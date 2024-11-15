package cucumber.config; // move?

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverManager {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless"); // Run in headless mode
            options.addArguments("--no-sandbox"); // Disable sandboxing
            options.addArguments("--disable-dev-shm-usage"); // Disable /dev/shm usage
            options.addArguments("--remote-debugging-port=9222"); // Necessary for debugging in CI environments

            // Initialize the driver with the Chrome options
            driver = new ChromeDriver(options);

            String baseUrl = "http://localhost"; // move this
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