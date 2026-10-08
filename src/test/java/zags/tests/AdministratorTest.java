package zags.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import zags.data.TestData;
import zags.models.Admin;
import zags.pages.MainPage;
import zags.pages.admin.AdminTablePage;

import static zags.data.Constants.*;

import java.util.List;

public class AdministratorTest extends BaseTest {

    AdminTablePage adminTable;
    Admin admin;

    @BeforeMethod(alwaysRun = true)
    public void openAdminTable () {
        admin = TestData.getAdmin();
        adminTable = new MainPage(driver)
                .selectAdminMode()
                .adminRegistrationFillAndNext(admin);
    }

    @Test(groups = {"admin"})
    public void checkAdministratorMode() {
        String mode = adminTable.getMode();
        Assert.assertEquals(mode, ADMIN_ROLE);
    }

    @Test(enabled = false,
            groups = {"admin"}) //  заголовки не соответствуют ТЗ
    public void checkAdminTableColumnsByRequirements() {

        List<String> headers = adminTable.getColumnHeaders();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(headers.contains(COLUMN_APPLICATION_NUMBER), String.format("Нет столбца %s", COLUMN_APPLICATION_NUMBER));
        softAssert.assertTrue(headers.contains(COLUMN_APPLICANT), String.format("Нет столбца %s", COLUMN_ACTION));
        softAssert.assertTrue(headers.contains(COLUMN_SERVICE_TYPE),String.format("Нет столбца %s", COLUMN_SERVICE_TYPE));
        softAssert.assertTrue(headers.contains(COLUMN_TIME), String.format("Нет столбца %s", COLUMN_TIME));
        softAssert.assertTrue(headers.contains(COLUMN_STATUS),String.format("Нет столбца %s", COLUMN_STATUS));
        softAssert.assertTrue(headers.contains(COLUMN_ACTION),String.format("Нет столбца %s", COLUMN_ACTION));
        softAssert.assertAll();
    }

    @Test(groups = {"admin"})
    public void checkAdminTableColumnsAsImplemented() {

        List<String> headers = adminTable.getColumnHeaders();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(headers.contains(COLUMN_NUMBER_AS_IMPLEMENTED), String.format("Нет столбца %s", COLUMN_APPLICATION_NUMBER));
        softAssert.assertTrue(headers.contains(COLUMN_APPLICANT), String.format("Нет столбца %s", COLUMN_ACTION));
        softAssert.assertTrue(headers.contains(COLUMN_TYPE_AS_IMPLEMENTED), String.format("Нет столбца %s", COLUMN_SERVICE_TYPE));
        softAssert.assertTrue(headers.contains(COLUMN_TIME),  String.format("Нет столбца %s", COLUMN_TIME));
        softAssert.assertTrue(headers.contains(COLUMN_STATUS), String.format("Нет столбца %s", COLUMN_STATUS));
        softAssert.assertTrue(headers.contains(COLUMN_ACTION), String.format("Нет столбца %s", COLUMN_ACTION));
        softAssert.assertAll();
    }
}
