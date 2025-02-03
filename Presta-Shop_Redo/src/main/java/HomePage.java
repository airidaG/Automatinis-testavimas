import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Sign out")
    private WebElement signOutLink;

    @FindBy(linkText = "Information")
    private WebElement informationLink;

    @FindBy(id = "field-email")
    private WebElement emailInfo;

    @FindBy(id = "field-firstname")
    private WebElement firstNameInfo;

    @FindBy(id = "field-lastname")
    private WebElement lastNameInfo;


    public void signOut() {
        signOutLink.click();
    }

    public void navigateToInformation() {
        informationLink.click();
    }

    public String getFirsNameInfo() {
        return firstNameInfo.getDomAttribute("value");
    }

    public String getEmailInfo() {
        return emailInfo.getDomAttribute("value");
    }

    public String getLastNameInfo() {
        return lastNameInfo.getDomAttribute("value");
    }
}
