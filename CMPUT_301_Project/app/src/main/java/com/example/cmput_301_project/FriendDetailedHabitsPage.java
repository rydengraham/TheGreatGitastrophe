package com.example.cmput_301_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class FriendDetailedHabitsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_detailed_habits_page);

        // create an arraylist of 10 generic habits
        ArrayList<HabitEvent> habits = generateHabits(20);

        // create 2 array lists to store complete and incomplete habits
        ArrayList<HabitEvent> incompleteHabits = new ArrayList<>();
        ArrayList<HabitEvent> completeHabits = new ArrayList<>();

        // iterate through all habits and separate them into complete and incomplete habits
        for(int i = 0; i < habits.size(); i++) {
            if(habits.get(i).isCompleted()) {
                completeHabits.add(habits.get(i));
            }
            else {
                incompleteHabits.add(habits.get(i));
            }
        }

        // set up recycler views and adapters for both complete and incomplete habits
        RecyclerView friendIncompleteHabitsView = findViewById(R.id.friendIncompleteHabitRV);
        FriendDetailedHabitsAdapter incompleteHabitsAdapter = new FriendDetailedHabitsAdapter(this, incompleteHabits);
        friendIncompleteHabitsView.setAdapter(incompleteHabitsAdapter);
        friendIncompleteHabitsView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView friendCompleteHabitsView = findViewById(R.id.friendCompleteHabitRV);
        FriendDetailedHabitsAdapter completeHabitsAdapter = new FriendDetailedHabitsAdapter(this, completeHabits);
        friendCompleteHabitsView.setAdapter(completeHabitsAdapter);
        friendCompleteHabitsView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Method create 'i' generic habits and adds them to an ArrayList.
     * Note: THIS SHOULD BE REMOVED WHEN FRIEND HABIT IMPLEMENTATION IS ADDED.
     * @param i number of habit events to be created
     * @return array list of all created habit events
     */
    public ArrayList<HabitEvent> generateHabits(int i) {
        ArrayList<HabitEvent> habits = new ArrayList<>();
        // create some random habit events
        String expandedText = "Generic expanded text";
        for(int j = 1; j < i; j++) {
            String habitName = "Habit" + String.valueOf(j);
            HabitEvent habit = new HabitEvent("02/02/2021", habitName);
            habit.setComment(expandedText);
            habit.setExpanded(false);
            habit.setCompleted(j%2 == 0);

            habits.add(habit);
        }

        return habits;
    }
}
