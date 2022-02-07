package Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class DriverHelper {
    WebDriver driver;

    public DriverHelper() {
        try {
            this.driver = WebDriverInstance.getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void emptyMailhog(){
        driver.get(UrlHelper.getUrl("mailhog",""));
        driver.findElement(By.cssSelector("a[ng-click=\"deleteAll()\"]")).click();
//        try {
//            driver.wait(200);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public int countNewMail(){
//        try {
//            driver.wait(300);
            driver.get(UrlHelper.getUrl("mailhog",""));
            return driver.findElements(By.cssSelector(".msglist-message")).size();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        // todo: should it be fail?
//        return 0;
    }

    public boolean hasNewMail(){
        return (countNewMail()>0);
    }

}
