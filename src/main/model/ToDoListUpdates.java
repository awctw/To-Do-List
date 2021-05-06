package main.model;

import java.util.Observable;
import java.util.Observer;

public class ToDoListUpdates implements Observer {
    private ToDoList observable;
    private static String notification;

    public ToDoListUpdates(ToDoList observable) {
        this.observable = observable;
    }

    // EFFECTS: prints out a console message if the the observable has been updated
    @Override
    public void update(Observable o, Object arg) {
        if (o == observable) {
          //  System.out.println("To do list updated to: " + observable.getList());
            notification = "To do list updated to: " + observable.getList();
        }
    }

    public static String getNotification() {
        return notification;
    }
}
