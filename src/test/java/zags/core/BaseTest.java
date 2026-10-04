package zags.core;

import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    Dotenv dotenv = Dotenv.load();
    String username = dotenv.get("TEST_USERNAME");
    String password = dotenv.get("TEST_PASSWORD");
    String BASE_URL = dotenv.get("BASE_URL");

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getInstance().getDriver();

        driver.get("https://"
                + username + ":" + password + BASE_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5));
    }

    @AfterMethod
    public void ternDown() {
        DriverManager.getInstance().closeDriver();
    }

}
