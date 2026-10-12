package zags.apiTests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import zags.api.AdminApi;
import zags.api.models.AdminRequest;
import zags.api.models.CreateAdminResponse;
import zags.core.ApplicationRepository;
import zags.core.StaffRepository;
import zags.data.Builders;

@Epic("API администратора")
@Feature("Создание администратора")
@Test(testName = "API: Создание администратора")
public class AdminCreationTest {

    private Integer staffId;

    @AfterMethod(alwaysRun = true)
    public void cleanup() {
        if (staffId != null) {
            ApplicationRepository.deleteById(staffId);
        }
    }

    @Test(description = "Админ создаётся в системе", groups = {"API"})
    @Story("POST /sendAdminRequest")
    @Description("Проверяем, что создание админа возвращает staffid > 0")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("186")
    public void checkCreateAdmin() {
        AdminRequest admin = Builders.admin();
        CreateAdminResponse response = AdminApi.sendAdminRequest(admin);
        staffId = response.getStaffid();
        SoftAssert soft = new SoftAssert();
        soft.assertNotNull(response, "response = null");
        soft.assertNotNull(response.getStaffid(),
                "staffid = null");
        soft.assertTrue(response.getStaffid() > 0,
                "staffid = " + response.getStaffid());
        soft.assertTrue(StaffRepository.existsById(response.getStaffid()),
                "Админ " + response.getStaffid() + " не найден в БД");
        soft.assertAll();
    }
}
