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

import static zags.pages.CommonLocators.*;


public class MarriageServiceDataPage {
    private static final Logger log = LogManager.getLogger(BirthCitizenDataPage.class);
    private final WebDriver driver;
    @FindBy(xpath = MARRIAGE_DATE_INPUT)
    private WebElement marriageDate;
    @FindBy(xpath = MARRIAGE_NEW_LAST_NAME_INPUT)
    private WebElement marriageNewLastName;
    @FindBy(xpath = MARRIAGE_SPOUSE_LAST_NAME_INPUT )
    private WebElement marriageSpouseLastName;
    @FindBy(xpath =  MARRIAGE_SPOUSE_FIRST_NAME_INPUT)
    private WebElement marriageSpouseFirstName;
    @FindBy(xpath = MARRIAGE_SPOUSE_MIDDLE_NAME_INPUT)
    private WebElement marriageSpouseMiddleName;
    @FindBy(xpath = MARRIAGE_SPOUSE_BIRTH_DATE_INPUT)
    private WebElement marriageSpouseBirthDate;
    @FindBy(xpath = MARRIAGE_SPOUSE_PASSPORT_INPUT)
    private WebElement marriageSpousePassport;
    @FindBy(xpath =  FINISH_BUTTON)
    private WebElement marriageFinishButton;
    @FindBy(xpath = BACK_BUTTON)
    private WebElement marriageBackButton;
    @FindBy(xpath = CLOSE_BUTTON)
    private WebElement marriageCloseButton;

    public MarriageServiceDataPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log.info("Открыта страница данных услуги (брак)");
        Wait.waitVisibility(driver,marriageDate,Wait.SHORT_TIMEOUT);
    }
    @Step("Заполнить данные о втором супруге и нажать  Завершить")
    public StatusPage marriageServiceFillAndFinish(Application app) {
        log.info("Заполняем данные об услуге (брак)");
        marriageDate.sendKeys(app.getMarriageDate());
        marriageNewLastName.sendKeys(app.getMarriageNewLastName());
        marriageSpouseLastName.sendKeys(app.getMarriageSpouseLastName());
        marriageSpouseFirstName.sendKeys(app.getMarriageSpouseFirstName());
        marriageSpouseMiddleName.sendKeys(app.getMarriageSpouseMiddleName());
        marriageSpouseBirthDate.sendKeys(app.getMarriageSpouseBirthDate());
        marriageSpousePassport.sendKeys(app.getMarriageSpousePassport());
        Wait.waitClickable(driver,marriageFinishButton,Wait.SHORT_TIMEOUT);
        marriageFinishButton.click();
        log.info("Отправляем данные об услуге (брак)");
        return new StatusPage(driver);
    }

    public MarriageCitizenDataPage clickMarriageServiceNextButton() {
        marriageBackButton.click();
        return new MarriageCitizenDataPage(driver);
    }

    public MainPage clickMarriageServiceCloseButton() {
        marriageCloseButton.click();
        return new MainPage(driver);
    }
}