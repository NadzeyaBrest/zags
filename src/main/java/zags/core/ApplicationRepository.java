package zags.core;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicationRepository {

    private static final Logger log = LogManager.getLogger(ApplicationRepository.class);


    @Step("БД: проверить существование заявки {applicationId}")
    public static boolean existsById(int applicationId) {
        String sql = "SELECT 1 FROM reg_office.applications WHERE applicationid = ?";

        try (Connection conn = DatabaseConnection.get();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, applicationId);

            try (ResultSet rs = ps.executeQuery()) {
                boolean exists = rs.next();
                log.info("БД: заявка {} {}", applicationId, exists ? "найдена" : "НЕ найдена");
                return exists;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка запроса к БД: " + e.getMessage(), e);
        }
    }

    @Step("БД: получить статус заявки {applicationId}")
    public static String getStatusById(int applicationId) {
        String sql = "SELECT statusofapplication FROM reg_office.applications WHERE applicationid = ?";

        try (Connection conn = DatabaseConnection.get();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, applicationId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String status = rs.getString("statusofapplication");
                    log.info("БД: статус заявки {} = '{}'", applicationId, status);
                    return status;
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка запроса к БД: " + e.getMessage(), e);
        }
    }


    @Step("БД: получить тип заявки {applicationId}")
    public static String getKindById(int applicationId) {
        String sql = "SELECT kindofapplication FROM reg_office.applications WHERE applicationid = ?";

        try (Connection conn = DatabaseConnection.get();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, applicationId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String kind = rs.getString("kindofapplication");
                    log.info("БД: тип заявки {} = '{}'", applicationId, kind);
                    return kind;
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка запроса к БД: " + e.getMessage(), e);
        }
    }
}