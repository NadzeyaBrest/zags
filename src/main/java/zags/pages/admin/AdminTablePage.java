package zags.pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import zags.core.Wait;
import zags.pages.MainPage;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static zags.core.Constants.ADMIN;
import static zags.core.Constants.CLOSE;
import static zags.core.Constants.COLUMN_APPLICATION_NUMBER;
import static zags.core.Constants.COLUMN_NUMBER_AS_IMPLEMENTED;
import static zags.core.Constants.COLUMN_STATUS;
import static zags.core.Constants.COLUMN_TYPE_AS_IMPLEMENTED;
import static zags.core.Constants.REFRESH;
import static zags.core.Constants.SHORT_TIMEOUT;


public class AdminTablePage {
    private WebDriver driver;
    @FindBy(xpath = "//table//tr[td]")
    private List<WebElement> applicationRows;
    @FindBy(xpath = "//button[contains(text(),'" + REFRESH + "')]")
    private WebElement adminTableRefreshButton;
    @FindBy(xpath = "//button[contains(text(),'" + CLOSE + "')]")
    private WebElement adminTableCloseButton;
    @FindBy(xpath = "//b[contains(text(), '" + ADMIN + "')]")
    private WebElement adminMode;
    @FindBy(xpath = "//table//th")
    private List<WebElement> tableHeaders;

    public AdminTablePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        Wait.waitVisibility(driver, adminTableRefreshButton, SHORT_TIMEOUT);
    }

    public List<String> getColumnHeaders() {
        Wait.createWait(driver, SHORT_TIMEOUT)
                .until(d -> !tableHeaders.isEmpty());
        return tableHeaders.stream()
                .map(h -> h.getText().trim())
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    private Map<String, Integer> getColumnIndexMap() {
        List<String> headers = getColumnHeaders();
        Map<String, Integer> mapWithHeaders = new HashMap<>();
        for (int i = 0; i < headers.size(); i++) {
            mapWithHeaders.put(headers.get(i), i + 1);
        }
        return mapWithHeaders;
    }

    private int getColumnIndexByHeaders(String columnName) {
        Integer ind = getColumnIndexMap().get(columnName);
        if (ind == null) {
            throw new IllegalStateException(
                    "Колонка не найдена");
        }
        return ind;
    }

    private String getCellText(WebElement row, String columnName) {
        int ind = getColumnIndexByHeaders(columnName);
        return row.findElement(By.xpath(String.format("./td[%d]", ind)))
                .getText()
                .trim();
    }

    public String getMode() {
        return adminMode.getText().trim();
    }

    public WebElement getApplicationRowById(String id) {
        for (WebElement row : applicationRows) {
            String applicationId = getCellText(row, COLUMN_NUMBER_AS_IMPLEMENTED);
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
        return getCellText(row, COLUMN_STATUS);
    }

    public String getTypeById(String id) {
        WebElement row = getApplicationRowById(id);
        if (row == null) {
            return null;
        }
        return getCellText(row, COLUMN_TYPE_AS_IMPLEMENTED);
    }

    public String getIdLatestAppFromAdminTable() {
        Wait.createWait(driver, SHORT_TIMEOUT)
                .until(d -> !applicationRows.isEmpty());
        return getCellText(applicationRows.get(0), COLUMN_NUMBER_AS_IMPLEMENTED);
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
