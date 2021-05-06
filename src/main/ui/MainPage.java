package main.ui;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainPage {
    public MainPage() {
        new CalendarPopOut();

 
    }

    // EFFECTS: run program
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException
                | ClassNotFoundException e) {
            e.printStackTrace();
        }
        /*new ReadWebPage();
        ReadWebPage.parseWeather();*/
       // System.out.println("Remember to check calendar!");
        new MainPage();


    }


}


