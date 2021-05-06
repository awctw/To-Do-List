package main.model;

import java.util.ArrayList;
import java.util.Observable;

public class ToDoList extends Observable {
    private ArrayList<String> listObservable;

    public ToDoList(ArrayList<String> listObservable) {
        this.listObservable = listObservable;
    }

    // EFFECTS: if listObservable has changed then notify observers
    // MODIFIES: this
    public void setList(ArrayList<String> listObservable) {
        this.listObservable = listObservable;
        setChanged();
        notifyObservers();
    }

    public ArrayList<String> getList() {
        return listObservable;
    }
}
