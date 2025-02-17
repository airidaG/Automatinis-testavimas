package testPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //links
    @FindBy(xpath = "//div[@class='no-account']/a")
    private WebElement linkNoAccount;
    //inputs
    @FindBy(xpath = "//input[@id='field-email']")
    private WebElement inputEmail;

    @FindBy(xpath = "//input[@id='field-password']")
    private WebElement inputPassword;
    //buttons
    @FindBy(xpath = "//button[@id='submit-login']")
    private WebElement buttonSignIn;

    public void clickNoAccount() {
        linkNoAccount.click();
    }

    public void clickSignIn() {
        buttonSignIn.click();
    }

    public void inputEmail(String email) {
        inputEmail.sendKeys(email);
    }

    public void inputPassword(String password) {
        inputPassword.sendKeys(password);
    }

}
