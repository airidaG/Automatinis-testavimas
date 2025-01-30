import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ButtonsTest extends Base {

    @Test
    void webElementClickTest() {

        WebElement button = driver.findElement(By.xpath("/html/body/div[@class='container']/div[2]/div[@class='col-md-12']/div/div[1]/div[@class='thumbnail']/div[@class='caption']/span[@class='btn btn-default btn-lg dropdown-toggle']"));

        button.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String text = (String) js.executeScript("return document.querySelector('#myModalClick .modal-dialog.modal-sm .modal-title').innerText;");

        assertEquals("Congratulations!", text);
        driver.findElement(By.xpath("/html//div[@id='myModalClick']//div[@class='modal-footer']/button[@type='button']")).click();

    }

    @Test
    void javaScriptClickTest() {

        WebElement button = driver.findElement(By.id("button2"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", button);
        String text = (String) js.executeScript("return document.querySelector('.modal-dialog.modal-md .modal-title').innerText;");
        assertEquals("It’s that Easy!!  Well I think it is.....", text);
        driver.findElement(By.cssSelector(".modal-dialog.modal-md .btn.btn-default")).click();


    }

    @Test
    void actionMoveAndClickTest() {

        WebElement button = driver.findElement(By.id("button3"));
        Actions actions = new Actions(driver);
        actions.click(button).perform();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String text = (String) js.executeScript("return document.querySelector('div#myModalMoveClick > .modal-dialog.modal-sm .modal-title').innerText;");
        assertEquals("Well done! the Action Move & Click can become very useful!", text);


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html//div[@id='myModalMoveClick']//div[@class='modal-footer']/button[@type='button']")));
        actions.click(closeButton).perform();


    }
}
