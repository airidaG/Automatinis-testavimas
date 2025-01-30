import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleCalculatorTest extends Base {

    @Test
    void calculateTest() {

        WebElement firstNumber = driver.findElement(By.id("number1"));
        WebElement secondNumber = driver.findElement(By.id("number2"));
        WebElement dropdown = driver.findElement(By.id("function"));


        firstNumber.sendKeys("3");
        dropdown.click();
        Select functionsList = new Select(dropdown);
        functionsList.selectByValue("minus");

        secondNumber.sendKeys("2");
        driver.findElement(By.id("calculate")).click();

        String answer = driver.findElement(By.id("answer")).getText();
        assertEquals("1", answer);
    }
}
