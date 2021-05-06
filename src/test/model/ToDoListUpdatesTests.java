package test.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.model.ToDoList;
import main.model.ToDoListUpdates;

public class ToDoListUpdatesTests {
    private ToDoListUpdates toDoListUpdates;
    private ToDoList toDoList;
    private ArrayList<String> list;

    public ToDoListUpdatesTests(){
        list = new ArrayList<>();
        toDoList = new ToDoList(list);
        toDoListUpdates = new ToDoListUpdates(toDoList);
    }

    @BeforeEach
    void runBefore(){}

    @Test
    void testUpdate(){
       toDoListUpdates.update(toDoList, null);
        assertEquals(list ,toDoList.getList());
    }

}
