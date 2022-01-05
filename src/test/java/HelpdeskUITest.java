import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AbstractPage;
import pages.TicketsPage;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Данный тест создает новый Ticket
 *
 * Чтобы запустить сборку введите - mvn clean test
 *
 * Чтобы сгенерировать отчет - allure serve target/allure-results
 *
 */

public class HelpdeskUITest extends AbstractPage {


    private final String SUMMARY = "Проблема глобального потепления";
    private final String DESCRIPTION = "Description of your issue";
    private final String DUE_ON = "2021-10-05 00:00:00";
    private final String EMAIL = "qwerty@emailo.at";

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
        ticketsPage.sendKeysSummaryOfTheProblem(SUMMARY);
        ticketsPage.sendKeysDescriptionOfYourIssue(DESCRIPTION);
        ticketsPage.clickPriority();
        ticketsPage.clickPriorityCritical();
        ticketsPage.sendKeyDueOn(DUE_ON);
        ticketsPage.sendKeyYourEmailAddress(EMAIL);
        ticketsPage.clickSubmitTicket();

        /**
         * Делает скриншот
         */
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("src/main/screenshot/screenCreateTicket.png"));

        driver.close();
    }

    @Test
    public void uiTestCreateTicket() throws IOException {
        createTicketTest();

    }

}

