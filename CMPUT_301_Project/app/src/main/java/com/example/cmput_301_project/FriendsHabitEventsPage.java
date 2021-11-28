package com.example.cmput_301_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;

public class FriendsHabitEventsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_habit_events_page);

        /*
        TODO: replace these default habit values with the real daily friend habit,
            using 2 separate lists for incompleteHabits and completeHabits respectively
         */
        String[] habitList = {"Habit 1", "Habit 2", "Habit 3", "Habit 4", "Habit 5", "Habit 6",
                "Habit 7", "Habit 8"};
        // convert the incomplete/complete habits to corresponding ArrayLists
        ArrayList<String> incompleteHabits = new ArrayList<>(Arrays.asList(habitList));
        ArrayList<String> completeHabits = new ArrayList<>(Arrays.asList(habitList));

        // set up the recyclerView and adapter for incomplete habits
        RecyclerView incompleteHabitsView = findViewById(R.id.friendIncompleteRV);
        FriendsHabitAdapter incompleteHabitsAdapter = new FriendsHabitAdapter(this, incompleteHabits);
        incompleteHabitsView.setAdapter(incompleteHabitsAdapter);
        incompleteHabitsView.setLayoutManager(new LinearLayoutManager(this));

        // set up the recyclerView and adapter for complete habits
        RecyclerView completeHabitsView = findViewById(R.id.friendCompleteRV);
        FriendsHabitAdapter completeHabitsAdapter = new FriendsHabitAdapter(this, completeHabits);
        completeHabitsView.setAdapter(completeHabitsAdapter);
        completeHabitsView.setLayoutManager(new LinearLayoutManager(this));

        // this just adds dividing lines between the values in both recycler views
        // from: https://stackoverflow.com/questions/24618829/how-to-add-dividers-and-spaces-between-items-in-recyclerview
        DividerItemDecoration incompleteRVDivider = new DividerItemDecoration(incompleteHabitsView.getContext(), LinearLayoutManager.VERTICAL);
        incompleteHabitsView.addItemDecoration(incompleteRVDivider);
        DividerItemDecoration completeRVDivider = new DividerItemDecoration(completeHabitsView.getContext(), LinearLayoutManager.VERTICAL);
        completeHabitsView.addItemDecoration(completeRVDivider);
    }
}