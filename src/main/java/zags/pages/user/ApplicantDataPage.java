package zags.pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import zags.core.Wait;
import zags.models.Application;
import zags.pages.MainPage;
import static zags.pages.CommonLocators.*;

public class ApplicantDataPage {
    private WebDriver driver;
    @FindBy(xpath = LAST_NAME_INPUT)
    private WebElement applicantLastName;
    @FindBy(xpath = FIRST_NAME_INPUT)
    private WebElement applicantFirstName;
    @FindBy(xpath = MIDDLE_NAME_INPUT)
    private WebElement applicantMiddleName;
    @FindBy(xpath = PHONE_INPUT)
    private WebElement applicantPhone;
    @FindBy(xpath = PASSPORT_INPUT)
    private WebElement applicantPassport;
    @FindBy(xpath = ADDRESS_INPUT)
    private WebElement applicantAddress;
    @FindBy(xpath = NEXT_BUTTON)
    private WebElement applicantPageNextButton;
    @FindBy(xpath = CLOSE_BUTTON )
    private WebElement applicantPageCloseButton;

    public ServiceSelectionPage applicantFillAndNext(Application app) {
        applicantLastName.sendKeys(app.getPersonalLastName());
        applicantFirstName.sendKeys(app.getPersonalFirstName());
        applicantMiddleName.sendKeys(app.getPersonalMiddleName());
        applicantPhone.sendKeys(app.getPersonalPhone());
        applicantPassport.sendKeys(app.getPersonalPassport());
        applicantAddress.sendKeys(app.getPersonalAddress());
        Wait.waitClickable(driver,applicantPageNextButton, Wait.SHORT_TIMEOUT);
        applicantPageNextButton.click();
        return new ServiceSelectionPage(driver);
    }

    public MainPage clickApplicantButtonClose() {
        applicantPageCloseButton.click();
        return new MainPage(driver);
    }


    public ApplicantDataPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
       Wait.waitVisibility(driver,applicantFirstName, Wait.SHORT_TIMEOUT);
    }
}
