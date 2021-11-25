package com.example.cmput_301_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class FriendDetailedHabitsPage extends AppCompatActivity {

    private RecyclerView friendHabitView;
    private RecyclerViewAdapter habitViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_detailed_habits_page);

        // initialize habit view and set its adapter
        friendHabitView = findViewById(R.id.friendHabitView);
        habitViewAdapter = new RecyclerViewAdapter(this, false);
        friendHabitView.setAdapter(habitViewAdapter);

        // TODO: add functionality for getting friends habits here
        // TODO: unclear if friends habits are stored as fragments already or if they need to be converted
    }
}