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
            options.addArguments("--no-sandbox"); // Required for CI
            options.addArguments("--disable-dev-shm-usage"); // Handle limited memory
            options.addArguments("--headless"); // Ensure headless mode
            options.addArguments("--disable-gpu"); // Disable GPU for headless stability
            options.addArguments("window-size=1920,1080"); // Match xvfb screen size

// Set DISPLAY for virtual framebuffer
            System.setProperty("DISPLAY", ":99");
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