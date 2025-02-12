import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Utility extends BaseTest{

    static List<String> foodItems = Arrays.asList(
            "Apple", "Banana", "Cherry", "Grapes", "Orange", "Mango",
            "Pizza", "Burger", "Pasta", "Sandwich", "Salad", "Steak",
            "Rice", "Sushi", "Taco", "Curry", "Ice Cream", "Cake", "Donut"
    );

    static String getRandomFood() {
        Random random = new Random();
        int index = random.nextInt(foodItems.size());
        return foodItems.get(index);
    }

    static String getRandomNum() {
       int randomNum = (int) (Math.random() * (200 - 10 + 1)) + 10;//nuo 10 iki 200
        return Integer.toString(randomNum);
    }


}
