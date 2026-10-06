package zags.pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import zags.core.Wait;
import zags.models.Application;
import zags.pages.MainPage;

import static zags.pages.CommonLocators.*;

public class BirthCitizenDataPage {
    private final WebDriver driver;
    private Wait wait;
    @FindBy(xpath = LAST_NAME_INPUT)
    private WebElement birthCitizenLastName;
    @FindBy(xpath = FIRST_NAME_INPUT)
    private WebElement birthCitizenFirstName;
    @FindBy(xpath = MIDDLE_NAME_INPUT )
    private WebElement birthCitizenMiddleName;
    @FindBy(xpath = BIRTH_DATE_INPUT)
    private WebElement birthCitizenBirthDate;
    @FindBy(xpath = GENDER_INPUT )
    private WebElement birthCitizenGender;
    @FindBy(xpath = PASSPORT_INPUT)
    private WebElement birthCitizenPassport;
    @FindBy(xpath =  ADDRESS_INPUT)
    private WebElement birthCitizenAddress;
    @FindBy(xpath =NEXT_BUTTON)
    private WebElement birthCitizenNextButton;
    @FindBy(xpath = CLOSE_BUTTON)
    private WebElement birthCitizenCloseButton;
    @FindBy(xpath =BACK_BUTTON )
    private WebElement birthCitizenButtonBack;

    public BirthCitizenDataPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        Wait.waitVisibility(driver,birthCitizenLastName, Wait.SHORT_TIMEOUT);
    }

    public BirthServiceDataPage birthCitizenDataFillAndNext(Application app) {
        birthCitizenLastName.sendKeys(app.getCitizenLastName());
        birthCitizenFirstName.sendKeys(app.getCitizenFirstName());
        birthCitizenMiddleName.sendKeys(app.getCitizenMiddleName());
        birthCitizenBirthDate.sendKeys(app.getCitizenBirthDate());
        birthCitizenGender.sendKeys(app.getCitizenGender());
        birthCitizenPassport.sendKeys(app.getCitizenPassport());
        birthCitizenAddress.sendKeys(app.getCitizenAddress());
        Wait.waitClickable(driver,birthCitizenNextButton,Wait.SHORT_TIMEOUT);
        birthCitizenNextButton.click();
        return new BirthServiceDataPage(driver);

    }

    public ServiceSelectionPage clickBirthCitizenBackButton() {
        birthCitizenButtonBack.click();
        return new ServiceSelectionPage(driver);

    }

    public MainPage clickBirthCitizenButtonClose() {
        birthCitizenCloseButton.click();
        return new MainPage(driver);
    }

}
