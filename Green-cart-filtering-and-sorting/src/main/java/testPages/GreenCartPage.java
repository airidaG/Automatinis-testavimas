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

    @FindBy(xpath = "//a[contains(@aria-label, 'Next')]")
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

    public void sortByPrice() {
        sortByPriceOption.click();
    }

    //---One method for different sorts---
    public void sortBy(WebElement element) {
        element.click();
    }

    public void clickNext() {
        buttonNext.click();
    }

    public List<String> getItemNameList() {
        return nameList.stream()
                .map(WebElement::getText)
                .toList();
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

    public List<String> getFullList(List<WebElement> elementList) {
        List<String> list = new ArrayList<>();
        while (isNextClickable()) {
            list.addAll(elementList.stream()
                    .map(WebElement::getText)
                    .toList());
            clickNext();
        }
        list.addAll(elementList.stream()
                .map(WebElement::getText)
                .toList());
        return list;
    }

    //    --OR different methods for different lists--
//    public List<String> getFullNameList() {
//        List<String> names = new ArrayList<>();
//        while (isNextClickable()) {
//            names.addAll(nameList.stream()
//                    .map(WebElement::getText)
//                    .toList());
//            clickNext();
//        }
//        names.addAll(nameList.stream()
//                .map(WebElement::getText)
//                .toList());
//        return names;
//    }

    public List<WebElement> getPriceList() {
        return priceList;
    }

    public List<WebElement> getDiscountList() {
        return discountList;
    }

    public List<WebElement> getNameList() {
        return nameList;
    }

    public WebElement getSortByNameOption() {
        return sortByNameOption;
    }

    public WebElement getSortByPriceOption() {
        return sortByPriceOption;
    }

    public WebElement getSortByDiscountOption() {
        return sortByDiscountOption;
    }

    public boolean doesResultContain(String searchedString) {
        return getItemNameList().stream()
                .allMatch(item -> item.toLowerCase().contains(searchedString.toLowerCase()));
    }

    public boolean resultContains(String searchedString, List<WebElement> element) {
        return getFullList(element).stream()
                .allMatch(item -> item.toLowerCase().contains(searchedString.toLowerCase()));
    }

    public void enterASearch(String toSearch) {
        inputSearch.sendKeys(toSearch);
    }

    public boolean isNextClickable() {
        return buttonNext.getDomAttribute("aria-disabled").equals("false");
    }
}

