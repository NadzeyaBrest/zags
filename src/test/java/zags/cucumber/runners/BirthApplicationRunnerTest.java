package zags.cucumber.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "zags.cucumber",
        plugin = {
                "pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "html:target/cucumber-reports/birth-application-report.html",
                "json:target/cucumber-reports/birth-application-report.json"
        },
        monochrome = true
)


public class BirthApplicationRunnerTest extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
