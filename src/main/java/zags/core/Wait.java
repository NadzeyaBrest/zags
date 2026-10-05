package zags.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Wait {
    private final WebDriver driver;

    public Wait(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriverWait seconds(long seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }
}