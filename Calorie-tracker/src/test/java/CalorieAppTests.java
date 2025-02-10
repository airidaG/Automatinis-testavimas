import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalorieAppTests extends BaseTest{

    private String item1 = "Apple";
    private String item2 = "Banana";
    private String calories1 = "50";
    private String calories2 = "100";
    private String itemEdit = "Carrot";
    private String caloriesEdit = "666";
    private int howMany = 4;


    @Test
    void addMealTest(){

        calorieAppPage.addItem(item1);
        calorieAppPage.addCalories(calories1);
        calorieAppPage.clickAddMeal();
        assertTrue(calorieAppPage.isItemInList(item1, calories1), "The item was not added");
    }

    @Test
    void addedMealsListSizeTest(){

        for(int i = 1; i <= howMany; i++) {
            calorieAppPage.addMeal(Utility.getRandomFood(), Utility.getRandomNum());
            assertEquals(i, calorieAppPage.getAllItemsList().size());
        }

    }

    @Test
    void verifyCalorieCounterTest(){

        int total = 0;
        for(int i = 1; i <= howMany; i++) {
            String item = Utility.getRandomFood();
            String cal = Utility.getRandomNum();
            total += Integer.parseInt(cal);
            calorieAppPage.addMeal(item,cal);
            assertTrue(calorieAppPage.isItemInList(item, cal), "The item was not added");
            assertEquals(total, calorieAppPage.getTotalCalories(), "Calorie count does not match");
        }

    }

    @Test
    void clearAllButtonWorks(){
        calorieAppPage.addMeal(item1, calories1);
        calorieAppPage.addMeal(item2, calories2);
        calorieAppPage.clickClearAll();
        assertTrue(calorieAppPage.getAllItemsList().isEmpty(), "Items were not removed");
    }

    @Test
    void updateButtonAppears(){

            calorieAppPage.addMeal(Utility.getRandomFood(), Utility.getRandomNum());
            calorieAppPage.clickButtonEdit(0);
            assertTrue(calorieAppPage.getButtonUpdate().isDisplayed());
    }

    @Test
    void deleteButtonAppears(){
        calorieAppPage.addMeal(Utility.getRandomFood(), Utility.getRandomNum());
        calorieAppPage.clickButtonEdit(0);
        assertTrue(calorieAppPage.getButtonDelete().isDisplayed());
    }

    @Test
    void backButtonAppears(){
        calorieAppPage.addMeal(Utility.getRandomFood(), Utility.getRandomNum());
        calorieAppPage.clickButtonEdit(0);
        assertTrue(calorieAppPage.getButtonBack().isDisplayed());
    }

    @Test
    void homeButtonFunctionalityTest(){

        calorieAppPage.clickHome();
        assertEquals(HOME_URL, "https://practice.expandtesting.com/", "Wrong page");
    }

    @Test
    void ableToEditItems(){
        calorieAppPage.addMeal(item1, calories1);
        calorieAppPage.clickButtonEdit(0);
        calorieAppPage.editItem(itemEdit, caloriesEdit);
        calorieAppPage.clickButtonUpdate();
        assertTrue(calorieAppPage.isItemInList(itemEdit, caloriesEdit));

    }

    @Test
    void ableToDeleteItems(){
        calorieAppPage.addMeal(item1, calories1);
        calorieAppPage.clickButtonEdit(0);
        calorieAppPage.clickButtonDelete();
        assertTrue(calorieAppPage.getAllItemsList().isEmpty(), "Items were not removed");
    }

    @Test
    void ableToClickBack(){
        calorieAppPage.addMeal(item1, calories1);
        calorieAppPage.clickButtonEdit(0);
        calorieAppPage.clickButtonBack();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='collection-item']")));

        assertTrue(calorieAppPage.isItemInList(item1, calories1));

    }

    @Test
    void reloadingDoesNotAlterMealList(){
        calorieAppPage.addMeal(item1, calories1);
        assertTrue(calorieAppPage.isItemInList(item1, calories1));
        calorieAppPage.addMeal(item2, calories2);
        assertTrue(calorieAppPage.isItemInList(item2, calories2));
        calorieAppPage.reloadPage();
        assertTrue(calorieAppPage.isItemInList(item1, calories1));
        assertTrue(calorieAppPage.isItemInList(item2, calories2));

    }
}
