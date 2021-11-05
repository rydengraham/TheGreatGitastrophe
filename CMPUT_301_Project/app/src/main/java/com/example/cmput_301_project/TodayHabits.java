package com.example.cmput_301_project;


import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
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

    // On create method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set layout
        setContentView(R.layout.today_habits);

        // Find the listview
        toDoList = findViewById(R.id.toDoList);
        completedList = findViewById(R.id.completedList);
        // Add test elements
        toDoEventsList = new ArrayList<>();
        completedEventsList = new ArrayList<>();

        // Temporary list filling variables
        toDoEventsList.add(new HabitEvent("COMMENT", "TITLE"));
        toDoEventsList.add(new HabitEvent("COMMENT2", "TITLE2"));
        toDoEventsList.add(new HabitEvent("COMMENT3", "TITLE3"));
        toDoEventsList.add(new HabitEvent("COMMENT4", "TITLE4"));
        toDoEventsList.add(new HabitEvent("COMMENT5", "TITLE5"));
        toDoEventsList.add(new HabitEvent("COMMENT6", "TITLE6"));

        // Set up adapter for list view
        toDoAdapter = new TodayHabitsAdapter(this, R.layout.today_habits_content, toDoEventsList);
        completedAdapter = new TodayHabitsAdapter(this, R.layout.today_habits_content, completedEventsList);
        toDoList.setAdapter(toDoAdapter);
        completedList.setAdapter(completedAdapter);

        // TODO: Implement Saving
        // Standard TBD Alert Dialogue
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("This Is Not Connected Yet");
        builder.setMessage("Habit integration will be added in project part 4. Existing events are for GUI testing only");
        builder.setNegativeButton("OK", null);
        // create the alert dialog and display it over the fragment
        AlertDialog alertBox = builder.create();
        alertBox.show();
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
