package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends AbstractPage {

    private WebElement user = driver.findElement(By.xpath("//*[@id=\"username\"]"));

    private WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));

    private WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"content-wrapper\"]/div/div/div/div[2]/form/input[1]"));

    public void login(String users, String passwords) {
        user.sendKeys(users);
        password.sendKeys(passwords);

    }

    public void clickLoginBtn() {
        loginBtn.click();
    }

}
