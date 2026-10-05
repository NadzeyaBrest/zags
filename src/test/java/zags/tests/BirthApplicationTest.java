package zags.tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import zags.data.TestData;
import zags.models.Application;
import zags.pages.MainPage;
import zags.pages.user.StatusPage;

public class BirthApplicationTest extends BaseTest {

    @Test (groups = {"user"})
    public void createBirthApplication() {
        Application app = TestData.getBirthApplication();
        SoftAssert softAssert =new SoftAssert();

        StatusPage statusPage = new MainPage(driver)
                .selectUserMode()
                .applicantFillAndNext(app)
                .selectBirth()
                .birthCitizenDataFillAndNext(app)
                .birthServiceFillAndFinish(app);

        String number = statusPage.getApplicationNumber();
        String status = statusPage.getStatus();

        softAssert.assertNotNull(number, "Номер заявки не получен");
        softAssert.assertFalse(number.isEmpty(), "Номер заявки пустой");
        softAssert.assertTrue(status.contains("На рассмотрении"),
                "Неверный статус заявки: " + status);
        softAssert.assertAll();

    }
}