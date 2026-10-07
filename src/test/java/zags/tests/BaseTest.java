package zags.tests;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import zags.core.DriverManager;


public class BaseTest {
    protected WebDriver driver;
    protected static final Logger log = LogManager.getLogger(BaseTest.class);

    Dotenv dotenv = Dotenv.load();
    String username = dotenv.get("TEST_USERNAME");
    String password = dotenv.get("TEST_PASSWORD");
    String BASE_URL = dotenv.get("BASE_URL");

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        log.info("Старт теста. Открываем браузер ");
        driver = DriverManager.getInstance().getDriver();

        driver.get("https://"
                + username + ":" + password + BASE_URL);
        log.info("Открыт URL: {}", BASE_URL);
    }

    @AfterMethod(alwaysRun = true)

    public void tearDown() {
        log.info("Тест завершён. Закрываем браузер");
        DriverManager.getInstance().closeDriver();
    }

}
