package zags.pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import zags.core.DriverManager;
import zags.pages.MainPage;

public class ServiceSelectionPage {
    private WebDriver driver;
    @FindBy(xpath = "//button[contains(text(), 'брак')]")
    private WebElement marriageButton;

    @FindBy(xpath = "//button[contains(text(), 'рождения')]")
    private WebElement birthButton;
    @FindBy(xpath = "//button[contains(text(), 'смерти')]")
    private WebElement deathButton;

    @FindBy(xpath = "//button[contains(text(), 'Назад')]")
    private WebElement serviceSectionPageBackButton;

    @FindBy(xpath = "//button[contains(text(), 'Закрыть')]")
    private WebElement serviceSectionPageCloseButton;

    public ServiceSelectionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        DriverManager.getInstance().getWait().until(ExpectedConditions.visibilityOf(marriageButton));
    }

    public ApplicantDataPage serviceSelectionBackClick() {
        serviceSectionPageBackButton.click();
        return new ApplicantDataPage(driver);

    }

    public MainPage serviceSelectionClickClose() {
        serviceSectionPageCloseButton.click();
        return new MainPage(driver);
    }

    public MarriageCitizenDataPage selectMarriage() {
        DriverManager.getInstance().getWait()
                .until(ExpectedConditions.elementToBeClickable(marriageButton));
        marriageButton.click();
        return new MarriageCitizenDataPage(driver);
    }

    public BirthCitizenDataPage selectBirth() {
        DriverManager.getInstance().getWait()
                .until(ExpectedConditions.elementToBeClickable(birthButton));
        birthButton.click();
        return new BirthCitizenDataPage(driver);
    }

    public DeathCitizenDataPage selectDeath() {
        DriverManager.getInstance().getWait()
                .until(ExpectedConditions.elementToBeClickable(deathButton));
        deathButton.click();
        return new DeathCitizenDataPage(driver);

    }
}
