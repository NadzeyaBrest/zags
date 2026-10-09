package zags.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import zags.data.TestData;
import zags.models.Admin;
import zags.models.Application;
import zags.pages.MainPage;
import zags.pages.admin.AdminTablePage;
import zags.pages.user.StatusPage;
@Epic("Кросс-ролевые сценарии")
@Feature("Проверка заявок администратором")
public class AdminCheckApplicationTest extends BaseTest {
    private MainPage mainPage;

    @BeforeMethod (alwaysRun = true)
    public void init() {
        mainPage = new MainPage(driver);
    }

    @Test(testName =  "Проверка заявки на рождение администратором",
            groups = {"crossRole"})
    @Story("Админ проверяет заявку на рождение")
    @Description("Пользователь создаёт заявку на рождение, админ  проверяет, " +
            "что заявка появилась в таблице с корректным типом и статусом")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("179")

    public void checkBirthApplicationByAdmin() {
        Application birthApp = TestData.getBirthApplication();

        StatusPage statusPage = mainPage
                .selectUserMode()
                .applicantFillAndNext(birthApp)
                .selectBirth()
                .birthCitizenDataFillAndNext(birthApp)
                .birthServiceFillAndFinish(birthApp);

        String applicationNumberShowedUser = statusPage.getApplicationNumber();
        String applicationStatusShowedUser = statusPage.getStatus();
        statusPage.clickStatusCloseButton();
        Admin admin = TestData.getAdmin();

        AdminTablePage adminTable = new MainPage(driver)
                .selectAdminMode()
                .adminRegistrationFillAndNext(admin);

        String idLatestAppFromAdminTable = adminTable.getIdLatestAppFromAdminTable();

        String typeOfAppFromAdminTable = adminTable.getTypeById(idLatestAppFromAdminTable);
        String statusOfAppFromAdminTable = adminTable.getStatusById(idLatestAppFromAdminTable);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(applicationNumberShowedUser.isEmpty(), "ID новой заявки не отражается на странице Статуса заявки пользователя");
        softAssert.assertEquals(applicationNumberShowedUser, idLatestAppFromAdminTable, "Новая заявка не отразилась сверху таблицы");
        softAssert.assertFalse(idLatestAppFromAdminTable.isEmpty(), "ID новой заявки не отражается в таблице");
        softAssert.assertEquals(typeOfAppFromAdminTable, "Получение свидетельства о рождении",
                "Тип заявки отличается от выбранной пользователем");
        softAssert.assertEquals(statusOfAppFromAdminTable, applicationStatusShowedUser, "Статус заявки в таблице администратора отличается от статуса заявки пользователя");
        softAssert.assertAll();
    }

    @Test(testName =  "Проверка заявки на рождение администратором",
            groups = {"crossRole"})
    @Story("Админ проверяет заявку на брак")
    @Description("Пользователь создаёт заявку на брак, админ  проверяет её в таблице")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("178")
    public void checkMarriageApplicationByAdmin() {
        Application marriageApp = TestData.getMarriageApplication();

        StatusPage marriageStatusPage = mainPage
                .selectUserMode()
                .applicantFillAndNext(marriageApp)
                .selectMarriage()
                .marriageCitizenFillAndNext(marriageApp)
                .marriageServiceFillAndFinish(marriageApp);
        String applicationNumberShowedUser = marriageStatusPage.getApplicationNumber();
        String applicationStatusShowedUser = marriageStatusPage.getStatus();
        marriageStatusPage.clickStatusCloseButton();

        Admin admin = TestData.getAdmin();

        AdminTablePage adminTable = new MainPage(driver)
                .selectAdminMode()
                .adminRegistrationFillAndNext(admin);

        String idLatestAppFromAdminTable = adminTable.getIdLatestAppFromAdminTable();
        String typeOfAppFromAdminTable = adminTable.getTypeById(idLatestAppFromAdminTable);
        String statusOfAppFromAdminTable = adminTable.getStatusById(idLatestAppFromAdminTable);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(applicationNumberShowedUser.isEmpty(), "ID новой заявки не отражается на странице Статуса заявки пользователя");
        softAssert.assertEquals(applicationNumberShowedUser, idLatestAppFromAdminTable, "Новая заявка не отразилась сверху таблицы");
        softAssert.assertFalse(idLatestAppFromAdminTable.isEmpty(), "ID новой заявки не отражается в таблице");
        softAssert.assertEquals(typeOfAppFromAdminTable, "Получение свидетельства о браке",
                "Тип заявки отличается от выбранной пользователем");
        softAssert.assertEquals(statusOfAppFromAdminTable, applicationStatusShowedUser, "Статус заявки в таблице администратора отличается от статуса заявки пользователя");
        softAssert.assertAll();
    }

    @Test(testName = "Проверка заявки на смерть администратором",
            groups = {"crossRole"})
    @Story("Админ проверяет заявку на смерть")
    @Description("Пользователь создаёт заявку на смерть, админ проверяет её в таблице")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("180")
    public void checkDeathApplicationByAdmin() {
        Application deathApp = TestData.getDeathApplication();

        StatusPage deathStatusPage = mainPage
                .selectUserMode()
                .applicantFillAndNext(deathApp)
                .selectDeath()
                .deathCitizenFillAndNext(deathApp)
                .deathServiceFillAndFinish(deathApp);
        String applicationNumberShowedUser = deathStatusPage.getApplicationNumber();
        String applicationStatusShowedUser = deathStatusPage.getStatus();
        deathStatusPage.clickStatusCloseButton();

        Admin admin = TestData.getAdmin();

        AdminTablePage adminTable = new MainPage(driver)
                .selectAdminMode()
                .adminRegistrationFillAndNext(admin);

        String idLatestAppFomAdminTable = adminTable.getIdLatestAppFromAdminTable();
        String typeOfAppFromAdminTable = adminTable.getTypeById(idLatestAppFomAdminTable);
        String statusOfAppFromAdminTable = adminTable.getStatusById(idLatestAppFomAdminTable);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(applicationNumberShowedUser.isEmpty(), "ID новой заявки не отражается на странице Статуса заявки пользователя");
        softAssert.assertEquals(applicationNumberShowedUser, idLatestAppFomAdminTable, "Новая заявка не отразилась сверху таблицы");
        softAssert.assertFalse(idLatestAppFomAdminTable.isEmpty(), "ID новой заявки не отражается в таблице");
        softAssert.assertEquals(typeOfAppFromAdminTable, "Получение свидетельства о смерти",
                "Тип заявки отличается от выбранной пользователем");
        softAssert.assertEquals(statusOfAppFromAdminTable, applicationStatusShowedUser, "Статус заявки в таблице администратора отличается от статуса заявки пользователя");
        softAssert.assertAll();

    }
}