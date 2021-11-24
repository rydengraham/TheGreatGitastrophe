package com.example.cmput_301_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HabitEventHistory extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EventHabitAdapter eventHabitAdapter;
    List<HabitEvent> test;
    private Button cancelButton;
    private Button delButton;
    private TextView delText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_event_history);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        cancelButton = (Button) findViewById(R.id.cancelEventDeletion);
        delButton = (Button) findViewById(R.id.removeHabitEventButton);
        delText = findViewById(R.id.deleteHabitEventText);
        mockData();
        eventHabitAdapter = new EventHabitAdapter(test,this, false);
        recyclerView.setAdapter(eventHabitAdapter);

        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventHabitAdapter.setDelMode(true);
                cancelButton.setVisibility(View.VISIBLE);
                delButton.setVisibility(View.GONE);
                delText.setVisibility(View.VISIBLE);

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventHabitAdapter.setDelMode(false);
                cancelButton.setVisibility(View.GONE);
                delButton.setVisibility(View.VISIBLE);
                delText.setVisibility(View.GONE);
            }
        });

    }


    public void mockData(){
        test = new ArrayList<>();
        test.add(new HabitEvent("2000/11/15", "Test"));
        test.add(new HabitEvent("2000/11/15", "Test2"));
        test.add(new HabitEvent("2000/11/15", "Test3"));


    }

}