package Helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class WebDriverInstance {
    private static WebDriver driver = null;

    public static WebDriver getDriver() {
        if(driver == null){

//            driver = new RemoteWebDriver(new URL(Configuration.readConfig("selenium_url")), new ChromeDriver().getCapabilities());
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability("max-duration", 10);
            capabilities.setCapability("command-timeout", 10);
            capabilities.setCapability("idle-timeout", 10);
            try {
                driver = new RemoteWebDriver(new URL(Configuration.readConfig("selenium_url")), capabilities);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(driver);
        }
        return driver;
    }
}
