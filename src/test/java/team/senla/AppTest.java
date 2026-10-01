package team.senla;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


public class AppTest {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10));
        try {
            driver.get("https://user:senlatest@regoffice.senla.eu/");

            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

            driver.findElement(By.xpath("//div/button[1]")).click();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[text()='Фамилия']/../following-sibling::input"))).sendKeys("Ivanov");
            driver.findElement(By.xpath("//label[text()='Имя']/../following-sibling::input")).sendKeys("Ivan");
            driver.findElement(By.xpath("//label[text()='Отчество']/../following-sibling::input")).sendKeys("Ivanovich");
            driver.findElement(By.xpath("//label[text()='Телефон']/../following-sibling::input")).sendKeys("1234567");
            driver.findElement(By.xpath("//label[text()='Номер паспорта']/../following-sibling::input")).sendKeys("ПА12345");
            driver.findElement(By.xpath("//label[text()='Адрес прописки']/../following-sibling::input")).sendKeys("Brest First street 15");

            WebElement nextButton = driver.findElement(By.xpath("//button[contains(text(), 'Далее')]"));
            wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(), 'брак')]"))).click();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[text()='Фамилия']/../following-sibling::input"))).sendKeys("Ivanov");
            driver.findElement(By.xpath("//label[text()='Имя']/../following-sibling::input")).sendKeys("Ivan");
            driver.findElement(By.xpath("//label[text()='Отчество']/../following-sibling::input")).sendKeys("Ivanovich");
            driver.findElement(By.xpath("//label[text()='Дата рождения']/../following-sibling::input")).sendKeys("2000-05-12");
            driver.findElement(By.xpath("//label[text()='Номер паспорта']/../following-sibling::input")).sendKeys("ПА12345");
            driver.findElement(By.xpath("//label[text()='Пол']/../following-sibling::input")).sendKeys("Муж");
            driver.findElement(By.xpath("//label[text()='Адрес прописки']/../following-sibling::input")).sendKeys("Brest First street 15");

            WebElement nextButton2 = driver.findElement(By.xpath("//button[contains(text(), 'Далее')]"));
            wait.until(ExpectedConditions.elementToBeClickable(nextButton2)).click();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[text()='Дата регистрации']/../following-sibling::input"))).sendKeys("2026-12-12");
            driver.findElement(By.xpath("//label[text()='Новая фамилия']/../following-sibling::input")).sendKeys("Ivanova");
            driver.findElement(By.xpath("//label[text()='Фамилия супруга/и']/../following-sibling::input")).sendKeys("Petrova");
            driver.findElement(By.xpath("//label[text()='Имя супруга/и']/../following-sibling::input")).sendKeys("Petra");
            driver.findElement(By.xpath("//label[text()='Отчество супруга/и']/../following-sibling::input")).sendKeys("Petrovna");
            driver.findElement(By.xpath("//label[text()='Дата рождения супруга/и']/../following-sibling::input")).sendKeys("2000-12-12");
            driver.findElement(By.xpath("//label[text()='Номер паспорта супруга/и']/../following-sibling::input")).sendKeys("ПА1234");

            WebElement nextButton3 = driver.findElement(By.xpath("//button[contains(text(), 'Завершить')]"));

            wait.until(ExpectedConditions.elementToBeClickable(nextButton3)).click();

            WebElement textThankYou = driver.findElement(By.xpath("//*[text()='Спасибо за обращение!']"));
            Assert.assertTrue(textThankYou.isDisplayed(), "Заголовок не появился");
            WebElement status = driver.findElement(By.xpath("//span[contains(text(), 'Статус заявки:')]"));
            Assert.assertEquals(status.getText(), "Статус заявки: На рассмотрении.");
            driver.findElement(By.xpath("//button[contains(text(), 'Закрыть')]")).click();

        } catch (Error e) {
            System.out.println(e.getMessage());
        } finally {
            driver.quit();
        }


    }
}