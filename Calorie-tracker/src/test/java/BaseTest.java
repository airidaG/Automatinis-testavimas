import TestPages.CalorieAppPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTest {
    static WebDriver driver;
    CalorieAppPage calorieAppPage;
    static final String  BASE_URL = "https://practice.expandtesting.com/tracalorie/";
    static final String HOME_URL = "https://practice.expandtesting.com/";
    static final String POPUP_BUTTON = ".fc-button-label";

    @BeforeEach
    void setup() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Enable headless mode
        options.addArguments("--disable-gpu"); // Disable GPU (for compatibility)
        options.addArguments("--window-size=1920x1080"); // Set window size

        // Initialize WebDriver with ChromeOptions
        driver = new ChromeDriver(options);
        calorieAppPage = new CalorieAppPage(driver);
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(POPUP_BUTTON))).click();

    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
