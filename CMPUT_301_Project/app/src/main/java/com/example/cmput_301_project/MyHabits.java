package com.example.cmput_301_project;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.Date;

/**
 * Class responsible for displaying recycle view of habits, and deleting habit items. Extends {@link AppCompatActivity} and implements {@link HabitFragments.OnFragmentInteractionListener}}
 */
public class MyHabits extends AppCompatActivity implements HabitFragments.OnFragmentInteractionListener{
    private RecyclerView recyclerView;
    private Account userAccount = AccountData.create().getActiveUserAccount();
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

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    /**
     * Function for adding new habit when pressing ok on Dialog fragment
     * @param newHabit
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onOkPressed(Habit newHabit) {
        userAccount.addHabit(newHabit);
        userAccount.backfillHabitEvents();
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
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onOkPressed(Habit retrieved_habit,  String habitName, String reason, Date startDate, int weekdays, boolean publicHabit) {
        int position = userAccount.getHabitTable().indexOf(retrieved_habit);
        retrieved_habit.setHabitName(habitName);
        retrieved_habit.setStartDate(startDate);
        retrieved_habit.setReason(reason);
        retrieved_habit.setWeekdays(weekdays);
        retrieved_habit.setPublic(publicHabit);
        userAccount.updateHabit(position, retrieved_habit);
        userAccount.backfillHabitEvents();
        userAccount.updateFirestore();
        recycleAdapter.notifyDataSetChanged();
    }

    /**
     * This allows the reordering and moving of Habit list item elements
     */
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END, 0) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();
            Collections.swap(userAccount.getHabitTable(), fromPosition, toPosition);
            recyclerView.getAdapter().notifyItemMoved(fromPosition,toPosition);
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        }
    };


}
