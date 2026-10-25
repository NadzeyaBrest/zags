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
    private static final ThreadLocal<String> currentBrowser = new ThreadLocal<>();

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

    public static String getCurrentBrowser() {
        return currentBrowser.get();
    }

    public WebDriver getDriver(String browserParam) {
        if (driverThreadLocal.get() == null) {

            String browser = browserParam;
            if (browser == null || browser.isEmpty()) {
                browser = "chrome";
            }
            currentBrowser.set(browser.toLowerCase());
            Thread.currentThread().setName(browser);
            log.debug("Создаём RemoteWebDriver для Selenoid, браузер={}, поток={}",
                    browser, Thread.currentThread().getId());

            MutableCapabilities options;
            switch (browser.toLowerCase()) {
                case "firefox":
                    options = new FirefoxOptions();
                    options.setCapability("browserName", "firefox");
                    break;
                case "edge":
                    options = new EdgeOptions();
                    options.setCapability("browserName", "MicrosoftEdge");
                    break;
                case "chrome":
                default:
                    options = new ChromeOptions();
                    options.setCapability("browserName", "chrome");
                    break;
            }

            Map<String, Object> selenoidOptions = new HashMap<>();
            selenoidOptions.put("sessionTimeout", "5m");
            selenoidOptions.put("enableVNC", true);
            selenoidOptions.put("enableVideo", false);
            selenoidOptions.put("name", "Test in thread " + Thread.currentThread().getId() + " on " + browser);
            options.setCapability("selenoid:options", selenoidOptions);
            log.info("Итоговый browserName = {}", options.getCapability("browserName"));
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
        currentBrowser.remove();
    }
}