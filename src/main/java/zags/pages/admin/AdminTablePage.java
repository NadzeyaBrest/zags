package zags.pages.admin;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import zags.core.Wait;
import zags.pages.MainPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static zags.core.Constants.ADMIN;
import static zags.core.Constants.CLOSE;
import static zags.core.Constants.COLUMN_NUMBER_AS_IMPLEMENTED;
import static zags.core.Constants.COLUMN_STATUS;
import static zags.core.Constants.COLUMN_TYPE_AS_IMPLEMENTED;
import static zags.core.Constants.REFRESH;
import static zags.core.Constants.SHORT_TIMEOUT;


public class AdminTablePage {
    private WebDriver driver;
    private static final Logger log = LogManager.getLogger(AdminTablePage.class);
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
        log.info("Открыта таблица заявок администратора");
        Wait.waitVisibility(driver, adminTableRefreshButton, SHORT_TIMEOUT);
    }

    @Step("Получить заголовки колонок таблицы заявок")
    public List<String> getColumnHeaders() {
        log.info("Получаем заголовки таблицы заявок");
        Wait.createWait(driver, SHORT_TIMEOUT)
                .until(d -> !tableHeaders.isEmpty());
        List<String> headers = tableHeaders.stream()
                .map(h -> h.getText().trim())
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
        log.debug("Заголовки таблицы: {}", headers);
        return headers;
    }
    private Map<String, Integer> createColumnIndexMap() {
        List<String> headers = getColumnHeaders();
        Map<String, Integer> mapWithHeaders = new HashMap<>();
        for (int i = 0; i < headers.size(); i++) {
            mapWithHeaders.put(headers.get(i), i + 1);
        }
        return mapWithHeaders;
    }

    private int getColumnIndexByHeaders(String columnName) {
        Integer ind = createColumnIndexMap().get(columnName);
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
    @Step("Получить роль в шапке страницы")
    public String getMode() {
        String mode =  adminMode.getText().trim();
        log.info("Роль в шапке: '{}'", mode);
        return mode;
    }
    @Step("Найти строку заявки по ID: {id}")
    public WebElement getApplicationRowById(String id) {
        log.debug("Ищем строку заявки по ID: {}", id);
        for (WebElement row : applicationRows) {
            String applicationId = getCellText(row, COLUMN_NUMBER_AS_IMPLEMENTED);
            if (applicationId.equals(id)) {
                log.debug("Строка с ID {} найдена", id);
                return row;
            }
        }

        throw new IllegalStateException("Заявка с ID " + id + " не найдена в таблице");
    }
    @Step("Получить статус заявки по ID: {id}")
    public String getStatusById(String id) {
        WebElement row = getApplicationRowById(id);
        String status = getCellText(row, COLUMN_STATUS);
        log.info("Статус заявки {}: '{}'", id, status);
        return status;
    }

    @Step("Получить тип заявки по ID: {id}")
    public String getTypeById(String id) {
        WebElement row = getApplicationRowById(id);
        String type = getCellText(row, COLUMN_TYPE_AS_IMPLEMENTED);
        log.info("Тип заявки {}: '{}'", id, type);
        return type;
    }

    @Step("Получить ID последней заявки из таблицы")
    public String getIdLatestAppFromAdminTable() {
        log.info("Получаем ID последней заявки из таблицы");
        Wait.createWait(driver, SHORT_TIMEOUT)
                .until(d -> !applicationRows.isEmpty());
        String id = getCellText(applicationRows.get(0), COLUMN_NUMBER_AS_IMPLEMENTED);
        log.info("ID последней заявки: {}", id);
        return id;
    }

    @Step("Обновить таблицу заявок")
    public AdminTablePage refresh() {
        log.info("Клик: Обновить таблицу заявок");
        adminTableRefreshButton.click();
        return this;
    }

    @Step("Закрыть таблицу заявок")
    public MainPage clickAdminTableClose() {
        log.info("Клик: Закрыть таблицу заявок (переход на главную страницу)");
        adminTableCloseButton.click();
        return new MainPage(driver);
    }

}
