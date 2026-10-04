package zags.pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import zags.core.DriverManager;
import zags.pages.MainPage;

import java.util.List;

public class AdminTablePage {
    private WebDriver driver;
    @FindBy(xpath = "//table//tr[td]")
    private List<WebElement> tableRows;
    @FindBy(xpath = "//button[contains(text(),'Обновить')]")
    private WebElement adminTableRefreshButton;
    @FindBy(xpath = "//button[contains(text(),'Закрыть')]")
    private WebElement adminTableCloseButton;
    @FindBy(xpath = "//b[contains(text(), 'Aдминистратор')]")
    private WebElement adminMode;

    public AdminTablePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        DriverManager.getInstance().getWait().until(ExpectedConditions.visibilityOf(adminTableRefreshButton));
    }

    public String getMode() {
        return adminMode.getText().trim();
    }

    public boolean isTableDisplayed() {
        return !tableRows.isEmpty() && tableRows.get(0).isDisplayed();
    }

    public WebElement getRowById(String id) {
        for (WebElement row : tableRows) {
            String rowId = row.findElement(By.xpath("./td[1]")).getText().trim();
            if (rowId.equals(id)) {
                return row;
            }
        }

        return null;
    }

    public String getStatusById(String id) {
        WebElement row = getRowById(id);
        if (row == null) {
            return null;
        }
        return row.findElement(By.xpath("./td[5]")).getText().trim();
    }

    public String getTypeById(String id) {
        WebElement row = getRowById(id);
        if (row == null) {
            return null;
        }
        return row.findElement(By.xpath("./td[3]")).getText().trim();
    }

    public String getFirstId() {
        DriverManager.getInstance().getWait().until(d -> !tableRows.isEmpty());
        return tableRows.get(0).findElement(By.xpath("./td[1]")).getText().trim();
    }

    public void approveById(String id) {
        WebElement row = getRowById(id);
        if (row == null) return;

        row.findElement(By.xpath("./td[6]//button[1]")).click();
        DriverManager.getInstance().getWait().until(driver -> {
            String currentStatus = getStatusById(id);
            return "Одобрена".equals(currentStatus);
        });
    }

    public void rejectById(String id) {
        WebElement row = getRowById(id);
        if (row != null) {
            String statusBefore = getStatusById(id);
            row.findElement(By.xpath("./td[6]//button[2]")).click();
            DriverManager.getInstance().getWait().until(driver -> {
                String currentStatus = getStatusById(id);
                return "Одобрена".equals(currentStatus);
            });
        }
    }

    public AdminTablePage refresh() {
        adminTableRefreshButton.click();
        return this;
    }

    public MainPage clickClose() {
        adminTableCloseButton.click();
        return new MainPage(driver);
    }

}
