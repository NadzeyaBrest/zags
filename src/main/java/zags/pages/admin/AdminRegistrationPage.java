package zags.pages.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import zags.core.Wait;
import zags.models.Admin;
import zags.pages.MainPage;

import static zags.core.Constants.BIRTH_DATE;
import static zags.core.Constants.CLOSE;
import static zags.core.Constants.FIRST_NAME;
import static zags.core.Constants.LAST_NAME;
import static zags.core.Constants.MIDDLE_NAME;
import static zags.core.Constants.NEXT;
import static zags.core.Constants.PASSPORT;
import static zags.core.Constants.PHONE;
import static zags.core.Constants.SHORT_TIMEOUT;

public class AdminRegistrationPage {
    private final WebDriver driver;

    @FindBy(xpath =  "//label[contains(text(),'" + LAST_NAME + "')]/../following-sibling::input")
    private WebElement adminLastName;

    @FindBy(xpath =  "//label[contains(text(),'" + FIRST_NAME + "')]/../following-sibling::input")
    private WebElement adminFirstName;

    @FindBy(xpath = "//label[contains(text(),'" + MIDDLE_NAME + "')]/../following-sibling::input")
    private WebElement adminMiddleName;

    @FindBy(xpath = "//label[contains(text(),'" + PHONE + "')]/../following-sibling::input")
    private WebElement adminPhone;

    @FindBy(xpath = "//label[contains(text(),'" + PASSPORT + "')]/../following-sibling::input")
    private WebElement adminPassport;

    @FindBy(xpath =  "//label[contains(text(),'" + BIRTH_DATE + "')]/../following-sibling::input")
    private WebElement adminBirthDate;

    @FindBy(xpath = "//button[contains(text(),'" + NEXT + "')]")
    private WebElement adminRegistrationNextButton;

    @FindBy(xpath = "//button[contains(text(),'" + CLOSE + "')]")
    private WebElement adminRegistrationCloseButton;

    public AdminRegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
       Wait.waitVisibility(driver,adminLastName, SHORT_TIMEOUT) ;
    }

    public AdminTablePage adminRegistrationFillAndNext(Admin admin) {
        adminLastName.sendKeys(admin.getLastName());
        adminFirstName.sendKeys(admin.getFirstName());
        adminMiddleName.sendKeys(admin.getMiddleName());
        adminPhone.sendKeys(admin.getPhone());
        adminPassport.sendKeys(admin.getPassportNumber());
        adminBirthDate.sendKeys(admin.getBirthDate());
       Wait.waitClickable(driver,adminRegistrationNextButton,SHORT_TIMEOUT);
        adminRegistrationNextButton.click();
        return new AdminTablePage(driver);
    }

    public MainPage clickClose() {
        adminRegistrationCloseButton.click();
        return new MainPage(driver);
    }
}