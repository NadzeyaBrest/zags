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

public class BirthServiceDataPage {

    private static final Logger log = LogManager.getLogger(BirthCitizenDataPage.class);
    private final WebDriver driver;
    @FindBy(xpath = BIRTH_PLACE_INPUT)
    private WebElement birthPlace;
    @FindBy(xpath = BIRTH_MOTHER_INPUT )
    private WebElement birthMother;
    @FindBy(xpath = BIRTH_FATHER_INPUT)
    private WebElement birthFather;
    @FindBy(xpath = BIRTH_GRANDMOTHER_INPUT)
    private WebElement birthGrandmother;
    @FindBy(xpath = BIRTH_GRANDFATHER_INPUT)
    private WebElement birthGrandfather;
    @FindBy(xpath = FINISH_BUTTON)
    private WebElement birthFinishButton;
    @FindBy(xpath = NEXT_BUTTON)
    private WebElement birthBackButton;
    @FindBy(xpath = CLOSE_BUTTON)
    private WebElement birthCloseButton;

    public BirthServiceDataPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log.info("Открыта страница регистрации услуги(рождение)");
        Wait.waitVisibility(driver,birthPlace,Wait.SHORT_TIMEOUT);
    }
    @Step("Заполнить данные о рождении и нажать 'Завершить'")
    public StatusPage birthServiceFillAndFinish(Application app) {
        log.info("Заполняем данные услуги (рождение)");
        birthPlace.sendKeys(app.getBirthPlace());
        birthMother.sendKeys(app.getBirthMother());
        birthFather.sendKeys(app.getBirthFather());
        birthGrandmother.sendKeys(app.getBirthGrandmother());
        birthGrandfather.sendKeys(app.getBirthGrandfather());
        Wait.waitClickable(driver,birthFinishButton,Wait.SHORT_TIMEOUT);
        birthFinishButton.click();
        log.info("Данные услуги (рождение) отправлены");
        return new StatusPage(driver);
    }

    public BirthCitizenDataPage clickBirthServiceBackButton() {
        birthBackButton.click();
        return new BirthCitizenDataPage(driver);
    }

    public MainPage clickBirthServiceCloseButton() {
        birthCloseButton.click();
        return new MainPage(driver);
    }
}