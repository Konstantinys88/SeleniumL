import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pages.AbstractPage;
import pages.LoginPage;
import pages.MainPage;
import pages.TicketsPage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HelpdeskUITest {

    private final String SUMMARY = "Проблема глобального потепления";
    private final String DESCRIPTION = "Description of your issue";
    private final String DUE_ON = "2021-10-05 00:00:00";
    private final String EMAIL = "qwerty@emailo.at";


    private WebDriver driver;

    @Before
    public void setup() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("config.properties"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        AbstractPage.setDriver(driver);
    }

    /**
     * Метод для создания и проверки Ticket.
     */

    @Test
    public void createTicketTest() throws IOException {

        driver.get(System.getProperty("site.url"));
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("user.properties"));

        /**
         * Создаем Ticket
         */

        TicketsPage ticketsPage = PageFactory.initElements(driver, TicketsPage.class);
        ticketsPage.clickNewTicket();
        ticketsPage.clickQueue();
        ticketsPage.chooseDjangoHelpdesk();
        ticketsPage.sendKeysSummaryOfTheProblem(SUMMARY);
        ticketsPage.sendKeysDescriptionOfYourIssue(DESCRIPTION);
        ticketsPage.clickPriority();
        ticketsPage.clickPriorityCritical();
        ticketsPage.sendKeyDueOn(DUE_ON);
        ticketsPage.sendKeyYourEmailAddress(EMAIL);
        ticketsPage.clickSubmitTicket();

        /**
         * Логинимся
         */

        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.clickLogInBtn();
        loginPage.login(System.getProperty("user"), System.getProperty("password"));
        loginPage.clickLogin();

        /**
         * Поиск Ticket
         */

        MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
        mainPage.sendKeySearch(SUMMARY);
        mainPage.clickGo();
        mainPage.goMyTicket();

        /**
         * Проверка Ticket
         */

        String textTicket = mainPage.checkTicket();

        if (textTicket.equals(DESCRIPTION)) {
            System.out.println("Данные соответствуют введенным");
        } else {
            System.out.println("Данные не соответствуют");
        }

        driver.close();
    }
}

