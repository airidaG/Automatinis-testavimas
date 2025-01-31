import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Random;

public class Base {

    WebDriver driver;
    Random random;

//    @BeforeAll
//    static void cleanData(){
//
//    }

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        random = new Random();
        driver.get("http://192.168.89.95/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
