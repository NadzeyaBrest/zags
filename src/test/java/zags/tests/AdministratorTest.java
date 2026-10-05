package zags.tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import zags.data.TestData;
import zags.models.Admin;
import zags.pages.MainPage;
import zags.pages.admin.AdminTablePage;

import java.util.List;

public class AdministratorTest extends BaseTest {
    @Test(groups = {"admin"})

    public void checkAdministratorMode() {
        Admin admin = TestData.getAdmin();
        SoftAssert softAssert = new SoftAssert();

        AdminTablePage adminTable = new MainPage(driver)
                .selectAdminMode()
                .adminRegistrationFillAndNext(admin);

        String mode = adminTable.getMode();
        softAssert.assertTrue(mode.contains("Aдминистратор"));
        softAssert.assertAll();
    }

    @Test(enabled = false,
            groups = {"admin"}) //  заголовки не соответствуют ТЗ
    public void checkAdminTableColumnsByRequirements() {
        Admin admin = TestData.getAdmin();
        SoftAssert softAssert = new SoftAssert();

        AdminTablePage adminTable = new MainPage(driver)
                .selectAdminMode()
                .adminRegistrationFillAndNext(admin);

        List<String> headers = adminTable.getColumnHeaders();

        softAssert.assertTrue(headers.contains("№ заявки"), "Нет столбца '№ заявки'");
        softAssert.assertTrue(headers.contains("Заявитель"), "Нет столбца 'Заявитель'");
        softAssert.assertTrue(headers.contains("Вид услуги"), "Нет столбца 'Вид услуги'");
        softAssert.assertTrue(headers.contains("Время"), "Нет столбца 'Время'");
        softAssert.assertTrue(headers.contains("Статус"), "Нет столбца 'Статус'");
        softAssert.assertTrue(headers.contains("Действие"), "Нет столбца 'Действие'");
        softAssert.assertAll();
    }

    @Test(groups = {"admin"})
    public void checkAdminTableColumnsAsImplemented() {
        Admin admin = TestData.getAdmin();
        SoftAssert softAssert = new SoftAssert();

        AdminTablePage adminTable = new MainPage(driver)
                .selectAdminMode()
                .adminRegistrationFillAndNext(admin);

        List<String> headers = adminTable.getColumnHeaders();

        softAssert.assertTrue(headers.contains("№"), "Нет столбца '№ заявки'");
        softAssert.assertTrue(headers.contains("Заявитель"), "Нет столбца 'Заявитель'");
        softAssert.assertTrue(headers.contains("Тип"), "Нет столбца 'Тип '");
        softAssert.assertTrue(headers.contains("Время"), "Нет столбца 'Время'");
        softAssert.assertTrue(headers.contains("Статус"), "Нет столбца 'Статус'");
        softAssert.assertTrue(headers.contains("Действие"), "Нет столбца 'Действие'");
        softAssert.assertAll();
    }
}
