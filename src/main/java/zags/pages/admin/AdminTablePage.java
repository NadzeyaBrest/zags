package zags.pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import zags.core.Wait;
import zags.pages.MainPage;

import java.util.List;
import java.util.stream.Collectors;

import static zags.pages.CommonLocators.*;

public class AdminTablePage {
    private WebDriver driver;

    @FindBy(xpath = "//table//tr[td]")
    private List<WebElement> applicationRows;
    @FindBy(xpath = REFRESH_BUTTON)
    private WebElement adminTableRefreshButton;
    @FindBy(xpath = CLOSE_BUTTON)
    private WebElement adminTableCloseButton;
    @FindBy(xpath = "//b[contains(text(), 'Aдминистратор')]")
    private WebElement adminMode;
    @FindBy(xpath = "//table//th")
    private List<WebElement> tableHeaders;

    public AdminTablePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        Wait.waitVisibility(driver, adminTableRefreshButton, Wait.SHORT_TIMEOUT);

    }

    private String getCellText(WebElement row, int columnIndex) {
        return row.findElement(By.xpath("./td[" + columnIndex + "]"))
                .getText()
                .trim();
    }

    public String getMode() {
        return adminMode.getText().trim();
    }

    public WebElement getApplicationRowById(String id) {
        for (WebElement row : applicationRows) {
            String applicationId = getCellText(row, 1);
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
        return getCellText(row, 5);
    }

    public String getTypeById(String id) {
        WebElement row = getApplicationRowById(id);
        if (row == null) {
            return null;
        }
        return getCellText(row, 3);
    }

    public String getIdLatestAppFromAdminTable() {
        Wait.createWait(driver, Wait.SHORT_TIMEOUT)
                .until(d -> !applicationRows.isEmpty());
        return getCellText(applicationRows.get(0), 1);
    }

    public List<String> getColumnHeaders() {
        Wait.createWait(driver, Wait.SHORT_TIMEOUT)
                .until(d -> !tableHeaders.isEmpty());
        return tableHeaders.stream()
                .map(h -> h.getText().trim())
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    public AdminTablePage refresh() {
        adminTableRefreshButton.click();
        return this;
    }

    public MainPage clickAdminTableClose() {
        adminTableCloseButton.click();
        return new MainPage(driver);
    }

}
