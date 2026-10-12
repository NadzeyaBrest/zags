package zags.core;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffRepository {

    private static final Logger log = LogManager.getLogger(StaffRepository.class);
    
    @Step("БД: проверить существование админа {staffId}")
    public static boolean existsById(int staffId) {
        String sql = "SELECT 1 FROM reg_office.staff WHERE staffid = ?";

        try (Connection conn = DatabaseConnection.get();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, staffId);

            try (ResultSet rs = ps.executeQuery()) {
                boolean exists = rs.next();
                log.info("БД: админ {} {}", staffId, exists ? "найден" : "НЕ найден");
                return exists;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка запроса к БД: " + e.getMessage(), e);
        }
    }
}