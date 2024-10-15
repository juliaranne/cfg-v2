package cucumber.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebDriverConfig {
    private String chromeDriverPath;
    private String baseUrl;
}