import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OpenCartSeleniumTest extends Base {

    @Test
    void opencartTest() {

        driver.findElement(By.cssSelector("[title='Wish List \\(0\\)'] .d-md-inline")).click();
        assertEquals("http://192.168.91.87/en-gb?route=account/login", driver.getCurrentUrl());

        List<WebElement> searchBoxes = driver.findElements(By.cssSelector("div#search > input[name='search']"));
        System.out.println(searchBoxes.size());

        WebElement inputEmail = driver.findElement(By.id("input-email"));
        inputEmail.sendKeys("email@email.com");
        inputEmail.clear();

        driver.findElement(By.cssSelector("[action] .btn-primary")).click();

        driver.findElement(By.linkText("Continue")).click();

        boolean inputPasswordEnabled = driver.findElement(By.id("input-password")).isEnabled();
        System.out.println("Password is enabled: " + inputPasswordEnabled);

        boolean elementDisplayed = driver.findElement(By.className("btn-inverse")).isDisplayed();
        System.out.println("Element is displayed: " + elementDisplayed);

        boolean agreeCheckbox = driver.findElement(By.className("form-check-input")).isSelected();
        System.out.println("Agree checkbox is selected: " + agreeCheckbox);

//Go to top menu -> Desktops
        WebElement menuDesktop = driver.findElement(By.linkText("Desktops"));
        Actions action = new Actions(driver);
        action.moveToElement(menuDesktop).build().perform();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Show All Desktops")));
        driver.findElement(By.linkText("Show All Desktops")).click();
        assertEquals("http://192.168.91.87/en-gb/catalog/desktops", driver.getCurrentUrl());

//Select to show 25 items per page
        Select dropdownShowLimit = new Select(driver.findElement(By.id("input-limit")));
        dropdownShowLimit.selectByIndex(1);

//Print selected option from the dropbox
        dropdownShowLimit = new Select(driver.findElement(By.id("input-limit")));//reikia is naujo paselectint
        String selectedOption25 = dropdownShowLimit.getFirstSelectedOption().getText();
        System.out.println("Show items: " + selectedOption25);

//Select 4th option in the show items per page dropdown
        dropdownShowLimit.selectByIndex(3);

//Print selected option
        dropdownShowLimit = new Select(driver.findElement(By.id("input-limit")));
        String selectedOption4th = dropdownShowLimit.getFirstSelectedOption().getText();
        System.out.println("Show items: " + selectedOption4th);

    }
}
