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
import zags.api.UserApi;
import zags.api.models.UserCreateResponceApp;
import zags.api.models.UserCreateRequest;
import zags.core.ApplicationRepository;

@Epic("API заявок")
@Feature("Создание заявки на смерть")
@Test(testName = "API: Создание заявки на смерть",
        groups = {"API"})
public class DeathUserApplicationTest {
    @Test(description = "Пользователь создаёт заявку на смерть")
    @Story("POST /sendUserRequest (death)")
    @Description("Заявка создаётся, возвращается deathcertificateid")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("182")
    public void checkCreateDeathUserApplication() {
        UserCreateRequest userAppDeath = UserCreateRequest.builder()
                .mode("death")
                .personalLastName("Vladi")
                .personalFirstName("Olga")
                .personalMiddleName("Ivanovna")
                .personalPhoneNumber("7888823")
                .personalNumberOfPassport("MA456444")
                .citizenLastName("Vladi")
                .citizenFirstName("Olga")
                .citizenMiddleName("Ivanovna")
                .citizenBirthDate("1998-06-11")
                .citizenNumberOfPassport("MA45689")
                .citizenGender("Жен")
                .death_dateOfDeath("2026-10-0")
                .death_placeOfDeath("Minsk")
                .build();

        UserCreateResponceApp createdDeathApplication = UserApi.sendUserRequest(userAppDeath);
        int appId = createdDeathApplication.getApplicationid();
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
        softAssert.assertEquals(ApplicationRepository.getKindById(appId),
                "Получение свидетельства о рождении", "Неверный тип в БД");

        softAssert.assertAll();
    }
}
