package zags.pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import zags.core.Wait;
import zags.models.Application;
import zags.pages.MainPage;

import static zags.pages.CommonLocators.*;

public class DeathServiceDataPage {
    private WebDriver driver;
    @FindBy(xpath = DEATH_DATE_INPUT )
    private WebElement deathDate;
    @FindBy(xpath = DEATH_PLACE_INPUT )
    private WebElement deathPlace;
    @FindBy(xpath = FINISH_BUTTON )
    private WebElement deathFinishButton;
    @FindBy(xpath = BACK_BUTTON)
    private WebElement deathBackButton;
    @FindBy(xpath = CLOSE_BUTTON)
    private WebElement deathCloseButton;

    public DeathServiceDataPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        Wait.waitVisibility(driver,deathDate,Wait.SHORT_TIMEOUT);
    }

    public StatusPage deathServiceFillAndFinish(Application app) {
        deathDate.sendKeys(app.getDeathDate());
        deathPlace.sendKeys(app.getDeathPlace());
        Wait.waitClickable(driver,deathFinishButton,Wait.SHORT_TIMEOUT);
        deathFinishButton.click();
        return new StatusPage(driver);
    }


    public DeathCitizenDataPage clickDeathServiceBackButton() {
        deathBackButton.click();
        return new DeathCitizenDataPage(driver);
    }

    public MainPage clickDeathServiceCloseButton() {
        deathCloseButton.click();
        return new MainPage(driver);
    }
}