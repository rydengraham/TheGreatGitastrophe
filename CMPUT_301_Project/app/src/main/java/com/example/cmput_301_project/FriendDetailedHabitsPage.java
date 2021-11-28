package com.example.cmput_301_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Backend class for FriendDetailedHabitsPage
 */
public class FriendDetailedHabitsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_detailed_habits_page);

        // create an arraylist of 20 generic habits
        // TODO: when accounts are implemented, replace this with ArrayList of friend's habits
        ArrayList<Habit> habits = generateHabits(20);

        // set up recycler views and adapters for the habit array list
        RecyclerView friendsHabitsView = findViewById(R.id.friendHabitsRV);
        FriendDetailedHabitsAdapter friendsHabitsAdapter = new FriendDetailedHabitsAdapter(this, habits);
        friendsHabitsView.setAdapter(friendsHabitsAdapter);
        friendsHabitsView.setLayoutManager(new LinearLayoutManager(this));

        // this just adds dividing lines between the values in both recycler views
        // from: https://stackoverflow.com/questions/24618829/how-to-add-dividers-and-spaces-between-items-in-recyclerview
        DividerItemDecoration habitsRvDivider = new DividerItemDecoration(friendsHabitsView.getContext(), LinearLayoutManager.VERTICAL);
        friendsHabitsView.addItemDecoration(habitsRvDivider);
    }

    /**
     * Method create 'i' generic habits and adds them to an ArrayList.
     * Note: THIS SHOULD BE REMOVED WHEN FRIEND HABIT IMPLEMENTATION IS ADDED.
     * @param i number of habit events to be created
     * @return array list of all created habit events
     */
    public ArrayList<Habit> generateHabits(int i) {
        // TODO: method is for testing only, remove when account implementation is complete
        ArrayList<Habit> habits = new ArrayList<>();
        // create some random habits
        String expandedText = "123456789012345678901234567890";
        for(int j = 1; j < i; j++) {
            String habitName = "Habit " + String.valueOf(j);
            Habit habit = new Habit(habitName, Calendar.getInstance().getTime(), expandedText, 127, true);
            habit.setReason(expandedText);
            habit.setExpanded(false);

            habits.add(habit);
        }

        return habits;
    }
}
