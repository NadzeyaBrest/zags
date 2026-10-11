package zags.apiTests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;
import zags.core.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

@Epic("API заявок")
@Feature("Подключение к БД")
@Test(testName = "API: Проверка подключения к БД",
        groups = {"API"})
public class DatabaseConnectionTest {

    @Test(description = "Проверить подключение к PostgreSQL")
    @Story("JDBC connection")
    @Description("Открываем JDBC-подключение и выводим метаданные БД")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("189")
    public void checkDbConnection() throws SQLException {
        try (Connection conn = DatabaseConnection.get()) {
            System.out.println("URL: " + conn.getMetaData().getURL());
            System.out.println("User: " + conn.getMetaData().getUserName());
        }
    }
}