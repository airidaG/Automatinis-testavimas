package testPages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends BasePage {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[text()='Add To Cart']")
    private WebElement buttonAddToCart;

    @FindBy(xpath = "//a[@class='logotype']")
    private WebElement buttonLogo;

    @FindBy(xpath = "//select[@name='options[Size]']")
    private WebElement selectorForSize;

    private final int INDEX_TO_SELECT = 1;

    public void clickAddToCart() {
        buttonAddToCart.click();
    }

    public void clickLogo() {
        buttonLogo.click();
    }

    public void handleOptionsAndAdd() {

        if (checkIfOptionsDisplayed()) {
            Select selector = new Select(selectorForSize);
            selector.selectByIndex(INDEX_TO_SELECT);
            clickAddToCart();
        } else {
            buttonAddToCart.click();
        }
    }

    public boolean checkIfOptionsDisplayed() {
        try {
            return selectorForSize.isDisplayed() && selectorForSize.isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
