package zags.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Wait {
    private static final Logger log = LogManager.getLogger(Wait.class);
    public static final long SHORT_TIMEOUT = 2;
    public static final long MEDIUM_TIMEOUT = 5;
    public static final long LONG_TIMEOUT = 10;

    private Wait() {
    }

    public static WebDriverWait createWait(WebDriver driver, long seconds) {

        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    public static WebElement waitVisibility(WebDriver driver, WebElement element,long seconds) {
        log.debug("Ожидаем видимость элемента ({} сек)", seconds);
        return createWait(driver, seconds).until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitClickable(WebDriver driver, WebElement element,long seconds) {
        log.debug("Ожидаем кликабельность элемента ({} сек)", seconds);
        return createWait(driver, seconds).until(ExpectedConditions.elementToBeClickable(element));
    }
}