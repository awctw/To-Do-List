package test.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.model.CalendarFunctions;

//package test;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CalendarFunctionsTests {
    @BeforeEach
    void runBefore() {
        new CalendarFunctions();

    }

    @Test
    void getDateTest() {
        assertEquals(2019, CalendarFunctions.getDate());
    }

    @Test
    void daysLeftTest() {
        assertEquals(CalendarFunctions.daysLeft() , CalendarFunctions.daysLeft());
    }
}


