package zags.tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import zags.data.TestData;
import zags.models.Application;
import zags.pages.MainPage;
import zags.pages.user.StatusPage;
import static zags.data.Constants.*;

public class DeathApplicationTest extends BaseTest {
    @Test (groups = {"user"})
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
