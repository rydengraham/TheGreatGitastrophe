package com.example.cmput_301_project;

import android.content.Context;
import android.graphics.Color;
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

import java.util.ArrayList;

public class TodayHabitsAdapter extends BaseAdapter {

    int resource;
    Context context;
    ArrayList<HabitEvent> habitList;
    TodayHabitViewHolder holder = null;

    public TodayHabitsAdapter(Context context, int resource, ArrayList<HabitEvent> myList){
        this.resource = resource;
        this.context = context;
        habitList = myList;
    }

    @Override
    public int getCount() {
        return habitList.size();
    }

    @Override
    public HabitEvent getItem(int i) {
        return habitList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=null;
        View v = view;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(context);
            v = vi.inflate(resource, null);
        }
        LinearLayout commentHolder = v.findViewById(R.id.commentHolder);
        LinearLayout iconLocationHolder = v.findViewById(R.id.iconLocationHolder);
        LinearLayout eventHolder = v.findViewById(R.id.eventHolder);
        LinearLayout textHolder = v.findViewById(R.id.textHolder);
        Button completedButtonX = (Button) v.findViewById(R.id.completeHabitX);
        Button completedButtonC = (Button) v.findViewById(R.id.completeHabitC);
        Button addLocationButton = (Button) v.findViewById(R.id.addLocationButton);
        Button addPhotoButton = (Button) v.findViewById(R.id.addPhotoButton);
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
        HabitEvent selectedHabit = getItem(i);
        if (selectedHabit.getHolder() == null){
            holder = new TodayHabitViewHolder(commentHolder, iconLocationHolder, eventHolder, textHolder, true);
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
                comment.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        selectedHabit.setComment(comment.getText().toString());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                    }
                });
            }
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
                    }
                });
            }
            if (addLocationButton != null){
                addLocationButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Add link to add photo page
                    }
                });
            }
            if (addPhotoButton != null){
                addPhotoButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                      Add link to add location page
                    }
                });
            }
        }

        return v;
    }

    @Override
    public boolean areAllItemsEnabled()
    {
        return true;
    }

    @Override
    public boolean isEnabled(int arg0)
    {
        return true;
    }
}
