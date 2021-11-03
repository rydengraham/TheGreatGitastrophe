package com.example.cmput_301_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyHabits extends AppCompatActivity implements HabitFragments.OnFragmentInteractionListener{
    private RecyclerView recyclerView;
    List<Habit> modelList;
    private Button removeButton;
    private Button cancelButton;
    private RecyclerViewAdapter adapter2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_habits);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        removeButton = (Button) findViewById(R.id.removeButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);

        mockItems();

        adapter2 = new RecyclerViewAdapter(modelList);
        recyclerView.setAdapter(adapter2);

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelButton.setVisibility(View.VISIBLE);
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelButton.setVisibility(View.GONE);
            }
        });
        final Button addCityButton = findViewById(R.id.addButton);
        addCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new HabitFragments().show(getSupportFragmentManager(),"ADD_CITY");
            }
        });

    }
    private void mockItems() {
        modelList = new ArrayList<>();
        modelList.add(new Habit("habitName", new Date(2000, 11, 15), "reason", (byte) 1));

    }



    @Override
    public void onOkPressed(Habit newHabit) {
        modelList.add(newHabit);
        adapter2.notifyDataSetChanged();

    }

    @Override
    public void onOkPressed(Habit retrieved_habit,  String habitName, String reason, Date startDate, Byte weekdays) {
         retrieved_habit.setHabitName(habitName);
         retrieved_habit.setStartDate(startDate);
         retrieved_habit.setReason(reason);
         retrieved_habit.setWeekdays(weekdays);
         adapter2.notifyDataSetChanged();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}
