package zags.apiTests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
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
import zags.core.StaffRepository;
import zags.data.AdminBuilder;
import zags.data.UserBuilder;

@Epic("API администратора")
@Feature("Обработка заявок")
@Test(testName = "API: Смена статуса заявки администратором")
public class AdminProcessApplicationTest {
    private Integer applId;
    private Integer staffid;

    @AfterMethod(alwaysRun = true)
    @Step("Очистка тестовых данных")
    public void cleanup() {
        if (applId != null) {
            ApplicationRepository.deleteById(applId);
        }
        if (staffid != null) {
            StaffRepository.deleteById(staffid);
        }
    }
    @BeforeMethod(alwaysRun = true)
    public void prepareApplicationId() {
        UserCreateRequest userRequest = UserBuilder.userWeddingRequest();
        UserCreateResponceApp createdApp = UserApi.sendUserRequest(userRequest).jsonPath().getObject("data", UserCreateResponceApp.class);
        applId = createdApp.getApplicationid();
        AdminRequest adminRequest = AdminBuilder.admin();
        CreateAdminResponse admin = AdminApi.sendAdminRequest(adminRequest).jsonPath().getObject("data", CreateAdminResponse.class);
        staffid = admin.getStaffid();
    }

    @Test(description = "Админ одобряет заявку", groups = {"API"})
    @Story("POST /requestProcess")
    @Description("POST /requestProcess с action=approved меняет статус на 'approved'")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("187")
    public void changedStatusToApprovedByAdmin() {
        AdminProcessRequest processRequest = AdminBuilder.adminProcessRequest(applId, staffid,"approved");
        Response response = AdminApi.processApplication(processRequest);
        ApplicationResponseForAdmin processedData = response.jsonPath().getObject("data", ApplicationResponseForAdmin.class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(),200);
        softAssert.assertNotNull(processedData, "data = null");
        softAssert.assertEquals(processedData.getStatusofapplication(), "approved",
                "Статус не 'approved', а: " + processedData.getStatusofapplication());
        softAssert.assertEquals(processedData.getApplicationid(), applId,
                "applicationid не совпадает");
        softAssert.assertNotNull(processedData.getKindofapplication(),
                "kindofapplication = null");
        softAssert.assertEquals(ApplicationRepository.getStatusById(applId),
                "approved", "Статус в БД не изменился на 'approved'");
        softAssert.assertAll();
    }

    @Test(description = "Админ отклоняет заявку", groups = {"API"})
    @Story("POST /requestProcess")
    @Description("POST /requestProcess с action=rejected меняет статус на 'rejected'")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("188")
    public void changedStatusToRejectedByAdmin() {
        AdminProcessRequest processRequest = AdminBuilder.adminProcessRequest(applId, staffid,"rejected");
        Response response = AdminApi.processApplication(processRequest);
        ApplicationResponseForAdmin processedData = response.jsonPath().getObject("data", ApplicationResponseForAdmin.class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(),200);
        softAssert.assertNotNull(processedData, "data = null");
        softAssert.assertEquals(processedData.getStatusofapplication(), "rejected",
                "Статус не 'rejected', а: " + processedData.getStatusofapplication());
        softAssert.assertEquals(processedData.getApplicationid(), applId,
                "applicationid не совпадает");
        softAssert.assertNotNull(processedData.getKindofapplication(),
                "kindofapplication = null");
        softAssert.assertEquals(ApplicationRepository.getStatusById(applId),
                "rejected", "Статус в БД не изменился на 'rejected'");
        softAssert.assertAll();
    }
}
