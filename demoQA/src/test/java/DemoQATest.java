import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DemoQATest extends Base {

    @Test
    void fillForm() {

        String firstName = "Vardas";
        driver.findElement(By.id("firstName")).sendKeys(firstName);

        String lastName = "Pavarde";
        driver.findElement(By.id("lastName")).sendKeys(lastName);

        String email = "test@ecample.com";
        driver.findElement(By.id("userEmail")).sendKeys(email);

        driver.findElement(By.cssSelector("div#genterWrapper > .col-md-9.col-sm-12 > div:nth-of-type(2) > label")).click();

        String phoneNumber = "1231231234";
        driver.findElement(By.id("userNumber")).sendKeys(phoneNumber);

// Select date from calendar
        driver.findElement(By.id("dateOfBirthInput")).click();

        Select birthDateMonthInput = new Select(driver.findElement(By.cssSelector(".react-datepicker__month-select")));
        birthDateMonthInput.selectByIndex(2);

        Select birthDateYearInput = new Select(driver.findElement(By.cssSelector(".react-datepicker__year-select")));
        birthDateYearInput.selectByValue("1998");

        driver.findElement(By.className("react-datepicker__day--008")).click();


//****
        WebElement subjectInput = driver.findElement(By.id("subjectsInput"));
        subjectInput.click();
        subjectInput.sendKeys("Maths");
        subjectInput.sendKeys(Keys.ENTER);


        driver.findElement(By.cssSelector("div#hobbiesWrapper > .col-md-9.col-sm-12 > div:nth-of-type(2) > label")).click();
        driver.findElement(By.cssSelector("div#hobbiesWrapper > .col-md-9.col-sm-12 > div:nth-of-type(3) > label")).click();

        driver.findElement(By.id("uploadPicture")).sendKeys("C:\\Users\\D3PT\\Desktop\\automatinis_testavimas\\demoQA\\src\\test\\resources\\hello.txt");

        driver.findElement(By.id("currentAddress")).sendKeys("Vilnius, Lithuania");

        WebElement state = driver.findElement(By.cssSelector("div#state .css-1wa3eu0-placeholder"));

        Actions action = new Actions(driver);
        action.moveToElement(state).click().sendKeys("u").sendKeys(Keys.ENTER).perform();

        WebElement city = driver.findElement(By.cssSelector("div#city .css-1g6gooi"));

        action.moveToElement(city).click().sendKeys("a").sendKeys(Keys.ENTER).perform();

        driver.findElement(By.id("submit")).click();


        String resultName = driver.findElement(By.cssSelector("tr:nth-of-type(1) > td:nth-of-type(2)")).getText();
        assertEquals(firstName + " " + lastName, resultName);

        String resultEmail = driver.findElement(By.cssSelector("tr:nth-of-type(2) > td:nth-of-type(2)")).getText();
        assertEquals(email, resultEmail);

        String resultGender = driver.findElement(By.cssSelector("tr:nth-of-type(3) > td:nth-of-type(2)")).getText();
        assertEquals("Female", resultGender);

        String resultPhoneNumber = driver.findElement(By.cssSelector("tr:nth-of-type(4) > td:nth-of-type(2)")).getText();
        assertEquals(phoneNumber, resultPhoneNumber);

        String resultBirthday = driver.findElement(By.cssSelector("tr:nth-of-type(5) > td:nth-of-type(2)")).getText();
        assertEquals("08 March,1998", resultBirthday);

        String subjectResult = driver.findElement(By.cssSelector("tr:nth-of-type(6) > td:nth-of-type(2)")).getText();
        assertEquals("Maths", subjectResult);

        String resultHobbies = driver.findElement(By.cssSelector("tr:nth-of-type(7) > td:nth-of-type(2)")).getText();
        assertEquals("Reading, Music", resultHobbies);

        String resultPicture = driver.findElement(By.cssSelector("tr:nth-of-type(8) > td:nth-of-type(2)")).getText();
        assertEquals("hello.txt", resultPicture);

        String resultAddress = driver.findElement(By.cssSelector("tr:nth-of-type(9) > td:nth-of-type(2)")).getText();
        assertEquals("Vilnius, Lithuania", resultAddress);

        String resultStateAndCity = driver.findElement(By.cssSelector("tr:nth-of-type(10) > td:nth-of-type(2)")).getText();
        assertEquals("Uttar Pradesh Agra", resultStateAndCity);
    }
}
