package zags.pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import zags.core.Wait;
import zags.models.Application;
import zags.pages.MainPage;

import static zags.core.Constants.ADDRESS;
import static zags.core.Constants.BACK;
import static zags.core.Constants.BIRTH_DATE;
import static zags.core.Constants.CLOSE;
import static zags.core.Constants.FIRST_NAME;
import static zags.core.Constants.GENDER;
import static zags.core.Constants.LAST_NAME;
import static zags.core.Constants.MIDDLE_NAME;
import static zags.core.Constants.NEXT;
import static zags.core.Constants.PASSPORT;
import static zags.core.Constants.SHORT_TIMEOUT;

public class MarriageCitizenDataPage {
    private WebDriver driver;
    @FindBy(xpath = "//label[contains(text(),'" + LAST_NAME + "')]/../following-sibling::input")
    private WebElement marriageCitizenLastName;
    @FindBy(xpath ="//label[contains(text(),'" + FIRST_NAME + "')]/../following-sibling::input")
    private WebElement marriageCitizenFirstName;
    @FindBy(xpath = "//label[contains(text(),'" + MIDDLE_NAME + "')]/../following-sibling::input")
    private WebElement marriageCitizenMiddleName;
    @FindBy(xpath = "//label[contains(text(),'" + BIRTH_DATE + "')]/../following-sibling::input")
    private WebElement marriageCitizenBirthDate;
    @FindBy(xpath =  "//label[contains(text(),'" + GENDER + "')]/../following-sibling::input")
    private WebElement marriageCitizenGender;
    @FindBy(xpath = "//label[contains(text(),'" + PASSPORT + "')]/../following-sibling::input")
    private WebElement marriageCitizenPassport;
    @FindBy(xpath ="//label[contains(text(),'" + ADDRESS + "')]/../following-sibling::input")
    private WebElement marriageCitizenAddress;
    @FindBy(xpath =  "//button[contains(text(),'" + NEXT + "')]")
    private WebElement marriageCitizenNextButton;
    @FindBy(xpath ="//button[contains(text(),'" + CLOSE + "')]")
    private WebElement marriageCitizenCloseButton;
    @FindBy(xpath ="//button[contains(text(),'" + BACK + "')]")
    private WebElement marriageCitizenBackButton;

    public MarriageCitizenDataPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        Wait.waitVisibility(driver,marriageCitizenLastName,SHORT_TIMEOUT);
    }

    public MarriageServiceDataPage marriageCitizenFillAndNext(Application app) {
        marriageCitizenLastName.sendKeys(app.getCitizenLastName());
        marriageCitizenFirstName.sendKeys(app.getCitizenFirstName());
        marriageCitizenMiddleName.sendKeys(app.getCitizenMiddleName());
        marriageCitizenBirthDate.sendKeys(app.getCitizenBirthDate());
        marriageCitizenGender.sendKeys(app.getCitizenGender());
        marriageCitizenPassport.sendKeys(app.getCitizenPassport());
        marriageCitizenAddress.sendKeys(app.getCitizenAddress());
        Wait.waitClickable(driver,marriageCitizenNextButton,SHORT_TIMEOUT);
        marriageCitizenNextButton.click();
        return new MarriageServiceDataPage(driver);
    }
    public ServiceSelectionPage clickMarriageCitizenBackButton() {
        marriageCitizenBackButton.click();
        return new ServiceSelectionPage(driver);
    }
    public MainPage clickMarriageCitizenCloseButton() {
        marriageCitizenCloseButton.click();
        return new MainPage(driver);
    }
}
