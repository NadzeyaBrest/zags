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
import static zags.core.Constants.BIRTH_FATHER;
import static zags.core.Constants.BIRTH_GRANDFATHER;
import static zags.core.Constants.BIRTH_GRANDMOTHER;
import static zags.core.Constants.BIRTH_MOTHER;
import static zags.core.Constants.BIRTH_PLACE;
import static zags.core.Constants.CLOSE;
import static zags.core.Constants.FINISH;
import static zags.core.Constants.NEXT;
import static zags.core.Constants.SHORT_TIMEOUT;

public class BirthServiceDataPage {
    private final WebDriver driver;
    private static final Logger log = LogManager.getLogger(BirthServiceDataPage.class);
    @FindBy(xpath = "//label[contains(text(),'" + BIRTH_PLACE + "')]/../following-sibling::input")
    private WebElement birthPlace;
    @FindBy(xpath = "//label[contains(text(),'" + BIRTH_MOTHER + "')]/../following-sibling::input")
    private WebElement birthMother;
    @FindBy(xpath = "//label[contains(text(),'" + BIRTH_FATHER + "')]/../following-sibling::input")
    private WebElement birthFather;
    @FindBy(xpath = "//label[contains(text(),'" + BIRTH_GRANDMOTHER + "')]/../following-sibling::input")
    private WebElement birthGrandmother;
    @FindBy(xpath = "//label[contains(text(),'" + BIRTH_GRANDFATHER + "')]/../following-sibling::input")
    private WebElement birthGrandfather;
    @FindBy(xpath = "//button[contains(text(),'" + FINISH + "')]")
    private WebElement birthFinishButton;
    @FindBy(xpath = "//button[contains(text(),'" + BACK + "')]")
    private WebElement birthBackButton;
    @FindBy(xpath = "//button[contains(text(),'" + CLOSE + "')]")
    private WebElement birthCloseButton;

    public BirthServiceDataPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log.info("Открыта страница регистрации услуги (рождение)");
        Wait.waitVisibility(driver, birthPlace, SHORT_TIMEOUT);
    }

    @Step("Заполнить данные о рождении и нажать Завершить")
    public StatusPage birthServiceFillAndFinish(Application app) {
        log.info("Заполняем данные услуги (рождение)");
        birthPlace.sendKeys(app.getBirthPlace());
        birthMother.sendKeys(app.getBirthMother());
        birthFather.sendKeys(app.getBirthFather());
        birthGrandmother.sendKeys(app.getBirthGrandmother());
        birthGrandfather.sendKeys(app.getBirthGrandfather());
        Wait.waitClickable(driver, birthFinishButton, SHORT_TIMEOUT);
        birthFinishButton.click();
        log.info("Данные услуги (рождение) отправлены");
        return new StatusPage(driver);
    }

    @Step("Вернуться на страницу данных гражданина (рождение)")
    public BirthCitizenDataPage clickBirthServiceBackButton() {
        log.info("Клик: Назад (возврат на страницу данных гражданина, рождение)");
        birthBackButton.click();
        return new BirthCitizenDataPage(driver);
    }

    @Step("Закрыть страницу услуги (рождение)")
    public MainPage clickBirthServiceCloseButton() {
        log.info("Клик: Закрыть страницу услуги (рождение) (переход на главную)");
        birthCloseButton.click();
        return new MainPage(driver);
    }
}