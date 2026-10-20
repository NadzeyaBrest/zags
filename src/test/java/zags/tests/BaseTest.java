package zags.tests;

import io.github.cdimascio.dotenv.Dotenv;
import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
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
    public void setUp(ITestContext context) {
        String browser = context.getCurrentXmlTest().getParameter("browser");
        if (browser == null || browser.isEmpty()) {
            browser = "chrome";
        }
        log.debug("Старт теста. Открываем браузер {}", browser);
        driver = DriverManager.getInstance().getDriver(browser);
        String authorizedUrl = String.format("https://%s:%s@%s", username, password, BASE_URL);
        driver.get(authorizedUrl);
        log.info("Открыт URL: {}", authorizedUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            takeScreenshot();
        }
        log.debug("Тест завершён. Закрываем браузер");
        DriverManager.getInstance().closeDriver();
    }
    @Attachment(value = "Screenshot on failure", type = "image/png")
    private byte[] takeScreenshot() {
        try {
            if (driver != null) {
                return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            }
        } catch (Exception e) {
            log.warn("Не удалось сделать скриншот: {}", e.getMessage());
        }
        return new byte[]{};
    }
}

