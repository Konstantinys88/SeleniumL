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

    @Before
    public void setup() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("config.properties"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        AbstractPage.setDriver(driver);
    }

    @Test
    public void createTicketTest() throws IOException {
        driver.get(System.getProperty("site.url"));
        driver.findElement(By.xpath("//*[@id=\"wrapper\"]/ul/li[2]/a/span")).click();
        driver.findElement(By.xpath("//*[@id=\"id_queue\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"id_queue\"]/option[3]")).click();
        driver.findElement(By.xpath("//*[@id=\"id_title\"]")).sendKeys("Уникальный тикет");
        driver.findElement(By.xpath("//*[@id=\"id_body\"]")).sendKeys("Домашка по Seleniym");
        driver.findElement(By.xpath("//*[@id=\"id_priority\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"id_priority\"]/option[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"id_due_date\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[4]/td[1]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"id_submitter_email\"]")).sendKeys("maschkovc@yandex.ru");
        driver.findElement(By.xpath("//*[@id=\"content-wrapper\"]/div/div/div/div[2]/form/button")).click();

        driver.findElement(By.xpath("//*[@id=\"userDropdown\"]")).click();

        LoginPage loginPage = new LoginPage();
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("user.properties"));
        loginPage.login(System.getProperty("user"), System.getProperty("password"));
        loginPage.clickLoginBtn();

        driver.close();
    }

    @Test
    public void checkTicket() throws IOException {
        driver.get("https://at-sandbox.workbench.lanit.ru/login/?next=/");
        LoginPage loginPage = new LoginPage();
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("user.properties"));
        loginPage.login(System.getProperty("user"), System.getProperty("password"));
        loginPage.clickLoginBtn();

        driver.findElement(By.xpath("//*[@id=\"search_query\"]")).sendKeys("Уникальный");
        driver.findElement(By.xpath("//*[@id=\"searchform\"]/div/div/button")).click();
        driver.findElement(By.xpath("//*[@id=\"ticketTable\"]/tbody/tr[3]/td[2]/div/a")).click();


    }

}
