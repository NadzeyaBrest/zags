package zags.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DriverManager {
    private static DriverManager instance;
    private WebDriver driver;
    private  WebDriverWait wait;

    private DriverManager() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public static DriverManager getInstance() {
        if (instance == null) {
            instance = new DriverManager();
        }
        return instance;
    }

    public WebDriver getDriver() {
        return driver;
    }
    public WebDriverWait getWait(){
        return wait;
    }

    public void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            wait = null;
            instance = null;
        }
    }
}
