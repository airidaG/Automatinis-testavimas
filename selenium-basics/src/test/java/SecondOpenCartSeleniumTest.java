import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SecondOpenCartSeleniumTest extends Base {

    @Test
    void secondOpencartTest() {

        driver.findElement(By.cssSelector("div#search > input[name='search']")).sendKeys("MaxBook");
        driver.findElement(By.cssSelector(".btn-light.btn-lg")).click();
        assertEquals("http://192.168.91.87/index.php?route=product/search&language=en-gb&search=MaxBook", driver.getCurrentUrl());

        driver.findElement(By.id("button-search")).isDisplayed();
        WebElement search = driver.findElement(By.id("input-search"));

        search.clear();
        search.sendKeys("Macbook");
        driver.findElement(By.id("button-search")).click();

        List<WebElement> foundItems = driver.findElements(By.className("product-thumb"));
        System.out.println("Total found: " + foundItems.size());

        Select dropdownSort = new Select(driver.findElement(By.id("input-sort")));
        dropdownSort.selectByIndex(3);

        dropdownSort = new Select(driver.findElement(By.id("input-sort")));
        String selectedOption = dropdownSort.getFirstSelectedOption().getText();
        System.out.println("Selected option: " + selectedOption);
    }
}
