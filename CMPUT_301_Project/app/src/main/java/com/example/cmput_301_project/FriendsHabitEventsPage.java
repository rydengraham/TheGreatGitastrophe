package com.example.cmput_301_project;

import androidx.appcompat.app.AppCompatActivity;
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

        String[] habitList = {"Habit 1", "Habit 2", "Habit 3", "Habit 4", "Habit 5", "Habit 6",
                "Habit 7", "Habit 8"};
        ArrayList<String> habits = new ArrayList<>(Arrays.asList(habitList));

        RecyclerView incompleteHabitsView = findViewById(R.id.incompleteRV);
        FriendsHabitAdapter adapter = new FriendsHabitAdapter(this, habits);
        incompleteHabitsView.setAdapter(adapter);
        incompleteHabitsView.setLayoutManager(new LinearLayoutManager(this));
    }
}