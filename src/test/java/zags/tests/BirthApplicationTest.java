package zags.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.TmsLink;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import zags.core.ApplicationRepository;
import zags.data.TestData;
import zags.models.Application;
import zags.pages.MainPage;
import zags.pages.user.StatusPage;

import static zags.data.Constants.STATUS_IN_PROGRESS;
@Epic("Пользовательские заявки")
@Feature("Создание заявки на рождение")
@Test(testName = "Создание заявки на регистрацию рождения (Пользователь)")
public class BirthApplicationTest extends BaseTest {
    private Integer createdAppId;
    @AfterMethod(alwaysRun = true)
    @Step("Очистка тестовых данных")
    public void cleanup() {
        if (createdAppId != null) {
            ApplicationRepository.deleteById(createdAppId);
            log.info("Тестовая заявка ID={} удалена из БД", createdAppId);
        }
    }
    @Test( groups = {"user"}, description = "Успешное создание заявки и получение статуса, типа")
    @Description("Проверяем, что пользователь может заполнить все формы заявки на рождение " +
            "и после завершения видит номер заявки и статус 'На рассмотрении'")
    @TmsLink("176")
    @Severity(SeverityLevel.CRITICAL)
    public void checkCreateBirthApplication() {
        Application app = TestData.getBirthApplication();

        StatusPage statusPage = new MainPage(driver)
                .selectUserMode()
                .applicantFillAndNext(app)
                .selectBirth()
                .birthCitizenDataFillAndNext(app)
                .birthServiceFillAndFinish(app);

        String number = statusPage.getApplicationNumber();
        createdAppId = Integer.parseInt(number.trim());
        String status = statusPage.getStatus();

        SoftAssert softAssert =new SoftAssert();
        softAssert.assertNotNull(number, "Номер заявки не получен");
        softAssert.assertFalse(number.isEmpty(), "Номер заявки пустой");
        softAssert.assertTrue(status.contains(STATUS_IN_PROGRESS),
                "Неверный статус заявки: " + status);
        softAssert.assertAll();

    }
}