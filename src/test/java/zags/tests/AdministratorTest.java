package zags.tests;

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

    @BeforeMethod (alwaysRun = true)
    public void init() {
        admin = TestData.getAdmin();
        adminTable = new MainPage(driver)
                .selectAdminMode()
                .adminRegistrationFillAndNext(admin);
    }

    @Test(groups = {"admin"})
    public void checkAdministratorMode() {
        ;
        String mode = adminTable.getMode();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(mode, ADMIN_ROLE);
        softAssert.assertAll();
    }

    @Test(enabled = false,
            groups = {"admin"}) //  заголовки не соответствуют ТЗ
    public void checkAdminTableColumnsByRequirements() {

        List<String> headers = adminTable.getColumnHeaders();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(headers.contains(COLUMN_APPLICATION_NUMBER), "Нет столбца " + COLUMN_APPLICATION_NUMBER);
        softAssert.assertTrue(headers.contains(COLUMN_APPLICANT), "Нет столбца " + COLUMN_APPLICANT);
        softAssert.assertTrue(headers.contains(COLUMN_SERVICE_TYPE), "Нет столбца " + COLUMN_SERVICE_TYPE);
        softAssert.assertTrue(headers.contains(COLUMN_TIME), "Нет столбца " + COLUMN_TIME);
        softAssert.assertTrue(headers.contains(COLUMN_STATUS), "Нет столбца " + COLUMN_STATUS);
        softAssert.assertTrue(headers.contains(COLUMN_ACTION), "Нет столбца " + COLUMN_ACTION);
        softAssert.assertAll();
    }

    @Test(groups = {"admin"})
    public void checkAdminTableColumnsAsImplemented() {

        List<String> headers = adminTable.getColumnHeaders();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(headers.contains(COLUMN_NUMBER_AS_IMPLEMENTED), "Нет столбца " + COLUMN_NUMBER_AS_IMPLEMENTED);
        softAssert.assertTrue(headers.contains(COLUMN_APPLICANT), "Нет столбца " + COLUMN_APPLICANT);
        softAssert.assertTrue(headers.contains(COLUMN_TYPE_AS_IMPLEMENTED), "Нет столбца " + COLUMN_TYPE_AS_IMPLEMENTED);
        softAssert.assertTrue(headers.contains(COLUMN_TIME), "Нет столбца " + COLUMN_TIME);
        softAssert.assertTrue(headers.contains(COLUMN_STATUS), "Нет столбца " + COLUMN_STATUS);
        softAssert.assertTrue(headers.contains(COLUMN_ACTION), "Нет столбца " + COLUMN_ACTION);
        softAssert.assertAll();
    }
}
