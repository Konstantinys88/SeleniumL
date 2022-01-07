import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AbstractPage;
import pages.TicketsPage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

    /**
    * Данный тест создает новый Ticket
    * Чтобы запустить сборку введите - mvn clean test
    * Чтобы сгенерировать отчет - allure serve target/allure-results
    */

public class HelpdeskUITest extends AbstractPage {

    private WebDriver driver;

    @BeforeClass
    public void setup() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("config.properties"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        AbstractPage.setDriver(driver);
    }

    @Step("Проверка создания нового Ticket")
    public void createTicketTest() throws IOException {
        driver.get(System.getProperty("site.url"));
        TicketsPage ticketsPage = PageFactory.initElements(driver, TicketsPage.class);
        ticketsPage.clickNewTicket();
        ticketsPage.clickQueue();
        ticketsPage.chooseDjangoHelpdesk();
        ticketsPage.sendKeysSummaryOfTheProblem(getSUMMARY());
        ticketsPage.sendKeysDescriptionOfYourIssue(getDESCRIPTION());
        ticketsPage.clickPriority();
        ticketsPage.clickPriorityCritical();
        ticketsPage.sendKeyDueOn(getDUE_ON());
        ticketsPage.sendKeyYourEmailAddress(getEMAIL());
        ticketsPage.clickSubmitTicket();

    /**
    * Делает скриншот
    */
        GetScreenshots.takeScreenshot("screenCreateTicket.png");

        driver.close();
    }

    @Test
    @Description("Тест проверяет создается ли Ticket")
    public void uiTestCreateTicket() throws IOException {
        createTicketTest();

    }

}

