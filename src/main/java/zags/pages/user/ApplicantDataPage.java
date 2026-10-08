package zags.pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import zags.core.Wait;
import zags.models.Application;
import zags.pages.MainPage;


import static zags.core.Constants.ADDRESS;
import static zags.core.Constants.CLOSE;
import static zags.core.Constants.FIRST_NAME;
import static zags.core.Constants.LAST_NAME;
import static zags.core.Constants.MIDDLE_NAME;
import static zags.core.Constants.NEXT;
import static zags.core.Constants.PASSPORT;
import static zags.core.Constants.PHONE;
import static zags.core.Constants.SHORT_TIMEOUT;

public class ApplicantDataPage {
    private WebDriver driver;
    @FindBy(xpath = "//label[contains(text(),'" + LAST_NAME + "')]/../following-sibling::input")
    private WebElement applicantLastName;
    @FindBy(xpath ="//label[contains(text(),'" + FIRST_NAME + "')]/../following-sibling::input")
    private WebElement applicantFirstName;
    @FindBy(xpath =  "//label[contains(text(),'" + MIDDLE_NAME + "')]/../following-sibling::input")
    private WebElement applicantMiddleName;
    @FindBy(xpath = "//label[contains(text(),'" + PHONE + "')]/../following-sibling::input")
    private WebElement applicantPhone;
    @FindBy(xpath ="//label[contains(text(),'" + PASSPORT + "')]/../following-sibling::input")
    private WebElement applicantPassport;
    @FindBy(xpath = "//label[contains(text(),'" + ADDRESS  + "')]/../following-sibling::input")
    private WebElement applicantAddress;
    @FindBy(xpath = "//button[contains(text(),'" + NEXT + "')]")
    private WebElement applicantPageNextButton;

    @FindBy(xpath = "//button[contains(text(),'" + CLOSE + "')]")
    private WebElement applicantPageCloseButton;

    public ServiceSelectionPage applicantFillAndNext(Application app) {
        applicantLastName.sendKeys(app.getPersonalLastName());
        applicantFirstName.sendKeys(app.getPersonalFirstName());
        applicantMiddleName.sendKeys(app.getPersonalMiddleName());
        applicantPhone.sendKeys(app.getPersonalPhone());
        applicantPassport.sendKeys(app.getPersonalPassport());
        applicantAddress.sendKeys(app.getPersonalAddress());
        Wait.waitClickable(driver,applicantPageNextButton, SHORT_TIMEOUT);
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
       Wait.waitVisibility(driver,applicantFirstName, SHORT_TIMEOUT);
    }
}
