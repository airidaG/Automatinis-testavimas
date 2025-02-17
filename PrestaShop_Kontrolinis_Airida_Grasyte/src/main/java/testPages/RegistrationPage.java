package testPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    //inputs
    @FindBy(xpath = "//input[@id='field-firstname']")
    private WebElement inputFirstName;

    @FindBy(xpath = "//input[@id='field-lastname']")
    private WebElement inputLastName;

    @FindBy(xpath = "//input[@id='field-email']")
    private WebElement inputEmail;

    @FindBy(xpath = "//input[@id='field-password']")
    private WebElement inputPassword;
    //checkboxes
    @FindBy(xpath = "//input[@name='psgdpr']")
    private WebElement checkboxTermsAndConditions;

    @FindBy(xpath = "//input[@name='customer_privacy']")
    private WebElement checkboxCustomerPrivacy;
    //buttons
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement buttonSave;

    public void enterFirstName(String firstName) {
        inputFirstName.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        inputLastName.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        inputEmail.sendKeys(email);
    }

    public void enterPassword(String password) {
        inputPassword.sendKeys(password);
    }

    public void selectTermsAndConditions() {
        checkboxTermsAndConditions.click();
    }

    public void selectCustomerPrivacy() {
        checkboxCustomerPrivacy.click();
    }

    public void clickSave() {
        buttonSave.click();
    }

}
