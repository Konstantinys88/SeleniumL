package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {


    @FindBy(xpath = "//*[@id=\"username\"]")
    private WebElement user;

    @FindBy(xpath = "//*[@id=\"password\"]")
    private WebElement password;

    @FindBy(xpath = "//*[@id=\"userDropdown\"]")
    private WebElement logInBtn;

    public void clickLogInBtn() {
        logInBtn.click();
    }

    public void login(String users, String passwords) {
        user.sendKeys(users);
        password.sendKeys(passwords);

    }

    @FindBy(xpath = "//*[@class=\"btn btn-lg btn-primary btn-block\"]")
    private WebElement login;

    public void clickLogin(){
        login.click();
    }

}
