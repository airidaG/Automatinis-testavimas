import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.*;


public class LoginTest extends ParentTestClass {


    @Test
    void loginTest() {

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
        assertTrue(driver.findElement(By.cssSelector(".shopping_cart_link")).isDisplayed());
    }

    @Test
    void invalidLoginTest() {

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("wrong_password");
        driver.findElement(By.id("login-button")).click();

        String resultText = driver.findElement(By.cssSelector(".error-message-container")).getText();
        assertEquals("Epic sadface: Username and password do not match any user in this service", resultText);

    }

    @Test
    void lockedOutUserTest() {

        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        String resultText = driver.findElement(By.cssSelector(".error-message-container")).getText();
        assertTrue(resultText.contains("this user has been locked out"));
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/logins.csv", numLinesToSkip = 1)
    void fromCsvFileTest(String username, String password) {

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

    }

    @Test
    void timeoutTest() {

        assertTimeout(ofSeconds(2), () -> {
            driver.findElement(By.id("user-name")).sendKeys("performance_glitch_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();
        });
    }


}
