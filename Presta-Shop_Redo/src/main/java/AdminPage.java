import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminPage extends BasePage {
    public AdminPage(WebDriver driver) {
        super(driver);
    }

    private final String adminEmail = "user@example.com";
    private final String adminPassword = "I4ygfj6.at4,";

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "passwd")
    private WebElement passwordInput;

    @FindBy(id = "submit_login")
    private WebElement loginButton;

    @FindBy(id = "subtab-AdminParentCustomer")
    private WebElement customerDropdown;

    @FindBy(xpath = "/html//li[@id='subtab-AdminCustomers']")
    private WebElement customerLink;

    @FindBy(css = ".column-filters > td:nth-of-type(1) > .md-checkbox")
    private WebElement selectAllBox;

    @FindBy(css = ".btn.btn-outline-secondary.dropdown-toggle.js-bulk-actions-btn")
    private WebElement deleteDropdown;

    @FindBy(css = "button#customer_grid_bulk_action_delete_selection")
    private WebElement deleteBulkSection;

    @FindBy(css = ".js-submit-delete-customers")
    private WebElement deleteButton;

    void navigateToAdminLink() {
        driver.get("http://192.168.89.95/administration/");
    }

    void enterEmail() {
        emailInput.sendKeys(adminEmail);
    }

    void enterPassword() {
        passwordInput.sendKeys(adminPassword);
    }

    void clickLogin() {
        loginButton.click();
    }

    void clickCustomerDropdown() {
        customerDropdown.click();
    }

    void clickCustomerLink() {
        customerLink.click();
    }

    void selectAll() {
        selectAllBox.click();
    }

    void clickDeleteDropdown() {
        deleteDropdown.click();
    }

    void clickDeleteBulkSection() {
        deleteBulkSection.click();
    }

    void clickDelete() {
        deleteButton.click();
    }

}
