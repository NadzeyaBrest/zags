package zags.pages.user;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import zags.core.Wait;
import zags.models.Application;
import zags.pages.MainPage;

public class BirthServiceDataPage {
    private final WebDriver driver;
    private Wait wait;
    @FindBy(xpath = "//label[contains(text(),'Место рождения')]/../following-sibling::input")
    private WebElement birthPlace;

    @FindBy(xpath = "//label[contains(text(),'Мать')]/../following-sibling::input")
    private WebElement birthMother;

    @FindBy(xpath = "//label[contains(text(),'Отец')]/../following-sibling::input")
    private WebElement birthFather;

    @FindBy(xpath = "//label[contains(text(),'Бабушка')]/../following-sibling::input")
    private WebElement birthGrandmother;

    @FindBy(xpath = "//label[contains(text(),'Дедушка')]/../following-sibling::input")
    private WebElement birthGrandfather;

    @FindBy(xpath = "//button[contains(text(),'Завершить')]")
    private WebElement birthFinishButton;

    @FindBy(xpath = "//button[contains(text(),'Назад')]")
    private WebElement birthBackButton;

    @FindBy(xpath = "//button[contains(text(),'Закрыть')]")
    private WebElement birthCloseButton;

    public BirthServiceDataPage(WebDriver driver) {
        this.driver = driver;
        this.wait=new Wait(driver);
        PageFactory.initElements(driver, this);
        wait.seconds(2).until(ExpectedConditions.visibilityOf(birthPlace));
    }

    public StatusPage birthServiceFillAndFinish(Application app) {
        birthPlace.sendKeys(app.getBirthPlace());
        birthMother.sendKeys(app.getBirthMother());
        birthFather.sendKeys(app.getBirthFather());
        birthGrandmother.sendKeys(app.getBirthGrandmother());
        birthGrandfather.sendKeys(app.getBirthGrandfather());
        wait.seconds(2).until(ExpectedConditions.elementToBeClickable(birthFinishButton));
        birthFinishButton.click();
        return new StatusPage(driver);
    }


    public BirthCitizenDataPage clickBack() {
        birthBackButton.click();
        return new BirthCitizenDataPage(driver);
    }

    public MainPage clickClose() {
        birthCloseButton.click();
        return new MainPage(driver);
    }
}