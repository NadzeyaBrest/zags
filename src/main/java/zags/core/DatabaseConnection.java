package zags.core;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final Logger log = LogManager.getLogger(DatabaseConnection.class);
    private static Dotenv DOTENV = Dotenv.load();
    private static String URL = DOTENV.get("DB_URL");
    private static String USER = DOTENV.get("DB_USER");
    private static String PASSWORD = DOTENV.get("DB_PASSWORD");

    public static  Connection get() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            log.debug("Подключено к ДБ {}", URL);
            return conn;
        } catch (SQLException e) {
            log.error("Не удалось подключиться к БД {}", e.getMessage());
            throw  new RuntimeException("Ошибка подключения к БД", e);
        }
    }
}