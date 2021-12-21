package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class LoginPage extends AbstractPage {


    private WebElement user = driver.findElement(By.xpath("//*[@id=\"username\"]"));

    // Поиск элемента через аннотацию
    //@FindBy(xpath = "//*[@id=\"password\"]")
    private WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));

    //@FindBy(xpath = "//*[@id=\"content-wrapper\"]/div/div/div/div[2]/form/input[1]")
    private WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"content-wrapper\"]/div/div/div/div[2]/form/input[1]"));

    public void login(String users, String passwords) {
        user.sendKeys(users);
        password.sendKeys(passwords);

    }

    public void clickLoginBtn() {
        loginBtn.click();
    }

}
