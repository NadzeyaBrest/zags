package zags.tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import zags.data.TestData;
import zags.models.Application;
import zags.pages.MainPage;
import zags.pages.user.StatusPage;

import static zags.data.Constants.*;

public class MarriageApplicationTest extends BaseTest {
    @Test(groups = {"user"})
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


