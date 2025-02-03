import java.util.Random;

public class Utility extends BaseTest {

    static String getRandomName(int howLong) {

        Random random = new Random();
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder randomName = new StringBuilder();
        for (int i = 0; i < howLong; i++) {
            int index = random.nextInt(letters.length());
            randomName.append(letters.charAt(index));
        }
        return randomName.toString();
    }

    public static void cleanup() {
        AdminPage adminPage = new AdminPage(driver);
        adminPage.navigateToAdminLink();
        adminPage.enterEmail();
        adminPage.enterPassword();
        adminPage.clickLogin();
        adminPage.clickCustomerDropdown();
        adminPage.clickCustomerLink();
        adminPage.selectAll();
        adminPage.clickDeleteDropdown();
        adminPage.clickDeleteBulkSection();
        adminPage.clickDelete();

    }
}
