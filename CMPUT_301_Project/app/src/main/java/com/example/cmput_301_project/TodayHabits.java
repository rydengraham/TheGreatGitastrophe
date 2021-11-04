package com.example.cmput_301_project;


import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TodayHabits extends AppCompatActivity {

    LinearLayout commentHolder;
    LinearLayout photoLocationHolder;
    Button completeGreenButton;
    ListView toDoList;
    ListView completedList;
    static ArrayList<HabitEvent> toDoEventsList;
    static ArrayList<HabitEvent> completedEventsList;
    static TodayHabitsAdapter toDoAdapter;
    static TodayHabitsAdapter completedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.today_habits);

        commentHolder = findViewById(R.id.commentHolder);
        photoLocationHolder = findViewById(R.id.iconLocationHolder);
        completeGreenButton = findViewById(R.id.completeHabitC);
        toDoList = findViewById(R.id.toDoList);
        completedList = findViewById(R.id.completedList);
        toDoEventsList = new ArrayList<HabitEvent>();
        completedEventsList = new ArrayList<HabitEvent>();
        toDoEventsList.add(new HabitEvent("COMMENT", "TITLE"));
        toDoEventsList.add(new HabitEvent("COMMENT2", "TITLE2"));
        toDoEventsList.add(new HabitEvent("COMMENT3", "TITLE3"));
        toDoEventsList.add(new HabitEvent("COMMENT4", "TITLE4"));
        toDoEventsList.add(new HabitEvent("COMMENT5", "TITLE5"));
        toDoEventsList.add(new HabitEvent("COMMENT6", "TITLE6"));
        toDoAdapter = new TodayHabitsAdapter(this, R.layout.today_habits_content, toDoEventsList);
        completedAdapter = new TodayHabitsAdapter(this, R.layout.today_habits_content, completedEventsList);
        toDoList.setAdapter(toDoAdapter);
        completedList.setAdapter(completedAdapter);


    }

    public static ArrayList<HabitEvent> getToDoList(){
        return toDoEventsList;
    }

    public static ArrayList<HabitEvent> getCompleteList(){
        return completedEventsList;
    }

    public static TodayHabitsAdapter getToDoListAdapter(){
        return toDoAdapter;
    }

    public static TodayHabitsAdapter getCompletedListAdapter(){
        return completedAdapter;
    }

}
