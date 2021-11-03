package com.example.cmput_301_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;

import java.util.Date;

public class HabitFragments extends DialogFragment {

    private EditText habitTitle;
    private EditText startDate;
    private HabitFragments.OnFragmentInteractionListener listener; //Comforting to have.
    private TableLayout tableLayout;
    private Button frequencyButton;
    private Button dateButton;
    private CalendarView calendarView;



    public interface OnFragmentInteractionListener{
        void onOkPressed(Habit newHabit);

        void onOkPressed(Habit Retrieved_Habit, String name, String reason, Date date , Byte weekdays);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException((context.toString()) + "must implement OnFragementInteractionListener." );
        }
    }

    static HabitFragments newInstance (Habit habit){
        Bundle args = new Bundle();
        args.putSerializable("habit", habit);
        HabitFragments fragment = new HabitFragments();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //return super.onCreateDialog(savedInstanceState);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_habit_fragments, null);
        habitTitle = view.findViewById(R.id.habitTitleEditText);
        startDate = view.findViewById(R.id.reasonEditText);
        tableLayout = view.findViewById(R.id.customSelector);
        frequencyButton = view.findViewById(R.id.frequencyButton);
        dateButton = view.findViewById(R.id.dateButton);
        calendarView = view.findViewById(R.id.calendarView2);
        calendarView.setVisibility(View.GONE);
        tableLayout.setVisibility(View.GONE);



        frequencyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tableLayout.setVisibility(tableLayout.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
            }
        });
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (calendarView.getVisibility() == View.GONE)
                    calendarView.setVisibility(View.VISIBLE);
                else
                    calendarView.setVisibility(View.GONE);
                //calendarView.setVisibility(calendarView.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
            }
        });

        Bundle args = getArguments();
        if (args != null) {
            Habit Retrieved_Habit = (Habit)args.getSerializable("habit");
            habitTitle.setText(Retrieved_Habit.getHabitName());
            startDate.setText(Retrieved_Habit.getReason());
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            return builder
                    .setView(view)
                    .setTitle("Add Habit")
                    .setNegativeButton("Cancel", null)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {

                            String name = habitTitle.getText().toString();
                            String reason = startDate.getText().toString();
                            Retrieved_Habit.setHabitName(habitTitle.getText().toString());
                            Date date = new Date(calendarView.getDate());
                            byte weekdays = 0;
                            CheckBox checkBox = view.findViewById(R.id.mondayBox);
                            if (checkBox.isChecked() == true)
                                weekdays = (byte) (weekdays + 1);
                            checkBox = view.findViewById(R.id.tuesdayBox);
                            if (checkBox.isChecked() == true)
                                weekdays = (byte) (weekdays + 2);
                            checkBox = view.findViewById(R.id.wednesdayBox);
                            if (checkBox.isChecked() == true)
                                weekdays = (byte) (weekdays + 4);
                            checkBox = view.findViewById(R.id.thursdayBox);
                            if (checkBox.isChecked() == true)
                                weekdays = (byte) (weekdays + 8);
                            checkBox = view.findViewById(R.id.fridayBox);
                            if (checkBox.isChecked() == true)
                                weekdays = (byte) (weekdays + 16);
                            checkBox = view.findViewById(R.id.saturdayBox);
                            if (checkBox.isChecked() == true)
                                weekdays = (byte) (weekdays + 32);
                            checkBox = view.findViewById(R.id.sundayBox);
                            if (checkBox.isChecked() == true)
                                weekdays = (byte) (weekdays + 64);
                            listener.onOkPressed(Retrieved_Habit, name,reason, date, weekdays);
                        }
                    }).create();
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            return builder
                    .setView(view)
                    .setTitle("Add Habit")
                    .setNegativeButton("Cancel", null)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {

                            String name = habitTitle.getText().toString();
                            String reason = startDate.getText().toString();
                            Date date = new Date(calendarView.getDate());
                            byte weekdays = 0;
                            CheckBox checkBox = view.findViewById(R.id.mondayBox);
                            if (checkBox.isChecked() == true)
                                weekdays = (byte) (weekdays + 1);
                            checkBox = view.findViewById(R.id.tuesdayBox);
                            if (checkBox.isChecked() == true)
                                weekdays = (byte) (weekdays + 2);
                            checkBox = view.findViewById(R.id.wednesdayBox);
                            if (checkBox.isChecked() == true)
                                weekdays = (byte) (weekdays + 4);
                            checkBox = view.findViewById(R.id.thursdayBox);
                            if (checkBox.isChecked() == true)
                                weekdays = (byte) (weekdays + 8);
                            checkBox = view.findViewById(R.id.fridayBox);
                            if (checkBox.isChecked() == true)
                                weekdays = (byte) (weekdays + 16);
                            checkBox = view.findViewById(R.id.saturdayBox);
                            if (checkBox.isChecked() == true)
                                weekdays = (byte) (weekdays + 32);
                            checkBox = view.findViewById(R.id.sundayBox);
                            if (checkBox.isChecked() == true)
                                weekdays = (byte) (weekdays + 64);
                            if (weekdays == 0)
                                Toast.makeText(getActivity(), "Missing Habit Fields.", Toast.LENGTH_SHORT).show();
                            else
                                listener.onOkPressed(new Habit(name, date, reason,  weekdays));
                        }
                    }).create();
        }




    }




}

