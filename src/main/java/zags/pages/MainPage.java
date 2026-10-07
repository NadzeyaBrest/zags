package zags.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import zags.core.Wait;
import zags.pages.admin.AdminRegistrationPage;
import zags.pages.user.ApplicantDataPage;

import static zags.core.Constants.ADMIN;
import static zags.core.Constants.REFERENCE;
import static zags.core.Constants.SHORT_TIMEOUT;
import static zags.core.Constants.USER;

public class MainPage {
    private WebDriver driver;
    @FindBy(xpath ="//button[contains(text(),'" + USER + "')]")
    private WebElement userModeButton;
    @FindBy(xpath ="//button[contains(text(),'" + ADMIN + "')]")
    private WebElement adminModeButton;
    @FindBy(xpath ="//button[contains(text(),'" + REFERENCE + "')]")
    private WebElement orderCertificateButton;

    public ApplicantDataPage selectUserMode() {
        userModeButton.click();
        return new ApplicantDataPage(driver);
    }

    public AdminRegistrationPage selectAdminMode() {
        adminModeButton.click();
        return new AdminRegistrationPage(driver);
    }


    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
       Wait.waitVisibility(driver,userModeButton,SHORT_TIMEOUT);

    }

}
