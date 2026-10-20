package zags.data;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import zags.core.DriverManager;

import java.io.ByteArrayInputStream;


public class TestListener implements ITestListener {
    private static Logger log = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        log.info(">>> Старт теста: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("!!! Тест упал: {}", result.getMethod().getMethodName());
    }

    private void attachScreenshot() {
        try {
            WebDriver driver = DriverManager.getInstance().getCurrentDriver();
            if (driver != null) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Screenshot on failure", "image/png",
                        new ByteArrayInputStream(screenshot), "png");

                log.info("Скриншот успешно прикреплен к Allure");
            } else {
                log.warn("Драйвер null, скриншот не сделан.");
            }
        } catch (Exception e) {
            log.warn("Не удалось сделать скриншот: {}", e.getMessage());
        }
    }

}
