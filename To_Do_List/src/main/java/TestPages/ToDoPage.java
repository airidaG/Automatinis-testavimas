package TestPages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class ToDoPage extends BasePage {
    public ToDoPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "input")
    private WebElement inputField;

    @FindBy(xpath = "//li[1]/div/input")
    private WebElement firstListItemBox;

    @FindBy(css = ".toggle")
    private List<WebElement> allCheckboxes;

    @FindBy(xpath = "//ul[@class=\"todo-list\"]/li[1]")
    private WebElement firstListItem;

    @FindBy(css = ".todo-list")
    private WebElement todoListBlock;

    @FindBy(xpath = "//li[@data-testid]")
    private List<WebElement> allToDoList;

    @FindBy(xpath = "//li[@data-testid and not(contains(@class, 'completed'))]")
    private List<WebElement> activeToDos;

    @FindBy(css = ".todo-count")
    private WebElement todoCount;

    @FindBy(css = ".toggle-all")
    private WebElement toggleAllArrow;

    @FindBy(xpath = "(//button[@class=\"destroy\"])[1]")
    private WebElement firstDeleteButton;

    public void enterAToDo(String item) {
        inputField.sendKeys(item + Keys.ENTER);
    }

    public void selectFirst() {
        firstListItemBox.click();
    }

    public void selectValue(String item) {

//        int itemToCheckIndex = 0;
//        for (int i = 0; i < allToDoList.size(); i++) {
//            if (allToDoList.get(i).getText().equals(item)) {
//                itemToCheckIndex = i;
//            }
//        }
//        allCheckboxes.get(itemToCheckIndex).click();
        //OR:
        OptionalInt itemToCheckIndex = IntStream.range(0, allToDoList.size())
                .filter(i -> allToDoList.get(i).getText().equals(item))
                .findFirst();

        itemToCheckIndex.ifPresent(index -> allCheckboxes.get(index).click());
    }

    public void toggleAll() {
        toggleAllArrow.click();
    }

    public void deleteFirst() {
        Actions actions = new Actions(driver);
        actions.moveToElement(firstListItem).moveToElement(firstDeleteButton).click().perform();
    }

    public WebElement getFirstListItem() {
        return firstListItem;
    }

    public WebElement getTodoList() {
        return todoListBlock;
    }

    public List<WebElement> getAllToDoList() {
        return allToDoList;
    }

    public List<WebElement> getActiveToDos() {
        return activeToDos;
    }

    public int getTodoCountNumber() {
        String[] text = todoCount.getText().split(" ");
        return Integer.parseInt(text[0]);
    }

    public boolean isToDoCompleted(String itemToCheck) {

        return allToDoList.stream()
                .filter(item -> item.getText().equals(itemToCheck))
                .findFirst()
                .map(item -> item.getDomAttribute("class"))
                .map(classes -> classes.contains("completed"))
                .orElse(false);
    }
}
