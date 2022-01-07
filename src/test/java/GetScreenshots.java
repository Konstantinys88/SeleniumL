import io.qameta.allure.Attachment;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static pages.AbstractPage.driver;

public class GetScreenshots {
    /**
     * Класс для создания и прикрепления скриншотов в Allure
     */

    public static void takeScreenshot(String name) throws IOException {
        Screenshot screenshot = new AShot().takeScreenshot(driver);
        ImageIO.write(screenshot.getImage(),"png",new File("src/main/screenshot/screenLogin.png"));
        getBytes(name);
    }

    @Attachment
    public static byte[] getBytes(String resourceName) throws IOException {
        return Files.readAllBytes(Paths.get("src/main/screenshot", resourceName));
    }

}
