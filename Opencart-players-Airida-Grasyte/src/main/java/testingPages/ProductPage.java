package testingPages;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class ProductPage extends BasePage {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@name='quantity']")
    private WebElement inputQuantity;

    @FindBy(xpath = "//button[@id='button-cart']")
    private WebElement buttonSubmit;

    @FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
    private WebElement alertSuccess;

    public void enterQuantity(String quantity) {
        inputQuantity.clear();
        inputQuantity.sendKeys(quantity);
    }

    public void clickSubmit() {
        buttonSubmit.click();
    }

    public String getAlert() {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        return wait.until(ExpectedConditions.elementToBeClickable(alertSuccess)).getText();
    }
}
