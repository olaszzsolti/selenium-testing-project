package stepDefinitions;

import Helpers.WebDriverInstance;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class FormCheckerDefinitions {
    WebDriver driver = WebDriverInstance.getDriver();

    @Then("^Wait (\\d+)$")
    public static void wait(int waitMs) {
        try {
            Thread.sleep(waitMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void checkInputAndType(String inputName, String type) {
        try {
            WebElement externalInput = driver.findElement(By.cssSelector("form input[type=" + type + "][name=" + inputName + "]"));
        } catch (NoSuchElementException e) {
            Assert.fail("Element not found");
        }
    }

    @Then("^Input (.*) named (.*) is visible")
    public void inputIsVisible(String type, String inputName) {
        try {
            WebElement element = driver.findElement(By.cssSelector("form input[type=" + type + "][name=" + inputName + "]"));
            Assert.assertTrue(element.isDisplayed());
        } catch (NoSuchElementException e) {
            Assert.fail("Element not found");
        }
    }

    @Then("^Has input text (.*)$")
    public void checkInputText(String inputName) {
        checkInputAndType(inputName, "text");
    }

    @Then("^Has input password (.*)$")
    public void checkInputPassword(String inputName) {
        checkInputAndType(inputName, "password");
    }

    @Then("^Has input checkbox (.*)$")
    public void checkInputCheckbox(String inputName) {
        checkInputAndType(inputName, "checkbox");
    }

    @Then("^Has input number (.*)$")
    public void checkInputNumber(String inputName) {
        checkInputAndType(inputName, "number");
    }

    @Then("^Has button (.*)$")
    public void checkButton(String inputName) {
        checkInputAndType(inputName, "button");
    }

    @Then("^Has textarea (.*)$")
    public void checkTextarea(String textareaName) {
        try {
            WebElement externalTextarea = driver.findElement(By.cssSelector("form textarea[name=" + textareaName + "]"));
        } catch (NoSuchElementException e) {
            Assert.fail("Element not found");
        }
    }

    @Then("^Has input select (.*)$")
    public void checkSelectCheckbox(String inputName) {
        try {
            WebElement externalSelect = driver.findElement(By.cssSelector("form select[name=" + inputName + "]"));
        } catch (NoSuchElementException e) {
            Assert.fail("Element not found");
        }
    }

    @Then("^Has input by id (.*)$")
    public void checkInputById(String inputId) {
        try {
            WebElement externalInput = driver.findElement(By.cssSelector("form input#" + inputId));
        } catch (NoSuchElementException e) {
            Assert.fail("Element not found");
        }
    }

    @Then("^Has submit (.*)$")
    public void checkSubmit(String inputName) {
        checkInputAndType(inputName, "submit");
    }

    @Then("^Submit (.*) click$")
    public void clickSubmit(String inputName) {
        try {
            driver.findElement(By.cssSelector("form input[type=submit][name=" + inputName + "]")).click();
        } catch (NoSuchElementException e) {
            Assert.fail("Element not found: " + inputName);
        }
    }

    @Then("^Dropdown (.*) has (.*) selected$")
    public void dropdownHasSelected(String dropdownName, String optionValue) {
        try {
            WebElement externalDropdown = driver.findElement(By.cssSelector("form select[name=" + dropdownName + "] option[selected]"));
            Assert.assertEquals(externalDropdown.getAttribute("value"), optionValue, "Value does not matches");
        } catch (NoSuchElementException e) {
            Assert.fail("Element not found");
        }
    }

    @Then("^Dropdown (.*) select (.*) option")
    public void dropdownSelectOption(String dropdownName, String optionValue) {
        WebElement externalDropdown;
        try {
            externalDropdown = driver.findElement(By.cssSelector("form select[name=" + dropdownName + "]"));
            externalDropdown.click();
            wait(20);
        } catch (NoSuchElementException e) {
            Assert.fail("Element not found:" + dropdownName + ".");
        }
        try {
            // needs extra escaping of css path, because value is starting with number (7d)
            externalDropdown = driver.findElement(By.cssSelector("form select[name=" + dropdownName + "] option[value=\"" + optionValue + "\"]"));
            externalDropdown.click();

        } catch (NoSuchElementException e) {
            Assert.fail("Element not found:" + optionValue + ".");
        }

    }

    @Then("^Checkbox (.*) clicked$")
    public void checkboxClick(String checkboxName) {
        try {
            driver.findElement(By.cssSelector("form input[type=checkbox][name=" + checkboxName + "]")).click();
        } catch (NoSuchElementException e) {
            Assert.fail("Checkbox not found: " + checkboxName);
        }
    }

    @Then("^Checkbox (.*) set to (\\d+)$")
    public void checkboxSetTo(String checkboxName, int state) {
        try {
            WebElement externalCheckbox = driver.findElement(By.cssSelector("form input[type=checkbox][name=" + checkboxName + "]"));
            if (externalCheckbox.isSelected() != (state == 1)) {
                externalCheckbox.click();
            }
        } catch (NoSuchElementException e) {
            Assert.fail("Checkbox not found: " + checkboxName);
        }
    }

    @Then("^Has element with id (.*)")
    public void hasElementWithId(String id) {
        try {
            WebElement externalElement = driver.findElement(By.cssSelector("#" + id));
        } catch (NoSuchElementException e) {
            Assert.fail("Element not found");
        }
    }

    @Then("^Has element with class (.*)")
    public void hasElementWithclass(String classname) {
        try {
            WebElement externalElement = driver.findElement(By.cssSelector("div[class='"+classname+"']"));
        } catch (NoSuchElementException e) {
            Assert.fail("Element not found");
        }
    }

    @Then("^Element with id (.*) is visible")
    public void elementWithIdVisible(String id) {
        try {
            WebElement externalElement = driver.findElement(By.cssSelector("#" + id));
            Assert.assertTrue(externalElement.isDisplayed());
        } catch (NoSuchElementException e) {
            Assert.fail("Element not found");
        }
    }

    @Then("^Input (.*) has value (.*)$")
    public void inputValueCheck(String inputName, String value) {
        try {
            WebElement externalInput = driver.findElement(By.cssSelector("form input[name=" + inputName + "]"));
            Assert.assertEquals(externalInput.getAttribute("value"), value, "Input content does not match");
        } catch (NoSuchElementException e) {
            Assert.fail("Element not found");
        }
    }

    @Then("^Input (.*) set to (.*)")
    public void inputSetValue(String inputName, String value) {
        try {
            WebElement externalInput = driver.findElement(By.cssSelector("form input[name=" + inputName + "]"));
            externalInput.clear();
            wait(30);
            externalInput.clear();
            externalInput.sendKeys(value);
        } catch (NoSuchElementException e) {
            Assert.fail("Element not found");
        }

    }

    @Then("^Textarea (.*) set to (.*)$")
    public void textareaDescriptionSetToDescription(String inputName, String value) {
        WebElement externalTextarea = driver.findElement(By.cssSelector("form textarea[name=" + inputName + "]"));
        externalTextarea.clear();
        externalTextarea.sendKeys(value);
    }

    @Then("^Button (.*) click")
    public void clickButton(String inputName) {
        WebElement externalCheckbox = driver.findElement(By.cssSelector("form input[type=button][name=" + inputName + "]"));
        externalCheckbox.click();
    }

    @Then("^Insert (.*) to (.*) field")
    public void insertTextToInput(String text, String inputname) {
        try {
            driver.findElement(By.cssSelector("input[name=" + inputname + "]")).sendKeys(text);
        } catch (NoSuchElementException e) {
            Assert.fail("Element not found");
        }
    }

    @Then("^Click on Submit$")
    public void ClickOnSubmit() {
        try {
            driver.findElement(By.cssSelector("input[name=submit]")).submit();
        } catch (NoSuchElementException e) {
            Assert.fail("Element not found");
        }
    }

    @Then("^Click on (.*) Link with data-i18n")
    public void ClickOnLinkdatai18n(String link) {
        try {
            driver.findElement(By.cssSelector("[data-i18n=" + link + "]")).click();
        } catch (NoSuchElementException e) {
            Assert.fail("Element not found");
        }
    }

    @Then("^Click on (.*) Link with title")
    public void ClickOnLinkwithtitle(String title) {
        try {
            driver.findElement(By.cssSelector("[title=" + title + "]")).click();
        } catch (NoSuchElementException e) {
            Assert.fail("Element not found");
        }
    }

    @Then("^Click on (.*) Link with id")
    public void ClickOnLinkwithID(String id) {
        try {
            driver.findElement(By.cssSelector("#" + id)).click();
        } catch (NoSuchElementException e) {
            Assert.fail("Element not found");
        }
    }

    @Then("^Click on p92 header logo")
    public void ClickOnp92headerlogo() {
        try {
            driver.findElement(By.cssSelector("#p92logoheader span[class=p92-logo-text] span[class=p92com]")).click();
        } catch (NoSuchElementException e) {
            Assert.fail("Element not found");
        }
    }

    @Then("^Click on p92 footer logo")
    public void ClickOnp92footerlogo() {
        try {
            driver.findElement(By.cssSelector("#p92logofooter span[class=p92-logo-text]")).click();
        } catch (NoSuchElementException e) {
            Assert.fail("Element not found");
        }
    }

    @Then("^Check link with i18nData (.*) href is equal to (.*)")
    public void checkLinksi18nDataAreEquals(String i18nData, String href) {
        try {
           String href1= driver.findElement(By.cssSelector("[data-i18n="+i18nData+"]")).getAttribute("href");
           Assert.assertEquals(href1, href);
        } catch (NoSuchElementException e) {
            Assert.fail("Element not found");
        }
    }

    @Then("^Check link with title (.*) href is equal to (.*)")
    public void checkLinkstitleAreEquals(String title, String href) {
        try {
            String href1= driver.findElement(By.cssSelector("[title=" + title + "]")).getAttribute("href");
            Assert.assertEquals(href1, href);
        } catch (NoSuchElementException e) {
            Assert.fail("Element not found");
        }
    }

    @Then("^Check current url is equals to (.*) url")
    public void checkLinksAreEquals(String url) {
        try {
            String currenturl = driver.getCurrentUrl();
            Assert.assertEquals(currenturl, url);
        } catch (NoSuchElementException e) {
            Assert.fail("Element not found");
        }
    }
}