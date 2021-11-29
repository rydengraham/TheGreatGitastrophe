package com.example.cmput_301_project;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class HabitLocationTest {
    /**
     * Example local unit test, which will execute on the development machine (host).
     *
     * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
     */

    @Test
    public void CreateHabitLocation() {
        HabitLocation testHabitLocation = new HabitLocation("Blunt Street","1342 037 Blunt Street NW", "34 36 33 S", "58 22 54 W");
        assertEquals(testHabitLocation.getLocationName(), "Blunt Street");
        assertEquals(testHabitLocation.getAddress(), "1342 037 Blunt Street NW");
        assertEquals(testHabitLocation.getLatitude(), "34 36 33 S");
        assertEquals(testHabitLocation.getLongitude(), "58 22 54 W");
        HabitLocation testHabitLocation2 = new HabitLocation("1984 Grove Street SE", "64 66 63 S", "58 69 88 E");
        assertEquals(testHabitLocation2.getAddress(), "1984 Grove Street SE");
        assertEquals(testHabitLocation2.getLongitude(), "64 66 63 S");
        assertEquals(testHabitLocation2.getLatitude(), "58 69 88 E");
        HabitLocation testHabitLocation3 = testHabitLocation2;
        assertEquals(testHabitLocation3.getAddress(), "1984 Grove Street SE");
        assertEquals(testHabitLocation2.getLongitude(), "64 66 63 S");
        assertEquals(testHabitLocation2.getLatitude(), "58 69 88 E");
    }

    @Test
    public void ChangeAttributes() {
        HabitLocation testHabitLocation = new HabitLocation("Blunt Street","1342 037 Blunt Street NW", "34 36 33 S", "58 22 54 W");
        assertEquals(testHabitLocation.getLocationName(), "Blunt Street");
        assertEquals(testHabitLocation.getAddress(), "1342 037 Blunt Street NW");
        assertEquals(testHabitLocation.getLatitude(), "34 36 33 S");
        assertEquals(testHabitLocation.getLongitude(), "58 22 54 W");
        testHabitLocation.setLocationName("Neptune Valley");
        testHabitLocation.setAddress("1897 691 Neptune Avenue NE");
        testHabitLocation.setLatitude("21 12 09 N");
        testHabitLocation.setLongitude("01 32 49 E");
        assertEquals(testHabitLocation.getLocationName(), "Neptune Valley");
        assertEquals(testHabitLocation.getAddress(), "1897 691 Neptune Avenue NE");
        assertEquals(testHabitLocation.getLatitude(), "21 12 09 N");
        assertEquals(testHabitLocation.getLongitude(), "01 32 49 E");

    }


}