/**
 * Today's habits page activity
 */
package com.example.cmput_301_project;


import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Class that creates the page and sets up everything
 */
public class TodayHabits extends AppCompatActivity {

    // Variable declaration
    ListView toDoList;
    ListView completedList;
    static ArrayList<HabitEvent> toDoEventsList;
    static ArrayList<HabitEvent> completedEventsList;
    static TodayHabitsAdapter toDoAdapter;
    static TodayHabitsAdapter completedAdapter;
    static AccountData accountData;

    // On create method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set layout
        setContentView(R.layout.today_habits);
        accountData = AccountData.create();

        // Find the listview
        toDoList = findViewById(R.id.toDoList);
        completedList = findViewById(R.id.completedList);
        // Add elements
        toDoEventsList = new ArrayList<>();
        completedEventsList = new ArrayList<>();

        accountData.getActiveUserAccount().getTodayHabitEvents(toDoEventsList, completedEventsList, true, false);

        // Set up adapter for list view
        toDoAdapter = new TodayHabitsAdapter(this, R.layout.today_habits_content, toDoEventsList);
        completedAdapter = new TodayHabitsAdapter(this, R.layout.today_habits_content, completedEventsList);
        toDoList.setAdapter(toDoAdapter);
        completedList.setAdapter(completedAdapter);
    }

    public void update() {

    }

    /**
     * Returns the ToDo array list
     * @return Array list containing ToDo events
     */
    public static ArrayList<HabitEvent> getToDoList(){
        return toDoEventsList;
    }

    /**
     * Returns the Completed array list
     * @return Array list containing Completed events
     */
    public static ArrayList<HabitEvent> getCompleteList(){
        return completedEventsList;
    }

    /**
     * Returns the ToDo adapter
     * @return Adapter for ToDo list
     */
    public static TodayHabitsAdapter getToDoListAdapter(){
        return toDoAdapter;
    }

    /**
     * Returns the Completed adapter
     * @return Adapter for ToDo list
     */
    public static TodayHabitsAdapter getCompletedListAdapter(){
        return completedAdapter;
    }
}
