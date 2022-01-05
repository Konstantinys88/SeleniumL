import io.qameta.allure.Attachment;
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
import pages.LoginPage;
import pages.MainPage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

    /**
    * Чтобы запустить сборку введите - mvn clean test
    * Чтобы сгенерировать отчет - allure serve target/allure-results
    */

public class CheckTicketTest {

    private WebDriver driver;

    private final String SUMMARY = "Проблема глобального потепления";
    private final String DESCRIPTION = "Description of your issue";

    /**
     * Метод для прикрепления скриншота к Allure
     */

    @Attachment
    public static byte[] getBytes(String resourceName) throws IOException {
        return Files.readAllBytes(Paths.get("src/main/screenshot", resourceName));
    }

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
        mainPage.sendKeySearch(SUMMARY);
        mainPage.clickGo();
        mainPage.goMyTicket();

        String textTicket = mainPage.checkTicket();

        if (textTicket.equals(DESCRIPTION)) {
            System.out.println("Данные соответствуют введенным");
        } else {
            System.out.println("Данные не соответствуют");
        }

    /**
    * Делает скриншот
    */
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("src/main/screenshot/screeCheckTicket.png"));

        getBytes("screeCheckTicket.png");

        driver.close();

    }

    @Test
    public void uiTestCheckTicket() throws IOException {
        checkTicket();
    }


}
