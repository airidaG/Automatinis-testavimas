import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ColorWithoutMiddleTest extends Base {


    @Test
    void colorEdgesTest() {

        while (true) {
            List<WebElement> elements = driver.findElements(By.className("icon"));

            int size = (int) Math.sqrt(elements.size());

            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {

                    int index = row * size + col;

                    if (row == 0 || row == size - 1 || col == 0 || col == size - 1) {

                        elements.get(index).click();

                    }
                }
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
