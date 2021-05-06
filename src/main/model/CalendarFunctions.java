package main.model;

import java.time.Clock;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Calendar;

public class CalendarFunctions {

    public static int getDate() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }


    // code source: https://stackoverflow.com/questions/668083/how-can-
    // i-build-a-list-of-days-months-years-from-a-calendar-object-in-java

    //EFFECTS: subtracts length of month by current day for days left in month
    public static int daysLeft() {
        LocalDate today = LocalDate.now(Clock.systemDefaultZone());
        int year = today.getYear();
        int monthNumber = today.getMonthValue();
        int dayOfMonth = today.getDayOfMonth();
        LocalDate ld = LocalDate.of(year, monthNumber, dayOfMonth);
        YearMonth yearMonth = YearMonth.from(ld);
        int lengthOfMonth = yearMonth.lengthOfMonth();
        // Calendar cal = Calendar.getInstance();
        //  System.out.println("The number of days left in the month: " + daysleft);
        return ((lengthOfMonth - dayOfMonth));
    }

}

