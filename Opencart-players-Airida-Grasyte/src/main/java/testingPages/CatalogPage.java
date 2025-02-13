package testingPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;

public class CatalogPage extends BasePage {
    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@name='search']")
    private WebElement inputSearch;

    @FindBy(xpath = "//button[@id='button-list']")
    private WebElement buttonListView;

    @FindBy(xpath = "//button[@class='btn btn-light btn-lg']")
    private WebElement buttonSearch;

//    @FindBy(xpath = "/html//div[@id='product-list']/div[1]/div[@class='product-thumb']/div[@class='content']/form[@method='post']/div/button[1]")
//    private WebElement oneButton;

//    @FindBy(xpath = "//div[@class='button-group']/button[@aria-label='Add to Cart']")
//    private List<WebElement> buttonsAddToCart;

    @FindBy(xpath = "//div[@class='description']/h4/a")
    private List<WebElement> productTitleElementList;

    public void clickListview() {
        buttonListView.click();
    }

    public void clickSearch() {
        buttonSearch.click();
    }

    public void selectItem(String title) {
        WebElement elementToSelect = productTitleElementList.stream()
                .filter(element -> element.getText().toLowerCase().contains(title.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No item with title '" + title + "' found"));

        Actions actions = new Actions(driver);
        actions.moveToElement(elementToSelect).perform();

        elementToSelect.click();
    }

    //TODO
    public void addAllToCart() {
       
        List<WebElement> buttonsAddToCart = driver.findElements(By.xpath("//div[@class='button-group']/button[@aria-label='Add to Cart']"));

        buttonsAddToCart.forEach(WebElement::click);
    }

//    public void clickOne() {
//        oneButton.click();
//    }

    public void enterASearch(String item) {
        inputSearch.sendKeys(item);
    }

    public boolean isItemInList(String item) {
        return productTitleElementList.stream()
                .filter(element -> element.getText().toLowerCase().contains(item.toLowerCase()))
                .allMatch(title -> title.getText().toLowerCase()
                        .contains(item.toLowerCase()));
    }

}
