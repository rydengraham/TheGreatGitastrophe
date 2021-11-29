package com.example.cmput_301_project.pages;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cmput_301_project.Account;
import com.example.cmput_301_project.adapters.EventHabitAdapter;
import com.example.cmput_301_project.FirestoreHandler;
import com.example.cmput_301_project.HabitEvent;
import com.example.cmput_301_project.R;

import java.util.ArrayList;

/**
 * Activity that is used to display all Habit Events the user has pertaining to them, Uses the EventHabitAdapter to display a list item
 */

public class HabitEventHistoryPage extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EventHabitAdapter eventHabitAdapter;
    ArrayList<HabitEvent> test;
    private Button cancelButton;
    private Button delButton;
    private TextView delText;
    Account userAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_habit_event_history_page);
        userAccount = FirestoreHandler.create().getActiveUserAccount();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        cancelButton = (Button) findViewById(R.id.cancelEventDeletion);
        delButton = (Button) findViewById(R.id.removeHabitEventButton);
        delText = findViewById(R.id.deleteHabitEventText);
        getHabitEvents();

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

    /**
     * Gets habit events for a given habit
     */
    public void getHabitEvents(){
        test = new ArrayList<>();
        Bundle extras = getIntent().getExtras();
        userAccount.getHabitEventsForHabit(test, extras.getString("habitId"));
    }

    @Override
    public void onResume(){
        super.onResume();
        eventHabitAdapter.notifyDataSetChanged();
    }
}