
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import testPages.CheckoutPage;
import testPages.LandingPage;
import testPages.ProductPage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class BaseTest {
    static WebDriver driver;
    LandingPage landingPage;
    ProductPage productPage;
    CheckoutPage checkoutPage;
    static final String BASE_URL = "https://demo.litecart.net/";
    static List<String> testData;

    @BeforeAll
    static void setUpOnce() throws IOException {
        testData = readData();
    }

    @BeforeEach
    void setup() {

//        ChromeOptions options = getHeadlessOptions();
//        driver = new ChromeDriver(options);
        driver = new ChromeDriver();
        landingPage = new LandingPage(driver);
        productPage = new ProductPage(driver);
        checkoutPage = new CheckoutPage(driver);
        driver.get(BASE_URL);
        driver.manage().window().maximize();
    }

//    @AfterEach
//    void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }


//    private ChromeOptions getHeadlessOptions() {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless"); // Enable headless mode
//        options.addArguments("--disable-gpu"); // Disable GPU (for compatibility)
//        options.addArguments("--window-size=1920x1080"); // Set window size
//        return options;
//    }

    private static List<String> readData() throws IOException {
        Path filePath = Paths.get("src/test/resources/templateData.csv");
        List<String> lines = Files.lines(filePath)
                .toList();
        return lines;
    }
}
