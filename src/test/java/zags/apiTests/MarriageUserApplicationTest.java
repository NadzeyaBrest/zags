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
@Feature("Создание заявки на брак")
@Test(testName = "API: Создание заявки на брак",
        groups = {"API"})
public class MarriageUserApplicationTest {
    @Test(description = "Пользователь создаёт заявку на брак")
    @Story("POST /sendUserRequest (wedding)")
    @Description("Заявка создаётся, возвращается merrigecertificateid")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("183")
    public void checkCreateMarriageUserApplication() {
        UserCreateRequest userAppMarriage = UserCreateRequest.builder()
                .mode("wedding")
                .personalLastName("Gromov")
                .personalFirstName("Mark")
                .personalMiddleName("Ivanovich")
                .personalPhoneNumber("799912345")
                .personalNumberOfPassport("AB123456")
                .personalAddress("Брестская")
                .citizenLastName("Gromov")
                .citizenFirstName("Mark")
                .citizenMiddleName("Ivanovich")
                .citizenBirthDate("2000-09-11")
                .citizenNumberOfPassport("AB123456")
                .citizenGender("Муж")
                .citizenAddress("Brestskaya,15")
                .dateOfMarriage("2026-09-30")
                .newLastName("Gromov")
                .anotherPersonLastName("Svetova")
                .anotherPersonFirstName("Svetlana")
                .anotherPersonMiddleName("Petrovna")
                .birth_of_anotoherPerson("2001-06-11")
                .anotherPersonPassport("AB176543")
                .build();

        UserCreateResponceApp createdMarriageApplication = UserApi.sendUserRequest(userAppMarriage);
        Integer appId = createdMarriageApplication.getApplicationid();
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
