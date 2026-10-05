package zags.tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import zags.data.TestData;
import zags.models.Application;
import zags.pages.MainPage;
import zags.pages.user.StatusPage;

public class DeathApplicationTest extends BaseTest {
    @Test (groups = {"user"})
    public void createDeathApplication() {
        Application app = TestData.getDeathApplication();
        SoftAssert softAssert =new SoftAssert();
        StatusPage deathStatusPage = new MainPage(driver)
                .selectUserMode()
                .applicantFillAndNext(app)
                .selectDeath()
                .deathCitizenFillAndNext(app)
                .deathServiceFillAndFinish(app);

        String number = deathStatusPage.getApplicationNumber();
        String status = deathStatusPage.getStatus();

        softAssert.assertNotNull(number, "Номер заявки не получен");
        softAssert.assertFalse(number.isEmpty(), "Номер заявки пустой");
        softAssert.assertTrue(status.contains("На рассмотрении"), "Неверный статус заявки: " + status);
        softAssert.assertAll();


    }


}
