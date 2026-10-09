package zags.pages.user;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import zags.core.Wait;
import zags.pages.MainPage;

import static zags.core.Constants.BACK;
import static zags.core.Constants.BIRTH;
import static zags.core.Constants.CLOSE;
import static zags.core.Constants.DEATH;
import static zags.core.Constants.MARRIAGE;
import static zags.core.Constants.SHORT_TIMEOUT;

public class ServiceSelectionPage {
    private WebDriver driver;
    private static final Logger log = LogManager.getLogger(ServiceSelectionPage.class);
    @FindBy(xpath = "//button[contains(text(),'" + MARRIAGE + "')]")
    private WebElement marriageButton;
    @FindBy(xpath = "//button[contains(text(),'" + BIRTH + "')]")
    private WebElement birthButton;
    @FindBy(xpath = "//button[contains(text(),'" + DEATH + "')]")
    private WebElement deathButton;
    @FindBy(xpath = "//button[contains(text(),'" + BACK + "')]")
    private WebElement serviceSectionPageBackButton;
    @FindBy(xpath = "//button[contains(text(),'" + CLOSE + "')]")
    private WebElement serviceSectionPageCloseButton;

    public ServiceSelectionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log.info("Открыта страница выбора услуги");
        Wait.waitVisibility(driver, marriageButton, SHORT_TIMEOUT);
    }

    @Step("Вернуться на страницу данных заявителя")
    public ApplicantDataPage clickServiceSelectionBackButton() {
        log.info("Клик: Назад (возврат на страницу данных заявителя)");
        serviceSectionPageBackButton.click();
        return new ApplicantDataPage(driver);

    }

    @Step("Закрыть страницу выбора услуги")
    public MainPage clickServiceSelectionCloseButton() {
        log.info("Клик: Закрыть страницу выбора услуги (переход на главную)");
        serviceSectionPageCloseButton.click();
        return new MainPage(driver);
    }

    @Step("Выбрать услугу Регистрация брака")
    public MarriageCitizenDataPage selectMarriage() {
        log.info("Выбираем услугу: Брак");
        Wait.waitClickable(driver, marriageButton, SHORT_TIMEOUT);
        marriageButton.click();
        log.info("Услуга выбрана: Регистрация брака");
        return new MarriageCitizenDataPage(driver);
    }

    @Step("Выбрать услугу Регистрация рождения")
    public BirthCitizenDataPage selectBirth() {
        log.info("Выбираем услугу: Рождение");
        Wait.waitClickable(driver, birthButton, SHORT_TIMEOUT);
        birthButton.click();
        log.info("Услуга выбрана: Регистрация рождения");
        return new BirthCitizenDataPage(driver);
    }

    @Step("Выбрать услугу Регистрация смерти")
    public DeathCitizenDataPage selectDeath() {
        log.info("Выбираем услугу: Смерть");
        Wait.waitClickable(driver, deathButton, SHORT_TIMEOUT);
        deathButton.click();
        log.info("Услуга выбрана: Регистрация смерти");
        return new DeathCitizenDataPage(driver);

    }
}
