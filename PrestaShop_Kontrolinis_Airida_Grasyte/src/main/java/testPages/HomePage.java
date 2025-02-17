package testPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    //links
    @FindBy(xpath = "//div[@class='user-info']/a[1]")
    private WebElement linkSignOut;

    @FindBy(css = "[title] .hidden-sm-down")
    private WebElement linkCustomerAccount;

    public void clickSignOut() {
        linkSignOut.click();
    }

    public String getCustomerAccountDisplayText() {
        return linkCustomerAccount.getText();
    }
}
