package zags.pages.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import zags.core.Wait;
import zags.models.Admin;
import zags.pages.MainPage;

public class AdminRegistrationPage {
    private final WebDriver driver;
    private  Wait wait;

    @FindBy(xpath = "//label[text()='Фамилия']/../following-sibling::input")
    private WebElement adminLastName;

    @FindBy(xpath = "//label[text()='Имя']/../following-sibling::input")
    private WebElement adminFirstName;

    @FindBy(xpath = "//label[text()='Отчество']/../following-sibling::input")
    private WebElement adminMiddleName;

    @FindBy(xpath = "//label[text()='Телефон']/../following-sibling::input")
    private WebElement adminPhone;

    @FindBy(xpath = "//label[text()='Номер паспорта']/../following-sibling::input")
    private WebElement adminPassport;

    @FindBy(xpath = "//label[text()='Дата рождения']/../following-sibling::input")
    private WebElement adminBirthDate;

    @FindBy(xpath = "//button[contains(text(),'Далее')]")
    private WebElement adminRegistrationNextButton;

    @FindBy(xpath = "//button[contains(text(),'Закрыть')]")
    private WebElement adminRegistrationCloseButton;

    public AdminRegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new Wait(driver);
        PageFactory.initElements(driver, this);
        wait.seconds(2).until(ExpectedConditions.visibilityOf(adminLastName));
    }

    public AdminTablePage adminRegistrationFillAndNext(Admin admin) {
        adminLastName.sendKeys(admin.getLastName());
        adminFirstName.sendKeys(admin.getFirstName());
        adminMiddleName.sendKeys(admin.getMiddleName());
        adminPhone.sendKeys(admin.getPhone());
        adminPassport.sendKeys(admin.getPassportNumber());
        adminBirthDate.sendKeys(admin.getBirthDate());
        wait.seconds(2).until(ExpectedConditions.elementToBeClickable(adminRegistrationNextButton));
        adminRegistrationNextButton.click();
        return new AdminTablePage(driver);
    }

    public MainPage clickClose() {
        adminRegistrationCloseButton.click();
        return new MainPage(driver);
    }
}