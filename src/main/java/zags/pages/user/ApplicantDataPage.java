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

public class ApplicantDataPage {
    private static final Logger log = LogManager.getLogger(ApplicantDataPage.class);
    private WebDriver driver;
    @FindBy(xpath = LAST_NAME_INPUT)
    private WebElement applicantLastName;
    @FindBy(xpath = FIRST_NAME_INPUT)
    private WebElement applicantFirstName;
    @FindBy(xpath = MIDDLE_NAME_INPUT)
    private WebElement applicantMiddleName;
    @FindBy(xpath = PHONE_INPUT)
    private WebElement applicantPhone;
    @FindBy(xpath = PASSPORT_INPUT)
    private WebElement applicantPassport;
    @FindBy(xpath = ADDRESS_INPUT)
    private WebElement applicantAddress;
    @FindBy(xpath = NEXT_BUTTON)
    private WebElement applicantPageNextButton;
    @FindBy(xpath = CLOSE_BUTTON )
    private WebElement applicantPageCloseButton;
    @Step("Заполнить данные заявителя и нажать 'Далее'")
    public ServiceSelectionPage applicantFillAndNext(Application app) {
        log.info("Заполняем данные заявителя: {} {}", app.getPersonalLastName(), app.getPersonalFirstName());
        applicantLastName.sendKeys(app.getPersonalLastName());
        applicantFirstName.sendKeys(app.getPersonalFirstName());
        applicantMiddleName.sendKeys(app.getPersonalMiddleName());
        applicantPhone.sendKeys(app.getPersonalPhone());
        applicantPassport.sendKeys(app.getPersonalPassport());
        applicantAddress.sendKeys(app.getPersonalAddress());
        Wait.waitClickable(driver,applicantPageNextButton, Wait.SHORT_TIMEOUT);
        applicantPageNextButton.click();
        log.info("Данные заявителя отправлены");
        return new ServiceSelectionPage(driver);
    }

    public MainPage clickApplicantButtonClose() {
        applicantPageCloseButton.click();
        return new MainPage(driver);
    }


    public ApplicantDataPage(WebDriver driver) {
        this.driver = driver;
        log.info("Открыта страница данных заявителя");
        PageFactory.initElements(driver, this);
       Wait.waitVisibility(driver,applicantFirstName, Wait.SHORT_TIMEOUT);
    }
}
