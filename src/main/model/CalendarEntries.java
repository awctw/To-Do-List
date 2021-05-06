package main.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CalendarEntries extends Entries {
    private ArrayList<CalendarEntries> log;
    private String st;
    private String out;

    public CalendarEntries() {
        super();
        item = "";
        list = new ArrayList<>();
        log = new ArrayList<>();
        st = "";
        out = "";
    }

    public ArrayList<CalendarEntries> getLog() {
        return log;
    }

    // EFFECTS: turns the event, time, place into one string
    public String add(String event, String time, String place) {
        event = event + " at " + time + ", location: " + place;
        return event;
    }

    public ArrayList<String> getList() {
        return list;
    }

    // EFFECTS: returns true if the list contains the given string, false otherwise
    @Override
    public boolean contains(String x) {
        return list.contains(x);
    }


    // EFFECTS: returns string of the first item on the list
    public String toStringPrint(ArrayList<CalendarEntries> x) {
        CalendarEntries i = x.get(0);
        return i.getList(0);
    }

    // EFFECTS: add the calendar entry to the log
    // MODIFIES: log, calendarEntries
    public void logResult(CalendarEntries calendarEntries, String event, String result) {
        calendarEntries.set(event);
        calendarEntries.add(result);
        log.add(calendarEntries);
    }

    // EFFECTS: add the to do item to the calendar entry log
    // MODIFIES: log, calendarEntries
    public void logAddToDo(CalendarEntries calendarEntries, String todo) {
        calendarEntries.set(todo);
        calendarEntries.add(todo);
        log.add(calendarEntries);
    }


    // EFFECTS: writes the calendar entry into a file
    // MODIFIES: z
    public void save(String z, ArrayList<CalendarEntries> a) throws IOException {
       /* CalendarEntries i = a.get(0);
        String x = i.getList(0);
        FileWriter writer = new FileWriter(z);
        writer.write(x + System.lineSeparator());
        writer.close();*/
        FileWriter writer = new FileWriter(z);
        for (int y = 0; y < log.size(); y++) {
            CalendarEntries i = log.get(y);
            String x = i.getList(y);
            writer.write(x + System.lineSeparator());
        }
        writer.close();
    }

    // REQUIRES: the given file to be valid
    // EFFECTS: reads the given file as a string
    public String load(String z) {
      /*  if (st != null) {
            BufferedReader br = new BufferedReader(new FileReader(z));
            st = br.readLine();
            return st;
        }
        throw new IOException();
    }*/
        try (BufferedReader br = new BufferedReader(new FileReader(z))) {
            //  String line;
            st = br.readLine();
            out = st;
            while ((st = br.readLine()) != null) {
                out = out + System.lineSeparator() + st;
                st = br.readLine();
            }
        } catch (IOException e) {
            load(z);
        }
        return out;
    }


}




