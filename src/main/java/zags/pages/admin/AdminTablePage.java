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

import java.util.List;
import java.util.stream.Collectors;

import static zags.pages.CommonLocators.*;

public class AdminTablePage {
    private static final Logger log = LogManager.getLogger(AdminTablePage.class);
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
        log.info("Открыта таблица заявок администратора");
        Wait.waitVisibility(driver, adminTableRefreshButton, Wait.SHORT_TIMEOUT);

    }

    private String getCellText(WebElement row, int columnIndex) {
        String text = row.findElement(By.xpath("./td[" + columnIndex + "]"))
                .getText().trim();
        log.debug("Колонка [{}]: '{}'", columnIndex, text);
        return text;
    }

    @Step("Подвтвердить, что работа ведется под ролью администратора")
    public String getMode() {
        String mode = adminMode.getText().trim();
        log.info("Роль в шапке: '{}'", mode);
        return mode;

    }

    @Step("Найти строку заявки по ID: {id}")
    public WebElement getApplicationRowById(String id) {
        log.debug("Ищем строку заявки по ID: {}", id);
        for (WebElement row : applicationRows) {
            String applicationId = getCellText(row, 1);
            if (applicationId.equals(id)) {
                log.debug("Строка найдена");
                return row;
            }
        }
        log.warn("Заявка с ID {} не найдена в таблице", id);
        return null;
    }

    @Step("Получить статус заявки по ID: {id}")
    public String getStatusById(String id) {
        WebElement row = getApplicationRowById(id);
        if (row == null) {
            return null;
        }
        String status = getCellText(row, 5);
        log.info("Статус заявки {}: '{}'", id, status);
        return status;
    }

    @Step("Получить тип заявки по ID: {id}")
    public String getTypeById(String id) {
        log.info("Получаем ID последней заявки");
        WebElement row = getApplicationRowById(id);
        if (row == null) {
            return null;
        }
        log.info("ID последней заявки: {}", id);
        return getCellText(row, 3);
    }

    @Step("Получить ID последней заявки из таблицы")
    public String getIdLatestAppFromAdminTable() {
        log.info("Получаем ID последней заявки");
        Wait.createWait(driver, Wait.SHORT_TIMEOUT)
                .until(d -> !applicationRows.isEmpty());
        String id  = getCellText(applicationRows.get(0), 1);
        log.info("ID последней заявки: {}", id);
        return id;
    }

    @Step("Получить заголовки колонок таблицы")
    public List<String> getColumnHeaders() {
        log.info("Получаем заголовки таблицы");
        Wait.createWait(driver, Wait.SHORT_TIMEOUT)
                .until(d -> !tableHeaders.isEmpty());
        List<String> headers =  tableHeaders.stream()
                .map(h -> h.getText().trim())
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
        log.debug("Заголовки: {}", headers);
        return headers;
    }

    @Step("Обновить таблицу заявок")
    public AdminTablePage refresh() {
        log.info("Обновляем таблицу");
        adminTableRefreshButton.click();
        return this;
    }

    @Step("Закрыть таблицу заявок")
    public MainPage clickAdminTableClose() {
        log.info("Закрываем таблицу заявок");

        adminTableCloseButton.click();
        return new MainPage(driver);
    }

}
