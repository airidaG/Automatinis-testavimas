import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


public class TestClassTemplate extends BaseTest {

//    TODO
//    DELETE unused imports, methods and variables.
//    DELETE selector cheatsheet
//    HeadlessMode(don't need window maximize)
//    After Each in base test
//    ScreenshotExtension needed?
//    if yes, add proper pom.xml from calorie tracker allure, copy everything
//    TestClass Name
//    testData file Name
//    is util class needed?
// ---       FLUENT WAIT EXAMPLE:
// public void selectAllMp3() {
//
//    FluentWait<WebDriver> wait = new FluentWait<>(driver)
//            .withTimeout(Duration.ofSeconds(15))
//            .pollingEvery(Duration.ofMillis(500))
//            .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
//    wait.until(ExpectedConditions.elementToBeClickable(dropdownMenuMP3)).click();
//    wait.until(ExpectedConditions.visibilityOf(buttonSeeAllMP3));
//    buttonSeeAllMP3.click();
//}


    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/templateData.csv", numLinesToSkip = 1)
    void parameterizedTemplateTest(String productName) {

    }
}
