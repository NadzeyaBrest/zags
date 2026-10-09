package zags.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {
    private static DriverManager instance;
    private WebDriver driver;
    private static final Logger log = LogManager.getLogger(DriverManager.class);
    private DriverManager() {
        log.debug("Создаём ChromeDriver");
        driver = new ChromeDriver();
    }

    public static DriverManager getInstance() {
        if (instance == null) {
            log.debug("DriverManager ещё не создан — создаём");
            instance = new DriverManager();
        }
        return instance;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            log.debug("Драйвер null — пересоздаём");
            driver = new ChromeDriver();
        }
        return driver;
    }

    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
        driver = null;
        instance = null;

    }
}
