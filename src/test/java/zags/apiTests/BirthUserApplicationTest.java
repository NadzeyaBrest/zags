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
@Feature("Создание заявки на рождение")
@Test(testName = "API: Создание заявки на рождение")
public class BirthUserApplicationTest {
    private Integer appId;
    @AfterMethod(alwaysRun = true)
    public void cleanup() {
        if (appId != null) {
            ApplicationRepository.deleteById(appId);
        }
    }
    @Test(description = "Пользователь создаёт заявку на рождение",    groups = {"API"})
    @Story("POST /sendUserRequest (birth)")
    @Description("Заявка создаётся, возвращается birthcertificateid")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("181")
    public void checkCreateBirthUserApplication() {
        UserCreateRequest userAppBirth = Builders.userBirthRequest();
        UserCreateResponceApp createdBirthApplication = UserApi.sendUserRequest(userAppBirth);
        appId = createdBirthApplication.getApplicationid();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(createdBirthApplication, "data = null");
        softAssert.assertTrue(createdBirthApplication.getApplicationid() > 0,
                "applicationid = " + createdBirthApplication.getApplicationid());
        softAssert.assertTrue(createdBirthApplication.getApplicantid() > 0,
                "applicantid = " + createdBirthApplication.getApplicantid());
        softAssert.assertTrue(createdBirthApplication.getCitizenid() > 0,
                "citizenid = " + createdBirthApplication.getCitizenid());
        softAssert.assertNotNull(createdBirthApplication.getBirthcertificateid(),
                "birthcertificateid = null");

        softAssert.assertTrue(ApplicationRepository.existsById(appId), "Заявка "+appId + " не найдена в БД");
        softAssert.assertEquals(ApplicationRepository.getStatusById(appId),
                "under consideration", "Неверный статус заявки в БД");

        softAssert.assertAll();
    }

}
