package team.senla;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;


public class AppTest {
    @Test
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10));
        String username = System.getenv("TEST_USERNAME");
        String password = System.getenv("TEST_PASSWORD");

        driver.get("https://"
                +username +":"+password+"@regoffice.senla.eu/");

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        driver.findElement(By.xpath("//button[contains(text(), 'пользователь')]")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[text()='Фамилия']/../following-sibling::input"))).sendKeys("Ivanov");
        driver.findElement(By.xpath("//label[text()='Имя']/../following-sibling::input")).sendKeys("Ivan");
        driver.findElement(By.xpath("//label[text()='Отчество']/../following-sibling::input")).sendKeys("Ivanovich");
        driver.findElement(By.xpath("//label[text()='Телефон']/../following-sibling::input")).sendKeys("1234567");
        driver.findElement(By.xpath("//label[text()='Номер паспорта']/../following-sibling::input")).sendKeys("ПА12345");
        driver.findElement(By.xpath("//label[text()='Адрес прописки']/../following-sibling::input")).sendKeys("Brest First street 15");

        WebElement applicantPageNextButton = driver.findElement(By.xpath("//button[contains(text(), 'Далее')]"));
        wait.until(ExpectedConditions.elementToBeClickable(applicantPageNextButton)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(), 'брак')]"))).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[text()='Фамилия']/../following-sibling::input"))).sendKeys("Ivanov");
        driver.findElement(By.xpath("//label[text()='Имя']/../following-sibling::input")).sendKeys("Ivan");
        driver.findElement(By.xpath("//label[text()='Отчество']/../following-sibling::input")).sendKeys("Ivanovich");
        driver.findElement(By.xpath("//label[text()='Дата рождения']/../following-sibling::input")).sendKeys("2000-05-12");
        driver.findElement(By.xpath("//label[text()='Номер паспорта']/../following-sibling::input")).sendKeys("ПА12345");
        driver.findElement(By.xpath("//label[text()='Пол']/../following-sibling::input")).sendKeys("Муж");
        driver.findElement(By.xpath("//label[text()='Адрес прописки']/../following-sibling::input")).sendKeys("Brest First street 15");

        WebElement marriageCitizenPageNextButton = driver.findElement(By.xpath("//button[contains(text(), 'Далее')]"));
        wait.until(ExpectedConditions.elementToBeClickable(marriageCitizenPageNextButton)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[text()='Дата регистрации']/../following-sibling::input"))).sendKeys("2026-12-12");
        driver.findElement(By.xpath("//label[text()='Новая фамилия']/../following-sibling::input")).sendKeys("Ivanova");
        driver.findElement(By.xpath("//label[text()='Фамилия супруга/и']/../following-sibling::input")).sendKeys("Petrova");
        driver.findElement(By.xpath("//label[text()='Имя супруга/и']/../following-sibling::input")).sendKeys("Petra");
        driver.findElement(By.xpath("//label[text()='Отчество супруга/и']/../following-sibling::input")).sendKeys("Petrovna");
        driver.findElement(By.xpath("//label[text()='Дата рождения супруга/и']/../following-sibling::input")).sendKeys("2000-12-12");
        driver.findElement(By.xpath("//label[text()='Номер паспорта супруга/и']/../following-sibling::input")).sendKeys("ПА1234");

        WebElement marriageServicePageFinishButton = driver.findElement(By.xpath("//button[contains(text(), 'Завершить')]"));

        wait.until(ExpectedConditions.elementToBeClickable(marriageServicePageFinishButton)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(), 'Ваша заявка №')]")));

        WebElement textThankYou = driver.findElement(By.xpath("//*[text()='Спасибо за обращение!']"));
        Assert.assertTrue(textThankYou.isDisplayed(), "Заголовок не появился");
        WebElement status = driver.findElement(By.xpath("//span[contains(text(), 'Статус заявки:')]"));
        Assert.assertEquals(status.getText(), "Статус заявки: На рассмотрении.");
        driver.findElement(By.xpath("//button[contains(text(), 'Закрыть')]")).click();

        driver.quit();


    }
}