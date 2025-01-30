import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ClickAllTest extends Base {

    @Test
    void clickAllTest() {

        while (true) {
            List<WebElement> gridElements = driver.findElements(By.className("icon"));

            for (WebElement element : gridElements) {
                element.click();
            }
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();

            if (alertText.equals("DONE! Congratulations on completing the task!")) {
                alert.accept();
                break;
            }
            alert.accept();

        }

    }
}
