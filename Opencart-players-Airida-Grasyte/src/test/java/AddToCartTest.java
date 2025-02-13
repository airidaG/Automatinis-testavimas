import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AddToCartTest extends BaseTest {

    private static final String SUCCESS_MESSAGE_TEMPLATE = "Success: You have added %s to your shopping cart!";
    private String quantity;
    private final String ITEM_TO_SEARCH = "touch";

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/testdataFile.csv", numLinesToSkip = 1)
    void addProductToCartTest(String productName) {
        landingPage.selectAllMp3();
        catalogPage.clickListview();
        catalogPage.enterASearch(productName);
        catalogPage.clickSearch();
        assertProductInCatalog(productName);
        catalogPage.selectItem(productName);
        quantity = Util.generateRandomNumber();
        productPage.enterQuantity(quantity);
        productPage.clickSubmit();
        assertSuccessMessage(productName);
        productPage.clickCart();
        assertItemInCart(productName);
    }

    @Test
    void searchAndAddSpecificProductTest() throws InterruptedException {

        landingPage.enterSearch(ITEM_TO_SEARCH);
        landingPage.clickSearch();
        catalogPage.isItemInList(ITEM_TO_SEARCH);
//        catalogPage.clickOne();
        catalogPage.addAllToCart();
    }

    private void assertProductInCatalog(String productName) {
        assertThat(catalogPage.isItemInList(productName))
                .withFailMessage(productName + "does not exist in the eshop")
                .isTrue();
    }

    private void assertSuccessMessage(String productName) {
        String expectedMessage = String.format(SUCCESS_MESSAGE_TEMPLATE, productName);

        assertThat(productPage.getAlert())
                .withFailMessage("Messages don't match")
                .isEqualTo(expectedMessage);
    }

    private void assertItemInCart(String productName) {
        double total = productPage.getItemPrice() * Integer.parseInt(quantity);

        assertThat(productPage.getItemsInCart(productName)
                .equals(productName.toLowerCase() + " x " + quantity + " $" + total + "0"))
                .withFailMessage("Item is not properly added to the cart")
                .isTrue();
    }
}
