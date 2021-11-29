package com.example.cmput_301_project;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class HabitUnitTest {
    @Test
    public void CreateHabit() {
        
        Habit testHabit = new Habit("TestHabit", new Date(2017,11,12), "For testing uses", 64, true);
        assertEquals(testHabit.getHabitName(), "TestHabit");
    }

    @Test
    public void ChangeAttributes() {

        Habit testHabit = new Habit("TestHabit", new Date(2017-1900,11,12), "For testing uses", 64, true);
        assertEquals(testHabit.getHabitName(), "TestHabit");
        testHabit.setHabitName("TestHabitRename");
        assertNotEquals(testHabit.getHabitName(), "TestHabit");
        assertEquals(testHabit.getHabitName(), "TestHabitRename");
        testHabit.setPublic(false);
        assertEquals(testHabit.getPublic(), false);
        testHabit.setReason("To change to something else");
        assertNotEquals(testHabit.getReason(), "For testing uses");
        assertEquals(testHabit.getReason(), "To change to something else");
        testHabit.setStartDate( new Date(2018-1900,11,12));
        assertNotEquals(testHabit.getStartDate(), new Date(2017-1900,11,12));
        assertEquals(testHabit.getStartDate(), new Date(2018-1900,11,12));
        testHabit.setWeekdays(1);
        assertNotEquals(testHabit.getWeekdays(), 64);
        assertEquals(testHabit.getWeekdays(), 1);

    }

    @Test
    public void CreateHabitEvents() {

        Habit testHabit = new Habit("TestHabit", new Date(2017-1900,11,12), "For testing uses", 64, true);
        assertEquals(testHabit.getHabitName(), "TestHabit");
        testHabit.addHabitEvent(new HabitEvent("2000/11/12","TestEventHabit"));
        assertEquals(testHabit.getHabitEventTable().size(), 1);
        assertEquals(testHabit.getHabitEventTable().get(0).getTitle(), "TestEventHabit");
        testHabit.getHabitEventTable().remove(testHabit.getHabitEventTable().get(0));
        assertEquals(testHabit.getHabitEventTable().size(), 0);

    }

}