package testPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class GreenCartPage extends BasePage {
    public GreenCartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#search-field")
    private WebElement inputSearch;

    @FindBy(xpath = "//select[@id='page-menu']")
    private WebElement pageSizeElement;

    @FindBy(xpath = "//th[contains(@aria-label, 'Veg/fruit name')]")
    private WebElement sortByNameOption;

    @FindBy(xpath = "//th[contains(@aria-label, 'Price:')]")
    private WebElement sortByPriceOption;

    @FindBy(xpath = "//th[contains(@aria-label, 'Discount price:')]")
    private WebElement sortByDiscountOption;


    //    TODO
    @FindBy(css = "li:nth-of-type(4)")
    private WebElement buttonNext;

    @FindBy(xpath = "//tbody/tr/td[1]")
    private List<WebElement> nameList;

    @FindBy(xpath = "//tbody/tr/td[2]")
    private List<WebElement> priceList;

    @FindBy(xpath = "//tbody/tr/td[3]")
    private List<WebElement> discountList;


    public void selectPageSize(String value) {
        Select pageSizeSelector = new Select(pageSizeElement);
        pageSizeSelector.selectByValue(value);
    }

    public void sortByName() {
        sortByNameOption.click();
    }

    public void sortByPrice() {
        sortByPriceOption.click();
    }

    public void sortBeyDiscount() {
        sortByDiscountOption.click();
    }

    public void clickNext() {
        buttonNext.click();
    }

    public List<String> getItemNameList() {
        return nameList.stream()
                .map(WebElement::getText)
                .toList();
    }

    //    TODO
    public List<String> getAllItemNameList() {
        List<String> names = new ArrayList<>();
        while (buttonNext.getDomAttribute("class").contains("active")) {
            names.addAll(nameList.stream()
                    .map(WebElement::getText)
                    .toList());
            buttonNext.click();
        }
        names.addAll(nameList.stream()
                .map(WebElement::getText)
                .toList());

        return names;
    }

    public List<String> getItemPriceList() {
        return priceList.stream()
                .map(WebElement::getText)
                .toList();
    }

    public List<String> getItemDiscountList() {
        return discountList.stream()
                .map(WebElement::getText)
                .toList();
    }

    public boolean doesResultContain(String searchedString) {
        return getItemNameList().stream()
                .allMatch(item -> item.contains(searchedString));
    }


    public void enterASearch(String toSearch) {
        inputSearch.sendKeys(toSearch);
    }
}

