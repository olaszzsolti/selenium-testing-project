package Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MailHelper {
    WebDriver driver;
    String senderFull, senderName, senderEmail, subject;
    String recipientFull, recipientName, recipientEmail;
    String contentHtmlTxt, contentTxt, contentHtmlRaw;
    List<WebElement> links, strong, h2;
    int size;

    public MailHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void getNewestMail() {
//            driver.wait(300);
        driver.get(UrlHelper.getUrl("mailhog", ""));
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /////
        WebElement mail = driver.findElement(By.cssSelector(".msglist-message"));

        this.size = this.getMailSize(
                mail.findElements(By.cssSelector("div>.row .col-md-3")).get(1).getText()
//                driver.findElements(By.cssSelector(".msglist-message>div")).get(2).getText()
        );
        driver.findElements(By.cssSelector(".msglist-message")).get(0).click();
        // mail content processing
        List<WebElement> headers = driver.findElements(By.cssSelector(".row.headers table tr"));
        this.senderFull = headers.get(0).findElement(By.cssSelector("td")).getText();
        String[] senderPartial = senderFull.split("<");
        this.senderName = senderPartial[0].replaceAll("\"", "");
        this.senderEmail = senderPartial[1].replaceAll(">", "");
        this.subject = headers.get(1).findElement(By.cssSelector("td")).getText();
        this.recipientFull = headers.get(2).findElement(By.cssSelector("td")).getText();
        String[] recipientPartial = recipientFull.split("<");
        this.recipientName = recipientPartial[0].replaceAll("\"", "");
        this.recipientEmail = recipientPartial[1].replaceAll(">", "");

        // default tab, html preview
        this.contentHtmlTxt = driver.findElement(By.cssSelector("#preview-html")).getAttribute("srcdoc");

        List<WebElement> tabs = driver.findElements(By.cssSelector(".mail-content [data-toggle=tab]"));

        tabs.get(1).click();
        this.contentTxt = driver.findElement(By.cssSelector("#preview-plain")).getText();
        tabs.get(2).click();
        this.contentHtmlRaw = driver.findElement(By.cssSelector("#preview-source")).getText();

        // back to html
        tabs.get(0).click();
        driver.switchTo().frame(driver.findElement(By.cssSelector("#preview-html")));
        links = driver.findElements(By.cssSelector("a"));
        strong = driver.findElements(By.cssSelector("b,strong"));
//        strong.addAll(driver.findElements(By.cssSelector("#preview-html strong")));
        h2 = driver.findElements(By.cssSelector("h2"));

        System.out.println("Finished mail processing");
    }

    public int getMailSize(String size) {
//        "\n" +
//        "          12.76 kB\n" +
//        "        ";
        size = size.trim();

        String[] sizeSpl = size.split(" ");
        String sizeN = sizeSpl[0];
        if (sizeN.equals("")) {
            sizeN = sizeSpl[1];
        }
        float sizeInt = Float.parseFloat(sizeN);

        int last = sizeSpl.length - 1;
        if (sizeSpl[last].equals(""))
            last -= 1;
        if (sizeSpl[last].equals("kB"))
            sizeInt *= 1024;
        if (sizeSpl[last].equals("mB"))
            sizeInt *= 1024 * 1024;
        return (int) sizeInt;
    }


    public void deleteAllMail() {
        driver.get(UrlHelper.getUrl("mailhog", ""));
        driver.findElement(By.cssSelector("a[ng-click=\"deleteAll()\"]")).click();
//        try {
//            driver.wait(200);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }


    public String getSubject() {
        return subject;
    }

    public int getSize() {
        return size;
    }

    public String getSenderFull() {
        return senderFull;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public String getRecipientFull() {
        return recipientFull;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public String getContentHtmlTxt() {
        return contentHtmlTxt;
    }

    public String getContentTxt() {
        return contentTxt;
    }

    public String getContentHtmlRaw() {
        return contentHtmlRaw;
    }

    public List<WebElement> getLinks() {
        return links;
    }

    public List<WebElement> getStrong() {
        return strong;
    }

    public List<WebElement> getH2() {
        return h2;
    }
}
