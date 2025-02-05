import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoTests extends BaseTest {

    @Test
    void enterItemTest() {
        toDoPage.enterAToDo("Get a puppy");
        toDoPage.enterAToDo("Buy puppy food");
        toDoPage.enterAToDo("Buy toys");

        assertTrue(toDoPage.getTodoList().getText().contains("Get a puppy"));
        assertTrue(toDoPage.getTodoList().getText().contains("Buy puppy food"));
        assertTrue(toDoPage.getTodoList().getText().contains("Buy toys"));
    }

    @Test
    void enterItemsSmarterTest() {
        List<String> toDos = Arrays.asList("Get a puppy", "Buy puppy food", "Buy toys");

//        toDos.forEach(to do -> {
//            toDoPage.enterAToDo(to do);
//            assertTrue(toDoPage.getTodoList().getText().contains(to do));
//        }); //OR:

        toDos.forEach(todo -> {
            toDoPage.enterAToDo(todo);
            assertTrue(toDoPage.getAllToDoList().stream()
                    .map(WebElement::getText)
                    .anyMatch(text -> text.contains(todo)));
        });

        assertEquals(toDoPage.getAllToDoList().size(), toDos.size());
        assertEquals(toDoPage.getTodoCountNumber(), toDos.size());//counter vs list

    }

    @Test
    void selectFirstTest() {
        toDoPage.enterAToDo("Get a puppy");
        toDoPage.enterAToDo("Buy puppy food");
        toDoPage.enterAToDo("Buy toys");

        toDoPage.selectFirst();
        assertTrue(toDoPage.getFirstListItem().getDomAttribute("class").contains("completed"),
                "The to do item should have the 'completed' class.");

//-------OR:
//        assertTrue(toDoPage.getAllToDos().get(0).getDomAttribute("class").contains("completed"),
//                "The to do item should have the 'completed' class.");

        for (int i = 1; i < toDoPage.getAllToDoList().size(); i++) {
            assertFalse(toDoPage.getAllToDoList().get(i).getDomAttribute("class").contains("completed"),
                    "The to do item should NOT have the 'completed' class.");
        }
    }

    @Test
    void selectFirstSmarterTest() {

        List<String> toDos = Arrays.asList("Get a puppy", "Buy puppy food", "Buy toys");

        toDos.forEach(todo -> {
            toDoPage.enterAToDo(todo);
            assertTrue(toDoPage.getTodoList().getText().contains(todo));
        });
        toDoPage.selectFirst();
        assertEquals(toDoPage.getTodoCountNumber(), toDoPage.getActiveToDos().size());//counter vs active

        for (int i = 1; i < toDoPage.getAllToDoList().size(); i++) {
            assertFalse(toDoPage.getAllToDoList().get(i).getDomAttribute("class").contains("completed"),
                    "The to do item should NOT have the 'completed' class.");
        }

    }

    @Test
    void selectWithSpecificValue() {

        List<String> toDos = Arrays.asList("Get a puppy", "Buy puppy food", "Buy toys");

        toDos.forEach(todo -> {
            toDoPage.enterAToDo(todo);
            assertTrue(toDoPage.getTodoList().getText().contains(todo));
        });

        toDoPage.selectValue("Buy toys");
        assertTrue(toDoPage.isToDoCompleted("Buy toys"));


    }

    @Test
    void selectAllDisplayTest() {

        List<String> toDos = Arrays.asList("Get a puppy", "Buy puppy food", "Buy toys");
        toDos.forEach(todo -> {
            toDoPage.enterAToDo(todo);
            assertTrue(toDoPage.getTodoList().getText().contains(todo));
        });

        toDoPage.toggleAll();
        toDoPage.getAllToDoList().forEach(item ->
                assertTrue(item.getDomAttribute("class").contains("completed"),
                        "The to-do item should have the 'completed' class.")
        );
        assertTrue(toDoPage.getActiveToDos().isEmpty(), "Active list should be empty.");

    }

    @Test
    void checkEmptyListCounter() {

        List<String> toDos = Arrays.asList("Get a puppy", "Buy puppy food", "Buy toys");
        toDos.forEach(todo -> {
            toDoPage.enterAToDo(todo);
            assertTrue(toDoPage.getTodoList().getText().contains(todo));
        });

        toDoPage.toggleAll();

        assertEquals(toDoPage.getTodoCountNumber(), toDoPage.getActiveToDos().size(),
                "To do list count should be 0.");
    }

    @Test
    void deleteFirstFromListTest() {

        toDoPage.enterAToDo("Get a puppy");
        toDoPage.enterAToDo("Buy puppy food");
        toDoPage.enterAToDo("Buy toys");
        toDoPage.deleteFirst();

        assertFalse(toDoPage.getTodoList().getText().contains("Get a puppy"));
        assertEquals(2, toDoPage.getAllToDoList().size());
    }

//To be continued some day...
}
