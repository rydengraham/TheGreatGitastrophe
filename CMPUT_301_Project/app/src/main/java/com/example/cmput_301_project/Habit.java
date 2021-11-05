package com.example.cmput_301_project;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class Habit implements Serializable {
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
    private boolean isExpanded;

    private HashMap<String, HabitEvent> habitEventTable = new HashMap<String, HabitEvent>();

    public Habit(String habitName, Date startDate, String reason, byte weekdays) {
        this.id = UUID.randomUUID().toString();
        this.habitName = habitName;
        this.startDate = startDate;
        this.reason = reason;
        setIsOnDayOfWeek(weekdays);
    }

    // GETTERS
    public String getId() {
        return id;
    }

    public String getHabitName() {
        return habitName;
    }

    public boolean isExpanded() {
        return isExpanded;
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

    // SETTERS
    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }

    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public void setReason(String reason) { this.reason = reason; }

    public void setWeekdays(byte weekdays) { this.weekdays = weekdays; }

    // Checks to see if a day applies to a habit
    public boolean setIsOnDayOfWeek(byte weekdays) {
        // only accept numbers in range
        if (weekdays >= 0) {
            this.weekdays = weekdays;
            return true;
        }
        return false;
    }



}