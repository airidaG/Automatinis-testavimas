package testPages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class CheckoutPage extends BasePage {
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='name']/a")
    private List<WebElement> itemsInCart;

    @FindBy(xpath = "//div[@class='col-sm-2']/div")
    private List<WebElement> itemInCartPrices;

    @FindBy(xpath = "//button[@name='remove_cart_item']")
    private List<WebElement> buttonsRemoveItems;

    @FindBy(xpath = "//strong[@class='formatted-value']")
    private WebElement subtotal;

    @FindBy(xpath = "//p[@class='text-center']")
    private WebElement textElementEmptyCart;

    @FindBy(xpath = "//div[@class='loader']")
    private WebElement loader;

    public boolean isItemInCart(String item) {
        return itemsInCart.stream()
                .map(duck -> duck.getText().toLowerCase())
                .filter(duck -> duck.equals(item.toLowerCase()))
                .allMatch(duck -> duck.equals(item.toLowerCase()));
    }

    public boolean isItemCartEmpty(){
        return itemsInCart.isEmpty();
    }

    public double getManuallyAddedCartSum() {
        return itemInCartPrices.stream()
                .map(price -> price.getText().replace("$", "").trim())
                .mapToDouble(Double::parseDouble)
                .sum();
    }

    public double getDisplayedSubtotalNumber() {
        String textValue = subtotal.getText().replace("$", "");
        return Double.parseDouble(textValue);
    }

    public String getEmptyCartText(){
      return  textElementEmptyCart.getText();
    }

    public void removeAllItemsFromCart() {
        while(!buttonsRemoveItems.isEmpty()){
            try {
                WebElement singleRemoveButton = buttonsRemoveItems.getFirst();
                singleRemoveButton.click();
                handleLoader();
                buttonsRemoveItems = driver.findElements(By.xpath("//button[@name='remove_cart_item']"));

            } catch (StaleElementReferenceException e) {
                System.out.println("StaleElementReferenceException caught, retrying...");
            }
        }
    }

    public void handleLoader(){
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        wait.until(ExpectedConditions.visibilityOf(loader));
        wait.until(ExpectedConditions.invisibilityOf(loader));
    }
}
