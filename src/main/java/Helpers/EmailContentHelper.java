package Helpers;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EmailContentHelper {

    // date format used in php date formatting
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    List<WebElement> links, strong, h2;

    public EmailContentHelper(List<WebElement> links, List<WebElement> strong, List<WebElement> h2) {
        this.links = links;
        this.strong = strong;
        this.h2 = h2;
    }

    public void validateEmailContent(String expireOption, String greeting, String sender, String system, String username) {
        validatePasswordManagerLocalLinks();
        validateExpireDate(expireOption);
        validateGreeting(greeting);
        validateSender(sender);
        validateSystem(system);
        validateUsername(username);
    }


    // all links in e-mail should point to apache_php service, or mailto:
    public void validatePasswordManagerLocalLinks() {
        for (WebElement element : links) {
            String link = element.getAttribute("href");
            if (!link.startsWith(UrlHelper.getUrl("apache_php", "")) && !element.getAttribute("href").startsWith("mailto:")) {
                Assert.fail("Link does not starts with apache base url (expected:\n" + UrlHelper.getUrl("apache_php", "") + "\ngot:\n" + element.getAttribute("href"));
            }
            if (link.startsWith(UrlHelper.getUrl("apache_php", ""))) {
                if (!link.contains("token=") || !link.contains("key=")) {
                    Assert.fail("Link does not contains token, or key\n" + link);
                }
            }
        }
    }

    public String getExpireDate(String expireOption) {
        Date currentDate = new Date();
        int addDays = 0;
        addDays = Integer.parseInt(expireOption.replace("d", ""));
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);

        // manipulate date
        c.add(Calendar.DATE, addDays);
        return dateFormat.format(c.getTime());
    }

    public void validateStringInElementList(List<WebElement> elements, String containsMe) {
        for (WebElement element : elements) {
            if (element.getText().contains(containsMe))
                return;
        }
        List<String> strongs2 = new ArrayList<String>(elements.size());
        for (WebElement element : elements)
            strongs2.add(element.getText());
        Assert.fail("Expected expire date not found\n::" + containsMe + "\n:     " + String.join("\n    ", strongs2));

    }

    // validates if expire date is good
    public void validateExpireDate(String expireOption) {
        String expireDate = getExpireDate(expireOption);
        if(!expireOption.equals("0"))
            validateStringInElementList(strong, expireDate);
    }

    public void validateGreeting(String greeting) {
        validateStringInElementList(h2, greeting);
    }

    public void validateSender(String sender) {
        validateStringInElementList(strong, sender);
    }

    public void validateSystem(String system) {
        validateStringInElementList(strong, system);
    }

    public void validateUsername(String username) {
        validateStringInElementList(strong, username);
    }

    public String getPasswordLink() {
        for (WebElement element : links) {
            String link = element.getAttribute("href");
            if (link.startsWith(UrlHelper.getUrl("apache_php", "")) && link.contains("token=") && link.contains("key=")) {
                return link;
            }
        }
        return null;
    }
}
