package zags.pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import zags.core.Wait;
import zags.pages.MainPage;

import static zags.core.Constants.BACK;
import static zags.core.Constants.BIRTH;
import static zags.core.Constants.CLOSE;
import static zags.core.Constants.DEATH;
import static zags.core.Constants.MARRIAGE;
import static zags.core.Constants.SHORT_TIMEOUT;

public class ServiceSelectionPage {
    private WebDriver driver;
    @FindBy(xpath ="//button[contains(text(),'" + MARRIAGE + "')]")
    private WebElement marriageButton;
    @FindBy(xpath ="//button[contains(text(),'" + BIRTH + "')]")
    private WebElement birthButton;
    @FindBy(xpath = "//button[contains(text(),'" + DEATH + "')]")
    private WebElement deathButton;
    @FindBy(xpath =  "//button[contains(text(),'" + BACK + "')]")
    private WebElement serviceSectionPageBackButton;
    @FindBy(xpath = "//button[contains(text(),'" + CLOSE + "')]")
    private WebElement serviceSectionPageCloseButton;

    public ServiceSelectionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        Wait.waitVisibility(driver,marriageButton,SHORT_TIMEOUT);
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
        Wait.waitClickable(driver,marriageButton,SHORT_TIMEOUT);
        marriageButton.click();
        return new MarriageCitizenDataPage(driver);
    }

    public BirthCitizenDataPage selectBirth() {
        Wait.waitClickable(driver,birthButton,SHORT_TIMEOUT);
        birthButton.click();
        return new BirthCitizenDataPage(driver);
    }

    public DeathCitizenDataPage selectDeath() {
        Wait.waitClickable(driver,deathButton,SHORT_TIMEOUT);
        deathButton.click();
        return new DeathCitizenDataPage(driver);

    }
}
