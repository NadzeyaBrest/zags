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

public class DeathCitizenDataPage {
    private static final Logger log = LogManager.getLogger(BirthCitizenDataPage.class);
    private WebDriver driver;
    @FindBy(xpath = LAST_NAME_INPUT )
    private WebElement deathCitizenLastName;
    @FindBy(xpath = FIRST_NAME_INPUT )
    private WebElement deathCitizenFirstName;
    @FindBy(xpath = MIDDLE_NAME_INPUT)
    private WebElement deathCitizenMiddleName;
    @FindBy(xpath = BIRTH_DATE_INPUT)
    private WebElement deathCitizenBirthDate;
    @FindBy(xpath = GENDER_INPUT)
    private WebElement deathCitizenGender;
    @FindBy(xpath = PASSPORT_INPUT)
    private WebElement deathCitizenPassport;
    @FindBy(xpath =  ADDRESS_INPUT)
    private WebElement deathCitizenAddress;
    @FindBy(xpath =  NEXT_BUTTON)
    private WebElement deathCitizenNextButton;
    @FindBy(xpath = CLOSE_BUTTON)
    private WebElement deathCitizenCloseButton;
    @FindBy(xpath = BACK_BUTTON)
    private WebElement deathCitizenBackButton;


    public DeathCitizenDataPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log.info("Открыта страница данных гражданина (смерть)");
       Wait.waitVisibility(driver,deathCitizenLastName, Wait.SHORT_TIMEOUT);
    }
    @Step("Заполнить данные гражданина  и нажать кнопку Далее")
    public DeathServiceDataPage deathCitizenFillAndNext(Application app) {
        log.info("Заполняем данные гражданина (смерть)");
        deathCitizenLastName.sendKeys(app.getCitizenLastName());
        deathCitizenFirstName.sendKeys(app.getCitizenFirstName());
        deathCitizenMiddleName.sendKeys(app.getCitizenMiddleName());
        deathCitizenBirthDate.sendKeys(app.getCitizenBirthDate());
        deathCitizenGender.sendKeys(app.getCitizenGender());
        deathCitizenPassport.sendKeys(app.getCitizenPassport());
        deathCitizenAddress.sendKeys(app.getCitizenAddress());
        Wait.waitClickable(driver,deathCitizenNextButton,Wait.SHORT_TIMEOUT);
        deathCitizenNextButton.click();
        log.info("Данные гражданина (смерть ) отправлены");
        return new DeathServiceDataPage(driver);
    }

    public ServiceSelectionPage clickDeathCitizenBackButton() {
        deathCitizenBackButton.click();
        return new ServiceSelectionPage(driver);
    }

    public MainPage clickDeathCitizenCloseButton() {
        deathCitizenCloseButton.click();
        return new MainPage(driver);
    }
}
