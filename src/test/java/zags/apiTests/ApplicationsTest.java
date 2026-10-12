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
import zags.api.models.ApplicationResponseForAdmin;
import zags.api.models.UserCreateResponceApp;
import zags.data.Builders;

import java.util.List;
import java.util.Objects;

@Epic("API администратора")
@Feature("Список заявок")
@Test(testName = "API: Получение списка всех заявок")
public class ApplicationsTest {

    private Integer appId;

    @BeforeMethod(alwaysRun = true)
    public void prepare() {
        UserCreateResponceApp createdApplication = UserApi.sendUserRequest(Builders.userWeddingRequest());
        appId = createdApplication.getApplicationid();
    }

    @Test(description = "Получить все заявки", groups = {"API"})
    @Story("GET /getApplications")
    @Description("Свежесозданная заявка присутствует в списке, все статусы допустимы, ID уникальны")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("185")
    public void checkApplications() {

        List<ApplicationResponseForAdmin> applications = AdminApi.getAllApplication();
        List<String> allowedStatuses = List.of("approved", "under consideration", "rejected");
        boolean newAppFound = applications.stream()
                .anyMatch(ap -> Objects.equals(ap.getApplicationid(), appId));

        List<Integer> ids = applications.stream().map(ApplicationResponseForAdmin::getApplicationid).toList();
        long uniqueCount = ids.stream().distinct().count();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(newAppFound,
                "Свежая заявка id=" + appId + " не найдена в списке");
        softAssert.assertFalse(applications.isEmpty());
        applications.forEach(ap -> softAssert.assertTrue(allowedStatuses.contains(ap.getStatusofapplication()),
                "Недопустимый статус заявки, id:" + ap.getApplicationid()));
        softAssert.assertEquals(uniqueCount, ids.size(), "Существуют дубликаты ID заявок");
        softAssert.assertAll();
    }
}
