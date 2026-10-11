package zags.apiTests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import zags.api.AdminApi;
import zags.api.models.AdminRequest;
import zags.api.models.CreateAdminResponse;
import zags.core.StaffRepository;

@Epic("API администратора")
@Feature("Создание администратора")
@Test(testName = "API: Создание администратора",
        groups = {"API"})
public class AdminCreationTest {
    @Test(description = "Админ создаётся в системе")
    @Story("POST /sendAdminRequest")
    @Description("Проверяем, что создание админа возвращает staffid > 0")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("186")
    public void checkCreateAdmin() {
        AdminRequest admin = AdminRequest.builder()
                .personalFirstName("Fedor")
                .personalLastName("Fedorov")
                .personalMiddleName("Borisovich")
                .personalPhoneNumber("123456")
                .personalNumberOfPassport("Ba12345")
                .dateofbirth("2001-06-11")
                .build();

        CreateAdminResponse response = AdminApi.sendAdminRequest(admin);

        SoftAssert soft = new SoftAssert();
        soft.assertNotNull(response, "response = null");
        soft.assertNotNull(response.getStaffid(),
                "staffid = null");
        soft.assertTrue(response.getStaffid() > 0,
                "staffid = " + response.getStaffid());
        soft.assertTrue(StaffRepository.existsById(response.getStaffid()),
                "Админ " + response.getStaffid() + " не найден в БД");

        soft.assertAll();
}}
