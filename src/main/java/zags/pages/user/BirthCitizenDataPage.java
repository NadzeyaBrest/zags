package zags.pages.user;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import zags.core.Wait;
import zags.models.Application;
import zags.pages.MainPage;

import static zags.core.Constants.ADDRESS;
import static zags.core.Constants.BACK;
import static zags.core.Constants.BIRTH_DATE;
import static zags.core.Constants.CLOSE;
import static zags.core.Constants.FIRST_NAME;
import static zags.core.Constants.GENDER;
import static zags.core.Constants.LAST_NAME;
import static zags.core.Constants.MIDDLE_NAME;
import static zags.core.Constants.NEXT;
import static zags.core.Constants.PASSPORT;
import static zags.core.Constants.SHORT_TIMEOUT;

public class BirthCitizenDataPage {
    private final WebDriver driver;
    private static final Logger log = LogManager.getLogger(BirthCitizenDataPage.class);

    @FindBy(xpath = "//label[contains(text(),'" + LAST_NAME + "')]/../following-sibling::input")
    private WebElement birthCitizenLastName;
    @FindBy(xpath = "//label[contains(text(),'" + FIRST_NAME + "')]/../following-sibling::input")
    private WebElement birthCitizenFirstName;
    @FindBy(xpath = "//label[contains(text(),'" + MIDDLE_NAME + "')]/../following-sibling::input")
    private WebElement birthCitizenMiddleName;
    @FindBy(xpath = "//label[contains(text(),'" + BIRTH_DATE + "')]/../following-sibling::input")
    private WebElement birthCitizenBirthDate;
    @FindBy(xpath = "//label[contains(text(),'" + GENDER + "')]/../following-sibling::input")
    private WebElement birthCitizenGender;
    @FindBy(xpath = "//label[contains(text(),'" + PASSPORT + "')]/../following-sibling::input")
    private WebElement birthCitizenPassport;
    @FindBy(xpath = "//label[contains(text(),'" + ADDRESS + "')]/../following-sibling::input")
    private WebElement birthCitizenAddress;
    @FindBy(xpath = "//button[contains(text(),'" + NEXT + "')]")
    private WebElement birthCitizenNextButton;
    @FindBy(xpath = "//button[contains(text(),'" + CLOSE + "')]")
    private WebElement birthCitizenCloseButton;
    @FindBy(xpath = "//button[contains(text(),'" + BACK + "')]")
    private WebElement birthCitizenButtonBack;

    public BirthCitizenDataPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log.info("Открыта страница данных гражданина (рождение)");
        Wait.waitVisibility(driver, birthCitizenLastName, SHORT_TIMEOUT);
    }

    @Step("Заполнить данные гражданина (рождение) и нажать 'Далее'")
    public BirthServiceDataPage birthCitizenDataFillAndNext(Application app) {
        birthCitizenLastName.sendKeys(app.getCitizenLastName());
        birthCitizenFirstName.sendKeys(app.getCitizenFirstName());
        birthCitizenMiddleName.sendKeys(app.getCitizenMiddleName());
        birthCitizenBirthDate.sendKeys(app.getCitizenBirthDate());
        birthCitizenGender.sendKeys(app.getCitizenGender());
        birthCitizenPassport.sendKeys(app.getCitizenPassport());
        birthCitizenAddress.sendKeys(app.getCitizenAddress());
        Wait.waitClickable(driver, birthCitizenNextButton, SHORT_TIMEOUT);
        birthCitizenNextButton.click();
        log.info("Данные гражданина (рождение) отправлены");
        return new BirthServiceDataPage(driver);
    }

    @Step("Вернуться на страницу выбора услуги (данные гражданина, рождение)")
    public ServiceSelectionPage clickBirthCitizenBackButton() {
        log.info("Клик: Назад (возврат на страницу выбора услуги)");
        birthCitizenButtonBack.click();
        return new ServiceSelectionPage(driver);
    }

    @Step("Закрыть страницу данных гражданина (рождение)")
    public MainPage clickBirthCitizenButtonClose() {
        log.info("Клик: Закрыть страницу данных гражданина (рождение) (переход на главную)");
        birthCitizenCloseButton.click();
        return new MainPage(driver);
    }

}
