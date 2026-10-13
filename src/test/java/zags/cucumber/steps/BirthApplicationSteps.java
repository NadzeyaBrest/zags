package zags.cucumber.steps;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import zags.core.DriverManager;
import zags.data.TestData;
import zags.models.Application;
import zags.pages.MainPage;
import zags.pages.user.ApplicantDataPage;
import zags.pages.user.BirthCitizenDataPage;
import zags.pages.user.BirthServiceDataPage;
import zags.pages.user.ServiceSelectionPage;
import zags.pages.user.StatusPage;

public class BirthApplicationSteps {
    private static final Logger log = LogManager.getLogger(BirthApplicationSteps.class);
    private WebDriver driver = DriverManager.getInstance().getDriver();
    private Application app;

    private MainPage mainPage;
    private ApplicantDataPage applicantDataPage;
    private ServiceSelectionPage serviceSelectionPage;
    private BirthCitizenDataPage birthCitizenDataPage;
    private BirthServiceDataPage birthServiceDataPage;
    private StatusPage statusPage;

    @Given("пользователь находится на главной странице и выбирает роль пользователь")
    public void userIsOnMainPage() {
        mainPage = new MainPage(driver);
        log.info("Ша1: выбран пользовательский режим");
        applicantDataPage = mainPage.selectUserMode();
    }

    @When("пользователь заполняет данные заявителя")
    public void userFillApplicantData() {
        log.info("Шаг2: пользователь заполняет данные заявителя (рождение)");
        app = TestData.getBirthApplication();
        serviceSelectionPage = applicantDataPage.applicantFillAndNext(app);
    }

    @And("пользователь выбирает услугу Регистрация рождения")
    public void userSelectsService() {
        log.info("Шаг3: пользователь выбирает услугу Регистрация рождения");
        birthCitizenDataPage = serviceSelectionPage.selectBirth();
    }

    @And("пользователь заполняет данные гражданина")
    public void userFillBirthCitizenData() {
        log.info("Шаг3: пользователь заполняет данные услуги (рождение)");
        birthServiceDataPage = birthCitizenDataPage.birthCitizenDataFillAndNext(app);
    }

    @And("пользователь заполняет данные услуги")
    public void userFillBirthServiceData() {
        log.info("Шаг4: пользователь заполняет данные услуги (рождение)");
        statusPage = birthServiceDataPage.birthServiceFillAndFinish(app);
    }

    @Then("на странице статуса отображается номер заявки и статус На рассмотрении")
        public void verifyStatusPage () {
        log.info("Шаг5:на странице статуса отображается номер заявки и статус На рассмотрении,(рождение)");
            String number = statusPage.getApplicationNumber();
            String status = statusPage.getStatus();
            SoftAssert softAssert = new SoftAssert();
            softAssert.assertFalse(number.isEmpty(), "Номер заявки пустой");
            softAssert.assertEquals(status, "На рассмотрении", "Неверный статус заявки");
            softAssert.assertAll();
        }

    }
