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
import zags.api.UserApi;
import zags.api.models.UserCreateResponceApp;
import zags.api.models.UserCreateRequest;
import zags.core.ApplicationRepository;
import zags.data.UserBuilder;

@Epic("API заявок")
@Feature("Создание заявки на смерть")
@Test(testName = "API: Создание заявки на смерть")
public class DeathUserApplicationTest {

    private Integer appId;

    @AfterMethod(alwaysRun = true)
    public void cleanup() {
        if (appId != null) {
            ApplicationRepository.deleteById(appId);
        }
    }
    @Test(description = "Пользователь создаёт заявку на смерть",   groups = {"API"})
    @Story("POST /sendUserRequest (death)")
    @Description("Заявка создаётся, возвращается deathcertificateid")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("182")
    public void checkCreateDeathUserApplication() {
        UserCreateRequest userAppDeath = UserBuilder.userDeathRequest();
        UserCreateResponceApp createdDeathApplication = UserApi.sendUserRequest(userAppDeath);
         appId = createdDeathApplication.getApplicationid();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(createdDeathApplication, "data = null");
        softAssert.assertTrue(createdDeathApplication.getApplicationid() > 0,
                "applicationid = " + createdDeathApplication.getApplicationid());
        softAssert.assertTrue(createdDeathApplication.getApplicantid() > 0,
                "applicantid = " + createdDeathApplication.getApplicantid());
        softAssert.assertTrue(createdDeathApplication.getCitizenid() > 0,
                "citizenid = " + createdDeathApplication.getCitizenid());
        softAssert.assertNotNull(createdDeathApplication.getDeathcertificateid(),
                "deathcertificateid = null");

        softAssert.assertTrue(ApplicationRepository.existsById(appId),
                "Заявка " + appId + " не найдена в БД");
        softAssert.assertEquals(ApplicationRepository.getStatusById(appId),
                "under consideration", "Неверный статус в БД");
        softAssert.assertEquals(ApplicationRepository.getKindById(appId),
                "Получение свидетельства о смерти", "Неверный тип в БД");
        softAssert.assertAll();
    }
}
