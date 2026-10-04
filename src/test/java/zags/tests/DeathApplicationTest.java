package zags.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import zags.core.BaseTest;
import zags.models.Application;
import zags.models.ServiceType;
import zags.pages.MainPage;
import zags.pages.user.StatusPage;

public class DeathApplicationTest extends BaseTest {
    @Test
    public void testCreateDeathApplication() {
        Application app = new Application(ServiceType.DEATH)
                .personalLastName("Vladi")
                .personalFirstName("Olga")
                .personalMiddleName("Ivanovna")
                .personalPhone("7888823")
                .personalPassport("MA456")
                .personalAddress("Minsk, Lesnaya 3")
                .citizenLastName("Vladi")
                .citizenFirstName("Olga")
                .citizenMiddleName("Ivanovna")
                .citizenBirthDate("15.01.1925")
                .citizenGender("Жен")
                .citizenPassport("MA456")
                .citizenAddress("Minsk, Lesnaya, 3")
                .deathDate("15.10.2026")
                .deathPlace("Minsk");


        StatusPage deathStatusPage = new MainPage(driver)
                .selectUserMode()
                .applicantFillAndNext(app)
                .selectDeath()
                .deathCitizenFillAndNext(app)
                .deathServiceFillAndFinish(app);

        String thankYou = deathStatusPage.getThankYouText();
        String number = deathStatusPage.getApplicationNumber();
        String status = deathStatusPage.getStatus();
        Assert.assertTrue(thankYou.contains("Спасибо за обращение"), "Нет сообщения благодарности. Текст: " + thankYou);
        Assert.assertNotNull(number, "Номер заявки не получен");
        Assert.assertFalse(number.isEmpty(), "Номер заявки пустой");
        Assert.assertTrue(status.contains("На рассмотрении"), "Неверный статус заявки: " + status);



    }


}
