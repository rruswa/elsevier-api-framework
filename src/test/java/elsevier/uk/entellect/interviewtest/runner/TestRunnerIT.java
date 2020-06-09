package elsevier.uk.entellect.interviewtest.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features/",
        glue = "elsevier.uk.entellect.interviewtest.steps",
        plugin = "json:target/results/json/results.json",
        strict = true
)
public class TestRunnerIT extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

}
