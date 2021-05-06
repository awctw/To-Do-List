package test.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.model.ToDoList;
import main.model.ToDoListUpdates;

public class ToDoListTests {
    private ArrayList<String> list;
    private ToDoList toDoList;
    private ArrayList<String> list1;
    
    public ToDoListTests(){
        list1 = new ArrayList<>();
        this.list = list1;
        toDoList = new ToDoList(list1);
        new ToDoListUpdates(toDoList);
    }

    @BeforeEach
    void runBefore(){}

    @Test
    void testSetList(){
        list1.add("Test");
        toDoList.setList(list1);
    }

    @Test
    void testGetList(){
        assertEquals(list, toDoList.getList());
    }
}
