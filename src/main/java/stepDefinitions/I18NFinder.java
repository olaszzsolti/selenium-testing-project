package stepDefinitions;

import Helpers.WebDriverInstance;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class I18NFinder {
    WebDriver driver = WebDriverInstance.getDriver();

    @Then("^Has i18n element (.*)$")
    public void hasHtmlElementWithI18N(String i18nData){
        if(driver.findElements(By.cssSelector("[data-i18n="+i18nData+"]")).size() < 1)
            Assert.fail("Element not found with i18n: "+i18nData);
    }

    @Then("^Has element (.*) with i18n (.*)$")
    public void hasHtmlSpecificElementWithI18N(String elementType, String i18nData){
        if(driver.findElements(By.cssSelector(elementType + "[data-i18n="+i18nData+"]")).size() < 1)
            Assert.fail("Element not found with i18n: "+i18nData);
    }


    @Then("^Has menu with i18n (.*)$")
    public void hasMenuI18N(String i18nData){
        try {
            WebElement externalCheckbox = driver.findElement(By.cssSelector(".left_menu .menu_item[data-i18n="+i18nData+"]"));
            Assert.assertTrue(true);
        } catch (NoSuchElementException e) {
            Assert.fail("I18n element not found: "+i18nData);
        }
    }

    @Then("^MenuItem with i18n (.*) clicked")
    public void menuItemI18nClick(String i18nData){
        try {
            driver.findElement(By.cssSelector(".left_menu .menu_item[data-i18n="+i18nData+"]")).click();
            Assert.assertTrue(true);
        } catch (NoSuchElementException e) {
            Assert.fail("I18n element not found: "+i18nData);
        }
    }


    @Then("^Check language selectors$")
    public void hasLanguageSelectors(){
        try {
            driver.findElements(By.cssSelector("#i18n-chooser"));
        } catch (NoSuchElementException e) {
            Assert.fail("No lang chooser: #i18n-chooser");
            return;
        }
        try {
            driver.findElements(By.cssSelector("#i18n-chooser span[data-i18n-lang=hu]"));
        } catch (NoSuchElementException e) {
            Assert.fail("No lang chooser: HU");
        }
        try {
            driver.findElements(By.cssSelector("#i18n-chooser span[data-i18n-lang=en]"));
        } catch (NoSuchElementException e) {
            Assert.fail("No lang chooser: EN");
        }
        try {
            driver.findElements(By.cssSelector("#i18n-chooser span[data-i18n-lang=de]"));
        } catch (NoSuchElementException e) {
            Assert.fail("No lang chooser: DE");
        }
    }





}
