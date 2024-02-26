package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = { "html:TestOutput/htmlReport/cucumberHooks.html",
                "json:TestOutput/json-reports/cucumber.json",
                "junit:TestOutput/xmlReport/cucumber.xml",
                "rerun:TestOutput/failed_scenario.txt",//rerun hata veren testleri kaydedip yeniden otomatik kosmak icin
               // "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        features = "src/test/resources/features",
        glue = {"stepDefinitions","hooks"},
        tags = "@enuygun",
        dryRun = false

)
public class TestRunner {
}
