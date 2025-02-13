package testingPages;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductPage extends BasePage {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@name='quantity']")
    private WebElement inputQuantity;

    @FindBy(xpath = "//button[@id='button-cart']")
    private WebElement buttonSubmit;

    @FindBy(css = ".btn-inverse")
    private WebElement buttonItemCart;

    @FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
    private WebElement alertSuccess;

    @FindBy(xpath = "//span[@class='price-new']")
    private WebElement itemPrice;

    @FindBy(xpath = "//table[@class='table table-striped mb-2']//tr")
    List<WebElement> itemsInCart;

    public void enterQuantity(String quantity) {
        inputQuantity.clear();
        inputQuantity.sendKeys(quantity);
    }

    public void clickSubmit() {
        buttonSubmit.click();
    }

    public void clickCart() {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        wait.until(ExpectedConditions.elementToBeClickable(buttonItemCart)).click();
    }

    public String getAlert() {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        return wait.until(ExpectedConditions.elementToBeClickable(alertSuccess)).getText();
    }

    public String getItemsInCart(String product) {
        String itemText = itemsInCart.stream()
                .map(WebElement::getText)
                .map(String::toLowerCase)
                .filter(item -> item.contains(product.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No item with title '" + product + "' found"));
        return itemText.replace(",", "");
    }

    public double getItemPrice() {
        String priceText = itemPrice.getText();
        String priceNum = priceText.replace("$", "");

        return Double.parseDouble(priceNum);
    }
}
