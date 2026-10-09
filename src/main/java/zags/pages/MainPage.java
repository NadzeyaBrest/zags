package zags.pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import zags.core.Wait;
import zags.pages.admin.AdminRegistrationPage;
import zags.pages.user.ApplicantDataPage;


import static zags.core.Constants.ADMIN;
import static zags.core.Constants.REFERENCE;
import static zags.core.Constants.SHORT_TIMEOUT;
import static zags.core.Constants.USER;

public class MainPage {
    private WebDriver driver;
    private static final Logger log = LogManager.getLogger(MainPage.class);
    @FindBy(xpath = "//button[contains(text(),'" + USER + "')]")
    private WebElement userModeButton;
    @FindBy(xpath = "//button[contains(text(),'" + ADMIN + "')]")
    private WebElement adminModeButton;
    @FindBy(xpath = "//button[contains(text(),'" + REFERENCE + "')]")
    private WebElement orderCertificateButton;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log.info("Открыта главная страница");
        Wait.waitVisibility(driver, userModeButton, SHORT_TIMEOUT);
    }

    @Step("Войти как пользователь")
    public ApplicantDataPage selectUserMode() {
        log.info("Клик: Войти как пользователь (переход на страницу данных заявителя)");
        userModeButton.click();
        return new ApplicantDataPage(driver);
    }

    @Step("Войти как администратор")
    public AdminRegistrationPage selectAdminMode() {
        log.info("Клик: Войти как администратор (переход на страницу регистрации администратора)");
        adminModeButton.click();
        return new AdminRegistrationPage(driver);
    }


}
