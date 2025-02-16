import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ShoppingCartTests extends BaseTest {

    private int itemCounter = 0;
    private final String EMPTY_CART_TEXT = "There are no items in your cart.";

    @Test
    void addAndRemoveProductsToTheSoppingCartTest(){

        enterAndVerifyAdditionOfMultipleItemsToCart();
        landingPage.navigateToCart();
        testData.forEach(line -> checkoutPage.isItemInCart(line));
        checkoutPage.handleLoader();
        assertThat(checkoutPage.getDisplayedSubtotalNumber())
                .isEqualTo(checkoutPage.getManuallyAddedCartSum());

        checkoutPage.removeAllItemsFromCart();
        assertCartIsEmpty();
    }


    private void enterAndVerifyAdditionOfMultipleItemsToCart() {
            testData.forEach(line -> {
            landingPage.selectADuckByHeading(line);
            productPage.handleOptionsAndAddToCart();
            itemCounter++;
            assertEquals(String.valueOf(itemCounter), landingPage.getCartCounterValue(itemCounter));
            productPage.clickLogo();
        });
    }

    private void assertCartIsEmpty(){
        assertThat(checkoutPage.isItemCartEmpty())
                .withFailMessage("Item cart is not empty.")
                .isTrue();

        assertThat(EMPTY_CART_TEXT)
                .withFailMessage("Text does not match")
                .isEqualTo(checkoutPage.getEmptyCartText());
    }

}
