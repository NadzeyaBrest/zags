package zags.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import zags.core.DriverManager;
import zags.pages.admin.AdminRegistrationPage;
import zags.pages.user.ApplicantDataPage;

public class MainPage {
    private WebDriver driver;
    @FindBy(xpath ="//button[contains(text(), 'пользователь')]")
            private WebElement userModeButton;
    @FindBy(xpath="//button[contains(text(), 'администратор')]")
    private WebElement adminModeButton;
    @FindBy(xpath="//button[contains(text(), 'справк')]")
    private WebElement orderCertificateButton;

    public ApplicantDataPage selectUserMode (){
        userModeButton.click();
        return  new ApplicantDataPage(driver);
    }

 public AdminRegistrationPage selectAdminMode(){
     adminModeButton.click();
     return new AdminRegistrationPage(driver);
 }


    public MainPage (WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
        DriverManager.getInstance().getWait().until(ExpectedConditions.visibilityOf(userModeButton));

    }

}
