package com.example.cmput_301_project;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class Habit {
    private String id;
    private String habitName;
    private Date startDate;
    private String reason;

    // one bit per day of the week
    // 1 = Monday
    // 2 = Tuesday
    // 3 = Monday AND Tuesday
    // 4 = Wednesday
    // etc.
    // (so each day takes 1 bit, with the most significant bit unused)
    private byte weekdays;

    private HashMap<String, HabitEvent> habitEventTable = new HashMap<String, HabitEvent>();

    public Habit(String habitName, Date startDate, String reason, byte weekdays) {
        this.id = UUID.randomUUID().toString();
        this.habitName = habitName;
        this.startDate = startDate;
        this.reason = reason;
        setIsOnDayOfWeek(weekdays);
    }

    public String getId() {
        return id;
    }

    public String getHabitName() {
        return habitName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getReason() {
        return reason;
    }

    public boolean getIsOnDayOfWeek(int day) {
        // 0 = Monday, 1 = Tuesday, 2 = Wednesday, etc.
        return ((weekdays >> day) & 1) == 1;
    }

    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }

    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public void setReason(String reason) { this.reason = reason; }

    public void setWeekdays(byte weekdays) { this.weekdays = weekdays; }

    public boolean setIsOnDayOfWeek(byte weekdays) {
        // only accept numbers in range
        if (weekdays >= 0) {
            this.weekdays = weekdays;
            return true;
        }
        return false;
    }

    public HashMap<String, HabitEvent> getHabitEventTable() {
        return habitEventTable;
    }

    public void setHabitEventTable(HashMap<String, HabitEvent> habitEventTable) {
        this.habitEventTable = habitEventTable;
    }
}
