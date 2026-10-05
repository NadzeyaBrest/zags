package zags.tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import zags.data.TestData;
import zags.models.Application;
import zags.pages.MainPage;
import zags.pages.user.StatusPage;

public class MarriageApplicationTest extends BaseTest {
    @Test (groups = {"user"})
    public void createMarriageApplication() {
        Application app = TestData.getMarriageApplication();
        SoftAssert softAssert = new SoftAssert();

        StatusPage marriageStatusPage = new MainPage(driver)
                .selectUserMode()
                .applicantFillAndNext(app)
                .selectMarriage()
                .marriageCitizenFillAndNext(app)
                .marriageServiceFillAndFinish(app);


        String number = marriageStatusPage.getApplicationNumber();
        String status = marriageStatusPage.getStatus();
        softAssert.assertNotNull(number, "Номер заявки не получен");
        softAssert.assertFalse(number.isEmpty(), "Номер заявки пустой");
        softAssert.assertTrue(status.contains("На рассмотрении"),
                "Неверный статус заявки: " + status);
        softAssert.assertAll();
    }
}


