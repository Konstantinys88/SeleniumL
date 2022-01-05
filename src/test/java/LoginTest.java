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

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Чтобы запустить сборку введите - mvn clean test
 * Чтобы сгенерировать отчет - allure serve target/allure-results
 */

public class LoginTest {

    private WebDriver driver;


    @BeforeClass
    public void setup() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("config.properties"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        AbstractPage.setDriver(driver);
    }

    @Step("Проверка логин и пароль")
    public void login() throws IOException {
        driver.get("https://at-sandbox.workbench.lanit.ru/login/?next=/");
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("user.properties"));
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.clickLogInBtn();
        loginPage.login(System.getProperty("user"), System.getProperty("password"));
        loginPage.clickLogin();

/**
 * Делает скриншот
 */

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("src/main/screenshot/screenLogin.png"));

        driver.close();

    }

    @Test
    public void uiTestLogin() throws IOException {
        login();

    }

}
