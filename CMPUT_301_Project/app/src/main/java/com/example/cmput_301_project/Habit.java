/**
 * Class to represent the habits
 */
package com.example.cmput_301_project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Habit implements Serializable {
    private String id;
    private String habitName;
    private Date startDate;
    private String reason;
    private Boolean isPublic;

    // one bit per day of the week
    // 1 = Monday
    // 2 = Tuesday
    // 3 = Monday AND Tuesday
    // 4 = Wednesday
    // etc.
    // (so each day takes 1 bit, with the most significant bit unused)
    private int weekdays;
    private boolean isExpanded;

    private List<HabitEvent> habitEventTable;

    public Habit(String habitName, Date startDate, String reason, int weekdays, boolean isPublic) {
        this.id = UUID.randomUUID().toString();
        this.habitName = habitName;
        this.startDate = startDate;
        this.reason = reason;
        this.habitEventTable = new ArrayList<>();
        this.isPublic = isPublic;
        setIsOnDayOfWeek(weekdays);
    }

    public Habit() {
        /* Required empty public constructor */
    }

    /**
     * Adds a new habit event to the table
     * @param newHabitEvent
     */
    public void addHabitEvent(HabitEvent newHabitEvent) {
        this.habitEventTable.add(newHabitEvent);
    }

    /**
     * Deletes a habit even from the table
     * @param newHabitEvent
     */
    public void deleteHabitEvent(HabitEvent newHabitEvent) {
        this.habitEventTable.remove(newHabitEvent);
    }

    /**
     * Updates a habit event item at a given position
     * @param position
     * @param updatedHabitEvent
     */
    public void updateHabitEvent(int position, HabitEvent updatedHabitEvent) {
        this.habitEventTable.set(position, updatedHabitEvent);
    }

    public Boolean getPublic() {
        return isPublic;
    }


    // GETTERS
    /**
     * Getters/Setters
     * @return
     */
    public List<HabitEvent> getHabitEventTable() {
        return this.habitEventTable;
    }

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

    public int getWeekdays() {
        return weekdays;
    }

    public boolean getIsOnDayOfWeek(int day) {
        // 0 = Monday, 1 = Tuesday, 2 = Wednesday, etc.
        return ((weekdays >> day) & 1) == 1;
    }

    public void setHabitEventTable(List<HabitEvent> habitEventTable) {
        this.habitEventTable = habitEventTable;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }

    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public void setReason(String reason) { this.reason = reason; }

    public void setWeekdays(int weekdays) { this.weekdays = weekdays; }

    public void setPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }


    /**
     * Checks to see if a day applies to a habit
     * @param weekdays
     * @return
     */
    public boolean setIsOnDayOfWeek(int weekdays) {
        // only accept numbers in range
        if (weekdays >= 0) {
            this.weekdays = weekdays;
            return true;
        }
        return false;
    }



}