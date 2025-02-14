package testPages;


import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class LandingPage extends BasePage {
    public LandingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='badge quantity']")
    private WebElement counterCartItems;

    @FindBy(xpath = "//a[@id='cart']")
    private WebElement linkCart;

    @FindBy(xpath = "//section[@id='box-popular-products']//div[@class='info']/h4")
    private List<WebElement> popularDucks;

    public void selectADuckByHeading(String duckTitle) {
        WebElement duckElementToSelect = popularDucks.stream()
                .filter(duck -> duck.getText().toLowerCase().contains(duckTitle.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No item with title '" + duckTitle + "' found"));
        duckElementToSelect.click();
    }

    public String getCartCounterValue(int counterValue) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        wait.until(ExpectedConditions.visibilityOf(counterCartItems));
        wait.until(driver -> Integer.parseInt(counterCartItems.getText()) == counterValue);
        return counterCartItems.getText();
    }


    public void navigateToCart() {
        linkCart.click();
    }
}
