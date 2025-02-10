import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class CalorieAppTests extends BaseTest{

    private final String ITEM1 = "Apple";
    private final String ITEM2 = "Banana";
    private final String CALORIES1 = "50";
    private final String CALORIES2 = "100";
    private final String ITEM_EDIT = "Carrot";
    private final String CALORIES_EDIT = "666";
    private final int HOW_MANY = 4;


    @Test
    void addMealTest(){

        calorieAppPage.addItem(ITEM1);
        calorieAppPage.addCalories(CALORIES1);
        calorieAppPage.clickAddMeal();
        assertTrue(calorieAppPage.isItemInList(ITEM1, CALORIES1), "The item was not added");
    }

    @Test
    void addedMealsListSizeTest(){

        for(int i = 1; i <= HOW_MANY; i++) {
            calorieAppPage.addMeal(Utility.getRandomFood(), Utility.getRandomNum());
            assertEquals(i, calorieAppPage.getAllItemsList().size());
        }

    }

    @Test
    void verifyCalorieCounterTest(){

        int total = 0;
        for(int i = 1; i <= HOW_MANY; i++) {
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
        addMealAndVerify(ITEM1, CALORIES1);
        addMealAndVerify(ITEM2, CALORIES2);
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
        addMealAndVerify(ITEM1, CALORIES1);
        calorieAppPage.clickButtonEdit(0);
        calorieAppPage.editItem(ITEM_EDIT, CALORIES_EDIT);
        calorieAppPage.clickButtonUpdate();
        assertTrue(calorieAppPage.isItemInList(ITEM_EDIT, CALORIES_EDIT));

    }

    @Test
    void ableToDeleteItems(){
        addMealAndVerify(ITEM1, CALORIES1);
        calorieAppPage.clickButtonEdit(0);
        calorieAppPage.clickButtonDelete();
        assertTrue(calorieAppPage.getAllItemsList().isEmpty(), "Items were not removed");
    }

    @Test
    void ableToClickBack(){
        addMealAndVerify(ITEM1, CALORIES1);
        calorieAppPage.clickButtonEdit(0);
        calorieAppPage.clickButtonBack();

        //to wait for text to be visible in an element
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//li[@class='collection-item']"),
//                item1 +": " + calories1 + " Calories"));

        assertTrue(calorieAppPage.isItemInList(ITEM1, CALORIES1),
                "The item should remain displayed as follows: " + ITEM1 +": " + CALORIES1 + " Calories");


    }

    @Test
    void reloadingDoesNotAlterMealList(){
        addMealAndVerify(ITEM1, CALORIES1);
        addMealAndVerify(ITEM2, CALORIES2);
        calorieAppPage.reloadPage();
        assertTrue(calorieAppPage.isItemInList(ITEM1, CALORIES1),
                "The item should remain displayed as follows: " + ITEM1 +": " + CALORIES1 + " Calories");
        assertTrue(calorieAppPage.isItemInList(ITEM2, CALORIES2),
                "The item should remain displayed as follows: " + ITEM1 +": " + CALORIES1 + " Calories");

    }

    @Test
    void reloadingDoesNotAlterCalorieCountDisplay(){

        int totalCal = Integer.parseInt(CALORIES1) + Integer.parseInt(CALORIES2);

        addMealAndVerify(ITEM1, CALORIES1);
        addMealAndVerify(ITEM2, CALORIES2);
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
        calorieAppPage.addMeal(ITEM1, "twenty");
        assertTrue(calorieAppPage.getAllItemsList().isEmpty(),
                "Calorie input field should accept only numbers");
        calorieAppPage.addMeal(ITEM2, "-+*");
        assertTrue(calorieAppPage.getAllItemsList().isEmpty(),
                "Calorie input field should accept only numbers");
    }


    @Test
    void negativeCalorieInputTest(){
        calorieAppPage.addMeal(ITEM1, "-150");
        assertTrue(calorieAppPage.getAllItemsList().isEmpty(),
                "Calorie input field should accept only positive numbers");
    }



    public void addMealAndVerify(String item, String cal){
        calorieAppPage.addMeal(item, cal);
        assertTrue(calorieAppPage.isItemInList(item, cal),
                "Item: " + item + "was not added");
    }

}
