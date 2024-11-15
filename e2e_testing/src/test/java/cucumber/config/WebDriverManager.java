package cucumber.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class WebDriverManager {
    private static WebDriver driver;
    private static Config config;

//    // Todo use springboot to load the config
//    static {
//        try (InputStream in = WebDriverManager.class.getClassLoader().getResourceAsStream("application.yaml")) {
//            if (in == null) {
//                throw new RuntimeException("application.yaml file not found in resources");
//            }
//            Yaml yaml = new Yaml();
//            config = yaml.loadAs(in, Config.class);
//
//            String chromeDriverPath = resolveEnvVars(config.getWebdriver().getChromeDriverPath());
//            config.getWebdriver().setChromeDriverPath(chromeDriverPath);
//
//        } catch (Exception e) {
//            throw new RuntimeException("Error loading configuration", e);
//        }
//    }

    public static WebDriver getDriver() {
        io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
        System.out.println("ChromeDriver version: " + io.github.bonigarcia.wdm.WebDriverManager.chromedriver().getDownloadedDriverVersion());


        return new ChromeDriver();
    }

    public static void closeDriver() {
//        if (driver != null) {
//            driver.quit();
//            driver = null;
//        }
        System.out.println("hi");
        // how to update this?
    }

//    private static String resolveEnvVars(String path) {
//        if (path != null && path.contains("${")) {
//            for (Map.Entry<String, String> env : System.getenv().entrySet()) {
//                path = path.replace("${" + env.getKey() + "}", env.getValue());
//            }
//        }
//        return path;
//    }
}
