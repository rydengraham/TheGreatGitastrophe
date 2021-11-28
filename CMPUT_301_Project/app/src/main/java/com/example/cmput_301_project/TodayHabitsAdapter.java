/**
 * Adapter for today's habits
 */
package com.example.cmput_301_project;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

/**
 * Custom adapter class for Today's Habits
 */
public class TodayHabitsAdapter extends BaseAdapter {

    // Variable Declaration
    int resource;
    Context context;
    ArrayList<HabitEvent> habitList;
    TodayHabitViewHolder holder = null;

    /**
     * Constructor for the adapter
     * @param context
     * @param resource
     * @param myList
     */
    public TodayHabitsAdapter(Context context, int resource, ArrayList<HabitEvent> myList){
        this.resource = resource;
        this.context = context;
        habitList = myList;
    }

    /**
     * Returns the list size
     * @return List size
     */
    @Override
    public int getCount() {
        return habitList.size();
    }

    /**
     * Returns the item at i
     * @param i index
     * @return Habit event at index
     */
    @Override
    public HabitEvent getItem(int i) {
        return habitList.get(i);
    }

    /**
     * Returns the id of item i
     * @param i index
     * @return Id at index
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * Main function to set the list item data
     * @param i
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=null;
        View v = view;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(context);
            v = vi.inflate(resource, null);
        }
        // Reference each item
        LinearLayout commentHolder = v.findViewById(R.id.commentHolder);
        LinearLayout iconLocationHolder = v.findViewById(R.id.iconLocationHolder);
        LinearLayout eventHolder = v.findViewById(R.id.eventHolder);
        LinearLayout textHolder = v.findViewById(R.id.textHolder);
        Button completedButtonX = (Button) v.findViewById(R.id.completeHabitX);
        Button completedButtonC = (Button) v.findViewById(R.id.completeHabitC);
        Button addLocationButton = (Button) v.findViewById(R.id.addLocationButton);
        Button addPhotoButton = (Button) v.findViewById(R.id.addPhotoButton);
        // Expand the view
        textHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(commentHolder.getVisibility() == View.GONE){
                    commentHolder.setVisibility(View.VISIBLE);
                    iconLocationHolder.setVisibility(View.VISIBLE);

                }else{
                    commentHolder.setVisibility(View.GONE);
                    iconLocationHolder.setVisibility(View.GONE);
                    InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        });
        // Set which item is selected
        HabitEvent selectedHabit = getItem(i);
        // Create new holder if none
        if (selectedHabit.getHolder() == null){
            holder = new TodayHabitViewHolder(commentHolder, iconLocationHolder, eventHolder, textHolder, true);
            if (selectedHabit.isCompleted()) {
                holder.setCompletedButton(false);
            }
        }
        else{
            holder = selectedHabit.getHolder();
        }
        selectedHabit.setHolder(holder);
        if (selectedHabit != null) {
            TextView habitName = (TextView) v.findViewById(R.id.habitName);
            EditText comment = (EditText) v.findViewById(R.id.comment);
            if (habitName != null) {
                habitName.setText(selectedHabit.getTitle());
            }
            if (comment != null) {
                comment.setText(selectedHabit.getComment());
                // Update comment in real time
                comment.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        selectedHabit.setComment(comment.getText().toString());
                        Account userAccount = AccountData.create().getActiveUserAccount();
                        userAccount.getHabitEvent(selectedHabit.getId(), selectedHabit.getTitle()).setComment(selectedHabit.getComment());
                        userAccount.updateFirestore();
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                    }
                });
            }
            // Update completed button if clicked
            if (selectedHabit.getHolder().getCompletedButton()){
                completedButtonX.setVisibility(View.VISIBLE);
                completedButtonX.setBackgroundResource(R.drawable.circle_red);
                completedButtonC.setVisibility(View.GONE);
            }
            else{
                completedButtonX.setVisibility(View.GONE);
                completedButtonC.setVisibility(View.VISIBLE);
                completedButtonX.setBackgroundResource(R.drawable.circle_green);
            }
            if (completedButtonX != null){
                completedButtonX.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TodayHabits.getCompleteList().add(selectedHabit);
                        TodayHabits.getToDoList().remove(selectedHabit);
                        selectedHabit.getHolder().setCompletedButton(false);
                        TodayHabits.getToDoListAdapter().notifyDataSetChanged();
                        TodayHabits.getCompletedListAdapter().notifyDataSetChanged();
                        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        Account userAccount = AccountData.create().getActiveUserAccount();
                        userAccount.getHabitEvent(selectedHabit.getId(), selectedHabit.getTitle()).setCompleted(true);
                        userAccount.updateFirestore();
                    }
                });
            }
            if (completedButtonC != null){
                completedButtonC.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TodayHabits.getToDoList().add(selectedHabit);
                        TodayHabits.getCompleteList().remove(selectedHabit);
                        selectedHabit.getHolder().setCompletedButton(true);
                        TodayHabits.getToDoListAdapter().notifyDataSetChanged();
                        TodayHabits.getCompletedListAdapter().notifyDataSetChanged();
                        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        Account userAccount = AccountData.create().getActiveUserAccount();
                        userAccount.getHabitEvent(selectedHabit.getId(), selectedHabit.getTitle()).setCompleted(false);
                        userAccount.updateFirestore();

                    }
                });
            }
            // Need to implement later when we have backend
            if (addLocationButton != null){
                addLocationButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // TODO: Add link to add photo page
                        // Standard TBD Alert Dialogue
                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setCancelable(true);
                        builder.setTitle("Use Location Services?");
                        builder.setMessage("Add location to habit event to commemorate the achievement with yourself and friends.");
                        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                context.startActivity(new Intent(context.getApplicationContext(), LocationActivity.class));


                            }
                        });
                        builder.setNegativeButton("DIMISS", null);
                        // create the alert dialog and display it over the fragment
                        AlertDialog alertBox = builder.create();
                        alertBox.show();
                    }
                });
            }
            // Need to implement later when we have backend
            if (addPhotoButton != null){
                addPhotoButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // TODO: Add link to add location page
                        // Standard TBD Alert Dialogue
                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setCancelable(true);
                        builder.setTitle("Page Does Not Exist");
                        builder.setMessage("This will be added in project part 4.");
                        builder.setNegativeButton("OK", null);
                        // create the alert dialog and display it over the fragment
                        AlertDialog alertBox = builder.create();
                        alertBox.show();
                    }
                });
            }
        }

        return v;
    }

    /**
     * Used to make items interactive
     * @return N/A
     */
    @Override
    public boolean areAllItemsEnabled()
    {
        return true;
    }

    /**
     * Used to make items interactive
     * @param arg0
     * @return N/A
     */
    @Override
    public boolean isEnabled(int arg0)
    {
        return true;
    }
}
