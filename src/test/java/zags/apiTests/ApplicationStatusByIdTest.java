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
import zags.api.models.ApplicationByIdResponse;
import zags.api.models.UserCreateResponceApp;
import zags.data.UserBuilder;

@Epic("API администратора")
@Feature("Статус заявки по ID")
@Test(testName = "API: Получение статуса заявки по ID")
public class ApplicationStatusByIdTest {
    private Integer appId;
    @BeforeMethod(alwaysRun = true)
    public void prepareApplication() {
        UserCreateResponceApp createdApplication = UserApi.sendUserRequest(UserBuilder.userWeddingRequest());
        appId = createdApplication.getApplicationid();
    }
    @Test(description = "Проверить статус заявки по ID", groups = {"API"})
    @Story("GET /getApplStatus/{applicationId}")
    @Description("Свежесозданная заявка на брак имеет статус under consideration и тип Получение свидетельства о браке")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("184")
    public void checkStatusById() {
        ApplicationByIdResponse applicationStatus = AdminApi.getApplicationById(appId);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(applicationStatus, "data = null");
        softAssert.assertEquals(applicationStatus.getStatusofapplication(), "under consideration",
                "Свежая заявка должна быть 'under consideration'");
        softAssert.assertEquals(applicationStatus.getKindofapplication(),
                "Получение свидетельства о браке",
                "Тип заявки не соответствует режиму 'wedding'");
        softAssert.assertAll();
    }
}
