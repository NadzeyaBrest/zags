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
import zags.api.models.ApplicationResponseForAdmin;
import zags.api.models.UserCreateResponceApp;
import zags.core.ApplicationRepository;
import zags.data.UserBuilder;

import java.util.List;
import java.util.Objects;

@Epic("API администратора")
@Feature("Список заявок")
@Test(testName = "API: Получение списка всех заявок")
public class ApplicationsTest {

    private Integer appId;
    @AfterMethod(alwaysRun = true)
    @Step("Очистка тестовых данных")
    public void cleanup() {
        if (appId != null) {
            ApplicationRepository.deleteById(appId);
        }
    }
    @BeforeMethod(alwaysRun = true)
    public void prepare() {
        UserCreateResponceApp createdApplication = UserApi.sendUserRequest(UserBuilder.userWeddingRequest()).jsonPath()
                .getObject("data", UserCreateResponceApp.class);;
        appId = createdApplication.getApplicationid();
    }

    @Test(description = "Получить все заявки", groups = {"API"})
    @Story("GET /getApplications")
    @Description("Свежесозданная заявка присутствует в списке, все статусы допустимы, ID уникальны")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("185")
    public void checkApplications() {
        Response response = AdminApi.getAllApplication();
        List<ApplicationResponseForAdmin> applications = response.jsonPath()
                .getList("data", ApplicationResponseForAdmin.class);
        List<String> allowedStatuses = List.of("approved", "under consideration", "rejected");
        boolean newAppFound = applications.stream()
                .anyMatch(ap -> Objects.equals(ap.getApplicationid(), appId));

        List<Integer> ids = applications.stream().map(ApplicationResponseForAdmin::getApplicationid).toList();
        long uniqueCount = ids.stream().distinct().count();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(),200);
        softAssert.assertTrue(newAppFound,
                "Свежая заявка id=" + appId + " не найдена в списке");
        softAssert.assertFalse(applications.isEmpty());
        applications.forEach(ap -> softAssert.assertTrue(allowedStatuses.contains(ap.getStatusofapplication()),
                "Недопустимый статус заявки, id:" + ap.getApplicationid()));
        softAssert.assertEquals(uniqueCount, ids.size(), "Существуют дубликаты ID заявок");
        softAssert.assertAll();
    }
}
