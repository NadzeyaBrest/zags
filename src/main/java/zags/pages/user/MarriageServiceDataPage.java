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

import static zags.core.Constants.BACK;
import static zags.core.Constants.CLOSE;
import static zags.core.Constants.FINISH;
import static zags.core.Constants.MARRIAGE_DATE;
import static zags.core.Constants.MARRIAGE_NEW_LAST_NAME;
import static zags.core.Constants.MARRIAGE_SPOUSE_BIRTH_DATE;
import static zags.core.Constants.MARRIAGE_SPOUSE_FIRST_NAME;
import static zags.core.Constants.MARRIAGE_SPOUSE_LAST_NAME;
import static zags.core.Constants.MARRIAGE_SPOUSE_MIDDLE_NAME;
import static zags.core.Constants.MARRIAGE_SPOUSE_PASSPORT;
import static zags.core.Constants.SHORT_TIMEOUT;


public class MarriageServiceDataPage {
    private final WebDriver driver;
    private static final Logger log = LogManager.getLogger(MarriageServiceDataPage.class);
    @FindBy(xpath = "//label[text()='" + MARRIAGE_DATE + "']/../following-sibling::input")
    private WebElement marriageDate;
    @FindBy(xpath ="//label[text()='" + MARRIAGE_NEW_LAST_NAME + "']/../following-sibling::input")
    private WebElement marriageNewLastName;
    @FindBy(xpath =  "//label[text()='" + MARRIAGE_SPOUSE_LAST_NAME + "']/../following-sibling::input")
    private WebElement marriageSpouseLastName;
    @FindBy(xpath = "//label[text()='" + MARRIAGE_SPOUSE_FIRST_NAME + "']/../following-sibling::input")
    private WebElement marriageSpouseFirstName;
    @FindBy(xpath = "//label[text()='" + MARRIAGE_SPOUSE_MIDDLE_NAME + "']/../following-sibling::input")
    private WebElement marriageSpouseMiddleName;
    @FindBy(xpath =  "//label[text()='" + MARRIAGE_SPOUSE_BIRTH_DATE + "']/../following-sibling::input")
    private WebElement marriageSpouseBirthDate;
    @FindBy(xpath = "//label[text()='" + MARRIAGE_SPOUSE_PASSPORT + "']/../following-sibling::input")
    private WebElement marriageSpousePassport;
    @FindBy(xpath =  "//button[contains(text(),'" + FINISH + "')]")
    private WebElement marriageFinishButton;
    @FindBy(xpath = "//button[contains(text(),'" + BACK + "')]")
    private WebElement marriageBackButton;
    @FindBy(xpath = "//button[contains(text(),'" + CLOSE + "')]")
    private WebElement marriageCloseButton;

    public MarriageServiceDataPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log.info("Открыта страница данных услуги (брак)");
        Wait.waitVisibility(driver,marriageDate,SHORT_TIMEOUT);
    }
    @Step("Заполнить данные о браке и нажать Завершить")
    public StatusPage marriageServiceFillAndFinish(Application app) {
        log.info("Заполняем данные об услуге (брак)");
        marriageDate.sendKeys(app.getMarriageDate());
        marriageNewLastName.sendKeys(app.getMarriageNewLastName());
        marriageSpouseLastName.sendKeys(app.getMarriageSpouseLastName());
        marriageSpouseFirstName.sendKeys(app.getMarriageSpouseFirstName());
        marriageSpouseMiddleName.sendKeys(app.getMarriageSpouseMiddleName());
        marriageSpouseBirthDate.sendKeys(app.getMarriageSpouseBirthDate());
        marriageSpousePassport.sendKeys(app.getMarriageSpousePassport());
        Wait.waitClickable(driver,marriageFinishButton,SHORT_TIMEOUT);
        marriageFinishButton.click();
        log.info("Данные услуги (брак) отправлены");
        return new StatusPage(driver);
    }
    @Step("Вернуться на страницу данных гражданина (брак)")
    public MarriageCitizenDataPage clickMarriageServiceNextButton() {
        log.info("Клик: Назад (возврат на страницу данных гражданина, брак)");
        marriageBackButton.click();
        return new MarriageCitizenDataPage(driver);
    }
    @Step("Закрыть страницу услуги (брак)")
    public MainPage clickMarriageServiceCloseButton() {
        log.info("Клик: Закрыть страницу услуги (брак) (переход на главную)");
        marriageCloseButton.click();
        return new MainPage(driver);
    }
}