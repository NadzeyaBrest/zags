package zags.pages.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import zags.core.Wait;
import zags.models.Admin;
import zags.pages.MainPage;

import static zags.pages.CommonLocators.*;

public class AdminRegistrationPage {
    private final WebDriver driver;

    @FindBy(xpath = LAST_NAME_INPUT)
    private WebElement adminLastName;

    @FindBy(xpath = FIRST_NAME_INPUT )
    private WebElement adminFirstName;

    @FindBy(xpath = MIDDLE_NAME_INPUT )
    private WebElement adminMiddleName;

    @FindBy(xpath = PHONE_INPUT )
    private WebElement adminPhone;

    @FindBy(xpath = PASSPORT_INPUT)
    private WebElement adminPassport;

    @FindBy(xpath = BIRTH_DATE_INPUT)
    private WebElement adminBirthDate;

    @FindBy(xpath = NEXT_BUTTON)
    private WebElement adminRegistrationNextButton;

    @FindBy(xpath = CLOSE_BUTTON)
    private WebElement adminRegistrationCloseButton;

    public AdminRegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
       Wait.waitVisibility(driver,adminLastName, Wait.SHORT_TIMEOUT) ;
    }

    public AdminTablePage adminRegistrationFillAndNext(Admin admin) {
        adminLastName.sendKeys(admin.getLastName());
        adminFirstName.sendKeys(admin.getFirstName());
        adminMiddleName.sendKeys(admin.getMiddleName());
        adminPhone.sendKeys(admin.getPhone());
        adminPassport.sendKeys(admin.getPassportNumber());
        adminBirthDate.sendKeys(admin.getBirthDate());
       Wait.waitClickable(driver,adminRegistrationNextButton,Wait.SHORT_TIMEOUT);
        adminRegistrationNextButton.click();
        return new AdminTablePage(driver);
    }

    public MainPage clickClose() {
        adminRegistrationCloseButton.click();
        return new MainPage(driver);
    }
}