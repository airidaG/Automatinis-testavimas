import TestPages.ToDoPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BaseTest {

    WebDriver driver;
    ToDoPage toDoPage;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        toDoPage = new ToDoPage(driver);
        driver.get("https://todomvc.com/examples/react/dist/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

//    @AfterEach
//    void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
}
