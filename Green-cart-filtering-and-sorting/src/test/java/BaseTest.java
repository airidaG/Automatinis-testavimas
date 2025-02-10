import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import testPages.GreenCartPage;

import java.time.Duration;

public class BaseTest {
    static WebDriver driver;
    GreenCartPage greenCartPage;
    static final String BASE_URL = "https://rahulshettyacademy.com/seleniumPractise/#/offers";

    @BeforeEach
    void setup() {

//        ChromeOptions options = getHeadlessOptions();
//        driver = new ChromeDriver(options);
        driver = new ChromeDriver();
        greenCartPage = new GreenCartPage(driver);
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
//
//    @AfterEach
//    void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
}

//
//    private ChromeOptions getHeadlessOptions() {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless"); // Enable headless mode
//        options.addArguments("--disable-gpu"); // Disable GPU (for compatibility)
//        options.addArguments("--window-size=1920x1080"); // Set window size
//        return options;
//    }

