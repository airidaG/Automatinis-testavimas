import net.datafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import testPages.*;

import java.time.Duration;

public class BaseTest {
    static WebDriver driver;
    LandingPage landingPage;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    HomePage homePage;
    ProductPage productPage;
    CartPage cartPage;
    static TestData testData;
    static Faker faker;
    static final String BASE_URL = "http://192.168.89.95/";

    @BeforeAll
    static void generateUserData() {
        faker = new Faker();
        testData = new TestData();
        Util.generateUserData();
    }

    @BeforeEach
    void setup() {

        ChromeOptions options = getHeadlessOptions();
        driver = new ChromeDriver(options);
        landingPage = new LandingPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        driver.get(BASE_URL);
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


    private ChromeOptions getHeadlessOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920x1080");
        return options;
    }
}
