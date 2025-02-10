import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

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
        addMealAndVerify(item1, calories1);
        addMealAndVerify(item2, calories2);
        calorieAppPage.clickClearAll();
        assertTrue(calorieAppPage.getAllItemsList().isEmpty(), "Items were not removed");
    }

    @Test
    void updateButtonIsDisplayed(){

            calorieAppPage.addMeal(Utility.getRandomFood(), Utility.getRandomNum());
            calorieAppPage.clickButtonEdit(0);
            assertTrue(calorieAppPage.getButtonUpdate().isDisplayed());
    }

    @Test
    void deleteButtonIsDisplayed(){
        calorieAppPage.addMeal(Utility.getRandomFood(), Utility.getRandomNum());
        calorieAppPage.clickButtonEdit(0);
        assertTrue(calorieAppPage.getButtonDelete().isDisplayed());
    }

    @Test
    void backButtonIsDisplayed(){
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
        addMealAndVerify(item1, calories1);
        calorieAppPage.clickButtonEdit(0);
        calorieAppPage.editItem(itemEdit, caloriesEdit);
        calorieAppPage.clickButtonUpdate();
        assertTrue(calorieAppPage.isItemInList(itemEdit, caloriesEdit));

    }

    @Test
    void ableToDeleteItems(){
        addMealAndVerify(item1, calories1);
        calorieAppPage.clickButtonEdit(0);
        calorieAppPage.clickButtonDelete();
        assertTrue(calorieAppPage.getAllItemsList().isEmpty(), "Items were not removed");
    }

    @Test
    void ableToClickBack(){
        addMealAndVerify(item1, calories1);
        calorieAppPage.clickButtonEdit(0);
        calorieAppPage.clickButtonBack();

        //to wait for text to be visible in an element
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//li[@class='collection-item']"),
//                item1 +": " + calories1 + " Calories"));

        assertTrue(calorieAppPage.isItemInList(item1, calories1),
                "The item should remain displayed as follows: " + item1 +": " + calories1 + " Calories");


    }

    @Test
    void reloadingDoesNotAlterMealList(){
        addMealAndVerify(item1, calories1);
        addMealAndVerify(item2, calories2);
        calorieAppPage.reloadPage();
        assertTrue(calorieAppPage.isItemInList(item1, calories1),
                "The item should remain displayed as follows: " + item1 +": " + calories1 + " Calories");
        assertTrue(calorieAppPage.isItemInList(item2, calories2),
                "The item should remain displayed as follows: " + item1 +": " + calories1 + " Calories");

    }

    @Test
    void reloadingDoesNotAlterCalorieCountDisplay(){

        int totalCal = Integer.parseInt(calories1) + Integer.parseInt(calories2);

        addMealAndVerify(item1, calories1);
        addMealAndVerify(item2, calories2);
        assertEquals(totalCal, calorieAppPage.getTotalCalories(), "Calorie count does not match");
        calorieAppPage.reloadPage();
        assertEquals(totalCal, calorieAppPage.getTotalCalories(), "Calorie count does not match");
    }
    @Test
    void reloadingDoesNotAlterButtonDisplay(){
        calorieAppPage.reloadPage();

        assertTrue(calorieAppPage.getButtonClearAll().isDisplayed());
        assertTrue(calorieAppPage.getButtonAddMeal().isDisplayed());
        assertTrue(calorieAppPage.getButtonHome().isDisplayed());

        assertFalse(calorieAppPage.getButtonDelete().isDisplayed());
        assertFalse(calorieAppPage.getButtonBack().isDisplayed());
        assertFalse(calorieAppPage.getButtonUpdate().isDisplayed());
    }

    @Test
    void addingEmptyMealTest(){
        calorieAppPage.clickAddMeal();
        assertTrue(calorieAppPage.getAllItemsList().isEmpty(),
                "List should be empty");
    }

    @Test
    void verifyCalorieInputAcceptsOnlyNumbers(){
        calorieAppPage.addMeal(item1, "twenty");
        assertTrue(calorieAppPage.getAllItemsList().isEmpty(),
                "Calorie input field should accept only numbers");
        calorieAppPage.addMeal(item2, "-+*");
        assertTrue(calorieAppPage.getAllItemsList().isEmpty(),
                "Calorie input field should accept only numbers");
    }


    @Test
    void negativeCalorieInputTest(){
        calorieAppPage.addMeal(item1, "-150");
        assertTrue(calorieAppPage.getAllItemsList().isEmpty(),
                "Calorie input field should accept only positive numbers");
    }



    public void addMealAndVerify(String item, String cal){
        calorieAppPage.addMeal(item, cal);
        assertTrue(calorieAppPage.isItemInList(item, cal),
                "Item: " + item + "was not added");
    }

}
