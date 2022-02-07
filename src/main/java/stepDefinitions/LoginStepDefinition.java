package stepDefinitions;

import Helpers.Configuration;
import Helpers.MailHelper;
import Helpers.UrlHelper;
import Helpers.WebDriverInstance;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.net.URL;

public class LoginStepDefinition {
    MailHelper lastMail;
    public static String status = "failed";
    WebDriver driver = WebDriverInstance.getDriver();

    @Given("Close driver")
    public void closeDriver()  {
        driver.quit();
    }

    @Given("Reset mails")
    public void resetMails() {
        WebDriver driver = WebDriverInstance.getDriver();
        new MailHelper(driver).deleteAllMail();
    }


    @Given("^Logged as admin$")
    public void loggedInAsAdmin() {
        try {
            driver.get(UrlHelper.getUrl("apache_php","login.php"));
            try {
                driver.findElement(By.cssSelector("input[name=username]")).sendKeys(Configuration.readConfig("admin_user"));
                driver.findElement(By.cssSelector("input[name=password]")).sendKeys(Configuration.readConfig("admin_pass"));
                driver.findElement(By.cssSelector("input[name=submit]")).submit();
            } catch (NoSuchElementException e) {
                // redirected, already logged in
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // should be on page: /add_password.php
        // checking if login succeeded
        Assert.assertEquals(1, driver.findElements(By.cssSelector("div[data-i18n=menu_logout]")).size());
    }

    @When("^Get email contents$")
    public void getEmailContents(){
        lastMail = new MailHelper(driver);
        lastMail.getNewestMail();
        System.out.println("mailer:".concat(lastMail.getSenderFull()));
        System.out.println(lastMail.getSenderName());
        System.out.println(lastMail.getSenderEmail());
        System.out.println("recipient:".concat(lastMail.getRecipientFull()));
        System.out.println(lastMail.getRecipientName());
        System.out.println(lastMail.getRecipientEmail());
        System.out.println("subject:".concat(lastMail.getSubject()));
        System.out.println("context html length:".concat(String.valueOf(lastMail.getContentHtmlTxt().length())));
        System.out.println("context raw  length:".concat(String.valueOf(lastMail.getContentHtmlRaw().length())));
        System.out.println("context text length:".concat(String.valueOf(lastMail.getContentTxt().length())));
        System.out.println("---------------------------------------");
        System.out.println(lastMail.getContentTxt());
        System.out.println("---------------------------------------");
    }

    public boolean isLoggedOut(){
        try {
            WebElement externalCheckbox = driver.findElement(By.cssSelector(".left_menu .menu_item[i18n=menu_logout]"));
        } catch (NoSuchElementException e) {
            return true;
        }
        return false;
    }
    @Then("^Logout$")
    public void logout(){
        driver.get(UrlHelper.getUrl("apache_php","logout.php"));
    }

    @Given("^Goto login page (.*)")
    public void gotoLoginPage(String page){
        // force logout
        driver.get(UrlHelper.getUrl("apache_php","logout.php"));
        driver.get(UrlHelper.getUrl("apache_php",page));
    }

    @Given("^Goto page (.*)")
    public void gotoPage(String page){
        // force login
        if(isLoggedOut()){
            loggedInAsAdmin();
        }
        driver.get(UrlHelper.getUrl("apache_php",page));
    }

}
