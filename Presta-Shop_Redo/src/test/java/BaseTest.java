import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Random;

public class BaseTest {

    static WebDriver driver;
    Random random;
    HomePage homePage;
    SignInPage signInPage;
    RegistrationPage registrationPage;
    LandingPage landingPage;


    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        random = new Random();
        homePage = new HomePage(driver);
        signInPage = new SignInPage(driver);
        registrationPage = new RegistrationPage(driver);
        landingPage = new LandingPage(driver);
        driver.get("http://192.168.89.95/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
