package zags.pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import zags.core.DriverManager;
import zags.models.Application;
import zags.pages.MainPage;

public class BirthCitizenDataPage {
    private final WebDriver driver;
    @FindBy(xpath = "//label[text()='Фамилия']/../following-sibling::input")
    private WebElement birthCitizenLastName;

    @FindBy(xpath = "//label[text()='Имя']/../following-sibling::input")
    private WebElement birthCitizenFirstName;
    @FindBy(xpath = "//label[text()='Отчество']/../following-sibling::input")
    private WebElement birthCitizenMiddleName;
    @FindBy(xpath = "//label[text()='Дата рождения']/../following-sibling::input")
    private WebElement birthCitizenBirthDate;
    @FindBy(xpath = "//label[text()='Пол']/../following-sibling::input")
    private WebElement birthCitizenGender;

    @FindBy(xpath = "//label[text()='Номер паспорта']/../following-sibling::input")
    private WebElement birthCitizenPassport;

    @FindBy(xpath = "//label[text()='Адрес прописки']/../following-sibling::input")
    private WebElement birthCitizenAddress;

    @FindBy(xpath = "//button[contains(text(), 'Далее')]")
    private WebElement birthCitizenNextButton;

    @FindBy(xpath = "//button[contains(text(), 'Закрыть')]")
    private WebElement birthCitizenCloseButton;
    @FindBy(xpath = "//button[contains(text(), 'Назад')]")
    private WebElement birthCitizenBackButton;

    public BirthCitizenDataPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        DriverManager.getInstance().getWait().until(ExpectedConditions.visibilityOf(birthCitizenLastName));
    }

    public BirthServiceDataPage birthCitizenDataFillAndNext(Application app) {
        birthCitizenLastName.sendKeys(app.getCitizenLastName());
        birthCitizenFirstName.sendKeys(app.getCitizenFirstName());
        birthCitizenMiddleName.sendKeys(app.getCitizenMiddleName());
        birthCitizenBirthDate.sendKeys(app.getCitizenBirthDate());
        birthCitizenGender.sendKeys(app.getCitizenGender());
        birthCitizenPassport.sendKeys(app.getCitizenPassport());
        birthCitizenAddress.sendKeys(app.getCitizenAddress());
        DriverManager.getInstance().getWait()
                .until(ExpectedConditions.elementToBeClickable(birthCitizenNextButton));
        birthCitizenNextButton.click();
        return new BirthServiceDataPage(driver);

    }

    public ServiceSelectionPage birthCitizenBackButton() {
        birthCitizenBackButton.click();
        return new ServiceSelectionPage(driver);

    }

    public MainPage clickClose() {
        birthCitizenCloseButton.click();
        return new MainPage(driver);
    }

}
