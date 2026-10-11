package zags.apiTests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import zags.api.AdminApi;
import zags.api.models.ApplicationResponseForAdmin;
import zags.api.models.ApplicationByIdResponse;

import java.util.List;

@Epic("API администратора")
@Feature("Статус заявки по ID")
@Test(testName = "API: Получение статуса заявки по ID", groups = {"API"})
public class ApplicationStatusByIdTest {
    private Integer appId;

    @BeforeMethod(alwaysRun = true)
    public void prepareApplicationId() {
        List<ApplicationResponseForAdmin> applications = AdminApi.getInfoForOneApplication();
        Assert.assertFalse(applications.isEmpty(), "Heт заявок для проверки");
        appId = applications.get(0).getApplicationid();
    }


    @Test(description = "Проверить статус заявки по ID")
    @Story("GET /getApplStatus/{applicationId}")
    @Description("Получаем статус заявки и проверяем, что статус и тип входят в допустимые списки")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("184")
    public void checkStatusById() {
        ApplicationByIdResponse applicationStatus = AdminApi.getApplicationById(appId);
        List<String> allowedStatuses = List.of("approved", "under consideration", "rejected");
        List<String> allowedTypes = List.of(
                "Получение свидетельства о рождении",
                "Получение свидетельства о браке",
                "Получение свидетельства о смерти"
        );

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(applicationStatus, "data = null");
        softAssert.assertTrue(allowedStatuses.contains(applicationStatus.getStatusofapplication()),
                "Недопустимый статус: " + applicationStatus.getStatusofapplication());
        softAssert.assertTrue(
                allowedTypes.contains(applicationStatus.getKindofapplication()),
                "Недопустимый тип: " + applicationStatus.getKindofapplication()
        );
        softAssert.assertAll();
    }
}
