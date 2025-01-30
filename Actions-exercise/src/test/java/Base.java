import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class Base {

    WebDriver driver;
    Actions actions;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.get("https://webdriveruniversity.com/Actions/index.html");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        actions = new Actions(driver);

    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
