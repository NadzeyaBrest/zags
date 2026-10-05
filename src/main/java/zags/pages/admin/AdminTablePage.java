package zags.pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import zags.core.Wait;
import zags.pages.MainPage;

import java.util.List;
import java.util.stream.Collectors;

public class AdminTablePage {
    private WebDriver driver;
    private Wait wait;

    @FindBy(xpath = "//table//tr[td]")
    private List<WebElement> applicationRows;
    @FindBy(xpath = "//button[contains(text(),'Обновить')]")
    private WebElement adminTableRefreshButton;
    @FindBy(xpath = "//button[contains(text(),'Закрыть')]")
    private WebElement adminTableCloseButton;
    @FindBy(xpath = "//b[contains(text(), 'Aдминистратор')]")
    private WebElement adminMode;
    @FindBy(xpath = "//table//th")
    private List<WebElement> tableHeaders;

    public AdminTablePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new Wait(driver);
        PageFactory.initElements(driver, this);

        wait.seconds(2).until(ExpectedConditions.visibilityOf(adminTableRefreshButton));
    }

    public String getMode() {
        return adminMode.getText().trim();
    }

    public WebElement getApplicationRowById(String id) {
        for (WebElement row : applicationRows) {
            String applicationId = row.findElement(By.xpath("./td[1]")).getText().trim();
            if (applicationId.equals(id)) {
                return row;
            }
        }

        return null;
    }

    public String getStatusById(String id) {
        WebElement row = getApplicationRowById(id);
        if (row == null) {
            return null;
        }
        return row.findElement(By.xpath("./td[5]")).getText().trim();
    }

    public String getTypeById(String id) {
        WebElement row = getApplicationRowById(id);
        if (row == null) {
            return null;
        }
        return row.findElement(By.xpath("./td[3]")).getText().trim();
    }

    public String getIdLatestAppFromAdminTable() {
        wait.seconds(2).until(d -> !applicationRows.isEmpty());
        return applicationRows.get(0).findElement(By.xpath("./td[1]")).getText().trim();
    }

    public List<String> getColumnHeaders() {
        wait.seconds(2).until(d -> !tableHeaders.isEmpty());
        return tableHeaders.stream()
                .map(h -> h.getText().trim())
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
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
