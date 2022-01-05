package pages;

import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.Screenshot;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public abstract class AbstractPage {

    public static WebDriver driver;

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }


}
