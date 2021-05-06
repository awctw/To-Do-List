package test.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.model.ToDoEntries;
import main.model.UrgentToDo;

public class UrgentToDoTest {
    private ArrayList<UrgentToDo> urgentToDo1;
    public ArrayList<String> list;
    private ToDoEntries toDoEntries;
    private UrgentToDo urgentToDo;


    @BeforeEach
    void runBefore(){
        urgentToDo1 = new ArrayList<>();
        toDoEntries = new ToDoEntries();
        urgentToDo = new UrgentToDo("Test");

        list = new ArrayList<>();

    }
    @Test
    public void testAddToDo(){
        urgentToDo.addToDo(toDoEntries);
        assertFalse(urgentToDo.contains("Test"));
    }

    @Test
    public void testRemoveToDo(){
        String item = "Test";
        UrgentToDo urgentToDo8 = new UrgentToDo(item);
        urgentToDo1.add(urgentToDo8);
        list.add("Test");
        toDoEntries.set("Test");
        toDoEntries.add("Test");
        urgentToDo1.remove(urgentToDo8);
        urgentToDo.removeToDo(toDoEntries);
        //assertFalse(list.contains("Test"));
        assertFalse(urgentToDo.contains("Test"));

    }

}
