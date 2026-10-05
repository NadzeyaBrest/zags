package zags.pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import zags.core.Wait;
import zags.pages.MainPage;


public class StatusPage {
    private WebDriver driver;
    private Wait wait;
    @FindBy(xpath = "//span[contains(text(),'Спасибо за обращение')]")
    private WebElement textThankYou;
    @FindBy(xpath = "//span[contains(text(),'Ваша заявка №')]")
    private WebElement statusApplicationNumberText;
    @FindBy(xpath = "//span[contains(text(), 'Статус заявки:')]")
    private WebElement statusValueText;
    @FindBy(xpath = "//button[contains(text(),'Создать новую заявку')]")
    private WebElement statusCreateNewButton;
    @FindBy(xpath = "//button[contains(text(),'Обновить')]")
    private WebElement statusRefreshButton;
    @FindBy(xpath = "//button[contains(text(),'Закрыть')]")
    private WebElement statusCloseButton;

    public MainPage clickCloseButton() {
        statusCloseButton.click();
        return new MainPage(driver);
    }

    public void clickCreateNew() {
        statusCreateNewButton.click();
    }

    public StatusPage refresh() {
        statusRefreshButton.click();
        return this;
    }

    public String getApplicationNumber() {
        return statusApplicationNumberText.getText().replaceAll("\\D+", "");
    }

    public String getStatus() {
        return statusValueText.getText()
                .split(":")[1]
                .replace(".", "")
                .trim();
    }

    public StatusPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new Wait(driver);
        PageFactory.initElements(driver, this);
        wait.seconds(2).until(ExpectedConditions.visibilityOf(textThankYou));
    }


}
