package stepDefinitions;

import Helpers.UrlHelper;
import Helpers.WebDriverInstance;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PasswordStepDefinitions {
    WebDriver driver = WebDriverInstance.getDriver();

    @When("AddPass set external \\{boolean\\}")
    public void setExternalAccess(boolean externalAccess){
        driver = WebDriverInstance.getDriver();
        WebElement externalCheckbox = driver.findElement(By.cssSelector("input#external"));
        if(!externalCheckbox.isSelected()){
            externalCheckbox.click();
        }
    }

    @When("^AddPass set email (.*)$")
    public void setEmail(String email){
        driver = WebDriverInstance.getDriver();
        WebElement emailInput = driver.findElement(By.cssSelector("input#email"));
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    @Given("^Send password (.*) to (.*):(.*)$")
    public void sendPassword(String password, String recipient, String recipientEmail){
        driver = WebDriverInstance.getDriver();
        new LoginStepDefinition().loggedInAsAdmin();
        driver.get(UrlHelper.getUrl("apache_php","add_password.php"));

    }
}

