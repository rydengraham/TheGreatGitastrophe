package com.example.cmput_301_project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;

/**
 * Class responsible for displaying recycle view of habits, and deleting habit items. Extends {@link AppCompatActivity} and implements {@link HabitFragments.OnFragmentInteractionListener}}
 */
public class MyHabits extends AppCompatActivity implements HabitFragments.OnFragmentInteractionListener{
    private RecyclerView recyclerView;
    Account userAccount = AccountData.create().getActiveUserAccount();
    private Button removeButton;
    private Button cancelButton;
    private Button addHabitButton;
    private TextView deleteText;
    private RecyclerViewAdapter recycleAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_habits);
        // Find our components here
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        removeButton = (Button) findViewById(R.id.removeButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        deleteText = (TextView) findViewById(R.id.deleteText);
        addHabitButton = findViewById(R.id.addButton);
        // Initialize adapter and set recycleView to it
        recycleAdapter = new RecyclerViewAdapter(this, false);
        recyclerView.setAdapter(recycleAdapter);

        // If remove button is clicked enable delete mode
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelButton.setVisibility(View.VISIBLE);
                deleteText.setVisibility(View.VISIBLE);
                addHabitButton.setVisibility(View.GONE);
                removeButton.setVisibility(View.GONE);
                recycleAdapter.setDelMode(true);
            }
        });

        // If cancel button is clicked disable delete mode
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelButton.setVisibility(View.GONE);
                deleteText.setVisibility(View.GONE);
                addHabitButton.setVisibility(View.VISIBLE);
                removeButton.setVisibility(View.VISIBLE);
                recycleAdapter.setDelMode(false);
            }
        });

        // If add Habit button is clicked create dialog fragment
        addHabitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new HabitFragments().show(getSupportFragmentManager(),"ADD_HABIT");
            }
        });

    }

    /**
     * Function for adding new habit when pressing ok on Dialog fragment
     * @param newHabit
     */
    @Override
    public void onOkPressed(Habit newHabit) {
//        modelList.add(newHabit);
        userAccount.addHabit(newHabit);
        userAccount.updateFirestore();
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
    public void onOkPressed(Habit retrieved_habit,  String habitName, String reason, Date startDate, int weekdays) {
        int position = userAccount.getHabitTable().indexOf(retrieved_habit);
        retrieved_habit.setHabitName(habitName);
        retrieved_habit.setStartDate(startDate);
        retrieved_habit.setReason(reason);
        retrieved_habit.setWeekdays(weekdays);
        userAccount.updateHabit(position, retrieved_habit);
        userAccount.updateFirestore();
        recycleAdapter.notifyDataSetChanged();
    }


}
