import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPageTest extends Base {


    @Test
    void LoginTest() {

        WebElement usernameInput = driver.findElement(By.cssSelector("input[name='username']"));
        WebElement passwordInput = driver.findElement(By.cssSelector("input[name='password']"));


        String username = driver.findElement(By.xpath(
                        "//div[@id='app']/div[@class='orangehrm-login-layout']/div[@class='orangehrm-login-layout-blob']/div[@class='orangehrm-login-container']//div[@class='orangehrm-login-error']/div/p[1]"))
                .getText();
        String password = driver.findElement(By.cssSelector(".orangehrm-demo-credentials.oxd-sheet.oxd-sheet--gray-lighten-2.oxd-sheet--gutters.oxd-sheet--rounded > p:nth-of-type(2)"))
                .getText();

        String[] test = username.split(": ");
        String[] test2 = password.split(": ");

        usernameInput.sendKeys(test[1]);
        passwordInput.sendKeys(test2[1]);

        System.out.println(test[1]);
        System.out.println(test2[1]);
        driver.findElement(By.cssSelector(".orangehrm-login-button")).click();

        assertEquals("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index", driver.getCurrentUrl());
    }
}
