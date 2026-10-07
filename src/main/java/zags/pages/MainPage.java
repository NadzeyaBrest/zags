package zags.pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import zags.core.Wait;
import zags.pages.admin.AdminRegistrationPage;
import zags.pages.user.ApplicantDataPage;
import static zags.pages.CommonLocators.*;

public class MainPage {
    private static final Logger log = LogManager.getLogger(MainPage.class);
    private WebDriver driver;
    @FindBy(xpath = USER_MODE_BUTTON )
    private WebElement userModeButton;
    @FindBy(xpath = ADMIN_MODE_BUTTON)
    private WebElement adminModeButton;
    @FindBy(xpath = REFERENCE_BUTTON)
    private WebElement orderCertificateButton;

    @Step("Выбрать режим Пользователь")
    public ApplicantDataPage selectUserMode() {
        log.info("Клик: Пользователь");
        userModeButton.click();

        return new ApplicantDataPage(driver);
    }
    @Step("Выбрать режим Администратор")
    public AdminRegistrationPage selectAdminMode() {
        log.info("Клик:Администратор");
        adminModeButton.click();
        return new AdminRegistrationPage(driver);
    }


    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log.info("Открывается главная страница");
       Wait.waitVisibility(driver,userModeButton, Wait.SHORT_TIMEOUT);

    }

}
