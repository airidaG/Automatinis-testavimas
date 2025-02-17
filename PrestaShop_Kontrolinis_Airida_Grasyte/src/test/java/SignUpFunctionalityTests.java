import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignUpFunctionalityTests extends BaseTest {

    private String displayName = testData.getFirstName() + " " + testData.getLastName();
    private final String HOME_PAGE_URL = "http://192.168.89.95/";
    private final String REGISTRATION_PAGE_URL = "http://192.168.89.95/registration";

    @Test
    @Tag("registration and login")
    void signUpWithValidCredentialsTest() {
        landingPage.clickSignIn();
        loginPage.clickNoAccount();
        registrationPage.enterFirstName(testData.getFirstName());
        registrationPage.enterLastName(testData.getLastName());
        registrationPage.enterEmail(testData.getEmail());
        registrationPage.enterPassword(testData.getPassword());
        registrationPage.selectTermsAndConditions();
        registrationPage.selectCustomerPrivacy();
        registrationPage.clickSave();
        assertUserIsLoggedIn();
        homePage.clickSignOut();
        landingPage.clickSignIn();
        loginPage.inputEmail(testData.getEmail());
        loginPage.inputPassword(testData.getPassword());
        loginPage.clickSignIn();
        assertUserIsLoggedIn();
    }

    @ParameterizedTest
    @Tag("registration and login")
    @CsvFileSource(files = "src/test/resources/invalidRegistrationCredentials.csv", numLinesToSkip = 1)
    void invalidCredentialsRegistrationTest(String firstName, String lastName, String email, String password) {
        landingPage.clickSignIn();
        loginPage.clickNoAccount();
        registrationPage.enterFirstName(firstName);
        registrationPage.enterLastName(lastName);
        registrationPage.enterEmail(email);
        registrationPage.enterPassword(password);
        registrationPage.selectTermsAndConditions();
        registrationPage.selectCustomerPrivacy();
        registrationPage.clickSave();
        verifyUnsuccessfulRegistration(email);
    }

    @Test
    @Tag("registration and login")
    void emptyInputsRegistrationTest() {
        landingPage.clickSignIn();
        loginPage.clickNoAccount();
        registrationPage.selectTermsAndConditions();
        registrationPage.selectCustomerPrivacy();
        registrationPage.clickSave();

        assertEquals(REGISTRATION_PAGE_URL, driver.getCurrentUrl(),
                "Was redirected to a different page: " + driver.getCurrentUrl());
    }

    private void assertUserIsLoggedIn() {
        assertThat(HOME_PAGE_URL)
                .withFailMessage("URL's don't match")
                .isEqualTo(driver.getCurrentUrl());
        assertThat(homePage.getCustomerAccountDisplayText())
                .withFailMessage("Display name is incorrect")
                .isEqualTo(displayName);
    }

    private void verifyUnsuccessfulRegistration(String email) {
        assertEquals(REGISTRATION_PAGE_URL, driver.getCurrentUrl(),
                "Able to register with this email: " + email);
    }

}
