import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = { "html:src/test/resources/features/htmlReport/cucumberHooks.html",
                "json:target/json-reports/cucumber.json",
                "junit:src/test/resources/features/htmlReport/cucumber.xml",
                "rerun:TestOutput/failed_scenario.txt",//rerun hata veren testleri kaydedip yeniden otomatik kosmak icin
               // "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        features = "src/test/resources/features",
        glue = "stepDefinitions",
        tags = "@enuygun",
        dryRun = false

)
public class TestRunner {
}
