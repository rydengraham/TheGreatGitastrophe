package com.example.cmput_301_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class responsible for displaying recycle view of habits, and deleting habit items. Extends {@link AppCompatActivity} and implements {@link HabitFragments.OnFragmentInteractionListener}}
 */
public class MyHabits extends AppCompatActivity implements HabitFragments.OnFragmentInteractionListener{
    private RecyclerView recyclerView;
    List<Habit> modelList;
    private Button removeButton;
    private Button cancelButton;
    private RecyclerViewAdapter recycleAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_habits);
        // Find our components here
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        removeButton = (Button) findViewById(R.id.removeButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        final Button addCityButton = findViewById(R.id.addButton);
        mockItems();
        // Initialize adapter and set recycleView to it
        recycleAdapter = new RecyclerViewAdapter(modelList, this, false);
        recyclerView.setAdapter(recycleAdapter);

        // If remove button is clicked enable delete mode
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelButton.setVisibility(View.VISIBLE);
                recycleAdapter.setDelMode(true);
            }
        });

        // If cancel button is clicked disable delete mode
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelButton.setVisibility(View.GONE);
                recycleAdapter.setDelMode(false);
            }
        });

        // If add City button is clicked create dialog fragment
        addCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new HabitFragments().show(getSupportFragmentManager(),"ADD_CITY");
            }
        });

    }

    /**
     * Function for creating initial data
     */
    private void mockItems() {
        modelList = new ArrayList<>();
        modelList.add(new Habit("habitName", new Date(2000-1900, 11, 15), "reason", (byte) 1));
    }


    /**
     * Function for adding new habit when pressing ok on Dialog fragment
     * @param newHabit
     */
    @Override
    public void onOkPressed(Habit newHabit) {
        modelList.add(newHabit);
        recycleAdapter.notifyDataSetChanged();

    }

    /**
     * Used for when an ok button on edit fragment is used, edits the pre-existing habit class
     * @param retrieved_habit
     * @param habitName
     * @param reason
     * @param startDate
     * @param weekdays
     */
    @Override
    public void onOkPressed(Habit retrieved_habit,  String habitName, String reason, Date startDate, Byte weekdays) {
         retrieved_habit.setHabitName(habitName);
         retrieved_habit.setStartDate(startDate);
         retrieved_habit.setReason(reason);
         retrieved_habit.setWeekdays(weekdays);
         recycleAdapter.notifyDataSetChanged();
    }


}
