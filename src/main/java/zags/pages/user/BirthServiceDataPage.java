package zags.pages.user;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import zags.core.Wait;
import zags.models.Application;
import zags.pages.MainPage;

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
    @FindBy(xpath =  "//label[contains(text(),'" + BIRTH_PLACE + "')]/../following-sibling::input")
    private WebElement birthPlace;
    @FindBy(xpath = "//label[contains(text(),'" + BIRTH_MOTHER + "')]/../following-sibling::input")
    private WebElement birthMother;
    @FindBy(xpath = "//label[contains(text(),'" + BIRTH_FATHER + "')]/../following-sibling::input")
    private WebElement birthFather;
    @FindBy(xpath = "//label[contains(text(),'" + BIRTH_GRANDMOTHER + "')]/../following-sibling::input")
    private WebElement birthGrandmother;
    @FindBy(xpath =  "//label[contains(text(),'" + BIRTH_GRANDFATHER + "')]/../following-sibling::input")
    private WebElement birthGrandfather;
    @FindBy(xpath = "//button[contains(text(),'" + FINISH + "')]")
    private WebElement birthFinishButton;
    @FindBy(xpath =  "//button[contains(text(),'" + NEXT + "')]")
    private WebElement birthBackButton;
    @FindBy(xpath = "//button[contains(text(),'" + CLOSE + "')]")
    private WebElement birthCloseButton;

    public BirthServiceDataPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        Wait.waitVisibility(driver,birthPlace,SHORT_TIMEOUT);
    }

    public StatusPage birthServiceFillAndFinish(Application app) {
        birthPlace.sendKeys(app.getBirthPlace());
        birthMother.sendKeys(app.getBirthMother());
        birthFather.sendKeys(app.getBirthFather());
        birthGrandmother.sendKeys(app.getBirthGrandmother());
        birthGrandfather.sendKeys(app.getBirthGrandfather());
        Wait.waitClickable(driver,birthFinishButton,SHORT_TIMEOUT);
        birthFinishButton.click();
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