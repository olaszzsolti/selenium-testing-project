package stepDefinitions;

import Helpers.EmailContentHelper;
import Helpers.MailHelper;
import Helpers.WebDriverInstance;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;

public class EmailContentCheckerDefinitions {
    MailHelper lastMail;
    WebDriver driver = WebDriverInstance.getDriver();


    @Then("Validate email content (.*), (.*), (.*), (.*), (.*), (.*), (.*), (.*)$")
    public void checkEmailContent(String email, String name, String username, String system, String expire, String password, String description, String sender){
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lastMail = new MailHelper(driver);
        lastMail.getNewestMail();
        EmailContentHelper emailContentHelper = new EmailContentHelper(lastMail.getLinks(), lastMail.getStrong(), lastMail.getH2());
        emailContentHelper.validateEmailContent(expire, name, sender, system, username);
    }
}
