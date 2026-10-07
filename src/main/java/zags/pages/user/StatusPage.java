package zags.pages.user;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import zags.core.Wait;
import zags.pages.MainPage;

import static zags.pages.CommonLocators.*;


public class StatusPage {

    private static final Logger log = LogManager.getLogger(StatusPage.class);

    private WebDriver driver;
    @FindBy(xpath = THANK_YOU_TEXT)
    private WebElement textThankYou;
    @FindBy(xpath = APPLICATION_NUMBER_TEXT)
    private WebElement statusApplicationNumberText;
    @FindBy(xpath = STATUS_VALUE_TEXT)
    private WebElement statusValueText;
    @FindBy(xpath = CREATE_NEW_APPLICATION_BUTTON)
    private WebElement statusCreateNewButton;
    @FindBy(xpath = REFRESH_BUTTON)
    private WebElement statusRefreshButton;
    @FindBy(xpath = CLOSE_BUTTON)
    private WebElement statusCloseButton;

    @Step("Закрыть страницу статуса")
    public MainPage clickStatusCloseButton() {
        statusCloseButton.click();
        return new MainPage(driver);
    }

    @Step("Перейти к созданию новой заявки")
    public void clickCreateNewButton() {
        statusCreateNewButton.click();
    }

    @Step("Получить обновленную админом информацию о статусе заявки")
    public StatusPage refresh() {
        statusRefreshButton.click();
        return this;
    }

    public String getApplicationNumber() {
        return statusApplicationNumberText.getText().replaceAll("\\D+", "");
    }

    @Step("Получить номер заявки, свормированной пользователем")
    public String getStatus() {
        String status=  statusValueText.getText()
                .split(":")[1]
                .replace(".", "")
                .trim();
        log.info("Статус заявки: '{}'", status);
        return status;
    }

    public StatusPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log.info("Открыта страница статуса заявки");
        Wait.waitVisibility(driver, textThankYou, Wait.SHORT_TIMEOUT);
    }


}
