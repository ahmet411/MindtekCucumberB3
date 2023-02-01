package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        plugin = {"pretty","json:target/cucumber.json"},
        features = "src/test/resources/features",
        glue = "steps",
        tags = "@smoke",
        dryRun = false
)
public class Runner {

}
