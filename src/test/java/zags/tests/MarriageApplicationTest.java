package zags.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import zags.core.BaseTest;
import zags.models.Application;
import zags.models.ServiceType;
import zags.pages.MainPage;
import zags.pages.user.StatusPage;

public class MarriageApplicationTest extends BaseTest {
    @Test
    public void testCreateMarriageApplication() {
        Application app = new Application(ServiceType.MARRIAGE)
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
                .applicantFillAndNext(app)
                .selectMarriage()
                .marriageCitizenFillAndNext(app)
                .marriageServiceFillAndFinish(app);

        String thankYou = marriageStatusPage.getThankYouText();
        String number = marriageStatusPage.getApplicationNumber();
        String status = marriageStatusPage.getStatus();
        Assert.assertTrue(thankYou.contains("Спасибо за обращение"),
                "Нет сообщения благодарности. Текст: " + thankYou);
        Assert.assertNotNull(number, "Номер заявки не получен");
        Assert.assertFalse(number.isEmpty(), "Номер заявки пустой");
        Assert.assertTrue(status.contains("На рассмотрении"),
                "Неверный статус заявки: " + status);

    }


}


