package pages;

import org.openqa.selenium.WebDriver;

public abstract class AbstractPage {

    private final String SUMMARY = "Проблема глобального потепления";
    private final String DESCRIPTION = "Description of your issue";
    private final String DUE_ON = "2021-10-05 00:00:00";
    private final String EMAIL = "qwerty@emailo.at";

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public String getDUE_ON() {
        return DUE_ON;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public String getSUMMARY() {
        return SUMMARY;
    }

    public static WebDriver driver;

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }


}
