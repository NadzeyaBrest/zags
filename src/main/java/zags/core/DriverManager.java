package zags.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class DriverManager {
    private static DriverManager instance;
    private static final Logger log = LogManager.getLogger(DriverManager.class);
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverManager() {
        log.debug("DriverManager создан ");
    }

    public static DriverManager getInstance() {
        if (instance == null) {
            log.debug("DriverManager ещё не создан — создаём");
            instance = new DriverManager();
        }
        return instance;
    }

    public  WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            // 1. Определяем браузер: TestNG-параметр → env → chrome по умолчанию
            String browser = System.getProperty("browser");
            if (browser == null || browser.isEmpty()) {
                browser = System.getenv("BROWSER");
            }
            if (browser == null || browser.isEmpty()) {
                browser = "chrome";
            }

            log.debug("Создаём RemoteWebDriver для Selenoid, браузер={}, поток={}",
                    browser, Thread.currentThread().getId());

            MutableCapabilities options;
            switch (browser.toLowerCase()) {
                case "firefox":
                    options = new FirefoxOptions();
                    break;
                case "edge":
                    options = new EdgeOptions();
                    break;
                case "chrome":
                default:
                    options = new ChromeOptions();
                    break;
            }


            Map<String, Object> selenoidOptions = new HashMap<>();
            selenoidOptions.put("sessionTimeout", "5m");
            selenoidOptions.put("enableVNC", true);
            selenoidOptions.put("enableVideo", true);
            selenoidOptions.put("name", "Test in thread " + Thread.currentThread().getId() + " on " + browser);
            options.setCapability("selenoid:options", selenoidOptions);
            String selenoidUrl = System.getenv("SELENOID_URL");
            if (selenoidUrl == null || selenoidUrl.isEmpty()) {
                selenoidUrl = "http://localhost:4444/wd/hub";
            }
            log.debug("Подключаемся к Selenoid: {}", selenoidUrl);

            try {
                driverThreadLocal.set(new RemoteWebDriver(new URL(selenoidUrl), options));
            } catch (MalformedURLException e) {
                log.error("Ошибка в URL Selenoid", e);
                throw new RuntimeException("Не удалось подключиться к Selenoid", e);
            }
        }
        return driverThreadLocal.get();
    }

    public void closeDriver() {
        if (driverThreadLocal.get() != null) {
            log.debug("Закрываем драйвер в потоке: " + Thread.currentThread().getId());
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }
}