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
@Test(testName = "Проверка отображения заявок пользователя в таблице администратора")
public class AdminCheckApplicationTest extends BaseTest {
    private MainPage mainPage;

    @BeforeMethod(alwaysRun = true)
    public void init() {
        mainPage = new MainPage(driver);
    }

    @Test(groups = {"crossRole"}, description = "Проверка заявки на рождение администратором")
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


        String typeOfAppFromAdminTable = adminTable.getTypeById(applicationNumberShowedUser);
        String statusOfAppFromAdminTable = adminTable.getStatusById(applicationNumberShowedUser);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(applicationNumberShowedUser.isEmpty(), "ID новой заявки не отражается на странице Статуса заявки пользователя");
        softAssert.assertEquals(typeOfAppFromAdminTable, "Получение свидетельства о рождении",
                "Тип заявки отличается от выбранной пользователем");
        softAssert.assertEquals(statusOfAppFromAdminTable, applicationStatusShowedUser, "Статус заявки в таблице администратора отличается от статуса заявки пользователя");
        softAssert.assertAll();
    }

    @Test(groups = {"crossRole"}, description = "Проверка заявки на рождение администратором")
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
        String typeOfAppFromAdminTable = adminTable.getTypeById(applicationNumberShowedUser);
        String statusOfAppFromAdminTable = adminTable.getStatusById(applicationNumberShowedUser);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(typeOfAppFromAdminTable, "Получение свидетельства о браке",
                "Тип заявки отличается от выбранной пользователем");
        softAssert.assertEquals(statusOfAppFromAdminTable, applicationStatusShowedUser, "Статус заявки в таблице администратора отличается от статуса заявки пользователя");
        softAssert.assertAll();
    }

    @Test(groups = {"crossRole"}, description = "Проверка заявки на смерть администратором")
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

        String typeOfAppFromAdminTable = adminTable.getTypeById(applicationNumberShowedUser);
        String statusOfAppFromAdminTable = adminTable.getStatusById(applicationNumberShowedUser);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(applicationNumberShowedUser.isEmpty(), "ID новой заявки не отражается на странице Статуса заявки пользователя");
        softAssert.assertEquals(typeOfAppFromAdminTable, "Получение свидетельства о смерти",
                "Тип заявки отличается от выбранной пользователем");
        softAssert.assertEquals(statusOfAppFromAdminTable, applicationStatusShowedUser, "Статус заявки в таблице администратора отличается от статуса заявки пользователя");
        softAssert.assertAll();
    }

    @Test(groups = {"crossRole"}, description = "Одобрение только что созданной заявки")
    @Story("Админ одобряет заявку")
    @Description("Пользователь создаёт заявку, админ находит её по ID, кликает 'Палец вверх' и проверяет новый статус")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("161")
    public void testApproveNewApplication() {
        Application deathApp = TestData.getDeathApplication();
        StatusPage deathStatusPage = mainPage
                .selectUserMode()
                .applicantFillAndNext(deathApp)
                .selectDeath()
                .deathCitizenFillAndNext(deathApp)
                .deathServiceFillAndFinish(deathApp);
        String appNumber = deathStatusPage.getApplicationNumber();
        log.info("Создана новая заявка с номером: {}", appNumber);
        deathStatusPage.clickStatusCloseButton();

        Admin admin = TestData.getAdmin();
        AdminTablePage adminTable = new MainPage(driver)
                .selectAdminMode()
                .adminRegistrationFillAndNext(admin);

        adminTable.clickApproveById(appNumber);
        adminTable.waitForStatusChange(appNumber, "Одобрена");
        String newStatus = adminTable.getStatusById(appNumber);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(newStatus, "Одобрена",
                "Статус заявки №" + appNumber + " не изменился на 'Одобрена'");
        softAssert.assertAll();
    }

    @Test(groups = {"crossRole"}, description = "Отклонение только что созданной заявки")
    @Story("Админ отклоняет заявку")
    @Description("Пользователь создаёт заявку, админ находит её по ID, кликает 'Палец вниз' и проверяет новый статус")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("162")
    public void testRejectNewApplication() {
        Application marriageApp = TestData.getMarriageApplication();
        StatusPage statusPage = mainPage
                .selectUserMode()
                .applicantFillAndNext(marriageApp)
                .selectMarriage()
                .marriageCitizenFillAndNext(marriageApp)
                .marriageServiceFillAndFinish(marriageApp);

        String appNumber = statusPage.getApplicationNumber();
        log.info("Создана новая заявка с номером: {}", appNumber);
        statusPage.clickStatusCloseButton();
        Admin admin = TestData.getAdmin();
        AdminTablePage adminTable = new MainPage(driver)
                .selectAdminMode()
                .adminRegistrationFillAndNext(admin);
        adminTable.clickRejectById(appNumber);
        adminTable.waitForStatusChange(appNumber, "Отклонена");
        String newStatus = adminTable.getStatusById(appNumber);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(newStatus, "Отклонена",
                "Статус заявки №" + appNumber + " не изменился на 'Отклонена'");
        softAssert.assertAll();
    }

    @Test(groups = {"crossRole"}, description = "Проверка появления новой заявки в таблице админа после нажатия 'Обновить'")
    @Story("Обновление таблицы администратора")
    @Description("Админ открывает таблицу, возвращается на главную, пользователь создает заявку, админ снова открывает таблицу, жмет 'Обновить' и видит новую заявку.")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("157")
    public void testRefreshShowsNewApplication() {
        Admin admin = TestData.getAdmin();
        AdminTablePage adminTable = new MainPage(driver)
                .selectAdminMode()
                .adminRegistrationFillAndNext(admin);
        MainPage mainPageForUser = adminTable.clickAdminTableClose();
        Application marriageApp = TestData.getMarriageApplication();
        StatusPage statusPage = mainPageForUser
                .selectUserMode()
                .applicantFillAndNext(marriageApp)
                .selectMarriage()
                .marriageCitizenFillAndNext(marriageApp)
                .marriageServiceFillAndFinish(marriageApp);

        String appNumber = statusPage.getApplicationNumber();
        log.info("Пользователь создал заявку: {}", appNumber);
        MainPage mainPageForAdmin = statusPage.clickStatusCloseButton();
        AdminTablePage adminTableRefreshed = mainPageForAdmin
                .selectAdminModeAfterCloseButton();
        log.info("Админ нажимает 'Обновить'");

        try {
            adminTableRefreshed.refresh();
        } catch (Exception e) {
            log.error("Не дождались загрузки таблицы. Cпиннер завис.");
            throw new RuntimeException("Таблица не загрузилась: спиннер не пропадает", e);
        }
        adminTableRefreshed.refresh();

        String typeFromTable = adminTableRefreshed.getTypeById(appNumber);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(typeFromTable, "Получение свидетельства о браке",
                "Заявка №" + appNumber + " не появилась в таблице после обновления");
        softAssert.assertAll();
    }
}