import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.AbstractPage;
import pages.LoginPage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HelpdeskUITest {

    private WebDriver driver;
    String nameTicket = "Уникальный билет 345";

    @Before
    public void setup() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("config.properties"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        AbstractPage.setDriver(driver);
    }

    /**
     * Метод для создания Ticket
     */
    @Test
    public void createTicketTest() throws IOException {
        driver.get(System.getProperty("site.url"));
        driver.findElement(By.xpath("//li[@class=\"nav-item\"][1]")).click();
        driver.findElement(By.xpath("//*[@id=\"id_queue\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"id_queue\"]/option[3]")).click();
        driver.findElement(By.xpath("//*[@id=\"id_title\"]")).sendKeys(nameTicket);
        driver.findElement(By.xpath("//*[@id=\"id_body\"]")).sendKeys("Домашка по Selenium");
        driver.findElement(By.xpath("//*[@id=\"id_priority\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"id_priority\"]/option[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"id_due_date\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[4]/td[1]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"id_submitter_email\"]")).sendKeys("maschkovc@yandex.ru");
        driver.findElement(By.xpath("//button[@type]")).click();
        driver.findElement(By.xpath("//*[@id=\"userDropdown\"]")).click();

        driver.close();
    }

    /**
     * Метод для проверки Ticket
     */
    @Test
    public void checkTicket() throws IOException {
        driver.get("https://at-sandbox.workbench.lanit.ru/login/?next=/");
        LoginPage loginPage = new LoginPage();
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("user.properties"));
        loginPage.login(System.getProperty("user"), System.getProperty("password"));
        loginPage.clickLoginBtn();

        driver.findElement(By.xpath("//*[@id=\"search_query\"]")).sendKeys(nameTicket);
        driver.findElement(By.xpath("//*[@id=\"searchform\"]/div/div/button")).click();
        driver.findElement(By.xpath("//*[@class=\"tickettitle\"]/a")).click();

        String text = driver.findElement(By.xpath("//*[@class=\"list-group-item list-group-item-action\"]/p[3]")).getText();
       // String text = driver.findElement(By.xpath("//*[@id=\"content-wrapper\"]/div/div[2]/div[2]/div/div/p[3]")).getText();
        if (text.equals("Домашка по Selenium")) {
            System.out.println("Данные соответствуют введенным");
        } else {
            System.out.println("Данные не соответствуют");
        }

        driver.close();

    }

}
