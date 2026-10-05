package zags.tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import zags.data.TestData;
import zags.models.Admin;
import zags.models.Application;
import zags.pages.MainPage;
import zags.pages.admin.AdminTablePage;
import zags.pages.user.StatusPage;

public class AdminCheckApplicationTest extends BaseTest {
    @Test (groups = {"crossRole"})
    public void checkBirthApplicationByAdmin() {
        Application birthApp = TestData.getBirthApplication();
        SoftAssert softAssert = new SoftAssert();

        StatusPage statusPage = new MainPage(driver)
                .selectUserMode()
                .applicantFillAndNext(birthApp)
                .selectBirth()
                .birthCitizenDataFillAndNext(birthApp)
                .birthServiceFillAndFinish(birthApp);

        String applicationNumberShowedUser = statusPage.getApplicationNumber();
        String applicationStatusShowedUser = statusPage.getStatus();

        statusPage.clickCloseButton();
        Admin admin = TestData.getAdmin();

        AdminTablePage adminTable = new MainPage(driver)
                .selectAdminMode()
                .adminRegistrationFillAndNext(admin);


        String idLatestAppFromAdminTable = adminTable.getIdLatestAppFromAdminTable();
        String typeOfAppFromAdminTable = adminTable.getTypeById(idLatestAppFromAdminTable);
        String statusOfAppFromAdminTable = adminTable.getStatusById(idLatestAppFromAdminTable);

        softAssert.assertFalse(applicationNumberShowedUser.isEmpty(), "ID новой заявки не отражается на странице Статуса заявки пользователя");
        softAssert.assertEquals(applicationNumberShowedUser, idLatestAppFromAdminTable, "Новая заявка не отразилась сверху таблицы");
        softAssert.assertFalse(idLatestAppFromAdminTable.isEmpty(), "ID новой заявки не отражается в таблице");
        softAssert.assertEquals(typeOfAppFromAdminTable, "Получение свидетельства о рождении",
                "Тип заявки отличается от выбранной пользователем");
        softAssert.assertEquals(statusOfAppFromAdminTable, applicationStatusShowedUser, "Статус заявки в таблице администратора отличается от статуса заявки пользователя");
        softAssert.assertAll();
    }

    @Test (groups = {"crossRole"})
    public void checkMarriageApplicationByAdmin() {
        Application marriageApp = TestData.getMarriageApplication();
        SoftAssert softAssert = new SoftAssert();
        StatusPage marriageStatusPage = new MainPage(driver)
                .selectUserMode()
                .applicantFillAndNext(marriageApp)
                .selectMarriage()
                .marriageCitizenFillAndNext(marriageApp)
                .marriageServiceFillAndFinish(marriageApp);
        String applicationNumberShowedUser = marriageStatusPage.getApplicationNumber();
        String applicationStatusShowedUser = marriageStatusPage.getStatus();
        marriageStatusPage.clickCloseButton();

        Admin admin = TestData.getAdmin();

        AdminTablePage adminTable = new MainPage(driver)
                .selectAdminMode()
                .adminRegistrationFillAndNext(admin);


        String idLatestAppFromAdminTable = adminTable.getIdLatestAppFromAdminTable();
        String typeOfAppFromAdminTable = adminTable.getTypeById(idLatestAppFromAdminTable);
        String statusOfAppFromAdminTable = adminTable.getStatusById(idLatestAppFromAdminTable);


        softAssert.assertFalse(applicationNumberShowedUser.isEmpty(), "ID новой заявки не отражается на странице Статуса заявки пользователя");
        softAssert.assertEquals(applicationNumberShowedUser, idLatestAppFromAdminTable, "Новая заявка не отразилась сверху таблицы");
        softAssert.assertFalse(idLatestAppFromAdminTable.isEmpty(), "ID новой заявки не отражается в таблице");
        softAssert.assertEquals(typeOfAppFromAdminTable, "Получение свидетельства о браке",
                "Тип заявки отличается от выбранной пользователем");
        softAssert.assertEquals(statusOfAppFromAdminTable, applicationStatusShowedUser, "Статус заявки в таблице администратора отличается от статуса заявки пользователя");
        softAssert.assertAll();
        


    }

    @Test (groups = {"crossRole"})
    public void checkDeathApplicationByAdmin() {
        Application deathApp = TestData.getDeathApplication();
        SoftAssert softAssert = new SoftAssert();
        
        StatusPage deathStatusPage = new MainPage(driver)
                .selectUserMode()
                .applicantFillAndNext(deathApp)
                .selectDeath()
                .deathCitizenFillAndNext(deathApp)
                .deathServiceFillAndFinish(deathApp);
        String applicationNumberShowedUser = deathStatusPage.getApplicationNumber();
        String applicationStatusShowedUser = deathStatusPage.getStatus();
        deathStatusPage.clickCloseButton();

        Admin admin = TestData.getAdmin();

        AdminTablePage adminTable = new MainPage(driver)
                .selectAdminMode()
                .adminRegistrationFillAndNext(admin);
        

        String idLatestAppFomAdminTable = adminTable.getIdLatestAppFromAdminTable();
        String typeOfAppFromAdminTable = adminTable.getTypeById(idLatestAppFomAdminTable);
        String statusOfAppFromAdminTable = adminTable.getStatusById(idLatestAppFomAdminTable);

        softAssert.assertFalse(applicationNumberShowedUser.isEmpty(), "ID новой заявки не отражается на странице Статуса заявки пользователя");
        softAssert.assertEquals(applicationNumberShowedUser, idLatestAppFomAdminTable, "Новая заявка не отразилась сверху таблицы");
        softAssert.assertFalse(idLatestAppFomAdminTable.isEmpty(), "ID новой заявки не отражается в таблице");
        softAssert.assertEquals(typeOfAppFromAdminTable, "Получение свидетельства о смерти",
                "Тип заявки отличается от выбранной пользователем");
        softAssert.assertEquals(statusOfAppFromAdminTable, applicationStatusShowedUser, "Статус заявки в таблице администратора отличается от статуса заявки пользователя");
        softAssert.assertAll();

    }
}