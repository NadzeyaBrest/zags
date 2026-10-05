package zags.pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import zags.core.Wait;
import zags.models.Application;
import zags.pages.MainPage;

public class DeathCitizenDataPage {
    private WebDriver driver;
    private Wait wait;
    @FindBy(xpath = "//label[text()='Фамилия']/../following-sibling::input")
    private WebElement deathCitizenLastName;

    @FindBy(xpath = "//label[text()='Имя']/../following-sibling::input")
    private WebElement deathCitizenFirstName;
    @FindBy(xpath = "//label[text()='Отчество']/../following-sibling::input")
    private WebElement deathCitizenMiddleName;
    @FindBy(xpath = "//label[text()='Дата рождения']/../following-sibling::input")
    private WebElement deathCitizenBirthDate;
    @FindBy(xpath = "//label[text()='Пол']/../following-sibling::input")
    private WebElement deathCitizenGender;

    @FindBy(xpath = "//label[text()='Номер паспорта']/../following-sibling::input")
    private WebElement deathCitizenPassport;

    @FindBy(xpath = "//label[text()='Адрес прописки']/../following-sibling::input")
    private WebElement deathCitizenAddress;

    @FindBy(xpath = "//button[contains(text(), 'Далее')]")
    private WebElement deathCitizenNextButton;

    @FindBy(xpath = "//button[contains(text(), 'Закрыть')]")
    private WebElement deathCitizenCloseButton;
    @FindBy(xpath = "//button[contains(text(), 'Назад')]")
    private WebElement deathCitizenBackButton;

    public DeathCitizenDataPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new Wait(driver);
        PageFactory.initElements(driver, this);
        wait.seconds(2).until(ExpectedConditions.visibilityOf(deathCitizenLastName));
    }

    public DeathServiceDataPage deathCitizenFillAndNext(Application app) {
        deathCitizenLastName.sendKeys(app.getCitizenLastName());
        deathCitizenFirstName.sendKeys(app.getCitizenFirstName());
        deathCitizenMiddleName.sendKeys(app.getCitizenMiddleName());
        deathCitizenBirthDate.sendKeys(app.getCitizenBirthDate());
        deathCitizenGender.sendKeys(app.getCitizenGender());
        deathCitizenPassport.sendKeys(app.getCitizenPassport());
        deathCitizenAddress.sendKeys(app.getCitizenAddress());
        wait.seconds(2).until(ExpectedConditions.elementToBeClickable(deathCitizenNextButton));
        deathCitizenNextButton.click();
        return new DeathServiceDataPage(driver);
    }

    public ServiceSelectionPage clickBack() {
        deathCitizenBackButton.click();
        return new ServiceSelectionPage(driver);
    }

    public MainPage clickClose() {
        deathCitizenCloseButton.click();
        return new MainPage(driver);
    }
}
