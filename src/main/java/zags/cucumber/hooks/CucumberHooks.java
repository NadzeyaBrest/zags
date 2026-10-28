package zags.cucumber.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.cdimascio.dotenv.Dotenv;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import zags.core.DriverManager;

public class CucumberHooks {
    public   static WebDriver driver;
    Dotenv dotenv = Dotenv.load();
    String username = dotenv.get("TEST_USERNAME");
    String password = dotenv.get("TEST_PASSWORD");
    String BASE_URL = dotenv.get("BASE_URL");
    protected static final Logger log = LogManager.getLogger(CucumberHooks.class);

    @Before("@ui")
    public void SetUp() {
        String browser = System.getProperty("browser", "chrome");
        driver = DriverManager.getInstance().getDriver(browser);
        String authorizedUrl = String.format("https://%s:%s@%s", username, password, BASE_URL);
        driver.get(authorizedUrl);
    }
    @After ("@ui")
    public void tearDown() {
        DriverManager.getInstance().closeDriver();
    }

}
