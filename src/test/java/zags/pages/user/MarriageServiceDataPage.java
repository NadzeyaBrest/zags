package zags.pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import zags.core.DriverManager;
import zags.models.Application;
import zags.pages.MainPage;


public class MarriageServiceDataPage {
    private final WebDriver driver;

    @FindBy(xpath = "//label[text()='Дата регистрации']/../following-sibling::input")
    private WebElement marriageDate;

    @FindBy(xpath = "//label[text()='Новая фамилия']/../following-sibling::input")
    private WebElement marriageNewLastName;

    @FindBy(xpath = "//label[text()='Фамилия супруга/и']/../following-sibling::input")
    private WebElement marriageSpouseLastName;

    @FindBy(xpath = "//label[text()='Имя супруга/и']/../following-sibling::input")
    private WebElement marriageSpouseFirstName;

    @FindBy(xpath = "//label[text()='Отчество супруга/и']/../following-sibling::input")
    private WebElement marriageSpouseMiddleName;

    @FindBy(xpath = "//label[text()='Дата рождения супруга/и']/../following-sibling::input")
    private WebElement marriageSpouseBirthDate;

    @FindBy(xpath = "//label[text()='Номер паспорта супруга/и']/../following-sibling::input")
    private WebElement marriageSpousePassport;

    @FindBy(xpath = "//button[contains(text(),'Завершить')]")
    private WebElement marriageFinishButton;

    @FindBy(xpath = "//button[contains(text(),'Назад')]")
    private WebElement marriageBackButton;

    @FindBy(xpath = "//button[contains(text(),'Закрыть')]")
    private WebElement marriageCloseButton;

    public MarriageServiceDataPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        DriverManager.getInstance().getWait().until(ExpectedConditions.visibilityOf(marriageDate));
    }

    public StatusPage marriageServiceFillAndFinish (Application app) {
        marriageDate.sendKeys(app.getMarriageDate());
        marriageNewLastName.sendKeys(app.getMarriageNewLastName());
        marriageSpouseLastName.sendKeys(app.getMarriageSpouseLastName());
        marriageSpouseFirstName.sendKeys(app.getMarriageSpouseFirstName());
        marriageSpouseMiddleName.sendKeys(app.getMarriageSpouseMiddleName());
        marriageSpouseBirthDate.sendKeys(app.getMarriageSpouseBirthDate());
        marriageSpousePassport.sendKeys(app.getMarriageSpousePassport());
        DriverManager.getInstance().getWait()
                .until(ExpectedConditions.elementToBeClickable(marriageFinishButton));
        marriageFinishButton.click();
        return new StatusPage(driver);
    }

    public MarriageCitizenDataPage clickBack() {
        marriageBackButton.click();
        return new MarriageCitizenDataPage(driver);
    }

    public MainPage clickClose() {
        marriageCloseButton.click();
        return new MainPage(driver);
    }
}