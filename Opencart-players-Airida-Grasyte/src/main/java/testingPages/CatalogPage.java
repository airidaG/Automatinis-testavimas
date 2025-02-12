package testingPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

//    @FindBy(xpath = "//div[@id='product-list']")
//    private List<WebElement> productList;

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

        elementToSelect.click();
    }

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
