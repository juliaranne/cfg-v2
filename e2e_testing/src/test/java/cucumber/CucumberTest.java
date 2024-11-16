package cucumber;

import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import io.cucumber.junit.CucumberOptions;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("cucumber")
@CucumberOptions(
        plugin = {"pretty", "json:target/cucumber-reports/cucumber-report.json"}
)
public class CucumberTest {
}