import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ActionsTest extends Base {

    @Test
    void dragAndDropTest() {

        WebElement draggable = driver.findElement(By.id("draggable"));
        WebElement droppable = driver.findElement(By.id("droppable"));

        actions.dragAndDrop(draggable, droppable).perform();

        assertEquals("Dropped!", driver.findElement(By.cssSelector("div#droppable > p > b")).getText());

        String actualColor = driver.findElement(By.cssSelector("div#droppable > p")).getCssValue("background-color");
        String expectedColor = "rgba(244, 89, 80, 1)";
        assertEquals(expectedColor, actualColor);

    }

    @Test
    void doubleClickTest() {

        WebElement doubleClickSection = driver.findElement(By.id("double-click"));
        String colorBefore = doubleClickSection.getCssValue("background-color");
        actions.doubleClick(doubleClickSection).perform();
        String colorAfter = doubleClickSection.getCssValue("background-color");
        assertNotEquals(colorBefore, colorAfter);

    }

    @Test
    void hoverTest() throws InterruptedException {

        WebElement hoverFirst = driver.findElement(By.cssSelector("div:nth-of-type(1) > .dropbtn"));
        WebElement hoverSecond = driver.findElement(By.cssSelector("div:nth-of-type(2) > .dropbtn"));
        WebElement hoverThird = driver.findElement(By.cssSelector("div:nth-of-type(3) > .dropbtn"));

        hoverAndClick(hoverFirst);
        hoverAndClick(hoverSecond);
        hoverAndClick(hoverThird);

//        actions.moveToElement(hoverFirst).perform();
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Link 1")));
//        driver.findElement(By.linkText("Link 1")).click();
//        Alert hoverAndClick = driver.switchTo().hoverAndClick();
//        String alertText = hoverAndClick.getText();
//        assertEquals("Well done you clicked on the link!", alertText);
//        hoverAndClick.accept();
//
//        actions.moveToElement(hoverSecond).perform();
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Link 1")));
//        driver.findElement(By.linkText("Link 1")).click();
//        driver.switchTo().hoverAndClick();
//        String secondAlertText = hoverAndClick.getText();
//        assertEquals("Well done you clicked on the link!", secondAlertText);
//        hoverAndClick.accept();
//
//        actions.moveToElement(hoverThird).perform();
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Link 1")));
//        driver.findElement(By.linkText("Link 1")).click();
//        driver.switchTo().hoverAndClick();
//        String ThirdAlertText = hoverAndClick.getText();
//        assertEquals("Well done you clicked on the link!", ThirdAlertText);
//        hoverAndClick.accept();


    }

    @Test
    void ClickAndHoldTest() {

        WebElement holdBox = driver.findElement(By.id("click-box"));

        String colorBefore = holdBox.getCssValue("background-color");
        String textBefore = holdBox.getText();
        actions.clickAndHold(holdBox).perform();
        String colorAfter = holdBox.getCssValue("background-color");
        String textAfter = holdBox.getText();

        assertNotEquals(colorBefore, colorAfter);
        assertNotEquals(textBefore, textAfter);

    }

    void hoverAndClick(WebElement element) {

        actions.moveToElement(element).perform();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Link 1")));
        driver.findElement(By.linkText("Link 1")).click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        assertEquals("Well done you clicked on the link!", alertText);
        alert.accept();

    }
}
