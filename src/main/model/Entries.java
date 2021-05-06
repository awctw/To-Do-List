package main.model;

import java.util.ArrayList;

public abstract class Entries {

    public String item;
    public ArrayList<String> list;
    public ArrayList<String> urgentList;

    public Entries() {
        this.list = new ArrayList<>();
        this.item = "";
        this.urgentList = new ArrayList<>();
    }

    //public abstract void add(String item);
    public void add(String item) {
        list.add(item);
    }

    // public abstract void set(String item);

    public void set(String item) {
        this.item = item;
    }

    //public abstract String getList(int i);

    public String getList(int i) {
        return list.get(i);
    }

    //public abstract String getItem(int i);

    public String getItem() {
        return item;
    }

    public abstract boolean contains(String x);


}



