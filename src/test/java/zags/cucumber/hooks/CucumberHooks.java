package zags.cucumber.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.WebDriver;
import zags.core.DriverManager;

public class CucumberHooks {
    protected WebDriver driver;
    Dotenv dotenv = Dotenv.load();
    String username = dotenv.get("TEST_USERNAME");
    String password = dotenv.get("TEST_PASSWORD");
    String BASE_URL = dotenv.get("BASE_URL");

    @Before
    public void SetUp() {
        driver = DriverManager.getInstance().getDriver();
        String authorizedUrl = String.format("https://%s:%s@%s", username, password, BASE_URL);
        driver.get(authorizedUrl);
    }

    @After
    public void tearDown() {
        DriverManager.getInstance().closeDriver();
    }

}
