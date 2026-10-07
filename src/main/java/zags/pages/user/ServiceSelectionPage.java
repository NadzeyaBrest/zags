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

import static zags.pages.CommonLocators.*;

public class ServiceSelectionPage {
    private static final Logger log = LogManager.getLogger(ServiceSelectionPage.class);

    private WebDriver driver;
    @FindBy(xpath =MARRIAGE_SERVICE_BUTTON)
    private WebElement marriageButton;
    @FindBy(xpath =BIRTH_SERVICE_BUTTON)
    private WebElement birthButton;
    @FindBy(xpath =DEATH_SERVICE_BUTTON)
    private WebElement deathButton;
    @FindBy(xpath = BACK_BUTTON)
    private WebElement serviceSectionPageBackButton;
    @FindBy(xpath = CLOSE_BUTTON)
    private WebElement serviceSectionPageCloseButton;

    public ServiceSelectionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log.info("Открыта страница выбора услуги");
        Wait.waitVisibility(driver,marriageButton,Wait.SHORT_TIMEOUT);
    }

    public ApplicantDataPage clickServiceSelectionBackButton() {
        serviceSectionPageBackButton.click();
        return new ApplicantDataPage(driver);

    }

    public MainPage clickServiceSelectionCloseButton() {
        serviceSectionPageCloseButton.click();
        return new MainPage(driver);
    }
    @Step("Выбрать услугу Регистрация брака")
    public MarriageCitizenDataPage selectMarriage() {
        log.info("Выбираем услугу: Брак");
        Wait.waitClickable(driver,marriageButton,Wait.SHORT_TIMEOUT);
        marriageButton.click();
        return new MarriageCitizenDataPage(driver);
    }
    @Step("Выбрать услугу Регистрация рождения")
    public BirthCitizenDataPage selectBirth() {
        log.info("Выбираем услугу: Рождение");
        Wait.waitClickable(driver,birthButton,Wait.SHORT_TIMEOUT);
        birthButton.click();
        return new BirthCitizenDataPage(driver);
    }
    @Step("Выбрать услугу Регистрация смерти")
    public DeathCitizenDataPage selectDeath() {
        log.info("Выбираем услугу: Смерть");
        Wait.waitClickable(driver,deathButton,Wait.SHORT_TIMEOUT);
        deathButton.click();
        return new DeathCitizenDataPage(driver);

    }
}
