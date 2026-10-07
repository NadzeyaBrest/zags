package zags.pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import zags.core.Wait;
import zags.pages.MainPage;

import static zags.core.Constants.APPLICATION_NUMBER;
import static zags.core.Constants.CLOSE;
import static zags.core.Constants.CREATE_NEW_APPLICATION;
import static zags.core.Constants.REFRESH;
import static zags.core.Constants.SHORT_TIMEOUT;
import static zags.core.Constants.STATUS_VALUE;
import static zags.core.Constants.THANK_YOU;


public class StatusPage {
    private WebDriver driver;
    @FindBy(xpath =  "//span[contains(text(),'" + THANK_YOU + "')]")
    private WebElement  textThankYou;
    @FindBy(xpath = "//span[contains(text(),'" + APPLICATION_NUMBER + "')]")
    private WebElement statusApplicationNumberText;
    @FindBy(xpath = "//span[contains(text(),'" + STATUS_VALUE + "')]")
    private WebElement statusValueText;
    @FindBy(xpath = "//button[contains(text(),'" + CREATE_NEW_APPLICATION + "')]")
    private WebElement statusCreateNewButton;
    @FindBy(xpath = "//button[contains(text(),'" + REFRESH + "')]")
    private WebElement statusRefreshButton;
    @FindBy(xpath ="//button[contains(text(),'" + CLOSE + "')]")
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
        Wait.waitVisibility(driver,textThankYou,SHORT_TIMEOUT);
    }


}
