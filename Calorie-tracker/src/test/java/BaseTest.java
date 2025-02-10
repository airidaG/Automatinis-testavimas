import TestPages.CalorieAppPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTest {
    static WebDriver driver;
    CalorieAppPage calorieAppPage;
    static final String  BASE_URL = "https://practice.expandtesting.com/tracalorie/";
    static final String HOME_URL = "https://practice.expandtesting.com/";

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        calorieAppPage = new CalorieAppPage(driver);
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".fc-button-label"))).click();

    }

//    @AfterEach
//    void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
}
