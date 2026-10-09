package zags.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
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

@Epic("Панель администратора")
@Feature("Просмотр таблицы заявок")
public class AdministratorTest extends BaseTest {

    AdminTablePage adminTable;
    Admin admin;

    @BeforeMethod(alwaysRun = true)
    public void openAdminTable() {
        admin = TestData.getAdmin();
        adminTable = new MainPage(driver)
                .selectAdminMode()
                .adminRegistrationFillAndNext(admin);
    }

    @Test(testName = "Проверка роли администратора в шапке страницы",
            groups = {"admin"})
    @Story("Проверка роли администратора")
    @Description("Проверяем, что после выбора роли администратор в шапке отображается роль Администратор")
    public void checkAdministratorMode() {
        String mode = adminTable.getMode();
        Assert.assertEquals(mode, ADMIN_ROLE);
    }

    @Test(  testName = "Проверка заголовков таблицы по ТЗ", groups = {"admin"}, enabled = false) //  заголовки не соответствуют ТЗ
    @Story("Проверка заголовков таблицы по ТЗ")
    @Description("Проверяем, что заголовки колонок таблицы соответствуют требованиям ТЗ")
    @TmsLink("156")
    public void checkAdminTableColumnsByRequirements() {
        List<String> headers = adminTable.getColumnHeaders();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(headers.contains(COLUMN_APPLICATION_NUMBER), String.format("Нет столбца %s", COLUMN_APPLICATION_NUMBER));
        softAssert.assertTrue(headers.contains(COLUMN_APPLICANT), String.format("Нет столбца %s", COLUMN_APPLICANT));
        softAssert.assertTrue(headers.contains(COLUMN_SERVICE_TYPE), String.format("Нет столбца %s", COLUMN_SERVICE_TYPE));
        softAssert.assertTrue(headers.contains(COLUMN_TIME), String.format("Нет столбца %s", COLUMN_TIME));
        softAssert.assertTrue(headers.contains(COLUMN_STATUS), String.format("Нет столбца %s", COLUMN_STATUS));
        softAssert.assertTrue(headers.contains(COLUMN_ACTION), String.format("Нет столбца %s", COLUMN_ACTION));
        softAssert.assertAll();
    }

    @Story("Проверка фактических заголовков таблицы")
    @Test(  testName = "Проверка фактических заголовков таблицы",groups = {"admin"})
    @TmsLink("156")
    public void checkAdminTableColumnsAsImplemented() {

        List<String> headers = adminTable.getColumnHeaders();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(headers.contains(COLUMN_NUMBER_AS_IMPLEMENTED), String.format("Нет столбца %s", COLUMN_APPLICATION_NUMBER));
        softAssert.assertTrue(headers.contains(COLUMN_APPLICANT), String.format("Нет столбца %s", COLUMN_APPLICANT));
        softAssert.assertTrue(headers.contains(COLUMN_TYPE_AS_IMPLEMENTED), String.format("Нет столбца %s", COLUMN_SERVICE_TYPE));
        softAssert.assertTrue(headers.contains(COLUMN_TIME), String.format("Нет столбца %s", COLUMN_TIME));
        softAssert.assertTrue(headers.contains(COLUMN_STATUS), String.format("Нет столбца %s", COLUMN_STATUS));
        softAssert.assertTrue(headers.contains(COLUMN_ACTION), String.format("Нет столбца %s", COLUMN_ACTION));
        softAssert.assertAll();
    }
}
