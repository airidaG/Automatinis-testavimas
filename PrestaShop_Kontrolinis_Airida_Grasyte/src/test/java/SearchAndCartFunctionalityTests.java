
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SearchAndCartFunctionalityTests extends BaseTest {

    private final String POPUP_MESSAGE = "You need to be logged in to save products in your wishlist.";
    private final String ITEM_ADDED_TO_CART_CONFIRMATION = "Product successfully added to your shopping cart";


    @ParameterizedTest
    @Tag("search and cart")
    @CsvFileSource(files = "src/test/resources/productData.csv", numLinesToSkip = 1)
    void addToCartTest(String item, double fullPrice, int discount, String size) {
        landingPage.inputSearch(item);
        assertItemWasFoundViaSearch(item);
        landingPage.selectProductFromList(item);
        productPage.clickAddToWishlist();
        checkIfCorrectPopUpMessageIsDisplayed(POPUP_MESSAGE);
        productPage.clickCancelAddToWishlist();
        productPage.selectSize(size);
        productPage.clickAddToCart();
        verifyConfirmationMessageAfterAddingItemToCart(ITEM_ADDED_TO_CART_CONFIRMATION);
        productPage.clickProceedToCheckout();
        verifyItemAddedToCart(item);
        verifyItemInCartCorrectSize(size);
        checkIfDisplayedDiscountIsCorrect(fullPrice, discount);
    }

    public void assertItemWasFoundViaSearch(String item) {
        assertThat(landingPage.isProductList(item))
                .withFailMessage("No matching products were  found when searching: " + item)
                .isTrue();
    }

    public void checkIfCorrectPopUpMessageIsDisplayed(String message) {
        assertThat(productPage.getAddToWishListPopupMessage())
                .withFailMessage("Message was supposed to be: " + message)
                .isEqualTo(message);
    }

    public void verifyConfirmationMessageAfterAddingItemToCart(String message) {
        assertThat(productPage.getModalTitle())
                .withFailMessage("Message was supposed to contain string: " + message)
                .contains(message);
    }

    public void verifyItemAddedToCart(String item) {
        assertThat(cartPage.isItemInCart(item))
                .withFailMessage("Item: " + item + "was not found in the cart")
                .isTrue();
    }

    public void verifyItemInCartCorrectSize(String size) {
        assertThat(cartPage.getProductSize())
                .withFailMessage("Size should be: " + size)
                .isEqualTo(size);
    }

    public void checkIfDisplayedDiscountIsCorrect(double fullPrice, int discount) {
        assertThat(cartPage.getDiscountText())
                .withFailMessage("Discount text was not correct")
                .isEqualTo(discount);

        double calculatedDiscount = Util.calculateDiscount(fullPrice, discount);
        assertThat(calculatedDiscount)
                .withFailMessage("Correct discount should be: " + calculatedDiscount)
                .isEqualTo(cartPage.getPriceAfterDiscount());
    }
}
