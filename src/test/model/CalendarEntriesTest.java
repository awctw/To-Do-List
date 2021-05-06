package test.model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.model.CalendarEntries;

class CalendarEntriesTest {
	 private CalendarEntries entry1;
	 private CalendarEntries event1;
	 private ArrayList<CalendarEntries> logtest;

	 @BeforeEach
	    void runBefore() {
	        entry1 = new CalendarEntries();
	        event1 = new CalendarEntries();
	        logtest = new ArrayList<>();

	        logtest.add(entry1);

	        entry1.add("Hello");
	        entry1.add("Test");


	    }


	    @Test
	    public void testGetList(){

	        assertEquals(entry1.getList(), entry1.getList());
	    }

	    @Test
	    public void testGetLog(){
	        assertEquals(entry1.getLog(), entry1.getLog());
	    }

	    @Test
	    public void testSetEvent() {
	        event1.set("Test");
	        assertEquals("Test", event1.getItem());

	    }

	    @Test
	    public void getEventsTest() {
	        assertEquals("Hello", entry1.getList(0));
	    }

	    @Test
	    public void containsEventTest() {
	        assertTrue(entry1.contains("Hello"));
	    }

	    @Test
	    public void toStringPrintTest() {
	        assertEquals("Hello", entry1.toStringPrint(logtest));
	    }

	    @Test
	    public void logResultTest() {
	        event1.logResult(event1, "Test", "Test");
	        assertTrue(event1.contains("Test"));
	    }
	    @Test
	    public void logAddToDoTest(){
	        event1.logAddToDo(event1, "Test");
	        assertTrue(event1.contains("Test"));
	    }
	    @Test
	    public void saveTest() throws IOException {
	        event1.logResult(event1, "Test", "Test");
	        event1.save("data/tests.txt", logtest);
	        assertEquals("Test", entry1.load("tests.txt"));
	    }

	    @Test
	    public void loadTest() {
	        assertEquals("Test", event1.load("data/tests.txt"));
	        assertNull(event1.load("data/testempty.txt"));

	        entry1.load("data/tests.txt");

	    }

	    @Test
	    public void addingTest() {
	        assertEquals("Work at 10am, location: School", event1.add("Work", "10am", "School"));
	    }
	

}
