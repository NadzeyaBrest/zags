package zags.pages.user;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import zags.core.Wait;
import zags.pages.MainPage;

import static zags.core.Constants.APPLICATION_NUMBER;
import static zags.core.Constants.CLOSE;
import static zags.core.Constants.CREATE_NEW_APPLICATION;
import static zags.core.Constants.REFRESH;
import static zags.core.Constants.SHORT_TIMEOUT;
import static zags.core.Constants.STATUS_VALUE;
import static zags.core.Constants.THANK_YOU;


public class StatusPage {
    private WebDriver driver;
    private static final Logger log = LogManager.getLogger(StatusPage.class);
    @FindBy(xpath = "//span[contains(text(),'" + THANK_YOU + "')]")
    private WebElement textThankYou;
    @FindBy(xpath = "//span[contains(text(),'" + APPLICATION_NUMBER + "')]")
    private WebElement statusApplicationNumberText;
    @FindBy(xpath = "//span[contains(text(),'" + STATUS_VALUE + "')]")
    private WebElement statusValueText;
    @FindBy(xpath = "//button[contains(text(),'" + CREATE_NEW_APPLICATION + "')]")
    private WebElement statusCreateNewButton;
    @FindBy(xpath = "//button[contains(text(),'" + REFRESH + "')]")
    private WebElement statusRefreshButton;
    @FindBy(xpath = "//button[contains(text(),'" + CLOSE + "')]")
    private WebElement statusCloseButton;

    public StatusPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log.info("Открыта страница статуса заявки");
        Wait.waitVisibility(driver, textThankYou, SHORT_TIMEOUT);
    }

    @Step("Закрыть страницу статуса")
    public MainPage clickStatusCloseButton() {
        log.info("Клик: Закрыть страницу статуса (переход на главную)");
        statusCloseButton.click();
        return new MainPage(driver);
    }

    @Step("Перейти к созданию новой заявки")
    public void clickCreateNewButton() {
        log.info("Клик: Создать новую заявку");
        statusCreateNewButton.click();
    }

    @Step("Обновить страницу статуса заявки")
    public StatusPage refresh() {
        log.info("Обновляем страницу статуса");
        statusRefreshButton.click();
        return this;
    }

    @Step("Получить номер заявки, сформированной пользователем")
    public String getApplicationNumber() {
        String number = statusApplicationNumberText.getText().replaceAll("\\D+", "");
        log.info("Номер заявки: '{}'", number);
        Allure.parameter("Номер заявки", number);
        Allure.addAttachment("Номер заявки", "text/plain", number, ".txt");
        return number;
    }

    @Step("Получить статус заявки, сформированной пользователем")
    public String getStatus() {
        String status = statusValueText.getText()
                .split(":")[1]
                .replace(".", "")
                .trim();
        log.info("Статус заявки: '{}'", status);
        Allure.parameter("Статус заявки", status);
        Allure.addAttachment("Статус заявки", "text/plain", status, ".txt");
        return status;
    }


}
