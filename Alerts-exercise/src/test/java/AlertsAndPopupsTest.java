import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AlertsAndPopupsTest extends Base {

    @Test
    void javaScriptAlertTest() {

        driver.findElement(By.id("button1")).click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        assertEquals("I am an alert box!", alertText);
        alert.accept();
    }

    @Test
    void javaScriptConfirmBoxTest() {

        driver.findElement(By.id("button4")).click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        assertEquals("Press a button!", alertText);
        alert.accept();

        String afterAlertText = driver.findElement(By.id("confirm-alert-text")).getText();
        assertEquals("You pressed OK!", afterAlertText);

    }

    @Test
    void modalPopupTest() {

        driver.findElement(By.id("button2")).click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        //Cast object to string, extract text
        String test = (String) js.executeScript("return document.querySelector('.modal-title').innerText;");

        assertEquals("It’s that Easy!!  Well I think it is.....", test);
        driver.findElement(By.cssSelector(".modal-footer [data-dismiss]")).click();


    }

    @Test
    void ajaxLoaderPopupTest() {

        driver.findElement(By.id("button3")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span#button1")));
        button.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String title = (String) js.executeScript("return document.querySelector('.modal-title').innerText;");

        assertEquals("Well Done For Waiting....!!!", title);
        driver.findElement(By.cssSelector(".modal-footer [data-dismiss]")).click();


    }
}
