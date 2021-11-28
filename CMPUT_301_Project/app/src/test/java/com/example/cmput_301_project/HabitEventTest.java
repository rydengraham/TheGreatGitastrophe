package com.example.cmput_301_project;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class HabitEventTest {


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

    @Test
    public void CreateHabitEvent() {
        HabitEvent testHabitEvent = new HabitEvent("2001/10/02", "TestHabitEvent");
        assertEquals(testHabitEvent.getTitle(), "TestHabitEvent");
    }

    @Test
    public void AddToHabit() {
        Habit testHabit = new Habit("TestHabit", new Date(2017-1900,11,12), "For testing uses", 64, true);
        assertEquals(testHabit.getHabitName(), "TestHabit");
        HabitEvent testHabitEvent = new HabitEvent("2001/10/02", "TestHabitEvent");
        assertEquals(testHabitEvent.getTitle(), "TestHabitEvent");
        testHabit.addHabitEvent(testHabitEvent);
        assertEquals(testHabit.getHabitEventTable().size(), 1);
        assertEquals(testHabit.getHabitEventTable().get(0).getTitle(), "TestHabitEvent");
        testHabit.getHabitEventTable().remove(testHabit.getHabitEventTable().get(0));
        assertEquals(testHabit.getHabitEventTable().size(), 0);
        testHabit.addHabitEvent(testHabitEvent);
        testHabit.addHabitEvent(new HabitEvent("2005/10/02", "TestHabitEvent2"));
        assertEquals(testHabit.getHabitEventTable().size(), 2);
        assertEquals(testHabit.getHabitEventTable().get(0).getTitle(), "TestHabitEvent");
        assertEquals(testHabit.getHabitEventTable().get(1).getTitle(), "TestHabitEvent2");
    }

    @Test
    public void ChangeAttributes() {
        HabitEvent testHabitEvent = new HabitEvent("2001/10/02", "TestHabitEvent");
        assertEquals(testHabitEvent.getTitle(), "TestHabitEvent");
        assertEquals(testHabitEvent.getDate(), "2001/10/02");
        assertEquals(testHabitEvent.isCompleted(), false);
        assertEquals(testHabitEvent.getComment(), null);
        testHabitEvent.setComment("Try");
        testHabitEvent.setDate("2004/10/04");
        testHabitEvent.setCompleted(true);
        testHabitEvent.setTitle("Rename");
        assertEquals(testHabitEvent.getTitle(), "Rename");
        assertEquals(testHabitEvent.getDate(), "2004/10/04");
        assertEquals(testHabitEvent.isCompleted(), true);
        assertEquals(testHabitEvent.getComment(), "Try");

    }


}