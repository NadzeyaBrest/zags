package zags.pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import zags.core.Wait;
import zags.pages.MainPage;

import static zags.pages.CommonLocators.*;


public class StatusPage {
    private WebDriver driver;
    @FindBy(xpath = THANK_YOU_TEXT)
    private WebElement  textThankYou;
    @FindBy(xpath = APPLICATION_NUMBER_TEXT)
    private WebElement statusApplicationNumberText;
    @FindBy(xpath = STATUS_VALUE_TEXT)
    private WebElement statusValueText;
    @FindBy(xpath = CREATE_NEW_APPLICATION_BUTTON )
    private WebElement statusCreateNewButton;
    @FindBy(xpath = REFRESH_BUTTON )
    private WebElement statusRefreshButton;
    @FindBy(xpath = CLOSE_BUTTON)
    private WebElement statusCloseButton;

    public MainPage clickStatusCloseButton() {
        statusCloseButton.click();
        return new MainPage(driver);
    }

    public void clickCreateNewButton() {
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
        PageFactory.initElements(driver, this);
        Wait.waitVisibility(driver,textThankYou,Wait.SHORT_TIMEOUT);
    }


}
