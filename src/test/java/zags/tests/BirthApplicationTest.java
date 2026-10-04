package zags.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import zags.core.BaseTest;
import zags.models.Application;
import zags.models.ServiceType;
import zags.pages.MainPage;
import zags.pages.user.StatusPage;

public class BirthApplicationTest extends BaseTest {

    @Test
    public void testCreateBirthApplication() {
        Application app = new Application(ServiceType.BIRTH)
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
                .applicantFillAndNext(app)
                .selectBirth()
                .birthCitizenDataFillAndNext(app)
                .birthServiceFillAndFinish(app);

        String thankYou = statusPage.getThankYouText();
        String number = statusPage.getApplicationNumber();
        String status = statusPage.getStatus();

        Assert.assertTrue(thankYou.contains("Спасибо за обращение"),
                "Нет сообщения благодарности. Текст: " + thankYou);
        Assert.assertNotNull(number, "Номер заявки не получен");
        Assert.assertFalse(number.isEmpty(), "Номер заявки пустой");
        Assert.assertTrue(status.contains("На рассмотрении"),
                "Неверный статус заявки: " + status);

        System.out.println("Заявка создана. Номер: " + number + ", Статус: " + status);
    }
}