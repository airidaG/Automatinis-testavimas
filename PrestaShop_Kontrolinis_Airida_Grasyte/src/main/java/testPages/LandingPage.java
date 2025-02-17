package testPages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;


public class LandingPage extends BasePage {
    public LandingPage(WebDriver driver) {
        super(driver);
    }

    //links
    @FindBy(xpath = "//div/a[@title='Log in to your customer account']")
    private WebElement linkSignIn;

    //inputs
    @FindBy(xpath = "//form/input[@type='text']")
    private WebElement inputSearch;

    //lists
    @FindBy(xpath = "//h2/a")
    private List<WebElement> productList;

    public void clickSignIn() {
        linkSignIn.click();
    }

    public void selectProductFromList(String productName) {
        WebElement productToSelect = productList.stream()
                .filter(element -> element.getText().equalsIgnoreCase(productName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No item with title '" + productName + "' found"));
        productToSelect.click();
    }

    public void inputSearch(String search) {
        inputSearch.sendKeys(search);
        inputSearch.sendKeys(Keys.ENTER);
    }

    public boolean isProductList(String title) {
        return productList.stream()
                .map(element -> element.getText().toLowerCase())
                .anyMatch(text -> text.equals(title.toLowerCase()));
    }
}
