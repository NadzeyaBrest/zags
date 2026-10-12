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
import zags.data.Builders;

@Epic("API заявок")
@Feature("Создание заявки на брак")
@Test(testName = "API: Создание заявки на брак")
public class MarriageUserApplicationTest {

    private Integer appId;

    @AfterMethod(alwaysRun = true)
    public void cleanup() {
        if (appId != null) {
            ApplicationRepository.deleteById(appId);
        }
    }
    @Test(description = "Пользователь создаёт заявку на брак",         groups = {"API"})
    @Story("POST /sendUserRequest (wedding)")
    @Description("Заявка создаётся, возвращается merrigecertificateid")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("183")
    public void checkCreateMarriageUserApplication() {
        UserCreateRequest userAppMarriage = Builders.userWeddingRequest();
        UserCreateResponceApp createdMarriageApplication = UserApi.sendUserRequest(userAppMarriage);
        appId = createdMarriageApplication.getApplicationid();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(createdMarriageApplication, "data = null");
        softAssert.assertTrue(createdMarriageApplication.getApplicationid() > 0,
                "applicationid = " + createdMarriageApplication.getApplicationid());
        softAssert.assertTrue(createdMarriageApplication.getApplicantid() > 0,
                "applicantid = " + createdMarriageApplication.getApplicantid());
        softAssert.assertTrue(createdMarriageApplication.getCitizenid() > 0,
                "citizenid = " + createdMarriageApplication.getCitizenid());
        softAssert.assertNotNull(createdMarriageApplication.getMerrigecertificateid(),
                "marriagecertificateid = null");

        softAssert.assertTrue(ApplicationRepository.existsById(appId),
                "Заявка " + appId + " не найдена в БД");
        softAssert.assertEquals(ApplicationRepository.getStatusById(appId),
                "under consideration", "Неверный статус в БД");
        softAssert.assertEquals(ApplicationRepository.getKindById(appId),
                "Получение свидетельства о браке", "Неверный тип в БД");
        softAssert.assertAll();
    }
}
