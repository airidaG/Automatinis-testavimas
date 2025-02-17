package testPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    //text elements
    @FindBy(xpath = "//div[@class='product-line-info']")
    private List<WebElement> productTitle;

    @FindBy(xpath = "//div[@class='product-line-info size']/span[@class='value']")
    private WebElement productSize;

    @FindBy(xpath = "//span[@class='discount discount-percentage']")
    private WebElement discountText;

    @FindBy(xpath = "//span[@class='price']")
    private WebElement priceAfterDiscount;

    public boolean isItemInCart(String itemName) {
        return productTitle.stream()
                .anyMatch(product -> product.getText().equalsIgnoreCase(itemName));
    }

    public String getProductSize() {
        return productSize.getText();
    }

    public int getDiscountText() {
        return Integer.parseInt(discountText.getText()
                .replace("-", "")
                .replace("%", ""));
    }

    public double getPriceAfterDiscount() {
        String priceAfterDiscountText = priceAfterDiscount.getText()
                .replace("$", "");
        return Double.parseDouble(priceAfterDiscountText);
    }
}
