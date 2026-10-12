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

    private static <T> T query(String sql, int id, RowMapper<T> mapper) {
        try (Connection conn = DatabaseConnection.get();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                return mapper.map(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка запроса к БД, id=" + id, e);
        }
    }
    private static int update(String sql, int id) {
        try (Connection conn = DatabaseConnection.get();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка запроса к БД, id=" + id, e);
        }
    }

    private static Boolean existsMap(ResultSet rs) throws SQLException {
        return rs.next();
    }

    private static String statusMap(ResultSet rs) throws SQLException {
        return rs.next() ? rs.getString("statusofapplication") : null;
    }

    private static String kindMap(ResultSet rs) throws SQLException {
        return rs.next() ? rs.getString("kindofapplication") : null;
    }

    @Step("БД: проверить существование заявки {applicationId}")
    public static boolean existsById(int applicationId) {

        String sql = "SELECT 1 FROM reg_office.applications WHERE applicationid = ?";
        return query(sql, applicationId, ApplicationRepository::existsMap);
    }

    @Step("БД: получить статус заявки {applicationId}")
    public static String getStatusById(int applicationId) {
        String sql = "SELECT statusofapplication FROM reg_office.applications WHERE applicationid = ?";
        return query(sql, applicationId, ApplicationRepository::statusMap);
    }

    @Step("БД: получить тип заявки {applicationId}")
    public static String getKindById(int applicationId) {
        String sql = "SELECT kindofapplication FROM reg_office.applications WHERE applicationid = ?";
        return query(sql, applicationId, ApplicationRepository::kindMap);
    }

    public static int deleteById(Integer applicationId) {
        String sql = "DELETE FROM reg_office.applications WHERE applicationid = ?";
        return update(sql, applicationId);
    }
}