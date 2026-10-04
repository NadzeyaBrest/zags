package zags.pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import zags.core.DriverManager;
import zags.models.Application;
import zags.pages.MainPage;

public class ApplicantDataPage {
    private WebDriver driver;
    @FindBy(xpath = "//label[text()='Фамилия']/../following-sibling::input")
    private WebElement applicantLastName;

    @FindBy(xpath = "//label[text()='Имя']/../following-sibling::input")
    private WebElement applicantFirstName;
    @FindBy(xpath = "//label[text()='Отчество']/../following-sibling::input")
    private WebElement applicantMiddleName;
    @FindBy(xpath = "//label[text()='Телефон']/../following-sibling::input")
    private WebElement applicantPhone;

    @FindBy(xpath = "//label[text()='Номер паспорта']/../following-sibling::input")
    private WebElement applicantPassport;

    @FindBy(xpath = "//label[text()='Адрес прописки']/../following-sibling::input")
    private WebElement applicantAddress;

    @FindBy(xpath = "//button[contains(text(), 'Далее')]")
    private WebElement applicantPageNextButton;

    @FindBy(xpath = "//button[contains(text(), 'Закрыть')]")
    private WebElement applicantPageCloseButton;

    public ServiceSelectionPage applicantFillAndNext(Application app) {
        applicantLastName.sendKeys(app.getPersonalLastName());
        applicantFirstName.sendKeys(app.getPersonalFirstName());
        applicantMiddleName.sendKeys(app.getPersonalMiddleName());
        applicantPhone.sendKeys(app.getPersonalPhone());
        applicantPassport.sendKeys(app.getPersonalPassport());
        applicantAddress.sendKeys(app.getPersonalAddress());
        DriverManager.getInstance().getWait()
                .until(ExpectedConditions.elementToBeClickable(applicantPageNextButton));

        applicantPageNextButton.click();
        return new ServiceSelectionPage(driver);
    }

    public MainPage clickClose() {
        applicantPageCloseButton.click();
        return new MainPage(driver);
    }


    public ApplicantDataPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        DriverManager.getInstance().getWait().until(ExpectedConditions.visibilityOf(applicantFirstName));
    }
}
