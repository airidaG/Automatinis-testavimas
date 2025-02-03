import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LandingPage extends BasePage {

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Sign in")
    private WebElement signInLink;


    public void navigateToSignInPage() {
        signInLink.click();
    }


}
