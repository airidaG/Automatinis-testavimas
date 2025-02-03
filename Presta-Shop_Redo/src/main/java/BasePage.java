import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.Random;

public class BasePage {

    protected WebDriver driver;
    protected Random random = new Random();


    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
