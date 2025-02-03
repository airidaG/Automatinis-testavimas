import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationTests extends BaseTest {


    @Test
    void standardUserRegistrationTest() {

        landingPage.navigateToSignInPage();
        signInPage.navigateToRegistrationPage();

        registrationPage.selectGender();
        TestData.firstName = Utility.getRandomName(8);
        registrationPage.enterFirstName(TestData.firstName);
        TestData.lastName = Utility.getRandomName(6);
        registrationPage.enterLastName(TestData.lastName);
        TestData.email = TestData.firstName + "@gmail.com";
        registrationPage.enterEmail(TestData.email);
        registrationPage.enterPassword();
        registrationPage.enterBirthDate("01/08/1998");

        registrationPage.selectTermsAndConditions();
        registrationPage.selectCustomerPrivacy();
        registrationPage.clickSave();

    }

    @Test
    void displayNameTest() {
        register();
        assertEquals("http://192.168.89.95/", driver.getCurrentUrl());
        assertEquals((TestData.firstName + " " + TestData.lastName), registrationPage.getDisplayName());

    }

    @Test
    void personalDataTest() {
        register();
        homePage.signOut();
        landingPage.navigateToSignInPage();

        signInPage.enterEmail(TestData.email);
        signInPage.enterPassword(registrationPage.getPassword());
        signInPage.clickLogin();

        homePage.navigateToInformation();

        assertEquals(TestData.email, homePage.getEmailInfo());
        assertEquals(TestData.firstName, homePage.getFirsNameInfo());
        assertEquals(TestData.lastName, homePage.getLastNameInfo());

    }

    public void register() {

        landingPage.navigateToSignInPage();
        signInPage.navigateToRegistrationPage();

        registrationPage.selectGender();
        TestData.firstName = Utility.getRandomName(8);
        registrationPage.enterFirstName(TestData.firstName);
        TestData.lastName = Utility.getRandomName(6);
        registrationPage.enterLastName(TestData.lastName);
        TestData.email = TestData.firstName + "@gmail.com";
        registrationPage.enterEmail(TestData.email);
        registrationPage.enterPassword();
        registrationPage.enterBirthDate("01/08/1998");

        registrationPage.selectTermsAndConditions();
        registrationPage.selectCustomerPrivacy();
        registrationPage.clickSave();
    }

    @Test
    void emptyInputsRegistrationTest() {

        landingPage.navigateToSignInPage();
        signInPage.navigateToRegistrationPage();

        registrationPage.selectTermsAndConditions();
        registrationPage.selectCustomerPrivacy();
        registrationPage.clickSave();

        assertEquals("http://192.168.89.95/registration", driver.getCurrentUrl());
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/invalid_registration_data.csv", numLinesToSkip = 1)
    void invalidRegistrationTest(String firstName, String lastName, String email, String password) {

        landingPage.navigateToSignInPage();
        signInPage.navigateToRegistrationPage();

        registrationPage.enterFirstName(firstName);
        registrationPage.enterLastName(lastName);
        registrationPage.enterEmail(email);
        registrationPage.enterGivenPassword(password);

        registrationPage.selectTermsAndConditions();
        registrationPage.selectCustomerPrivacy();
        registrationPage.clickSave();

        assertEquals("http://192.168.89.95/registration", driver.getCurrentUrl(),
                "Test fails with this email: " + email);

    }


}
