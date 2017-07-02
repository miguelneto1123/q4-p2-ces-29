package ita.cucumber.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
        monochrome = true,
        plugin = {"pretty"},
        features = "src/test/java/ita/cucumber/features/",
        glue = "ita.cucumber.steps"
)

/**
 * Created by miguel on 7/1/17.
 */
public class vulnerableMethodTest {
}
