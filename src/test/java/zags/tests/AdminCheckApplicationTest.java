package zags.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import zags.core.BaseTest;
import zags.models.Admin;
import zags.models.Application;
import zags.models.ServiceType;
import zags.pages.MainPage;
import zags.pages.admin.AdminTablePage;
import zags.pages.user.StatusPage;

public class AdminCheckApplicationTest extends BaseTest {
    @Test
    public void AdminCheckBirthApplication() {
        Application birthApp = new Application(ServiceType.BIRTH)
                .personalLastName("Ivanov")
                .personalFirstName("Ivan")
                .personalMiddleName("Ivanovich")
                .personalPhone("7999123")
                .personalPassport("АБ123456")
                .personalAddress("Brest, Sovetskaya 3/15")
                .citizenLastName("Ivanov")
                .citizenFirstName("Petr")
                .citizenMiddleName("Ivanovich")
                .citizenBirthDate("15.01.2024")
                .citizenGender("Муж")
                .citizenPassport("АБ123456")
                .citizenAddress("Brest, Sovetskaya 3/15")
                .birthPlace("Brest")
                .birthMother("Ivanova Maria Sergeevana")
                .birthFather("Ivanov Ivan Ivanovich")
                .birthGrandmother("Petrova Anna Ivanovna")
                .birthGrandfather("Petrov Petr Petrovich");

        StatusPage statusPage = new MainPage(driver)
                .selectUserMode()
                .applicantFillAndNext(birthApp)
                .selectBirth()
                .birthCitizenDataFillAndNext(birthApp)
                .birthServiceFillAndFinish(birthApp);

        String number = statusPage.getApplicationNumber();
        Assert.assertNotNull(number, "Номер заявки не получен");
        String status = statusPage.getStatus();
        MainPage mainPage = statusPage.statusClickClose();

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

        String firstRowId = adminTable.getFirstId();
        Assert.assertEquals(number, firstRowId, "Новая заявка не отразилась сверху таблицы");
        Assert.assertFalse(firstRowId.isEmpty(), "ID новой заявки не отражается в таблице");

        String typeOfAppFromAdminTable = adminTable.getTypeById(firstRowId);
        Assert.assertEquals(typeOfAppFromAdminTable, "Получение свидетельства о рождении",
                "Тип заявки отличается от выбранной пользователем");

        String statusAppFromAdminPage = adminTable.getStatusById(firstRowId);

        Assert.assertEquals(statusAppFromAdminPage, "На рассмотрении",
                "Заявке присвоен некорректый статус");
    }

    @Test
    public void AdminCheckMarriageApplication() {
        Application marriageApp = new Application(ServiceType.MARRIAGE)
                .personalLastName("Gromov")
                .personalFirstName("Mark")
                .personalMiddleName("Ivanovich")
                .personalPhone("7999123")
                .personalPassport("АБ123456")
                .personalAddress("Brest, Levaya 3/15")
                .citizenLastName("Gromov")
                .citizenFirstName("Mark")
                .citizenMiddleName("Ivanovich")
                .citizenBirthDate("16.02.2020")
                .citizenGender("Муж")
                .citizenPassport("АБ123456")
                .citizenAddress("Brest, Levaya 3/15")
                .marriageDate("10.12.2026")
                .marriageNewLastName("Gromov")
                .marriageSpouseLastName("Svetova")
                .marriageSpouseFirstName("Svetlana")
                .marriageSpouseMiddleName("Petrovana")
                .marriageSpouseBirthDate("18.03.2019")
                .marriageSpousePassport("АБ176543");

        StatusPage marriageStatusPage = new MainPage(driver)
                .selectUserMode()
                .applicantFillAndNext(marriageApp)
                .selectMarriage()
                .marriageCitizenFillAndNext(marriageApp)
                .marriageServiceFillAndFinish(marriageApp);

        String number = marriageStatusPage.getApplicationNumber();
        Assert.assertNotNull(number, "Номер заявки не получен");
        String status = marriageStatusPage.getStatus();
        MainPage mainPage = marriageStatusPage.statusClickClose();

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

        String firstRowId = adminTable.getFirstId();
        Assert.assertEquals(number, firstRowId, "Новая заявка не отразилась сверху таблицы");
        Assert.assertFalse(firstRowId.isEmpty(), "ID новой заявки не отражается в таблице");

        String typeOfAppFromAdminTable = adminTable.getTypeById(firstRowId);
        Assert.assertEquals(typeOfAppFromAdminTable, "Получение свидетельства о браке",
                "Тип заявки отличается от выбранной пользователем");

        String statusAppFromAdminPage = adminTable.getStatusById(firstRowId);

        Assert.assertEquals(statusAppFromAdminPage, "На рассмотрении",
                "Заявке присвоен некорректый статус");

        /*adminTable.approveById(firstRowId);
        Assert.assertEquals(statusAppFromAdminPage, "Одобрена",
                "Статус заявки не изменился на Одобрена");

        adminTable.rejectById(firstRowId);
        Assert.assertEquals(statusAppFromAdminPage, "Отклонена",
                "Статус заявки не изменился на Отклонена");
*/

    }

    @Test
    public void AdminCheckDeathApplication() {
        Application deathaApp = new Application(ServiceType.DEATH)
                .personalLastName("Vladi")
                .personalFirstName("Olga")
                .personalMiddleName("Ivanovna")
                .personalPhone("7888823")
                .personalPassport("MA456444")
                .personalAddress("Minsk, Lesnaya 3")
                .citizenLastName("Vladi")
                .citizenFirstName("Olga")
                .citizenMiddleName("Ivanovna")
                .citizenBirthDate("15.01.1925")
                .citizenGender("Жен")
                .citizenPassport("MA45689")
                .citizenAddress("Minsk, Lesnaya, 3")
                .deathDate("15.10.2026")
                .deathPlace("Minsk");


        StatusPage deathStatusPage = new MainPage(driver)
                .selectUserMode()
                .applicantFillAndNext(deathaApp)
                .selectDeath()
                .deathCitizenFillAndNext(deathaApp)
                .deathServiceFillAndFinish(deathaApp);


        String number = deathStatusPage.getApplicationNumber();
        Assert.assertNotNull(number, "Номер заявки не получен");
        String status = deathStatusPage.getStatus();
        MainPage mainPage = deathStatusPage.statusClickClose();

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

        String firstRowId = adminTable.getFirstId();
        Assert.assertEquals(number, firstRowId, "Новая заявка не отразилась сверху таблицы");
        Assert.assertFalse(firstRowId.isEmpty(), "ID новой заявки не отражается в таблице");

        String typeOfAppFromAdminTable = adminTable.getTypeById(firstRowId);
        Assert.assertEquals(typeOfAppFromAdminTable, "Получение свидетельства о смерти",
                "Тип заявки отличается от выбранной пользователем");

        String statusAppFromAdminPage = adminTable.getStatusById(firstRowId);

        Assert.assertEquals(statusAppFromAdminPage, "На рассмотрении",
                "Заявке присвоен некорректый статус");


    }
}