import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AddToCartTest extends BaseTest {

    private final String PRODUCT_NAME = "iPod Shuffle";

    @Test
    void addToCartTest() throws InterruptedException {
        landingPage.selectAllMp3();
        catalogPage.clickListview();
        catalogPage.enterASearch(PRODUCT_NAME);
        catalogPage.clickSearch();

        assertThat(catalogPage.isItemInList(PRODUCT_NAME)).isTrue();

        catalogPage.selectItem(PRODUCT_NAME);

        String num = Util.generateRandomNumber();
        productPage.enterQuantity(num);
        productPage.clickSubmit();

        assertThat(productPage.getAlert()).isEqualTo("Success: You have added " + PRODUCT_NAME + " to your shopping cart!");


    }

}
