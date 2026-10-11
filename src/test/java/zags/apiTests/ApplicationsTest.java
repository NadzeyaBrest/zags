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
import zags.api.models.ApplicationResponseForAdmin;

import java.util.List;

import static io.restassured.RestAssured.given;

@Epic("API администратора")
@Feature("Список заявок")
@Test(testName = "API: Получение списка всех заявок",
        groups = {"API"})
public class ApplicationsTest {
    @Test(description = "Получить все заявки")
    @Story("GET /getApplications")
    @Description("Проверяем, что все заявки имеют допустимый статус и уникальный ID")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("185")
    public void checkApplications() {

        List<ApplicationResponseForAdmin> applications = AdminApi.getAllApplication();
        List<String> allowedStatuses = List.of("approved", "under consideration", "rejected");
        List<Integer> ids = applications.stream().map(ApplicationResponseForAdmin::getApplicationid).toList();
        long uniqueCount = ids.stream().distinct().count();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(applications.isEmpty());
        applications.forEach(ap -> softAssert.assertTrue(allowedStatuses.contains(ap.getStatusofapplication()),
                "Недопустимый статус заявки, id:" + ap.getApplicationid()));
        softAssert.assertEquals(uniqueCount, ids.size(), "Существуют дубликаты ID заявок");
        softAssert.assertAll();
    }
}
