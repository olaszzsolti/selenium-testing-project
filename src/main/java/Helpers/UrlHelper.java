package Helpers;

public class UrlHelper {
    public static String getUrl(String service, String relative) {
        try {
            return Configuration.readConfig(service.concat("_baseUrl")).concat(relative);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
