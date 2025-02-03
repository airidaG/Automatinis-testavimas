import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends BasePage {


    public SignInPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "No account? Create one here")
    private WebElement registrationLink;

    @FindBy(id = "field-email")
    private WebElement emailInput;

    @FindBy(id = "field-password")
    private WebElement passwordInput;

    @FindBy(id = "submit-login")
    private WebElement loginButton;

    public void navigateToRegistrationPage() {
        registrationLink.click();
    }

    public void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void clickLogin() {
        loginButton.click();
    }

}
