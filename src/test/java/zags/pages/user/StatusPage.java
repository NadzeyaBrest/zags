package zags.pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import zags.core.DriverManager;
import zags.pages.MainPage;


public class StatusPage {
    private WebDriver driver;

    @FindBy(xpath = "//span[contains(text(),'Спасибо за обращение')]")
    private WebElement  textThankYou;
    @FindBy(xpath = "//span[contains(text(),'Ваша заявка №')]")
    private WebElement statusApplicationNumberText;
    @FindBy (xpath ="//span[contains(text(), 'Статус заявки:')]" )
    private WebElement statusValueText;
    @FindBy(xpath = "//button[contains(text(),'Создать новую заявку')]")
    private WebElement statusCreateNewButton;
    @FindBy(xpath = "//button[contains(text(),'Обновить')]")
    private WebElement statusRefreshButton;
    @FindBy(xpath = "//button[contains(text(),'Закрыть')]")
    private WebElement statusCloseButton;

    public MainPage statusClickClose (){
        statusCloseButton.click();
        return new MainPage(driver);
    }

    public void clickCreateNew() {
        statusCreateNewButton.click();
    }
    public StatusPage refresh(){
        statusRefreshButton.click();
        return this;
    }
    public String getThankYouText() {
        return textThankYou.getText();
    }

    public String getApplicationNumber() {
        return statusApplicationNumberText.getText().replaceAll("\\D+", "");
    }

    public String getStatus() {
        return statusValueText.getText();
    }
    public StatusPage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
        DriverManager.getInstance().getWait().until(ExpectedConditions.visibilityOf(textThankYou));
    }


}
