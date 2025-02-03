import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class Holder extends BaseTest {


    @Test
    void invalidFormatErrorTest() {

        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.linkText("No account? Create one here")).click();
        driver.findElement(By.id("field-firstname")).sendKeys("123");

        driver.findElement(By.id("field-lastname")).sendKeys("Doe");
        driver.findElement(By.id("field-email")).sendKeys("test@example.com");
        driver.findElement(By.id("field-password")).sendKeys("SecurePass123!");

        driver.findElement(By.name("psgdpr")).click();
        driver.findElement(By.name("customer_privacy")).click();
        driver.findElement(By.className("form-control-submit")).click();
        assertEquals("http://192.168.89.95/registration", driver.getCurrentUrl());
        assertEquals("Invalid format.", driver.findElement(By.cssSelector(".alert-danger")).getText());

    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/invalid_registration_data_with_error.csv", numLinesToSkip = 1)
    void invalidRegistrationTest(String firstName, String lastName, String email, String password, String error) {

        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.linkText("No account? Create one here")).click();
        driver.findElement(By.id("field-firstname")).sendKeys(firstName);

        driver.findElement(By.id("field-lastname")).sendKeys(lastName);
        driver.findElement(By.id("field-email")).sendKeys(email);
        driver.findElement(By.id("field-password")).sendKeys(password);

        driver.findElement(By.name("psgdpr")).click();
        driver.findElement(By.name("customer_privacy")).click();
        driver.findElement(By.className("form-control-submit")).click();
        assertEquals("http://192.168.89.95/registration", driver.getCurrentUrl(),
                "Should not have been able to register with this data: " + firstName + lastName + email + password + error);
        assertEquals(error, driver.findElement(By.cssSelector(".alert-danger")).getText(),
                "This error text does not match " + error);

    }


}


