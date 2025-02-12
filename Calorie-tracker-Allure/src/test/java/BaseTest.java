import TestPages.CalorieAppPage;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@ExtendWith(ScreenshotExtension.class)
public class BaseTest {
    static WebDriver driver;
    CalorieAppPage calorieAppPage;
    static final String  BASE_URL = "https://practice.expandtesting.com/tracalorie/";
    static final String HOME_URL = "https://practice.expandtesting.com/";
    static final String POPUP_BUTTON = ".fc-button-label";

//    public static WebDriver getDriver() {
//        return driver;
//    }

//TODO

    @BeforeAll
    static void setupAllureReports() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
        );
    }

    @BeforeEach
    void setup() {

        WebDriverRunner.setWebDriver(driver);
        calorieAppPage = new CalorieAppPage(driver);
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        handlePopup();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    private void handlePopup(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(POPUP_BUTTON))).click();

    }

//    private ChromeOptions getHeadlessOptions() {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless"); // Enable headless mode
//        options.addArguments("--disable-gpu"); // Disable GPU (for compatibility)
//        options.addArguments("--window-size=1920x1080"); // Set window size
//        return options;
//    }
}
