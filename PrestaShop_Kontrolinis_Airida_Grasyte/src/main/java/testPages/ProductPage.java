package testPages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class ProductPage extends BasePage {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    //buttons
    @FindBy(xpath = "//button[@class='wishlist-button-add wishlist-button-product']")
    private WebElement buttonAddToWishlist;

    @FindBy(css = ".show .btn-secondary")
    private WebElement buttonCancelAddToWishlist;

    @FindBy(xpath = "//button[@class='btn btn-primary add-to-cart']")
    private WebElement buttonAddToCart;

    @FindBy(xpath = "//a[text()='Proceed to checkout']")
    private WebElement buttonProceedToCheckout;

    //text elements
    @FindBy(xpath = "//p[@class='modal-text']")
    private WebElement addToWishListPopupMessage;

    @FindBy(xpath = "//span[contains(text(), 'Size')]")
    private WebElement sizeOptionText;

    @FindBy(xpath = "//h4[@id='myModalLabel']")
    private WebElement modalTitle;

    //select
    @FindBy(xpath = "//select[@aria-label='Size']")
    private WebElement selectorSize;

    public void clickAddToWishlist() {
        buttonAddToWishlist.click();
    }

    public void clickCancelAddToWishlist() {
        buttonCancelAddToWishlist.click();
    }

    public void clickAddToCart() {
        buttonAddToCart.click();
    }

    public void clickProceedToCheckout() {
        waitForCheckoutPopup();
        buttonProceedToCheckout.click();
    }

    public void selectSize(String size) {
        Select select = new Select(selectorSize);
        select.selectByVisibleText(size);
    }

    public String getAddToWishListPopupMessage() {
        return addToWishListPopupMessage.getText();
    }

    public String getModalTitle() {
        waitForCheckoutPopup();
        return modalTitle.getText();
    }

    private void waitForCheckoutPopup() {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        wait.until(ExpectedConditions.visibilityOf(buttonProceedToCheckout));
    }
}
