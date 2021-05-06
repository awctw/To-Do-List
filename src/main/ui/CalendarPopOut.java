package main.ui;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import main.exceptions.TooManyException;
import main.model.CalendarEntries;
import main.model.CalendarFunctions;
import main.model.ToDoEntries;
import main.model.ToDoList;
import main.model.ToDoListUpdates;
import main.model.UrgentToDo;
import main.network.ReadWebPage;


class CalendarPopOut {
    private CalendarEntries calendarEntries = new CalendarEntries();
    private ToDoList ov = new ToDoList(null);
    private ToDoListUpdates ob = new ToDoListUpdates(ov);
    private ToDoEntries toDoEntries = new ToDoEntries();
    private UrgentToDo urgent = new UrgentToDo(toDoEntries.getItem());

    private ArrayList<String> listing = new ArrayList<>();
    private ArrayList<String> urgentList = new ArrayList<>();
    private ArrayList<String> currentList = new ArrayList<>();
    private ArrayList<String> urgentItems = new ArrayList<>();
    private ArrayList<String> currentUrgents = new ArrayList<>();

    // Code Source: B04 SimpleCalculatorStarter

    CalendarPopOut() {
        process();
    }


    // REQUIRES: the user to type in the correct answers
    // EFFECTS: asks user for ui input to choose calendar/to do list
    private void process() {
        JFrame jframe = new JFrame();
        JFrame frameToDo = new JFrame("To Do List");
        JPanel panel = new JPanel(new GridBagLayout());
        JLabel topLabel = new JLabel("Welcome to the menu");
        JPanel topPanel = new JPanel(new GridBagLayout());
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
       // JPanel mainPanel = new JPanel(new GridBagLayout());
        //JPanel topPanel = new JPanel(new GridBagLayout());
        JLabel weatherNotification = new JLabel();
        JLabel notificationUpdate = new JLabel();
        JLabel remaining = new JLabel();
        JLabel thingsToDo = new JLabel();
      //  JPanel panel = new JPanel(new GridBagLayout());
        
        JButton button2 = new JButton("To Do List");
        JButton button3 = new JButton("Enter");
        JButton button7 = new JButton("Add");
        JButton button8 = new JButton("Enter");
        JButton button9 = new JButton("Enter");
        JButton button10 = new JButton("Enter");
        JButton button11 = new JButton("Enter");
        JButton button12 = new JButton("Enter");
        JButton button13 = new JButton("Done");
        
        JButton urgentDone = new JButton("Done");
        JButton buttonStop = new JButton("Done");
        JButton endApp = new JButton("End");
        JButton buttonStopping = new JButton("Done");
        JButton toDoListShortCut = new JButton("To Do List");
        JTextField toDoItemRemove = new JTextField(20);
        JTextField toDos = new JTextField(20);
        JTextField yesOrNo = new JTextField(20);
        JTextField urgentToDo = new JTextField(20);
        JTextField answerYesOrNo = new JTextField(20);
        JTextField removeItems = new JTextField(20);
        JTextField number = new JTextField(20);
        
        @SuppressWarnings("serial")
		DefaultTableModel model = new DefaultTableModel(new Object[]{"To Dos: "}, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 1) {
                    return Boolean.class;
                }
                if (columnIndex == 2) {
                    return Boolean.class;
                }
                return String.class;
            }
        };
        
        
        jframe.setTitle("Main Page");
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        topPanel.setLayout(new GridLayout(3, 1));
        topLabel = new JLabel("Welcome to the menu");

        // JLabel topLabel = new JLabel("Welcome to the menu");
        topPanel.add(topLabel, constraints);
        JLabel daysLeft = new JLabel("Year: " + CalendarFunctions.getDate() + " & Days Left in Month: " + CalendarFunctions.daysLeft());

        topPanel.add(daysLeft, constraints);
        topLabel.setHorizontalAlignment(JLabel.CENTER);
        daysLeft.setHorizontalAlignment(JLabel.CENTER);
        new ReadWebPage();
        ReadWebPage.parseWeather();
        weatherNotification.setText("Weather today: " + ReadWebPage.getWeatherNotification());
        topPanel.add(weatherNotification, constraints);
        weatherNotification.setHorizontalAlignment(JLabel.CENTER);
        JButton button1 = new JButton("Calendar");

        JLabel finalTopLabel = topLabel;
        JPanel finalTopPanel = topPanel;
        button1.addActionListener(e -> {
            topPanel.remove(weatherNotification);
            topPanel.remove(daysLeft);
            panel.remove(button1);
            panel.remove(button2);
            topPanel.repaint();
            panel.repaint();
            try {
                finalTopLabel.setText(calendarEntries.load("data/calendaritems.txt"));
            } finally {
                if (calendarEntries.load("data/calendaritems.txt") != null) {
                    try {
                        new BufferedReader(new FileReader("data/calendaritems.txt")).close();
                    } catch (IOException e1) {
                        finalTopLabel.setText("Invalid output");
                        new CalendarPopOut();
                    }
                }
            }
            JLabel label = new JLabel("Today's events: " + calendarEntries.load("data/calendaritems.txt"));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            topPanel.add(label, constraints);
            finalTopLabel.setText("Open calendar (y or n):");
            JTextField yesOrNoAnswer = new JTextField(20);
            panel.add(yesOrNoAnswer, constraints);

            JButton button6 = new JButton("Enter");
            panel.add(button6, constraints);

            button6.addActionListener(e0 -> {
                if (yesOrNoAnswer.getText().equals("n")) {
                    topPanel.remove(label);
                    String event = yesOrNoAnswer.getText();
                    panel.remove(button6);
                    panel.remove(yesOrNoAnswer);
                    panel.repaint();
                    finalTopLabel.setText("Please enter tomorrow's event name: ");
                    JTextField eventName = new JTextField(20);
                    panel.add(eventName, constraints);
                    panel.add(button3);


                    button3.addActionListener(e1 -> {
                        panel.remove(button3);
                        panel.remove(eventName);
                        panel.repaint();
                        finalTopLabel.setText("Please enter tomorrow's event time: ");
                        JTextField eventTime = new JTextField(20);
                        panel.add(eventTime, constraints);
                        JButton button4 = new JButton("Enter");
                        panel.add(button4);
                        button4.addActionListener(e11 -> {
                            panel.remove(button4);
                            panel.remove(eventTime);
                            panel.repaint();
                            finalTopLabel.setText("Please enter tomorrow's event location: ");
                            JTextField eventLocation = new JTextField(20);
                            panel.add(eventLocation, constraints);

                            JButton button5 = new JButton("Enter");
                            panel.add(button5);
                            button5.addActionListener(e2 -> {
                                calendarEntries.logResult(calendarEntries, event,
                                calendarEntries.add(eventName.getText(), eventTime.getText(), eventLocation.getText()));
                                try {
                                    panel.remove(button5);
                                    panel.remove(eventLocation);
                                    panel.repaint();

                                    String fullEvent = "Upcoming events: " + calendarEntries.toStringPrint(calendarEntries.getLog());
                                    finalTopLabel.setText(fullEvent);
                                    calendarEntries.save("data/calendaritems.txt", calendarEntries.getLog());
                                    panel.add(toDoListShortCut, constraints);


                                    toDoListShortCut.addActionListener(e7 -> {
                                        finalTopPanel.remove(weatherNotification);
                                        panel.remove(endApp);
                                        panel.remove(toDoListShortCut);
                                        panel.remove(button1);
                                        panel.remove(button2);
                                        finalTopPanel.remove(daysLeft);
                                        finalTopPanel.repaint();
                                        panel.repaint();
                                        finalTopLabel.setText("Please enter things to do: (press Done when finished)");
                                        panel.add(toDos, constraints);


                                        panel.add(button7, constraints);
                                        panel.add(buttonStop, constraints);

                                        frameToDo.setLayout(new BorderLayout());
                                        frameToDo.setSize(500, 500);
                                        JPanel panelToDo = new JPanel();
                                        frameToDo.add(panelToDo);
                                        buttonStop.addActionListener(e20 -> {
                                            frameToDo.setVisible(true);
                                            finalTopPanel.remove(notificationUpdate);
                                            panel.remove(buttonStop);
                                            panel.remove(button7);
                                            panel.remove(toDos);
                                            panel.repaint();
                                            finalTopPanel.repaint();
                                            toDoEntries.contains("q");
                                            thingsToDo.setText("Things to do: " + toDoEntries.getList());
                                            panel.add(thingsToDo, constraints);
                                            finalTopLabel.setText("Have you finished a task? (y/n)");
                                            panel.add(answerYesOrNo, constraints);


                                            panel.add(button8, constraints);

                                            button8.addActionListener(e4 -> {
                                                if (answerYesOrNo.getText().equals("y")) {
                                                    panel.remove(button8);
                                                    panel.remove(answerYesOrNo);
                                                    panel.repaint();

                                                    model.addTableModelListener(e31 -> {
                                                        for (int i = 0; i < model.getRowCount(); i++) {
                                                            Boolean checked = (Boolean) model.getValueAt(i, 1);
                                                            if (checked) {
                                                                model.removeRow(i);
                                                                i--;
                                                            }
                                                        }
                                                    });
                                                    finalTopLabel.setText("Please check off the tasks that you have finished!");
                                                    panel.add(buttonStopping, constraints);
                                                    buttonStopping.addActionListener(e15 -> {
                                                        panel.remove(thingsToDo);
                                                        panel.repaint();
                                                        panel.remove(button8);
                                                        panel.remove(thingsToDo);
                                                        panel.remove(answerYesOrNo);
                                                        panel.remove(buttonStopping);
                                                        panel.remove(button3);
                                                        panel.repaint();

                                                        for (int count = 0; count < model.getRowCount(); count++) {
                                                            listing.add(model.getValueAt(count, 0).toString());
                                                        }
                                                        
                                                        remaining.setText("Remaining tasks: " + listing);
                                                        panel.add(remaining, constraints);
                                                        finalTopLabel.setText("Do you wish to add todo list items to your calendar? [y/n]");
                                                        panel.add(yesOrNo, constraints);

                                                        panel.add(button10, constraints);
                                                        button10.addActionListener(e32 -> {
                                                            if (yesOrNo.getText().equals("y")) {
                                                                panel.remove(yesOrNo);
                                                                panel.remove(button10);
                                                                panel.repaint();
                                                                UrgentToDo.list = listing;
                                                                finalTopLabel.setText("Current todo items: " + listing
                                                                        + ". Please input the number of the todo item you wish to add. ");

                                                                panel.add(number, constraints);

                                                                panel.add(button9, constraints);

                                                                button9.addActionListener(e21 -> {
                                                                    panel.remove(button9);
                                                                    panel.remove(number);
                                                                    panel.repaint();
                                                                    String i = " & Things to do today: " + toDoEntries.addToCalendar(number.getText());
                                                                    calendarEntries.logAddToDo(calendarEntries, i);
                                                                    try {
                                                                        calendarEntries.save("data/calendaritems.txt", calendarEntries.getLog());
                                                                    } catch (IOException ex) {
                                                                        finalTopLabel.setText("Error");
                                                                    }
                                                                    finalTopLabel.setText("Today's event: " + calendarEntries.load("data/calendaritems.txt"));
                                                                    panel.remove(remaining);
                                                                    panel.remove(button13);
                                                                    panel.repaint();
                                                                    JButton ending = new JButton("End");
                                                                    panel.add(ending, constraints);
                                                                    JButton restart = new JButton("Restart");
                                                                    panel.add(restart, constraints);

                                                                    restart.addActionListener(e22 -> {
                                                                        new CalendarPopOut();
                                                                    });

                                                                    ending.addActionListener(e23 -> System.exit(0));
                                                                });
                                                            }
                                                            if (yesOrNo.getText().equals("n")) {
                                                                panel.remove(remaining);
                                                                panel.remove(button10);
                                                                panel.remove(yesOrNo);
                                                                panel.repaint();
                                                                finalTopLabel.setText("Please input urgent todo items: ");
                                                                panel.add(urgentToDo, constraints);


                                                                panel.add(button11, constraints);

                                                                panel.add(urgentDone, constraints);

                                                                urgentDone.addActionListener(e10 -> {
                                                                    for (int i = 0; i < model.getRowCount(); i++) {
                                                                        Boolean checked = (Boolean) model.getValueAt(i, 2);
                                                                        if (checked) {
                                                                            urgentItems.add((String) model.getValueAt(i, 0));
                                                                        }
                                                                    }

                                                                    for (int i = 0; i < UrgentToDo.list.size(); i++) {
                                                                        String str = UrgentToDo.list.toString();
                                                                        str = str.substring(1);
                                                                        str = str.substring(0, str.length() - 1);
                                                                        toDoEntries.getList().add(str);
                                                                    }

                                                                    panel.remove(button11);
                                                                    panel.remove(urgentToDo);
                                                                    panel.remove(urgentDone);
                                                                    panel.repaint();
                                                                    finalTopLabel.setText("Do you wish to remove any items from your todo list? [y/n]");
                                                                    panel.add(removeItems, constraints);


                                                                    panel.add(button12, constraints);

                                                                    button12.addActionListener(e24 -> {
                                                                        if (removeItems.getText().equals("y")) {
                                                                            panel.remove(button12);
                                                                            panel.remove(removeItems);
                                                                            panel.repaint();
                                                                            finalTopLabel.setText("Please check off the tasks you have finished!");
                                                                            panel.add(button13, constraints);


                                                                            model.addTableModelListener(e25 -> {
                                                                                for (int i = 0; i < model.getRowCount(); i++) {
                                                                                    Boolean checked = (Boolean) model.getValueAt(i, 1);
                                                                                    if (checked) {
                                                                                        model.removeRow(i);
                                                                                        i--;
                                                                                    }
                                                                                }
                                                                            });

                                                                            button13.addActionListener(e13 -> {
                                                                                urgent = toDoEntries.setToDoEntriesAsUrgentToDo(toDoItemRemove.getText());
                                                                                urgent.removeToDo(urgent);
                                                                                //urgent.removeUrgentToDo(urgent);
                                                                                panel.remove(button13);
                                                                                panel.remove(toDoItemRemove);
                                                                                panel.repaint();
                                                                                for (int count = 0; count < model.getRowCount(); count++) {
                                                                                    currentList.add(model.getValueAt(count, 0).toString());
                                                                                    listing = currentList;
                                                                                }
                                                                                for (int i = 0; i < model.getRowCount(); i++) {
                                                                                    Boolean checked = (Boolean) model.getValueAt(i, 2);
                                                                                    if (checked) {
                                                                                        currentUrgents.add((String) model.getValueAt(i, 0));
                                                                                    }
                                                                                }
                                                                                finalTopLabel.setText("The current items on your todo list: " + listing);
                                                                                JLabel currentUrgentToDo = new JLabel("The current items on your urgent todo list: "
                                                                                        + currentUrgents);

                                                                                finalTopPanel.add(currentUrgentToDo, constraints);
                                                                                currentUrgentToDo.setHorizontalAlignment(JLabel.CENTER);

                                                                                panel.remove(remaining);
                                                                                panel.remove(button13);
                                                                                panel.repaint();
                                                                                JButton ending = new JButton("End");
                                                                                panel.add(ending, constraints);
                                                                                JButton restart = new JButton("Restart");
                                                                                panel.add(restart, constraints);

                                                                                restart.addActionListener(e26 -> {
                                                                                    new CalendarPopOut();
                                                                                });

                                                                                ending.addActionListener(e3 -> System.exit(0));
                                                                            });

                                                                        }
                                                                        if (removeItems.getText().equals("n")) {
                                                                            panel.remove(button12);
                                                                            panel.remove(removeItems);
                                                                            panel.remove(toDoItemRemove);
                                                                            //panel.remove(urgentDone);
                                                                            panel.repaint();
                                                                            for (int count = 0; count < model.getRowCount(); count++) {
                                                                                currentList.add(model.getValueAt(count, 0).toString());
                                                                                listing = currentList;
                                                                            }
                                                                            UrgentToDo.list = listing;
                                                                            finalTopLabel.setText("The current items on your todo list: " + UrgentToDo.list);
                                                                            JLabel currentUrgentToDo = new JLabel("The current items on your urgent todo list: " + urgentItems);
                                                                            finalTopPanel.add(currentUrgentToDo, constraints);
                                                                            currentUrgentToDo.setHorizontalAlignment(JLabel.CENTER);

                                                                            panel.remove(remaining);
                                                                            panel.remove(button13);
                                                                            panel.repaint();
                                                                            JButton ending = new JButton("End");
                                                                            panel.add(ending, constraints);
                                                                            JButton restart = new JButton("Restart");
                                                                            panel.add(restart, constraints);

                                                                            restart.addActionListener(e19 -> {
                                                                                new CalendarPopOut();
                                                                            });

                                                                            ending.addActionListener(e27 -> System.exit(0));
                                                                        }
                                                                    });


                                                                    button11.addActionListener(e5 -> {
                                                                        Object[] work = {urgentToDo.getText(), false, true};
                                                                        model.addRow(work);
                                                                        UrgentToDo.list = listing;
                                                                        urgentList.add(urgentToDo.getText());
                                                                        urgent = toDoEntries.setToDoEntriesAsUrgentToDo(urgentToDo.getText());
                                                                        urgent.addUrgentToDo(urgent);
                                                                        finalTopLabel.setText("Please input urgent todo items: (press Done when finished)");
                                                                    });


                                                                });
                                                            }
                                                        });
                                                    });
                                                    // System.exit(0);
                                                } else if (answerYesOrNo.getText().equals("n")) {
                                                    finalTopLabel.setText("Remaining tasks: " + toDoEntries.getList());
                                                    panel.remove(button8);
                                                    panel.remove(thingsToDo);
                                                    panel.remove(answerYesOrNo);
                                                    panel.remove(buttonStopping);
                                                    panel.remove(button3);
                                                    panel.repaint();

                                                    for (int count = 0; count < model.getRowCount(); count++) {
                                                        listing.add(model.getValueAt(count, 0).toString());
                                                    }
                                                    remaining.setText("Remaining tasks: " + listing);

                                                    panel.add(remaining, constraints);
                                                    finalTopLabel.setText("Do you wish to add todo list items to your calendar? [y/n]");
                                                    panel.add(yesOrNo, constraints);
                                                    panel.add(button10, constraints);
                                                    button10.addActionListener(e28 -> {
                                                        if (yesOrNo.getText().equals("y")) {
                                                            panel.remove(yesOrNo);
                                                            panel.remove(button10);
                                                            panel.repaint();
                                                            UrgentToDo.list = listing;
                                                            finalTopLabel.setText("Current todo items: " + listing
                                                                    + ". Please input the number of the todo item you wish to add. ");


                                                            panel.add(number, constraints);
                                                            panel.add(button9, constraints);

                                                            button9.addActionListener(e35 -> {
                                                                panel.remove(button9);
                                                                panel.remove(number);
                                                                panel.repaint();
                                                                String i = " & Things to do today: " + toDoEntries.addToCalendar(number.getText());
                                                                calendarEntries.logAddToDo(calendarEntries, i);
                                                                try {
                                                                    calendarEntries.save("data/calendaritems.txt", calendarEntries.getLog());
                                                                } catch (IOException ex) {
                                                                    finalTopLabel.setText("Error");
                                                                }
                                                                finalTopLabel.setText("Today's event: " + calendarEntries.load("data/calendaritems.txt"));
                                                                panel.remove(remaining);
                                                                panel.remove(button13);
                                                                panel.repaint();
                                                                JButton ending = new JButton("End");
                                                                panel.add(ending, constraints);
                                                                JButton restart = new JButton("Restart");
                                                                panel.add(restart, constraints);

                                                                restart.addActionListener(e36 -> {
                                                                    new CalendarPopOut();
                                                                });

                                                                ending.addActionListener(e37 -> System.exit(0));
                                                            });
                                                        }
                                                        if (yesOrNo.getText().equals("n")) {
                                                            panel.remove(remaining);
                                                            panel.remove(button10);
                                                            panel.remove(yesOrNo);
                                                            panel.repaint();
                                                            finalTopLabel.setText("Please input urgent todo items: ");
                                                            panel.add(urgentToDo, constraints);

                                                            panel.add(button11, constraints);

                                                            panel.add(urgentDone, constraints);

                                                            urgentDone.addActionListener(e41 -> {
                                                                for (int i = 0; i < model.getRowCount(); i++) {
                                                                    Boolean checked = (Boolean) model.getValueAt(i, 2);
                                                                    if (checked) {
                                                                        urgentItems.add((String) model.getValueAt(i, 0));
                                                                    }
                                                                }

                                                                for (int i = 0; i < UrgentToDo.list.size(); i++) {
                                                                    String str = UrgentToDo.list.toString();
                                                                    str = str.substring(1);
                                                                    str = str.substring(0, str.length() - 1);
                                                                    toDoEntries.getList().add(str);
                                                                }

                                                                panel.remove(button11);
                                                                panel.remove(urgentToDo);
                                                                panel.remove(urgentDone);
                                                                panel.repaint();
                                                                finalTopLabel.setText("Do you wish to remove any items from your todo list? [y/n]");
                                                                panel.add(removeItems, constraints);
                                                                panel.add(button12, constraints);

                                                                button12.addActionListener(e45 -> {
                                                                    if (removeItems.getText().equals("y")) {
                                                                        panel.remove(button12);
                                                                        panel.remove(removeItems);
                                                                        panel.repaint();
                                                                        finalTopLabel.setText("Please check off the tasks you have finished!");
                                                                        panel.add(button13, constraints);


                                                                        model.addTableModelListener(e46 -> {
                                                                            for (int i = 0; i < model.getRowCount(); i++) {
                                                                                Boolean checked = (Boolean) model.getValueAt(i, 1);
                                                                                if (checked) {
                                                                                    model.removeRow(i);
                                                                                    i--;
                                                                                }
                                                                            }
                                                                        });

                                                                        button13.addActionListener(e47 -> {
                                                                            urgent = toDoEntries.setToDoEntriesAsUrgentToDo(toDoItemRemove.getText());
                                                                            urgent.removeToDo(urgent);

                                                                            panel.remove(button13);
                                                                            panel.remove(toDoItemRemove);
                                                                            panel.repaint();
                                                                            for (int count = 0; count < model.getRowCount(); count++) {
                                                                                currentList.add(model.getValueAt(count, 0).toString());
                                                                                listing = currentList;
                                                                            }
                                                                            for (int i = 0; i < model.getRowCount(); i++) {
                                                                                Boolean checked = (Boolean) model.getValueAt(i, 2);
                                                                                if (checked) {
                                                                                    currentUrgents.add((String) model.getValueAt(i, 0));
                                                                                }
                                                                            }

                                                                            finalTopLabel.setText("The current items on your todo list: " + listing);
                                                                            JLabel currentUrgentToDo = new JLabel("The current items on your urgent todo list: "
                                                                                    + currentUrgents);

                                                                            finalTopPanel.add(currentUrgentToDo, constraints);
                                                                            currentUrgentToDo.setHorizontalAlignment(JLabel.CENTER);

                                                                            panel.remove(remaining);
                                                                            panel.remove(button13);
                                                                            panel.repaint();
                                                                            JButton ending = new JButton("End");
                                                                            panel.add(ending, constraints);
                                                                            JButton restart = new JButton("Restart");
                                                                            panel.add(restart, constraints);

                                                                            restart.addActionListener(e50 -> {
                                                                                new CalendarPopOut();
                                                                            });

                                                                            ending.addActionListener(e49 -> System.exit(0));

                                                                        });

                                                                    }
                                                                    if (removeItems.getText().equals("n")) {
                                                                        panel.remove(button12);
                                                                        panel.remove(removeItems);
                                                                        panel.remove(toDoItemRemove);
                                                                        //panel.remove(urgentDone);
                                                                        panel.repaint();
                                                                        for (int count = 0; count < model.getRowCount(); count++) {
                                                                            currentList.add(model.getValueAt(count, 0).toString());
                                                                            listing = currentList;
                                                                        }
                                                                        UrgentToDo.list = listing;
                                                                        finalTopLabel.setText("The current items on your todo list: " + UrgentToDo.list);
                                                                        JLabel currentUrgentToDo = new JLabel("The current items on your urgent todo list: " + urgentItems);
                                                                        finalTopPanel.add(currentUrgentToDo, constraints);
                                                                        currentUrgentToDo.setHorizontalAlignment(JLabel.CENTER);

                                                                        panel.remove(remaining);
                                                                        panel.remove(button13);
                                                                        panel.repaint();
                                                                        JButton ending = new JButton("End");
                                                                        panel.add(ending, constraints);
                                                                        JButton restart = new JButton("Restart");
                                                                        panel.add(restart, constraints);

                                                                        restart.addActionListener(e58 -> {
                                                                            new CalendarPopOut();
                                                                        });

                                                                        ending.addActionListener(e57 -> System.exit(0));
                                                                    }
                                                                });

                                                            });

                                                            button11.addActionListener(e44 -> {
                                                                Object[] work = {urgentToDo.getText(), false, true};
                                                                model.addRow(work);
                                                                UrgentToDo.list = listing;
                                                                urgentList.add(urgentToDo.getText());
                                                                urgent = toDoEntries.setToDoEntriesAsUrgentToDo(urgentToDo.getText());
                                                                urgent.addUrgentToDo(urgent);
                                                                finalTopLabel.setText("Please input urgent todo items: (press Done when finished)");
                                                            });
                                                        }
                                                    });
                                                    //    System.exit(0);
                                                }

                                            });

                                        });

                                        JTable table = new JTable(model);
                                        model.addColumn("Done");
                                        model.addColumn("Is it Urgent?");
                                        frameToDo.add(new JScrollPane(table));
                                        button7.addActionListener(e32 -> {
                                            try {
                                                toDoEntries.toDoResult(toDoEntries, toDos.getText());
                                            } catch (TooManyException e29) {
                                                while (toDoEntries.getList().size() > 10) {
                                                    toDoEntries.getList().remove(toDoEntries.getList().size() - 1);
                                                }
                                                finalTopLabel.setText("Too many things to do! Press Done");
                                            }
                                            ov.addObserver(ob);
                                            ov.setList(toDoEntries.list);
                                            finalTopPanel.add(notificationUpdate);
                                            notificationUpdate.setText(ToDoListUpdates.getNotification());
                                            notificationUpdate.setHorizontalAlignment(JLabel.CENTER);
                                            finalTopLabel.setText("Please enter things to do: (press Done when finished)");
                                            Object[] check = {toDos.getText(), false, false};
                                            model.addRow(check);
                                        });
                                    });

                                    panel.add(endApp, constraints);


                                    endApp.addActionListener(e9 -> System.exit(0));
                                } catch (IOException e21) {
                                    finalTopLabel.setText("Error");
                                }

                            });

                        });

                    });

                }
                if (yesOrNoAnswer.getText().equals("y")) {
                    try {
                        URI uri = new URI("https://calendar.google.com/calendar/r");
                        Desktop.getDesktop().browse(uri);
                        System.exit(0);
                    } catch (URISyntaxException | IOException ex) {
                        finalTopLabel.setText("Could not find url");

                    }
                }
            });
        });


        JLabel finalTopLabel1 = topLabel;
        button2.addActionListener(e -> {
            topPanel.remove(weatherNotification);
            panel.remove(endApp);
            panel.remove(toDoListShortCut);
            panel.remove(button1);
            panel.remove(button2);
            topPanel.remove(daysLeft);
            topPanel.repaint();
            panel.repaint();
            finalTopLabel1.setText("Please enter things to do: (press Done when finished)");
            panel.add(toDos, constraints);
            panel.add(button7, constraints);
            panel.add(buttonStop, constraints);

            frameToDo.setLayout(new BorderLayout());
            frameToDo.setSize(500, 500);
            JPanel panelToDo = new JPanel();
            frameToDo.add(panelToDo);

            buttonStop.addActionListener(e30 -> {
                frameToDo.setVisible(true);
                topPanel.remove(notificationUpdate);
                panel.remove(buttonStop);
                panel.remove(button7);
                panel.remove(toDos);
                panel.repaint();
                topPanel.repaint();
                toDoEntries.contains("q");
                thingsToDo.setText("Things to do: " + toDoEntries.getList());
                panel.add(thingsToDo, constraints);
                finalTopLabel1.setText("Have you finished a task? (y/n)");
                panel.add(answerYesOrNo, constraints);
                panel.add(button8, constraints);

                button8.addActionListener(e4 -> {
                    if (answerYesOrNo.getText().equals("y")) {
                        panel.remove(button8);
                        panel.remove(answerYesOrNo);
                        panel.repaint();
                        model.addTableModelListener(e1 -> {
                            for (int i = 0; i < model.getRowCount(); i++) {
                                Boolean checked = (Boolean) model.getValueAt(i, 1);
                                if (checked) {
                                    model.removeRow(i);
                                    i--;
                                }
                            }
                        });
                        finalTopLabel1.setText("Please check off the tasks that you have finished!");
                        panel.add(buttonStopping, constraints);
                        buttonStopping.addActionListener(e15 -> {
                            panel.remove(thingsToDo);
                            panel.repaint();
                            panel.remove(button8);
                            panel.remove(thingsToDo);
                            panel.remove(answerYesOrNo);
                            panel.remove(buttonStopping);
                            panel.remove(button3);
                            panel.repaint();

                            for (int count = 0; count < model.getRowCount(); count++) {
                                listing.add(model.getValueAt(count, 0).toString());
                            }
                            remaining.setText("Remaining tasks: " + listing);

                            panel.add(remaining, constraints);
                            finalTopLabel1.setText("Do you wish to add todo list items to your calendar? [y/n]");
                            panel.add(yesOrNo, constraints);
                            panel.add(button10, constraints);
                            button10.addActionListener(e0 -> {
                                if (yesOrNo.getText().equals("y")) {
                                    panel.remove(yesOrNo);
                                    panel.remove(button10);
                                    panel.repaint();
                                    UrgentToDo.list = listing;
                                    finalTopLabel1.setText("Current todo items: " + listing
                                            + ". Please input the number of the todo item you wish to add. ");


                                    panel.add(number, constraints);
                                    panel.add(button9, constraints);

                                    button9.addActionListener(e11 -> {
                                        panel.remove(button9);
                                        panel.remove(number);
                                        panel.repaint();
                                        String i = " & Things to do today: " + toDoEntries.addToCalendar(number.getText());
                                        calendarEntries.logAddToDo(calendarEntries, i);
                                        try {
                                            calendarEntries.save("data/calendaritems.txt", calendarEntries.getLog());
                                        } catch (IOException ex) {
                                            finalTopLabel1.setText("Error");
                                        }
                                        finalTopLabel1.setText("Today's event: " + calendarEntries.load("data/calendaritems.txt"));
                                        panel.remove(remaining);
                                        panel.remove(button13);
                                        panel.repaint();
                                        JButton ending = new JButton("End");
                                        panel.add(ending, constraints);
                                        JButton restart = new JButton("Restart");
                                        panel.add(restart, constraints);

                                        restart.addActionListener(e1 -> {
                                            new CalendarPopOut();
                                        });

                                        ending.addActionListener(e2 -> System.exit(0));
                                    });
                                }
                                if (yesOrNo.getText().equals("n")) {
                                    panel.remove(remaining);
                                    panel.remove(button10);
                                    panel.remove(yesOrNo);
                                    panel.repaint();
                                    finalTopLabel1.setText("Please input urgent todo items: ");
                                    panel.add(urgentToDo, constraints);

                                    panel.add(button11, constraints);

                                    panel.add(urgentDone, constraints);

                                    urgentDone.addActionListener(e10 -> {
                                        for (int i = 0; i < model.getRowCount(); i++) {
                                            Boolean checked = (Boolean) model.getValueAt(i, 2);
                                            if (checked) {
                                                urgentItems.add((String) model.getValueAt(i, 0));
                                            }
                                        }

                                        for (int i = 0; i < UrgentToDo.list.size(); i++) {
                                            String str = UrgentToDo.list.toString();
                                            str = str.substring(1);
                                            str = str.substring(0, str.length() - 1);
                                            toDoEntries.getList().add(str);
                                        }

                                        panel.remove(button11);
                                        panel.remove(urgentToDo);
                                        panel.remove(urgentDone);
                                        panel.repaint();
                                        finalTopLabel1.setText("Do you wish to remove any items from your todo list? [y/n]");
                                        panel.add(removeItems, constraints);
                                        panel.add(button12, constraints);

                                        button12.addActionListener(e2 -> {
                                            if (removeItems.getText().equals("y")) {
                                                panel.remove(button12);
                                                panel.remove(removeItems);
                                                panel.repaint();
                                                finalTopLabel1.setText("Please check off the tasks you have finished!");
                                                panel.add(button13, constraints);


                                                model.addTableModelListener(e1 -> {
                                                    for (int i = 0; i < model.getRowCount(); i++) {
                                                        Boolean checked = (Boolean) model.getValueAt(i, 1);
                                                        if (checked) {
                                                            model.removeRow(i);
                                                            i--;
                                                        }
                                                    }
                                                });

                                                button13.addActionListener(e13 -> {
                                                    urgent = toDoEntries.setToDoEntriesAsUrgentToDo(toDoItemRemove.getText());
                                                    urgent.removeToDo(urgent);
                                                    //urgent.removeUrgentToDo(urgent);
                                                    panel.remove(button13);
                                                    panel.remove(toDoItemRemove);
                                                    panel.repaint();
                                                    for (int count = 0; count < model.getRowCount(); count++) {
                                                        currentList.add(model.getValueAt(count, 0).toString());
                                                        listing = currentList;
                                                    }
                                                    for (int i = 0; i < model.getRowCount(); i++) {
                                                        Boolean checked = (Boolean) model.getValueAt(i, 2);
                                                        if (checked) {
                                                            currentUrgents.add((String) model.getValueAt(i, 0));
                                                        }
                                                    }
                                                    finalTopLabel1.setText("The current items on your todo list: " + listing);
                                                    JLabel currentUrgentToDo = new JLabel("The current items on your urgent todo list: "
                                                            + currentUrgents);

                                                    topPanel.add(currentUrgentToDo, constraints);
                                                    currentUrgentToDo.setHorizontalAlignment(JLabel.CENTER);

                                                    panel.remove(remaining);
                                                    panel.remove(button13);
                                                    panel.repaint();
                                                    JButton ending = new JButton("End");
                                                    panel.add(ending, constraints);
                                                    JButton restart = new JButton("Restart");
                                                    panel.add(restart, constraints);

                                                    restart.addActionListener(e1 -> {
                                                        new CalendarPopOut();
                                                    });

                                                    ending.addActionListener(e3 -> System.exit(0));
                                                });

                                            }
                                            if (removeItems.getText().equals("n")) {
                                                panel.remove(button12);
                                                panel.remove(removeItems);
                                                panel.remove(toDoItemRemove);
                                                //panel.remove(urgentDone);
                                                panel.repaint();
                                                for (int count = 0; count < model.getRowCount(); count++) {
                                                    currentList.add(model.getValueAt(count, 0).toString());
                                                    listing = currentList;
                                                }
                                                UrgentToDo.list = listing;
                                                finalTopLabel1.setText("The current items on your todo list: " + UrgentToDo.list);
                                                JLabel currentUrgentToDo = new JLabel("The current items on your urgent todo list: " + urgentItems);
                                                topPanel.add(currentUrgentToDo, constraints);
                                                currentUrgentToDo.setHorizontalAlignment(JLabel.CENTER);

                                                panel.remove(remaining);
                                                panel.remove(button13);
                                                panel.repaint();
                                                JButton ending = new JButton("End");
                                                panel.add(ending, constraints);
                                                JButton restart = new JButton("Restart");
                                                panel.add(restart, constraints);

                                                restart.addActionListener(e19 -> {
                                                    new CalendarPopOut();
                                                });

                                                ending.addActionListener(e20 -> System.exit(0));
                                            }
                                        });


                                        button11.addActionListener(e5 -> {
                                            Object[] work = {urgentToDo.getText(), false, true};
                                            model.addRow(work);
                                            UrgentToDo.list = listing;
                                            urgentList.add(urgentToDo.getText());
                                            urgent = toDoEntries.setToDoEntriesAsUrgentToDo(urgentToDo.getText());
                                            urgent.addUrgentToDo(urgent);
                                            finalTopLabel1.setText("Please input urgent todo items: (press Done when finished)");
                                        });


                                    });
                                }
                            });
                        });
                        // System.exit(0);
                    } else if (answerYesOrNo.getText().equals("n")) {
                        finalTopLabel1.setText("Remaining tasks: " + toDoEntries.getList());
                        panel.remove(button8);
                        panel.remove(thingsToDo);
                        panel.remove(answerYesOrNo);
                        panel.remove(buttonStopping);
                        panel.remove(button3);
                        panel.repaint();

                        for (int count = 0; count < model.getRowCount(); count++) {
                            listing.add(model.getValueAt(count, 0).toString());
                        }
                        remaining.setText("Remaining tasks: " + listing);

                        panel.add(remaining, constraints);
                        finalTopLabel1.setText("Do you wish to add todo list items to your calendar? [y/n]");
                        panel.add(yesOrNo, constraints);
                        panel.add(button10, constraints);
                        button10.addActionListener(e0 -> {
                            if (yesOrNo.getText().equals("y")) {
                                panel.remove(yesOrNo);
                                panel.remove(button10);
                                panel.repaint();
                                UrgentToDo.list = listing;
                                finalTopLabel1.setText("Current todo items: " + listing
                                        + ". Please input the number of the todo item you wish to add. ");


                                panel.add(number, constraints);
                                panel.add(button9, constraints);

                                button9.addActionListener(e38 -> {
                                    panel.remove(button9);
                                    panel.remove(number);
                                    panel.repaint();
                                    String i = " & Things to do today: " + toDoEntries.addToCalendar(number.getText());
                                    calendarEntries.logAddToDo(calendarEntries, i);
                                    try {
                                        calendarEntries.save("data/calendaritems.txt", calendarEntries.getLog());
                                    } catch (IOException ex) {
                                        finalTopLabel1.setText("Error");
                                    }
                                    finalTopLabel1.setText("Today's event: " + calendarEntries.load("data/calendaritems.txt"));
                                    panel.remove(remaining);
                                    panel.remove(button13);
                                    panel.repaint();
                                    JButton ending = new JButton("End");
                                    panel.add(ending, constraints);
                                    JButton restart = new JButton("Restart");
                                    panel.add(restart, constraints);

                                    restart.addActionListener(e1 -> {
                                        new CalendarPopOut();
                                    });

                                    ending.addActionListener(e2 -> System.exit(0));
                                });
                            }
                            if (yesOrNo.getText().equals("n")) {
                                panel.remove(remaining);
                                panel.remove(button10);
                                panel.remove(yesOrNo);
                                panel.repaint();
                                finalTopLabel1.setText("Please input urgent todo items: ");
                                panel.add(urgentToDo, constraints);

                                panel.add(button11, constraints);

                                panel.add(urgentDone, constraints);

                                urgentDone.addActionListener(e42 -> {
                                    for (int i = 0; i < model.getRowCount(); i++) {
                                        Boolean checked = (Boolean) model.getValueAt(i, 2);
                                        if (checked) {
                                            urgentItems.add((String) model.getValueAt(i, 0));
                                        }
                                    }

                                    for (int i = 0; i < UrgentToDo.list.size(); i++) {
                                        String str = UrgentToDo.list.toString();
                                        str = str.substring(1);
                                        str = str.substring(0, str.length() - 1);
                                        toDoEntries.getList().add(str);
                                    }

                                    panel.remove(button11);
                                    panel.remove(urgentToDo);
                                    panel.remove(urgentDone);
                                    panel.repaint();
                                    finalTopLabel1.setText("Do you wish to remove any items from your todo list? [y/n]");
                                    panel.add(removeItems, constraints);
                                    panel.add(button12, constraints);

                                    button12.addActionListener(e48 -> {
                                        if (removeItems.getText().equals("y")) {
                                            panel.remove(button12);
                                            panel.remove(removeItems);
                                            panel.repaint();
                                            finalTopLabel1.setText("Please check off the tasks you have finished!");
                                            panel.add(button13, constraints);


                                            model.addTableModelListener(e1 -> {
                                                for (int i = 0; i < model.getRowCount(); i++) {
                                                    Boolean checked = (Boolean) model.getValueAt(i, 1);
                                                    if (checked) {
                                                        model.removeRow(i);
                                                        i--;
                                                    }
                                                }
                                            });

                                            button13.addActionListener(e43 -> {
                                                urgent = toDoEntries.setToDoEntriesAsUrgentToDo(toDoItemRemove.getText());
                                                urgent.removeToDo(urgent);

                                                panel.remove(button13);
                                                panel.remove(toDoItemRemove);
                                                panel.repaint();
                                                for (int count = 0; count < model.getRowCount(); count++) {
                                                    currentList.add(model.getValueAt(count, 0).toString());
                                                    listing = currentList;
                                                }
                                                for (int i = 0; i < model.getRowCount(); i++) {
                                                    Boolean checked = (Boolean) model.getValueAt(i, 2);
                                                    if (checked) {
                                                        currentUrgents.add((String) model.getValueAt(i, 0));
                                                    }
                                                }

                                                finalTopLabel1.setText("The current items on your todo list: " + listing);
                                                JLabel currentUrgentToDo = new JLabel("The current items on your urgent todo list: "
                                                        + currentUrgents);

                                                topPanel.add(currentUrgentToDo, constraints);
                                                currentUrgentToDo.setHorizontalAlignment(JLabel.CENTER);

                                                panel.remove(remaining);
                                                panel.remove(button13);
                                                panel.repaint();
                                                JButton ending = new JButton("End");
                                                panel.add(ending, constraints);
                                                JButton restart = new JButton("Restart");
                                                panel.add(restart, constraints);

                                                restart.addActionListener(e52 -> {
                                                    new CalendarPopOut();
                                                });

                                                ending.addActionListener(e51 -> System.exit(0));

                                            });

                                        }
                                        if (removeItems.getText().equals("n")) {
                                            panel.remove(button12);
                                            panel.remove(removeItems);
                                            panel.remove(toDoItemRemove);
                                            //panel.remove(urgentDone);
                                            panel.repaint();
                                            for (int count = 0; count < model.getRowCount(); count++) {
                                                currentList.add(model.getValueAt(count, 0).toString());
                                                listing = currentList;
                                            }
                                            UrgentToDo.list = listing;
                                            finalTopLabel1.setText("The current items on your todo list: " + UrgentToDo.list);
                                            JLabel currentUrgentToDo = new JLabel("The current items on your urgent todo list: " + urgentItems);
                                            topPanel.add(currentUrgentToDo, constraints);
                                            currentUrgentToDo.setHorizontalAlignment(JLabel.CENTER);

                                            panel.remove(remaining);
                                            panel.remove(button13);
                                            panel.repaint();
                                            JButton ending = new JButton("End");
                                            panel.add(ending, constraints);
                                            JButton restart = new JButton("Restart");
                                            panel.add(restart, constraints);

                                            restart.addActionListener(e55 -> {
                                                new CalendarPopOut();
                                            });

                                            ending.addActionListener(e56 -> System.exit(0));
                                        }
                                    });

                                });

                                button11.addActionListener(e40 -> {
                                    Object[] work = {urgentToDo.getText(), false, true};
                                    model.addRow(work);
                                    UrgentToDo.list = listing;
                                    urgentList.add(urgentToDo.getText());
                                    urgent = toDoEntries.setToDoEntriesAsUrgentToDo(urgentToDo.getText());
                                    urgent.addUrgentToDo(urgent);
                                    finalTopLabel1.setText("Please input urgent todo items: (press Done when finished)");
                                });
                            }
                        });
                        //    System.exit(0);
                    }

                });

            });

            JTable table = new JTable(model);
            model.addColumn("Done");
            model.addColumn("Is it Urgent?");
            frameToDo.add(new JScrollPane(table));
            button7.addActionListener(e33 -> {
                try {
                    toDoEntries.toDoResult(toDoEntries, toDos.getText());
                } catch (TooManyException e0) {
                    while (toDoEntries.getList().size() > 10) {
                        toDoEntries.getList().remove(toDoEntries.getList().size() - 1);
                    }
                    finalTopLabel1.setText("Too many things to do! Press Done");
                }
                ov.addObserver(ob);
                ov.setList(toDoEntries.list);
                topPanel.add(notificationUpdate);
                notificationUpdate.setText(ToDoListUpdates.getNotification());
                notificationUpdate.setHorizontalAlignment(JLabel.CENTER);
                finalTopLabel1.setText("Please enter things to do: (press Done when finished)");
                Object[] check = {toDos.getText(), false, false};
                model.addRow(check);
            });
        });

        mainPanel.add(topPanel);
        mainPanel.add(panel);


        panel.add(button1, constraints);
        panel.add(button2, constraints);


        Dimension expectedDimension = new Dimension(500, 500);

        mainPanel.setPreferredSize(expectedDimension);
        mainPanel.setMaximumSize(expectedDimension);
        mainPanel.setMinimumSize(expectedDimension);

        Box box = new Box(BoxLayout.Y_AXIS);
        box.add(Box.createVerticalGlue());
        box.add(mainPanel, BorderLayout.CENTER);
        box.add(Box.createVerticalGlue());
        jframe.add(box);
        jframe.setSize(new Dimension(200, 200));

        jframe.setLayout(new BorderLayout());
        jframe.setContentPane(new JLabel(new ImageIcon("data/image.jpg")));
        FlowLayout layout = new FlowLayout();
        layout.setHgap(10);
        layout.setVgap(20);
        layout.setAlignment(FlowLayout.CENTER);
        jframe.setLayout(layout);

        jframe.add(mainPanel);


        jframe.pack();


        // Adding panel to the frame so that it pops up

        jframe.setSize(600, 600);
        jframe.setLocationRelativeTo(null);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jframe.setVisible(true);

    }

}

