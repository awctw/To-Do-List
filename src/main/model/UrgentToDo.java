package main.model;

import java.util.ArrayList;

public class UrgentToDo extends ToDoEntries {
    //private ArrayList<String> urgentToDoList;
    public ArrayList<ToDoEntries> toDoList;
    public static ArrayList<String> list = new ArrayList<>();

    public UrgentToDo(String item) {
        this.item = item;
        // urgentToDoList = new ArrayList<>();
        toDoList = new ArrayList<>();
    }

    // EFFECTS: if the list does not contain toDoEntries then add it to the list, checks in urgentToDo list too
    // MODIFIES: list
    public void addToDo(ToDoEntries toDoEntries) {
        if (!list.contains(toDoEntries)) {
            list.add(toDoEntries.getItem());
            toDoEntries.addUrgentToDo(this);
        }
    }

    // EFFECTS: remove the toDoEntries from list if the list contains it, checks in urgentToDo list too
    // MODIFIES: list
    public ArrayList<String> removeToDo(ToDoEntries toDoEntries) {
        if (list.contains(toDoEntries.getItem())) {
            list.remove(toDoEntries.getItem());
            toDoEntries.removeUrgentToDo(this);
        }
        return list;
    }

    public ArrayList<String> getList() {
        return list;
    }

    @Override
    public String toString() {
        return item;
    }

}
