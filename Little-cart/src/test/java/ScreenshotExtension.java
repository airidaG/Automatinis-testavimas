import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class ScreenshotExtension implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {

        if (context.getExecutionException().isPresent()) {

            String testName = context.getDisplayName();
            String fileName = "screenshots/" + testName + "_failed.png";
//            screenshot();
//            Selenide.screenshot(fileName);
//            System.out.println("Screenshot saved: " + fileName);
        }
    }

//    @Attachment(value = "Screenshot", type = "image/png")
//    public static byte[] screenshot() {
//        return ((TakesScreenshot) BaseTest.getDriver()).getScreenshotAs(OutputType.BYTES);
//    }
}
