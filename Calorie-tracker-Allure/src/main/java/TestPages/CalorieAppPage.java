package TestPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CalorieAppPage extends BasePage {
    public CalorieAppPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#item-name")
    private WebElement inputAddItem;

    @FindBy(css = "#item-calories")
    private WebElement inputCalories;

    @FindBy(xpath = "//span[@class='total-calories']")
    private WebElement totalCalories;

    @FindBy(xpath = "//button[contains(@class, 'add-btn')]")
    private WebElement buttonAddMeal;

    @FindBy(xpath = "//a[text()='Clear All']")
    private WebElement buttonClearAll;

    @FindBy(xpath = "//a[text()='Home']")
    private WebElement buttonHome;

    @FindBy(css = ".btn.orange.update-btn")
    private WebElement buttonUpdate;

    @FindBy(css = ".btn.delete-btn.red")
    private WebElement buttonDelete;

    @FindBy(css = ".back-btn.btn.grey.pull-right")
    private WebElement buttonBack;

    @FindBy(css = ".secondary-content")
    List<WebElement> buttonsEdit;

    @FindBy(xpath = "//li[@class='collection-item']")
    List<WebElement> allItemsList;


    public void addItem(String itemName) {
        inputAddItem.sendKeys(itemName);
    }

    public void editItem(String itemName, String calories) {
        inputAddItem.clear();
        inputAddItem.sendKeys( itemName);
        inputCalories.clear();
        inputCalories.sendKeys(calories);
    }

    public void addCalories(String calories) {
        inputCalories.sendKeys(calories);
    }

    public void addMeal(String itemName, String calories) {
        inputAddItem.sendKeys(itemName);
        inputCalories.sendKeys(calories);
        buttonAddMeal.click();
    }

    public void clickAddMeal(){
        buttonAddMeal.click();
    }

    public void clickClearAll() {
        buttonClearAll.click();
    }

    public void clickHome() {
        buttonHome.click();
    }

    public void clickButtonEdit(int index) {
        buttonsEdit.get(index).click();
    }

    public void clickButtonUpdate() {
        buttonUpdate.click();
    }

    public void clickButtonDelete() {
        buttonDelete.click();
    }

    public void clickButtonBack() {
        buttonBack.click();
    }

    public List<WebElement> getAllItemsList() {
        return allItemsList;
    }

    public int getTotalCalories() {
        return Integer.parseInt(totalCalories.getText());
    }

    public WebElement getButtonUpdate() {
        return buttonUpdate;
    }

    public WebElement getButtonDelete() {
        return buttonDelete;
    }

    public WebElement getButtonBack() {
        return buttonBack;
    }

    public WebElement getButtonAddMeal() {
        return buttonAddMeal;
    }

    public WebElement getButtonHome() {
        return buttonHome;
    }

    public WebElement getButtonClearAll() {
        return buttonClearAll;
    }

    public boolean isItemInList(String itemName, String calories) {

        List<String> items = getAllItemsList().stream()
                .map(WebElement::getText)
                .toList();

        return items.contains(itemName +": " + calories + " Calories");
//for two different outcomes
//        return items.contains(itemName +": " + calories + " Calories") || items.contains(itemName + " " + calories + " Calories");
    }

    public void reloadPage(){
        driver.navigate().refresh();
    }

}
