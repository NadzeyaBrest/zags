package zags.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import zags.core.BaseTest;
import zags.models.Admin;
import zags.pages.MainPage;
import zags.pages.admin.AdminTablePage;

public class AdministratorTest extends BaseTest {
    @Test
    public void testAdministratorMode() {
        Admin admin = new Admin()
                .lastName("Fedorov")
                .firstName("Fedor")
                .middleName("Borisovich")
                .phone("123456")
                .passportNumber("Ba12345")
                .birthDate("26.04.1990");

        AdminTablePage adminTable = new MainPage(driver)
                .selectAdminMode()
                .adminRegistrationFillAndNext(admin);

        String mode = adminTable.getMode();
        Assert.assertTrue(adminTable.isTableDisplayed());

        Assert.assertTrue(mode.contains("Aдминистратор"));
    }


    public void testDateInAdminTable(){

    }

}
