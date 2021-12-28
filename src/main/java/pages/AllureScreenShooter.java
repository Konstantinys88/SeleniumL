package pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.internal.ExitCodeListener;

import java.nio.charset.StandardCharsets;

public class AllureScreenShooter extends ExitCodeListener {

    public void onTestFailure(final ITestResult result) {
        super.onTestFailure(result);
        AllureHelpers.takeScreenshot();

    }

    public static final class AllureHelpers{

        @SuppressWarnings("UnusedReturnValue")
        @Attachment(value = "AllureTextReport", type = "text/plain", fileExtension = ".txt")
        public static String attachText(final String text) {
            return text;
        }

        @SuppressWarnings("UnusedReturnValue")
        @Attachment(value = "AllureCSVReport", type = "text/csv", fileExtension = ".csv")
        public static String attachCSV(final String csv) {
            return csv;
        }

        @SuppressWarnings("UnusedReturnValue")
        @Attachment(value = "Html source", type = "text/html", fileExtension = ".html")
        public static byte[] getPageSource() {
            return getPageSourceBytes();
        }

        @SuppressWarnings("UnusedReturnValue")
        @Attachment(value = "Screenshot", type = "image/png", fileExtension = ".png")
        public static byte[] takeScreenshot() {
            return getScreenshotBytes();
        }

        @SuppressWarnings("UnusedReturnValue")
        @Attachment(value = "Element screenshot", type = "image/png", fileExtension = ".png")
        public static byte[] takeScreenshot(final SelenideElement elem) {
            return getScreenshotBytes(elem);
        }

        public static byte[] getPageSourceBytes() {
            return WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
        }

        public static byte[] getScreenshotBytes() {
            return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
        }

        public static byte[] getScreenshotBytes(final SelenideElement elem) {
            return elem.getScreenshotAs(OutputType.BYTES);
        }



    }

}
