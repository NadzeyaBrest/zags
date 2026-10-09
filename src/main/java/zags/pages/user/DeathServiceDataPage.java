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

import static zags.core.Constants.BACK;
import static zags.core.Constants.CLOSE;
import static zags.core.Constants.DEATH_DATE;
import static zags.core.Constants.DEATH_PLACE;
import static zags.core.Constants.FINISH;
import static zags.core.Constants.SHORT_TIMEOUT;

public class DeathServiceDataPage {
    private WebDriver driver;
    private static final Logger log = LogManager.getLogger(DeathServiceDataPage.class);
    @FindBy(xpath = "//label[contains(text(),'" + DEATH_DATE + "')]/../following-sibling::input")
    private WebElement deathDate;
    @FindBy(xpath = "//label[contains(text(),'" + DEATH_PLACE + "')]/../following-sibling::input")
    private WebElement deathPlace;
    @FindBy(xpath = "//button[contains(text(),'" + FINISH + "')]")
    private WebElement deathFinishButton;
    @FindBy(xpath = "//button[contains(text(),'" + BACK + "')]")
    private WebElement deathBackButton;
    @FindBy(xpath = "//button[contains(text(),'" + CLOSE + "')]")
    private WebElement deathCloseButton;

    public DeathServiceDataPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log.info("Открыта страница данных услуги (смерть)");
        Wait.waitVisibility(driver, deathDate, SHORT_TIMEOUT);
    }

    @Step("Заполнить данные о месте и дате смерти и нажать Завершить")
    public StatusPage deathServiceFillAndFinish(Application app) {
        deathDate.sendKeys(app.getDeathDate());
        deathPlace.sendKeys(app.getDeathPlace());
        Wait.waitClickable(driver, deathFinishButton, SHORT_TIMEOUT);
        deathFinishButton.click();
        log.info("Данные услуги (смерть) отправлены");
        return new StatusPage(driver);
    }

    @Step("Вернуться на страницу данных гражданина (смерть)")
    public DeathCitizenDataPage clickDeathServiceBackButton() {
        log.info("Клик: Назад (возврат на страницу данных гражданина, смерть)");
        deathBackButton.click();
        return new DeathCitizenDataPage(driver);
    }

    @Step("Закрыть страницу услуги (смерть)")
    public MainPage clickDeathServiceCloseButton() {
        log.info("Клик: Закрыть страницу услуги (смерть) (переход на главную)");
        deathCloseButton.click();
        return new MainPage(driver);
    }
}