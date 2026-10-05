package zags.pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import zags.core.Wait;
import zags.models.Application;
import zags.pages.MainPage;

public class MarriageCitizenDataPage {
    private WebDriver driver;
    private Wait wait;
    @FindBy(xpath = "//label[text()='Фамилия']/../following-sibling::input")
    private WebElement marriageCitizenLastName;

    @FindBy(xpath = "//label[text()='Имя']/../following-sibling::input")
    private WebElement marriageCitizenFirstName;
    @FindBy(xpath = "//label[text()='Отчество']/../following-sibling::input")
    private WebElement marriageCitizenMiddleName;
    @FindBy(xpath = "//label[text()='Дата рождения']/../following-sibling::input")
    private WebElement marriageCitizenBirthDate;
    @FindBy(xpath = "//label[text()='Пол']/../following-sibling::input")
    private WebElement marriageCitizenGender;

    @FindBy(xpath = "//label[text()='Номер паспорта']/../following-sibling::input")
    private WebElement marriageCitizenPassport;

    @FindBy(xpath = "//label[text()='Адрес прописки']/../following-sibling::input")
    private WebElement marriageCitizenAddress;

    @FindBy(xpath = "//button[contains(text(), 'Далее')]")
    private WebElement marriageCitizenNextButton;

    @FindBy(xpath = "//button[contains(text(), 'Закрыть')]")
    private WebElement marriageCitizenCloseButton;
    @FindBy(xpath = "//button[contains(text(), 'Назад')]")
    private WebElement marriageCitizenBackButton;

    public MarriageCitizenDataPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new Wait(driver);
        PageFactory.initElements(driver, this);
        wait.seconds(2).until(ExpectedConditions.visibilityOf(marriageCitizenLastName));
    }

    public MarriageServiceDataPage marriageCitizenFillAndNext(Application app) {
        marriageCitizenLastName.sendKeys(app.getCitizenLastName());
        marriageCitizenFirstName.sendKeys(app.getCitizenFirstName());
        marriageCitizenMiddleName.sendKeys(app.getCitizenMiddleName());
        marriageCitizenBirthDate.sendKeys(app.getCitizenBirthDate());
        marriageCitizenGender.sendKeys(app.getCitizenGender());
        marriageCitizenPassport.sendKeys(app.getCitizenPassport());
        marriageCitizenAddress.sendKeys(app.getCitizenAddress());
        wait.seconds(2).until(ExpectedConditions.elementToBeClickable(marriageCitizenNextButton));
        marriageCitizenNextButton.click();
        return new MarriageServiceDataPage(driver);
    }

    public ServiceSelectionPage clickBack() {
        marriageCitizenBackButton.click();
        return new ServiceSelectionPage(driver);
    }

    public MainPage clickClose() {
        marriageCitizenCloseButton.click();
        return new MainPage(driver);
    }
}
