import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AbstractPage;
import pages.LoginPage;
import pages.MainPage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

    /**
    * Чтобы запустить сборку введите - mvn clean test
    * Чтобы сгенерировать отчет - allure serve target/allure-results
    */

public class CheckTicketTest extends AbstractPage {

    private WebDriver driver;

    @BeforeClass
    public void setup() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("config.properties"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        AbstractPage.setDriver(driver);
    }

    @Step("Поиск созданного Ticket")
    public void checkTicket() throws IOException {

        driver.get("https://at-sandbox.workbench.lanit.ru/login/?next=/");
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("user.properties"));
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.clickLogInBtn();
        loginPage.login(System.getProperty("user"), System.getProperty("password"));
        loginPage.clickLogin();

        MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
        mainPage.sendKeySearch(getSUMMARY());
        mainPage.clickGo();
        mainPage.goMyTicket();

        String textTicket = mainPage.checkTicket();

        if (textTicket.equals(getDESCRIPTION())) {
            System.out.println("Данные соответствуют введенным");
        } else {
            System.out.println("Данные не соответствуют");
        }

    /**
    * Делает скриншот
    */
        GetScreenshots.takeScreenshot("screeCheckTicket.png");

        driver.close();

    }

    @Test
    @Description("Тест проверяет создался ли Ticket")
    public void uiTestCheckTicket() throws IOException {
        checkTicket();
    }

}
