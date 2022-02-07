package stepDefinitions;

import Helpers.WebDriverInstance;
import cucumber.api.java.en.Then;
import org.openqa.selenium.*;
import org.testng.Assert;

public class PasswordChecker {
    WebDriver driver = WebDriverInstance.getDriver();
    String currentPassword = "";
    int currentLength = 16;
    int currentDigits = 0;
    int currentSpecials = 0;

    public void getPassword() {
        try {
            WebElement externalCheckbox = driver.findElement(By.cssSelector("#generated_password_password"));
            currentPassword = externalCheckbox.getText();

            // validating generated password, that it has written into the hidden input password fields
            externalCheckbox = driver.findElement(By.cssSelector("#password1"));

            Assert.assertEquals(currentPassword, externalCheckbox.getAttribute("value"), "#generated_password_password does ont matches #password1");
            externalCheckbox = driver.findElement(By.cssSelector("#password2"));
            Assert.assertEquals(currentPassword, externalCheckbox.getAttribute("value"), "#generated_password_password does ont matches #password2");
        } catch (NoSuchElementException e) {
            Assert.fail("Element not found:");
            e.printStackTrace();
        }
    }

    public void passwordHasLength(int length) {
        Assert.assertEquals(length, currentPassword.length(), "Password has different length as specified");
    }

    public void passwordHasDigits(int expectedDigits) {
        int countedDigits = 0;
        for (int i = 0; i < currentPassword.length(); i++) {
            if (currentPassword.charAt(i) >= 48 && currentPassword.charAt(i) <= 57)
                countedDigits++;
        }
        Assert.assertEquals( expectedDigits, countedDigits,  "Password has different number of digits as specified");
    }

    public void passwordHasSpecial(int expectedSpecials) {
        String filteredOutPassword = currentPassword.replaceAll("[0-9]+", "").replaceAll("[a-z]+", "").replaceAll("[A-Z]+", "");
        Assert.assertEquals(expectedSpecials, filteredOutPassword.length(), "Password has different number of special characters as specified");
    }

    public void setInputToValue(String inputName, int value) {
        try {
            WebElement externalCheckbox = driver.findElement(By.cssSelector("input[name=" + inputName + "]"));
            externalCheckbox.clear();
            externalCheckbox.sendKeys(String.valueOf(value));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    public void regeneratePassword() {
        try {
            WebElement externalCheckbox = driver.findElement(By.cssSelector("input#generated_password_generate"));
            externalCheckbox.click();
            wait(100);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setPasswordSpecifications(int length, int digits, int specials) {
        setInputToValue("password_length", length);
        setInputToValue("password_numbers", digits);
        setInputToValue("password_special", specials);
        getPassword();
        currentLength = length;
        currentDigits = digits;
        currentSpecials = specials;
    }

    public void setHardPasswordSpecifications(int length) {
        setInputToValue("password_length", length);
        getPassword();
        currentLength = length;
    }

    public void checkPasswordSpecifications(int length, int digits, int specials) {
        getPassword();
        passwordHasLength(length);
        passwordHasDigits(digits);
        passwordHasSpecial(specials);
    }
    public void checkHardPasswordSpecifications(int length) {
        getPassword();
        passwordHasLength(length);
    }

    @Then("Test password specifications (\\d*), (\\d*), (\\d*), (\\d*)")
    public void setAndValidatePasswordSpecifications(int length, int digits, int specials, int regens) {
        setPasswordSpecifications(length, digits, specials);
        regeneratePassword();
        wait(100);
        checkPasswordSpecifications(length, digits, specials);
        int generated;
        for (generated = 0; generated < regens; generated++) {
            regeneratePassword();
            checkPasswordSpecifications(length, digits, specials);
        }
    }

    @Then("Test hard password specifications (\\d*), (\\d*)")
    public void setAndValidateHardPasswordSpecifications(int length, int regens) {
        setHardPasswordSpecifications(length);
        regeneratePassword();
        wait(100);
        checkHardPasswordSpecifications(length);
        int generated;
        for (generated = 0; generated < regens; generated++) {
            regeneratePassword();
            checkHardPasswordSpecifications(length);
        }
    }

    public void wait(int waitMs) {
        try {
            Thread.sleep(waitMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}