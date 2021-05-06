package main.model;

import java.util.ArrayList;
import java.util.HashMap;

import main.exceptions.TooManyException;

public class ToDoEntries extends Entries {
    private ArrayList<ToDoEntries> toDoLog;
    private HashMap<String, String> hashMap;
    public UrgentToDo urgentToDo;
    public ToDoEntries toDoEntries;
    public static ArrayList<UrgentToDo> urgentToDoList = new ArrayList<>();


    public ToDoEntries() {
        super();
        item = "";
        list = new ArrayList<>();
        toDoLog = new ArrayList<>();
        hashMap = new HashMap<String, String>();

    }

    // EFFECTS: adds the urgentToDo to the urgentToDoList if the list does not contain it
    // MODIFIES: urgentToDoList
    public void addUrgentToDo(UrgentToDo urgentToDo) {
        if (!urgentToDoList.contains(urgentToDo)) {
            urgentToDoList.add(urgentToDo);
            urgentToDo.addToDo(this);
        }
    }

    // EFFECTS: removes the urgentToDo if the urgentToDoList contains it
    // MODIFIES: urgentToDoList
    public void removeUrgentToDo(UrgentToDo urgentToDo) {
        if (urgentToDoList.contains(urgentToDo)) {
            urgentToDoList.remove(urgentToDo);
            urgentToDo.removeToDo(this);
        }
    }


    public static String getUrgentToDoList() {
        //return urgentToDoList.stream().map(Object::toString).collect(Collectors.joining(""));
        return urgentToDoList.toString();
    }

    // EFFECTS: returns the given string as a UrgentToDo object
    // MODIFIES: this
    public UrgentToDo setToDoEntriesAsUrgentToDo(String item) {
        this.item = item;
        return new UrgentToDo(item);
    }

    public ArrayList<String> getList() {
        return list;
    }

    // EFFECTS: if the list contains given string then remove it and return true, return false otherwise
    // MODIFIES: list
    @Override
    public boolean contains(String x) {
        if (list.contains(x)) {
            list.remove(x);
            return true;
        }
        return false;
    }

    // EFFECTS: adds the to do entry to the toDoLog, throws an exception if list size > 10, adds entries to a hash map
    // MODIFIES: toDoLog, toDoEntries, hashMap
    public ArrayList<String> toDoResult(ToDoEntries toDoEntries, String result) throws TooManyException {
        toDoEntries.set(result);
        toDoEntries.add(result);
        UrgentToDo.list.add(result);
        hashMap.put(Integer.toString(list.size()), result);
        if (list.size() > 10) {
            throw new TooManyException();
        }
        toDoLog.add(toDoEntries);
        return list;
    }


    public String addToCalendar(String todo) {
        return hashMap.get(todo);
    }

    // EFFECTS: item is set as the hashcode key
    @Override
    public int hashCode() {
        String title = item;
        return title.hashCode();
    }

    // EFFECTS: checks if the two objects equal each other
    @Override
    public boolean equals(Object str) {
        if (str == this) {
            return true;
        }
        if (str == null || str.getClass() != this.getClass()) {
            return false;
        }

        ToDoEntries other = (ToDoEntries) str;

        // return this.toDoEntries.equals(other.toDoEntries) && this.urgentToDo == other.urgentToDo;
       // return this.list.equals(other.list) && this.list == other.list;
        return this.item.equals(other.item);

    }

}
