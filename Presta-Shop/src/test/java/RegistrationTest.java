import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationTest extends Base {

    @Test
    void cleanup() {

        driver.get("http://192.168.89.95/administration/");
        driver.findElement(By.id("email")).sendKeys("user@example.com");
        driver.findElement(By.id("passwd")).sendKeys("I4ygfj6.at4,");
        driver.findElement(By.id("submit_login")).click();
        driver.findElement(By.id("subtab-AdminParentCustomer")).click();
        driver.findElement(By.id("collapse-24")).click();
        driver.findElement(By.xpath("/html//li[@id='subtab-AdminCustomers']")).click();
        driver.findElement(By.cssSelector(".column-filters > td:nth-of-type(1) > .md-checkbox")).click();
        driver.findElement(By.cssSelector(".btn.btn-outline-secondary.dropdown-toggle.js-bulk-actions-btn")).click();
        driver.findElement(By.cssSelector("button#customer_grid_bulk_action_delete_selection")).click();
        driver.findElement(By.cssSelector(".js-submit-delete-customers")).click();
    }

    @Test
    void signupTest() {

        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.linkText("No account? Create one here")).click();

        getRandomGender().click();

        String randomFirstname = getRandomName(7);
        WebElement firstNameInput = driver.findElement(By.id("field-firstname"));
        firstNameInput.sendKeys(randomFirstname);

        String randomLastname = getRandomName(9);
        WebElement lastNameInput = driver.findElement(By.id("field-lastname"));
        lastNameInput.sendKeys(randomLastname);
//--------------
        String email = randomFirstname + "@gmail.com";
        WebElement emailInput = driver.findElement(By.id("field-email"));
        emailInput.sendKeys(email);
//-------------
        WebElement passwordInput = driver.findElement(By.id("field-password"));
        String randomPassword = UUID.randomUUID().toString().substring(0, 12);
        passwordInput.sendKeys(randomPassword);
//--------------
        driver.findElement(By.id("field-birthday")).sendKeys("01/08/1998");

        driver.findElement(By.name("psgdpr")).click();
        driver.findElement(By.name("customer_privacy")).click();

        driver.findElement(By.className("form-control-submit")).click();

        assertEquals("http://192.168.89.95/", driver.getCurrentUrl());

        String displayedName = driver.findElement(By.cssSelector("[title] .hidden-sm-down")).getText();
        assertEquals((randomFirstname + " " + randomLastname), displayedName);

        driver.findElement(By.linkText("Sign out")).click();
        driver.findElement(By.cssSelector(".user-info .hidden-sm-down")).click();

        driver.findElement(By.id("field-email")).sendKeys(email);

        driver.findElement(By.id("field-password")).sendKeys(randomPassword);

        driver.findElement(By.id("submit-login")).click();
        driver.findElement(By.cssSelector("[title] .hidden-sm-down")).click();
        driver.findElement(By.linkText("Information")).click();

        String actualEmail = driver.findElement(By.id("field-email")).getDomAttribute("value");
        String actualFirstName = driver.findElement(By.id("field-firstname")).getDomAttribute("value");
        String actualLastName = driver.findElement(By.id("field-lastname")).getDomAttribute("value");

        //or
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        String actualFirstName = (String) js.executeScript("return document.getElementById('field-firstname').value;");
//        String actualLastName = (String) js.executeScript("return document.getElementById('field-lastname').value;");
//        String actualEmail = (String) js.executeScript("return document.getElementById('field-email').value;");
        assertEquals(randomFirstname, actualFirstName);
        assertEquals(randomLastname, actualLastName);
        assertEquals(email, actualEmail);
    }

    @Test
    void allEmptyFieldsSignupTest() {

        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.linkText("No account? Create one here")).click();

        driver.findElement(By.name("psgdpr")).click();
        driver.findElement(By.name("customer_privacy")).click();
        driver.findElement(By.className("form-control-submit")).click();
        assertEquals("http://192.168.89.95/registration", driver.getCurrentUrl());
    }

    @Test
    void invalidPasswordTest() {

        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.linkText("No account? Create one here")).click();

        String randomFirstname = getRandomName(8);
        driver.findElement(By.id("field-firstname")).sendKeys(randomFirstname);

        driver.findElement(By.id("field-lastname")).sendKeys(getRandomName(7));

        String randomPassword = UUID.randomUUID().toString().substring(0, 12);
        driver.findElement(By.id("field-password")).sendKeys(randomPassword);

        String email = randomFirstname + "gmail.com";
        driver.findElement(By.id("field-email")).sendKeys(email);

        driver.findElement(By.name("psgdpr")).click();
        driver.findElement(By.name("customer_privacy")).click();
        driver.findElement(By.className("form-control-submit")).click();
        assertEquals("http://192.168.89.95/registration", driver.getCurrentUrl());

    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/invalid_registration_data.csv", numLinesToSkip = 1)
    void invalidRegistrationTest(String firstName, String lastName, String email, String password) {

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
                "Test fails with this email: " + email);

        //-----
//        WebElement errorMessageElement = driver.findElement(By.cssSelector(".alert-danger"));
//        if (driver.findElement(By.cssSelector(".alert-danger")).isDisplayed()) {
//            String actualMessage = errorMessageElement.getText();
//            String expectedMessage = "The email is already used, please choose another one or sign in";
//            assertNotEquals(expectedMessage, actualMessage);
//        }
    }

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

    WebElement getRandomGender() {

        List<WebElement> genders = Arrays.asList(driver.findElement(By.id("field-id_gender-2")),
                driver.findElement(By.id("field-id_gender-1")));
        return genders.get(random.nextInt(genders.size()));
    }

    String getRandomName(int howLong) {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder randomName = new StringBuilder();
        for (int i = 0; i < howLong; i++) {
            int index = random.nextInt(letters.length());
            randomName.append(letters.charAt(index));
        }
        return randomName.toString();
    }
}
