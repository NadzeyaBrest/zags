package zags.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Wait {

    public static WebDriverWait createWait(WebDriver driver, long seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    public static WebElement waitVisibility(WebDriver driver, WebElement element,long seconds) {
        return createWait(driver, seconds).until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitClickable(WebDriver driver, WebElement element,long seconds) {
        return createWait(driver, seconds).until(ExpectedConditions.elementToBeClickable(element));
    }
}