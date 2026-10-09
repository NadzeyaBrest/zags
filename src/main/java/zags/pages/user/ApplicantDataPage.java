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


import static zags.core.Constants.ADDRESS;
import static zags.core.Constants.CLOSE;
import static zags.core.Constants.FIRST_NAME;
import static zags.core.Constants.LAST_NAME;
import static zags.core.Constants.MIDDLE_NAME;
import static zags.core.Constants.NEXT;
import static zags.core.Constants.PASSPORT;
import static zags.core.Constants.PHONE;
import static zags.core.Constants.SHORT_TIMEOUT;

public class ApplicantDataPage {
    private WebDriver driver;
    private static final Logger log = LogManager.getLogger(ApplicantDataPage.class);
    @FindBy(xpath = "//label[contains(text(),'" + LAST_NAME + "')]/../following-sibling::input")
    private WebElement applicantLastName;
    @FindBy(xpath = "//label[contains(text(),'" + FIRST_NAME + "')]/../following-sibling::input")
    private WebElement applicantFirstName;
    @FindBy(xpath = "//label[contains(text(),'" + MIDDLE_NAME + "')]/../following-sibling::input")
    private WebElement applicantMiddleName;
    @FindBy(xpath = "//label[contains(text(),'" + PHONE + "')]/../following-sibling::input")
    private WebElement applicantPhone;
    @FindBy(xpath = "//label[contains(text(),'" + PASSPORT + "')]/../following-sibling::input")
    private WebElement applicantPassport;
    @FindBy(xpath = "//label[contains(text(),'" + ADDRESS + "')]/../following-sibling::input")
    private WebElement applicantAddress;
    @FindBy(xpath = "//button[contains(text(),'" + NEXT + "')]")
    private WebElement applicantPageNextButton;
    @FindBy(xpath = "//button[contains(text(),'" + CLOSE + "')]")
    private WebElement applicantPageCloseButton;

    public ApplicantDataPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log.info("Открыта страница данных заявителя");
        Wait.waitVisibility(driver, applicantFirstName, SHORT_TIMEOUT);
    }

    @Step("Заполнить данные заявителя и нажать Далее")
    public ServiceSelectionPage applicantFillAndNext(Application app) {
        log.info("Заполняем данные заявителя");
        applicantLastName.sendKeys(app.getPersonalLastName());
        applicantFirstName.sendKeys(app.getPersonalFirstName());
        applicantMiddleName.sendKeys(app.getPersonalMiddleName());
        applicantPhone.sendKeys(app.getPersonalPhone());
        applicantPassport.sendKeys(app.getPersonalPassport());
        applicantAddress.sendKeys(app.getPersonalAddress());
        Wait.waitClickable(driver, applicantPageNextButton, SHORT_TIMEOUT);
        applicantPageNextButton.click();
        log.info("Данные заявителя отправлены, открывается страница выбора услуги");
        return new ServiceSelectionPage(driver);
    }

    @Step("Закрыть форму заявителя")
    public MainPage clickApplicantButtonClose() {
        log.info("Клик: Закрыть форму заявителя (переход на главную страницу)");
        applicantPageCloseButton.click();
        return new MainPage(driver);
    }
}
