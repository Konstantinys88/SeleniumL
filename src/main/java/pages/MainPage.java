package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends AbstractPage {

    @FindBy(xpath = "//*[@id=\"search_query\"]")
    private WebElement search;

    public void sendKeySearch(String text) {
        search.sendKeys(text);
    }

    @FindBy(xpath = "//*[@class=\"fas fa-search\"]")
    private WebElement go;

    public void clickGo() {
        go.click();
    }

    @FindBy(xpath = "//*[text()='278. Проблема глобального потепления']")
    private WebElement myTicket;

    public void goMyTicket(){
        myTicket.click();
    }

    @FindBy(xpath = "//*[@id=\"ticket-description\"]/p")
    private WebElement description;

    public String checkTicket(){
        return description.getText();
    }


}
