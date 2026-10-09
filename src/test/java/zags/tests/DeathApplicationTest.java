package zags.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import zags.data.TestData;
import zags.models.Application;
import zags.pages.MainPage;
import zags.pages.user.StatusPage;
import static zags.data.Constants.*;
@Epic("Пользовательские заявки")
@Feature("Создание заявки на смерть")
@Test(testName = "Создание заявки на регистрацию смерти (Пользователь)")
public class DeathApplicationTest extends BaseTest {
    @Test ( groups = {"user"},description = "Пользователь создаёт заявку на смерть")
    @Story("Пользователь создаёт заявку на смерть")
    @Description("Проверяем создание заявки на смерть: заполнение всех форм и получение номера со статусом")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("177")
    public void checkCreateDeathApplication() {
        Application app = TestData.getDeathApplication();

        StatusPage deathStatusPage = new MainPage(driver)
                .selectUserMode()
                .applicantFillAndNext(app)
                .selectDeath()
                .deathCitizenFillAndNext(app)
                .deathServiceFillAndFinish(app);

        String number = deathStatusPage.getApplicationNumber();
        String status = deathStatusPage.getStatus();
        SoftAssert softAssert =new SoftAssert();
        softAssert.assertNotNull(number, "Номер заявки не получен");
        softAssert.assertFalse(number.isEmpty(), "Номер заявки пустой");
        softAssert.assertTrue(status.contains(STATUS_IN_PROGRESS), "Неверный статус заявки: " + status);
        softAssert.assertAll();


    }


}
