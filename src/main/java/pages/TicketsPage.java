package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TicketsPage extends AbstractPage {


    @FindBy(xpath = "//*[@class=\"fas fa-fw fa-plus-circle\"]")
    private WebElement newTicket;

    public void clickNewTicket(){
        newTicket.click();
    }

    @FindBy(xpath = "//*[@id=\"id_queue\"]")
    private WebElement Queue;

    public void clickQueue(){
        Queue.click();
    }

    @FindBy(xpath = "//*[@id=\"id_queue\"]/option[2]")
    private WebElement djangoHelpdesk;

    public void chooseDjangoHelpdesk(){
        djangoHelpdesk.click();
    }

    @FindBy(xpath = "//*[@id=\"id_title\"]")
    private WebElement summaryOfTheProblem;

    public void sendKeysSummaryOfTheProblem(String text){
        summaryOfTheProblem.sendKeys(text);
    }

    @FindBy(xpath = "//*[@id=\"id_body\"]")
    private WebElement descriptionOfYourIssue;

    public void sendKeysDescriptionOfYourIssue(String text){
        descriptionOfYourIssue.sendKeys(text);

    }

    @FindBy(xpath = "//*[@id=\"id_priority\"]")
    private WebElement priority;

    public void clickPriority(){
        priority.click();
    }

    @FindBy(xpath = "//*[text()='1. Critical']")
    private WebElement priorityCritical;

    public void clickPriorityCritical(){
        priorityCritical.click();
    }

    @FindBy(xpath = "//*[@id=\"id_due_date\"]")
    private WebElement dueOn;

    public void sendKeyDueOn(String text){
        dueOn.sendKeys(text);
    }

    @FindBy(xpath = "//*[@id=\"id_submitter_email\"]")
    private WebElement yourEmailAddress;

    public void sendKeyYourEmailAddress(String text){
        yourEmailAddress.sendKeys(text);
    }

    @FindBy(xpath = "//*[@class=\"btn btn-primary btn-lg btn-block\"]")
    private WebElement submitTicket;

    public void clickSubmitTicket(){
        submitTicket.click();
    }

}