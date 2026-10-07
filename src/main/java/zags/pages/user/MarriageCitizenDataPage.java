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

public class MarriageCitizenDataPage {
    private static final Logger log = LogManager.getLogger(BirthCitizenDataPage.class);
    private WebDriver driver;
    @FindBy(xpath = LAST_NAME_INPUT)
    private WebElement marriageCitizenLastName;
    @FindBy(xpath = FIRST_NAME_INPUT )
    private WebElement marriageCitizenFirstName;
    @FindBy(xpath = MIDDLE_NAME_INPUT )
    private WebElement marriageCitizenMiddleName;
    @FindBy(xpath = BIRTH_DATE_INPUT )
    private WebElement marriageCitizenBirthDate;
    @FindBy(xpath = GENDER_INPUT)
    private WebElement marriageCitizenGender;
    @FindBy(xpath = PASSPORT_INPUT )
    private WebElement marriageCitizenPassport;
    @FindBy(xpath =ADDRESS_INPUT)
    private WebElement marriageCitizenAddress;
    @FindBy(xpath = NEXT_BUTTON)
    private WebElement marriageCitizenNextButton;
    @FindBy(xpath =CLOSE_BUTTON )
    private WebElement marriageCitizenCloseButton;
    @FindBy(xpath = BACK_BUTTON)
    private WebElement marriageCitizenBackButton;

    public MarriageCitizenDataPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log.info("Открыта страница данных гражданина (брак)");
        Wait.waitVisibility(driver,marriageCitizenLastName,Wait.SHORT_TIMEOUT);
    }
    @Step("Заполнить данные гражданина для рождения и нажать Далее")
    public MarriageServiceDataPage marriageCitizenFillAndNext(Application app) {
        log.info("Заполняем данные гражданина (брак)");
        marriageCitizenLastName.sendKeys(app.getCitizenLastName());
        marriageCitizenFirstName.sendKeys(app.getCitizenFirstName());
        marriageCitizenMiddleName.sendKeys(app.getCitizenMiddleName());
        marriageCitizenBirthDate.sendKeys(app.getCitizenBirthDate());
        marriageCitizenGender.sendKeys(app.getCitizenGender());
        marriageCitizenPassport.sendKeys(app.getCitizenPassport());
        marriageCitizenAddress.sendKeys(app.getCitizenAddress());
        Wait.waitClickable(driver,marriageCitizenNextButton,Wait.SHORT_TIMEOUT);
        marriageCitizenNextButton.click();
        log.info("Данные гражданина (брак) отправлены");
        return new MarriageServiceDataPage(driver);
    }
    public ServiceSelectionPage clickMarriageCitizenBackButton() {
        marriageCitizenBackButton.click();
        return new ServiceSelectionPage(driver);
    }
    public MainPage clickMarriageCitizenCloseButton() {
        marriageCitizenCloseButton.click();
        return new MainPage(driver);
    }
}
