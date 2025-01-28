import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {


    @Test
    void loginTest(){

        WebDriver driver = new ChromeDriver();
        driver.get("https://practicetestautomation.com/practice-test-login/");

        WebElement inputUsername = driver.findElement(By.id("username"));
        inputUsername.sendKeys("student"); //sukurti atskita kintamaji jei veliau naudosim

        driver.findElement(By.id("password")).sendKeys("Password123");
        driver.findElement(By.id("submit")).click();

        String result = driver.findElement(By.className("post-title")).getText();//getCssValue, getTagName

        System.out.println(result);
        String expected = "Logged In Successfully";

        assertEquals(expected, result);

        System.out.println(result.equals(expected) ? "Success" : "Fail");

        //Same
//        if(result.equals("Logged In Successfully")){
//            System.out.println("Test success");
//        }else {
//            System.out.println("Test failed");
//        }




    }
}
