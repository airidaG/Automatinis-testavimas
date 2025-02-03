
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class RegistrationPage extends BasePage {

    @FindBy(id = "field-firstname")
    private WebElement firstNameInput;

    @FindBy(id = "field-lastname")
    private WebElement lastNameInput;

    @FindBy(id = "field-email")
    private WebElement emailInput;

    @FindBy(id = "field-password")
    private WebElement passwordInput;

    @FindBy(id = "field-birthday")
    private WebElement birthDateInput;

    @FindBy(name = "psgdpr")
    private WebElement termsAndConditionsBox;

    @FindBy(name = "customer_privacy")
    private WebElement customerPrivacyBox;

    @FindBy(className = "form-control-submit")
    private WebElement saveButton;

    @FindBy(id = "field-id_gender-1")
    private WebElement genderMale;

    @FindBy(id = "field-id_gender-2")
    private WebElement genderFemale;

    @FindBy(css = "[title] .hidden-sm-down")
    private WebElement displayName;

    String password;


    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    WebElement getRandomGender() {
        List<WebElement> genders = Arrays.asList(genderFemale, genderMale);
        return genders.get(random.nextInt(genders.size()));
    }

    public void selectGender() {
        getRandomGender().click();
    }

    public void enterFirstName(String firstName) {
        firstNameInput.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameInput.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void enterPassword() {
        String randomPassword = UUID.randomUUID().toString().substring(0, 12);
        setPassword(randomPassword);
        passwordInput.sendKeys(randomPassword);
    }

    public void enterGivenPassword(String password) {
        setPassword(password);
        passwordInput.sendKeys(password);
    }

    public void enterBirthDate(String birthDate) {
        birthDateInput.sendKeys(birthDate);
    }

    public void selectTermsAndConditions() {
        termsAndConditionsBox.click();
    }

    public void selectCustomerPrivacy() {
        customerPrivacyBox.click();
    }

    public void clickSave() {
        saveButton.click();
    }

    public String getDisplayName() {
        return displayName.getText();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
