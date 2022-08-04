package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        plugin = {"json:target/cucumber/cucumber.json","rerun:target/rerun.txt"},
        features = "src/test/resources/features",
        glue = "steps",
        tags = "@api and @MB3-102",
        dryRun = false
)
public class Runner {

}
