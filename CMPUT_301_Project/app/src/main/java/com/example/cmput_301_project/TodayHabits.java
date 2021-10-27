package com.example.cmput_301_project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TodayHabits extends AppCompatActivity {

    LinearLayout commentHolder;
    LinearLayout photoLocationHolder;
    Button completeGreenButton;
    ListView toDoList;
    ListView completedList;
    ArrayList<HabitEvent> toDoEventsList;
    ArrayList<HabitEvent> completeEventsList;

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
        completeEventsList = new ArrayList<HabitEvent>();
        toDoEventsList.add(new HabitEvent("COMMENT", "TITLE"));
        toDoEventsList.add(new HabitEvent("COMMENT2", "TITLE2"));
        toDoEventsList.add(new HabitEvent("COMMENT3", "TITLE3"));
        toDoEventsList.add(new HabitEvent("COMMENT4", "TITLE4"));
        toDoEventsList.add(new HabitEvent("COMMENT5", "TITLE5"));
        toDoEventsList.add(new HabitEvent("COMMENT6", "TITLE6"));
        completeEventsList.add(new HabitEvent("COMMENT7", "TITLE7"));
        TodayHabitsAdapter toDoAdapter = new TodayHabitsAdapter(this, R.layout.today_habits_content, toDoEventsList);
        TodayHabitsAdapter completedAdapter = new TodayHabitsAdapter(this, R.layout.today_habits_content, completeEventsList);

        toDoList.setAdapter(toDoAdapter);
        completedList.setAdapter(completedAdapter);

        toDoList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HabitEvent selectedItem = toDoAdapter.getItem(i);
                TodayHabitViewHolder selectedHolder= selectedItem.getHolder();
                if(selectedHolder.getCommentHolder().getVisibility() == View.GONE){
                    selectedHolder.getCommentHolder().setVisibility(View.VISIBLE);
                    selectedHolder.getIconLocationHolder().setVisibility(View.VISIBLE);
                }else{
                    selectedHolder.getCommentHolder().setVisibility(View.GONE);
                    selectedHolder.getIconLocationHolder().setVisibility(View.GONE);
                }
            }
        });

    }

}
