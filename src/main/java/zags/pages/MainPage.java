package zags.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import zags.core.Wait;
import zags.pages.admin.AdminRegistrationPage;
import zags.pages.user.ApplicantDataPage;

import static zags.pages.CommonLocators.*;

public class MainPage {
    private WebDriver driver;
    @FindBy(xpath = USER_MODE_BUTTON )
    private WebElement userModeButton;
    @FindBy(xpath = ADMIN_MODE_BUTTON)
    private WebElement adminModeButton;
    @FindBy(xpath = REFERENCE_BUTTON)
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
       Wait.waitVisibility(driver,userModeButton, Wait.SHORT_TIMEOUT);

    }

}
