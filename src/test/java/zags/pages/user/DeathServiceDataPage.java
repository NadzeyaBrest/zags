package zags.pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import zags.core.DriverManager;
import zags.models.Application;
import zags.pages.MainPage;

public class DeathServiceDataPage {
    private  WebDriver driver;

    @FindBy(xpath = "//label[contains(text(),'Дата смерти')]/../following-sibling::input")
    private WebElement deathDate;

    @FindBy(xpath = "//label[contains(text(),'Место смерти')]/../following-sibling::input")
    private WebElement deathPlace;

    @FindBy(xpath = "//button[contains(text(),'Завершить')]")
    private WebElement deathFinishButton;

    @FindBy(xpath = "//button[contains(text(),'Назад')]")
    private WebElement deathBackButton;

    @FindBy(xpath = "//button[contains(text(),'Закрыть')]")
    private WebElement deathCloseButton;

    public DeathServiceDataPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        DriverManager.getInstance().getWait().until(ExpectedConditions.visibilityOf(deathDate));
    }

    public StatusPage deathServiceFillAndFinish (Application app) {
        deathDate.sendKeys(app.getDeathDate());
        deathPlace.sendKeys(app.getDeathPlace());
        DriverManager.getInstance().getWait()
                .until(ExpectedConditions.elementToBeClickable(deathFinishButton));
        deathFinishButton.click();
        return new StatusPage(driver);
    }



    public DeathCitizenDataPage clickBack() {
        deathBackButton.click();
        return new DeathCitizenDataPage(driver);
    }

    public MainPage clickClose() {
        deathCloseButton.click();
        return new MainPage(driver);
    }
}