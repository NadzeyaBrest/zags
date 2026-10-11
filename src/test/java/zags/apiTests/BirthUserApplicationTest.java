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
@Feature("Создание заявки на рождение")
@Test(testName = "API: Создание заявки на рождение",
        groups = {"API"})
public class BirthUserApplicationTest {
    @Test(description = "Пользователь создаёт заявку на рождение")
    @Story("POST /sendUserRequest (birth)")
    @Description("Заявка создаётся, возвращается birthcertificateid")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("181")
    public void checkCreateBirthUserApplication() {
        UserCreateRequest userAppBirth = UserCreateRequest
                .builder()
                .mode("birth")
                .personalLastName("Ivanov")
                .personalFirstName("Ivan")
                .personalMiddleName("Ivanovich")
                .personalPhoneNumber("79991234567")
                .personalNumberOfPassport("AB123456")
                .citizenLastName("Ivanov")
                .citizenFirstName("Petr")
                .citizenMiddleName("Ivanovich")
                .citizenBirthDate("2001-06-11")
                .citizenNumberOfPassport("AB123456")
                .citizenGender("Муж")
                .birth_place("Brest")
                .birth_mother("Ivanova Maria")
                .birth_father("Ivanov Ivan")
                .birth_grandma("Vilova Inna")
                .birth_grandpa("Vilou David")
                .build();

        UserCreateResponceApp createdBirthApplication = UserApi.sendUserRequest(userAppBirth);

        int appId = createdBirthApplication.getApplicationid();

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
