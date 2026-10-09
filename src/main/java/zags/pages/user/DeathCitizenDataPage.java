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

public class DeathCitizenDataPage {
    private final WebDriver driver;
    private static final Logger log = LogManager.getLogger(DeathCitizenDataPage.class);
    @FindBy(xpath = "//label[contains(text(),'" + LAST_NAME + "')]/../following-sibling::input")
    private WebElement deathCitizenLastName;
    @FindBy(xpath = "//label[contains(text(),'" + FIRST_NAME + "')]/../following-sibling::input")
    private WebElement deathCitizenFirstName;
    @FindBy(xpath = "//label[contains(text(),'" + MIDDLE_NAME + "')]/../following-sibling::input")
    private WebElement deathCitizenMiddleName;
    @FindBy(xpath = "//label[contains(text(),'" + BIRTH_DATE + "')]/../following-sibling::input")
    private WebElement deathCitizenBirthDate;
    @FindBy(xpath = "//label[contains(text(),'" + GENDER + "')]/../following-sibling::input")
    private WebElement deathCitizenGender;
    @FindBy(xpath = "//label[contains(text(),'" + PASSPORT + "')]/../following-sibling::input")
    private WebElement deathCitizenPassport;
    @FindBy(xpath = "//label[contains(text(),'" + ADDRESS + "')]/../following-sibling::input")
    private WebElement deathCitizenAddress;
    @FindBy(xpath = "//button[contains(text(),'" + NEXT + "')]")
    private WebElement deathCitizenNextButton;
    @FindBy(xpath = "//button[contains(text(),'" + CLOSE + "')]")
    private WebElement deathCitizenCloseButton;
    @FindBy(xpath = "//button[contains(text(),'" + BACK + "')]")
    private WebElement deathCitizenBackButton;

    public DeathCitizenDataPage(WebDriver driver) {
        this.driver = driver;
        log.info("Открыта страница данных гражданина (смерть)");
        PageFactory.initElements(driver, this);
        Wait.waitVisibility(driver, deathCitizenLastName, SHORT_TIMEOUT);
    }

    @Step("Заполнить данные гражданина (смерть) и нажать Далее")
    public DeathServiceDataPage deathCitizenFillAndNext(Application app) {
        log.info("Заполняем данные гражданина (смерть)");
        deathCitizenLastName.sendKeys(app.getCitizenLastName());
        deathCitizenFirstName.sendKeys(app.getCitizenFirstName());
        deathCitizenMiddleName.sendKeys(app.getCitizenMiddleName());
        deathCitizenBirthDate.sendKeys(app.getCitizenBirthDate());
        deathCitizenGender.sendKeys(app.getCitizenGender());
        deathCitizenPassport.sendKeys(app.getCitizenPassport());
        deathCitizenAddress.sendKeys(app.getCitizenAddress());
        Wait.waitClickable(driver, deathCitizenNextButton, SHORT_TIMEOUT);
        deathCitizenNextButton.click();
        log.info("Данные гражданина (смерть) отправлены");
        return new DeathServiceDataPage(driver);
    }

    @Step("Вернуться на страницу выбора услуги (данные гражданина, смерть)")
    public ServiceSelectionPage clickDeathCitizenBackButton() {
        log.info("Клик: Назад (возврат на страницу выбора услуги)");
        deathCitizenBackButton.click();
        return new ServiceSelectionPage(driver);
    }

    @Step("Закрыть страницу данных гражданина (смерть)")
    public MainPage clickDeathCitizenCloseButton() {
        log.info("Клик: Закрыть страницу данных гражданина (смерть) (переход на главную)");
        deathCitizenCloseButton.click();
        return new MainPage(driver);
    }
}
