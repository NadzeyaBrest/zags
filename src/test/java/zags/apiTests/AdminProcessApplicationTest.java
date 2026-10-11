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
import zags.api.models.ApplicationResponseForAdmin;
import zags.api.models.UserCreateResponceApp;
import zags.api.models.UserCreateRequest;
import zags.core.ApplicationRepository;

@Epic("API администратора")
@Feature("Обработка заявок")
@Test(testName = "API: Смена статуса заявки администратором",
        groups = {"API"})
public class AdminProcessApplicationTest {
    private Integer applId;

    @BeforeMethod(alwaysRun = true)
    public void prepareApplicationId() {
        UserCreateRequest userRequest = UserCreateRequest.builder()
                .mode("wedding")
                .personalLastName("Gromov")
                .personalFirstName("Mark")
                .personalMiddleName("Ivanovich")
                .personalPhoneNumber("79991234567")
                .personalNumberOfPassport("AB123456")
                .citizenLastName("Gromov")
                .citizenFirstName("Mark")
                .citizenMiddleName("Ivanovich")
                .citizenBirthDate("1998-06-11")
                .citizenNumberOfPassport("AB123456")
                .citizenGender("Муж")
                .dateOfMarriage("2026-10-11")
                .newLastName("Gromov")
                .anotherPersonLastName("Svetova")
                .anotherPersonFirstName("Svetlana")
                .anotherPersonMiddleName("Petrovna")
                .birth_of_anotoherPerson("2001-06-11")
                .anotherPersonPassport("AB176543")
                .build();

        UserCreateResponceApp createdApp = UserApi.sendUserRequest(userRequest);
        applId = createdApp.getApplicationid();
    }

    @Test(description = "Админ одобряет заявку")
    @Story("Одобрение заявки")
    @Description("POST /requestProcess с action=approved меняет статус на 'approved'")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("187")
    public void changedStatusToApprovedByAdmin() {
        AdminProcessRequest processRequest = AdminProcessRequest.builder()
                .applId(applId)
                .staffId(38402)
                .action("approved")
                .build();

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

    @Test(description = "Админ отклоняет заявку")
    @Story("Отклонение заявки")
    @Description("POST /requestProcess с action=rejected меняет статус на 'rejected'")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("188")
    public void changedStatusToRejectedByAdmin() {
        AdminProcessRequest processRequest = AdminProcessRequest.builder()
                .applId(applId)
                .staffId(38402)
                .action("rejected")
                .build();

        ApplicationResponseForAdmin processed = AdminApi.processApplication(processRequest);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(processed, "data = null");
        softAssert.assertEquals(processed.getStatusofapplication(), "rejected",
                "Статус не 'approved', а: " + processed.getStatusofapplication());
        softAssert.assertEquals(processed.getApplicationid(), applId,
                "applicationid не совпадает");
        softAssert.assertNotNull(processed.getKindofapplication(),
                "kindofapplication = null");
        softAssert.assertEquals(ApplicationRepository.getStatusById(applId),
                "rejected", "Статус в БД не изменился на 'rejected'");

        softAssert.assertAll();
    }
}
