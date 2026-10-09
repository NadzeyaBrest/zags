package zags.pages.admin;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import zags.core.Wait;
import zags.models.Admin;
import zags.pages.MainPage;

import static zags.core.Constants.BIRTH_DATE;
import static zags.core.Constants.CLOSE;
import static zags.core.Constants.FIRST_NAME;
import static zags.core.Constants.LAST_NAME;
import static zags.core.Constants.MIDDLE_NAME;
import static zags.core.Constants.NEXT;
import static zags.core.Constants.PASSPORT;
import static zags.core.Constants.PHONE;
import static zags.core.Constants.SHORT_TIMEOUT;

public class AdminRegistrationPage {
    private final WebDriver driver;

    private static final Logger log = LogManager.getLogger(AdminRegistrationPage.class);
    @FindBy(xpath = "//label[contains(text(),'" + LAST_NAME + "')]/../following-sibling::input")
    private WebElement adminLastName;

    @FindBy(xpath = "//label[contains(text(),'" + FIRST_NAME + "')]/../following-sibling::input")
    private WebElement adminFirstName;

    @FindBy(xpath = "//label[contains(text(),'" + MIDDLE_NAME + "')]/../following-sibling::input")
    private WebElement adminMiddleName;

    @FindBy(xpath = "//label[contains(text(),'" + PHONE + "')]/../following-sibling::input")
    private WebElement adminPhone;

    @FindBy(xpath = "//label[contains(text(),'" + PASSPORT + "')]/../following-sibling::input")
    private WebElement adminPassport;

    @FindBy(xpath = "//label[contains(text(),'" + BIRTH_DATE + "')]/../following-sibling::input")
    private WebElement adminBirthDate;

    @FindBy(xpath = "//button[contains(text(),'" + NEXT + "')]")
    private WebElement adminRegistrationNextButton;

    @FindBy(xpath = "//button[contains(text(),'" + CLOSE + "')]")
    private WebElement adminRegistrationCloseButton;

    public AdminRegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log.info("Открыта форма регистрации администратора");
        Wait.waitVisibility(driver, adminLastName, SHORT_TIMEOUT);
    }
    @Step("Заполнить форму регистрации администратора и нажать Далее")
    public AdminTablePage adminRegistrationFillAndNext(Admin admin) {
        log.info("Заполняем форму регистрации администратора");
        adminLastName.sendKeys(admin.getLastName());
        adminFirstName.sendKeys(admin.getFirstName());
        adminMiddleName.sendKeys(admin.getMiddleName());
        adminPhone.sendKeys(admin.getPhone());
        adminPassport.sendKeys(admin.getPassportNumber());
        adminBirthDate.sendKeys(admin.getBirthDate());
        Wait.waitClickable(driver, adminRegistrationNextButton, SHORT_TIMEOUT);
        adminRegistrationNextButton.click();
        log.info("Данные администратора отправлены");
        return new AdminTablePage(driver);
    }

    @Step("Закрыть форму  регистрации администратора ")
    public MainPage adminRegistrationClickClose() {
        log.info("Клик: Закрыть страницу данных администратора (переход на главную)");
        adminRegistrationCloseButton.click();
        return new MainPage(driver);
    }
}