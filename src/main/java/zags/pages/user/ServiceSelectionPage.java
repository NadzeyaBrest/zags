package zags.pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import zags.core.Wait;
import zags.pages.MainPage;

import static zags.pages.CommonLocators.*;

public class ServiceSelectionPage {
    private WebDriver driver;
    @FindBy(xpath =MARRIAGE_SERVICE_BUTTON)
    private WebElement marriageButton;
    @FindBy(xpath =BIRTH_SERVICE_BUTTON)
    private WebElement birthButton;
    @FindBy(xpath =DEATH_SERVICE_BUTTON)
    private WebElement deathButton;
    @FindBy(xpath = BACK_BUTTON)
    private WebElement serviceSectionPageBackButton;
    @FindBy(xpath = CLOSE_BUTTON)
    private WebElement serviceSectionPageCloseButton;

    public ServiceSelectionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        Wait.waitVisibility(driver,marriageButton,Wait.SHORT_TIMEOUT);
    }

    public ApplicantDataPage clickServiceSelectionBackButton() {
        serviceSectionPageBackButton.click();
        return new ApplicantDataPage(driver);

    }

    public MainPage clickServiceSelectionCloseButton() {
        serviceSectionPageCloseButton.click();
        return new MainPage(driver);
    }

    public MarriageCitizenDataPage selectMarriage() {
        Wait.waitClickable(driver,marriageButton,Wait.SHORT_TIMEOUT);
        marriageButton.click();
        return new MarriageCitizenDataPage(driver);
    }

    public BirthCitizenDataPage selectBirth() {
        Wait.waitClickable(driver,birthButton,Wait.SHORT_TIMEOUT);
        birthButton.click();
        return new BirthCitizenDataPage(driver);
    }

    public DeathCitizenDataPage selectDeath() {
        Wait.waitClickable(driver,deathButton,Wait.SHORT_TIMEOUT);
        deathButton.click();
        return new DeathCitizenDataPage(driver);

    }
}
