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
@Feature("Создание заявки на брак")
@Test(testName = "Создание заявки на регистрацию брака (Пользователь)")
public class MarriageApplicationTest extends BaseTest {

    @Test(groups = {"user"},description = "Пользователь создаёт заявку на брак")
    @Story("Пользователь создаёт заявку на брак")
    @Description("Проверяем создание заявки на брак: заполнение всех форм и получение номера со статусом")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("175")
    public void checkCreateMarriageApplication() {
        Application app = TestData.getMarriageApplication();

        StatusPage marriageStatusPage = new MainPage(driver)
                .selectUserMode()
                .applicantFillAndNext(app)
                .selectMarriage()
                .marriageCitizenFillAndNext(app)
                .marriageServiceFillAndFinish(app);

        SoftAssert softAssert = new SoftAssert();
        String number = marriageStatusPage.getApplicationNumber();
        String status = marriageStatusPage.getStatus();
        softAssert.assertNotNull(number, "Номер заявки не получен");
        softAssert.assertFalse(number.isEmpty(), "Номер заявки пустой");
        softAssert.assertTrue(status.contains(STATUS_IN_PROGRESS),
                "Неверный статус заявки: " + status);
        softAssert.assertAll();
    }
}


