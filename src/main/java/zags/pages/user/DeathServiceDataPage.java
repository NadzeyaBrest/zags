package zags.pages.user;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import zags.core.Wait;
import zags.models.Application;
import zags.pages.MainPage;

import static zags.pages.CommonLocators.*;

public class DeathServiceDataPage {
    private static final Logger log = LogManager.getLogger(BirthCitizenDataPage.class);
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
        log.info("Открыта страница данных услуги (смерть)");
        Wait.waitVisibility(driver,deathDate,Wait.SHORT_TIMEOUT);
    }
    @Step("Заполнить данные о месте и дате смерти и нажать Завершить")
    public StatusPage deathServiceFillAndFinish(Application app) {
        log.info("Заполняем данные об услуге (смерть)");
        deathDate.sendKeys(app.getDeathDate());
        deathPlace.sendKeys(app.getDeathPlace());
        Wait.waitClickable(driver,deathFinishButton,Wait.SHORT_TIMEOUT);
        deathFinishButton.click();
        log.info("Данные услуги (смерть) отправлены");
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