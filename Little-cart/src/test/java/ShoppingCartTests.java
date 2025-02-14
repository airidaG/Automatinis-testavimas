import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ShoppingCartTests extends BaseTest {

    private int itemCounter = 0;

    @Test
    void addProductsToTheSoppingCartTest() throws IOException, InterruptedException {
        readData().forEach(line -> {
            landingPage.selectADuckByHeading(line);
            productPage.handleOptionsAndAdd();
            itemCounter++;
            assertEquals(String.valueOf(itemCounter), landingPage.getCartCounterValue(itemCounter));
            productPage.clickLogo();
        });
        landingPage.navigateToCart();
        readData().forEach(line -> checkoutPage.isItemInCart(line));

        Thread.sleep(2000);

        assertThat(checkoutPage.getDisplayedSubtotalNumber())
                .isEqualTo(checkoutPage.manuallyAddedSum());

        checkoutPage.removeAllItems();
    }

    public List<String> readData() throws IOException {
        Path filePath = Paths.get("src/test/resources/templateData.csv");
        List<String> lines = Files.lines(filePath)
                .toList();
        return lines;
    }

}
