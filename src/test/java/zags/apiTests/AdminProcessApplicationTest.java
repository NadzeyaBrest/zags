package zags.apiTests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import zags.api.AdminApi;
import zags.api.UserApi;
import zags.api.models.AdminProcessRequest;
import zags.api.models.AdminRequest;
import zags.api.models.ApplicationResponseForAdmin;
import zags.api.models.CreateAdminResponse;
import zags.api.models.UserCreateResponceApp;
import zags.api.models.UserCreateRequest;
import zags.core.ApplicationRepository;
import zags.data.Builders;

@Epic("API администратора")
@Feature("Обработка заявок")
@Test(testName = "API: Смена статуса заявки администратором")
public class AdminProcessApplicationTest {
    private Integer applId;
    private Integer staffid;

    @BeforeMethod(alwaysRun = true)
    public void prepareApplicationId() {
        UserCreateRequest userRequest = Builders.userWeddingRequest();
        UserCreateResponceApp createdApp = UserApi.sendUserRequest(userRequest);
        applId = createdApp.getApplicationid();
        AdminRequest adminRequest = Builders.admin();
        CreateAdminResponse admin = AdminApi.sendAdminRequest(adminRequest);
        staffid = admin.getStaffid();
    }

    @Test(description = "Админ одобряет заявку", groups = {"API"})
    @Story("Одобрение заявки")
    @Description("POST /requestProcess с action=approved меняет статус на 'approved'")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("187")
    public void changedStatusToApprovedByAdmin() {
        AdminProcessRequest processRequest = Builders.adminProcessRequest(applId, staffid,"approved");
        ApplicationResponseForAdmin processed = AdminApi.processApplication(processRequest);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(processed, "data = null");
        softAssert.assertEquals(processed.getStatusofapplication(), "approved",
                "Статус не 'approved', а: " + processed.getStatusofapplication());
        softAssert.assertEquals(processed.getApplicationid(), applId,
                "applicationid не совпадает");
        softAssert.assertNotNull(processed.getKindofapplication(),
                "kindofapplication = null");

        softAssert.assertEquals(ApplicationRepository.getStatusById(applId),
                "approved", "Статус в БД не изменился на 'approved'");
        softAssert.assertAll();
    }

    @Test(description = "Админ отклоняет заявку", groups = {"API"})
    @Story("Отклонение заявки")
    @Description("POST /requestProcess с action=rejected меняет статус на 'rejected'")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("188")
    public void changedStatusToRejectedByAdmin() {
        AdminProcessRequest processRequest = Builders.adminProcessRequest(applId, staffid,"rejected");
        ApplicationResponseForAdmin processed = AdminApi.processApplication(processRequest);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(processed, "data = null");
        softAssert.assertEquals(processed.getStatusofapplication(), "rejected",
                "Статус не 'rejected', а: " + processed.getStatusofapplication());
        softAssert.assertEquals(processed.getApplicationid(), applId,
                "applicationid не совпадает");
        softAssert.assertNotNull(processed.getKindofapplication(),
                "kindofapplication = null");
        softAssert.assertEquals(ApplicationRepository.getStatusById(applId),
                "rejected", "Статус в БД не изменился на 'rejected'");
        softAssert.assertAll();
    }
}
