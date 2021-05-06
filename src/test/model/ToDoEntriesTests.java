package test.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.exceptions.TooManyException;
import main.model.ToDoEntries;
import main.model.UrgentToDo;

public class ToDoEntriesTests {
    private ToDoEntries todo1;
    private ToDoEntries todo2;
    private ArrayList<String> list1;
    private ToDoEntries todo3;
    private ArrayList<UrgentToDo> urgentToDoList;
    private UrgentToDo urgentToDo;

    @BeforeEach
    void runBefore() {
        todo1 = new ToDoEntries();
        todo2 = new ToDoEntries();

        todo1.add("Test");

        list1 = new ArrayList<>();
        list1.add("Test");

        todo3 = new ToDoEntries();
        todo3.add("a");
        todo3.add("a");
        todo3.add("a");
        todo3.add("a");
        todo3.add("a");
        todo3.add("a");
        todo3.add("a");
        todo3.add("a");
        todo3.add("a");
        todo3.add("a");

        urgentToDoList = new ArrayList<UrgentToDo>();
        urgentToDo = new UrgentToDo("Test");
    }

    @Test
    public void setToDo() {
        todo2.set("Test");
        assertEquals("Test", todo2.getItem());

    }

    @Test
    public void getToDos() {
        assertEquals("Test", todo1.getList(0));
    }

    @Test
    public void contains() {
        assertTrue(todo1.contains("Test"));
        assertFalse(todo1.contains("False"));
    }

    @Test
    public void testGetList(){
        assertEquals(list1 ,todo1.getList());
    }
    @Test
    public void toDoResultTest() throws TooManyException {
        assertEquals(list1, todo2.toDoResult(todo2, "Test"));

        //Does not throw test
        try {
            todo2.toDoResult(todo2, "a");
        } catch (TooManyException t) {
            fail();
        }

        //Does throw test
        try {
            todo3.toDoResult(todo3, "a");
            fail();
        } catch (TooManyException t) {
        }


    }

    @Test
    public void setUrgentToDoTest(){
        ToDoEntries todo7 = new ToDoEntries();
        todo7.setToDoEntriesAsUrgentToDo("Test");
        assertEquals("Test", todo7.getItem());
    }

    @Test
    public void hashCodeTest(){
        ToDoEntries todo8 = new ToDoEntries();
        todo8.add("Test");
        ToDoEntries todo9 = new ToDoEntries();
        todo9.add("Test");
        assertEquals(todo9.hashCode(), todo8.hashCode());
    }

    @Test
    public void recommendedTest(){
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("Test", "1");
        assertNull(todo1.addToCalendar("Test"));
    }

    @Test
    public void getItemTest(){
        assertEquals("", todo1.getItem());
    }

    @Test
    public void equalsTest(){
        todo2 = null;
        UrgentToDo urgentToDo = new UrgentToDo("bbb");
        urgentToDo.add("bbb");
        UrgentToDo urgentToDo1 = new UrgentToDo("test");
        urgentToDo1.add("test");
        assertTrue(todo1.equals(todo1));
        assertFalse(todo1.equals(todo2));
        assertFalse(urgentToDo1.equals(urgentToDo));
    }

    @Test
    public void testRemoveUrgentToDo(){
        todo1.addUrgentToDo(urgentToDo);
        todo1.removeUrgentToDo(urgentToDo);
        assertFalse(urgentToDoList.contains(urgentToDo));
    }
}
