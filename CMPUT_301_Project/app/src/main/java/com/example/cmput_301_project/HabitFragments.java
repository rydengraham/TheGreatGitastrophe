/**
 * Fragment used in the habits menu
 */
package com.example.cmput_301_project;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import java.util.Date;

/**
 * A {@link Fragment} subclass that helps create habit objects through dialog.
 * Use the {@link HabitFragments#newInstance} factory method to
 * create an instance of this fragment.
 */

public class HabitFragments extends DialogFragment {

    private EditText habitTitle;
    private EditText startDate;
    private HabitFragments.OnFragmentInteractionListener listener; //Comforting to have.
    private TableLayout tableLayout;
    private Button frequencyButton;
    private Button dateButton;
    private CalendarView calendarView;
    private Date dateToSend;
    private Switch publicSwitcher;
    private CheckBox monday,tuesday,wednesday,thursday,friday,saturday,sunday;
    private Integer weekday;



    /**
     * Fragment interaction listener used in MyHabits class
     */
    public interface OnFragmentInteractionListener{
        void onOkPressed(Habit newHabit);

        void onOkPressed(Habit retrievedHabit, String name, String reason, Date date , int weekdays, boolean publicHabit);
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

    public boolean checkFields(){
        String name = habitTitle.getText().toString();
        String reason = startDate.getText().toString();
        boolean hasWeekdaySelected = false;
        if (monday.isChecked() == true)
            hasWeekdaySelected = true;
        if (tuesday.isChecked() == true)
            hasWeekdaySelected = true;
        if (wednesday.isChecked() == true)
            hasWeekdaySelected = true;
        if (thursday.isChecked() == true)
            hasWeekdaySelected = true;
        if (friday.isChecked() == true)
            hasWeekdaySelected = true;
        if (saturday.isChecked() == true)
            hasWeekdaySelected = true;
        if (sunday.isChecked() == true)
            hasWeekdaySelected = true;
        // Check if fields are missing
        if (hasWeekdaySelected == false || name.isEmpty() || reason.isEmpty() || name.length() >= 20 || reason.length() >= 30)
            return false;
        else
            return true;
    }

    /**
     * Used to help create a dialog that has a pre-existing habit object to edit
     * @param habit
     * @return
     */
    static HabitFragments newInstance (Habit habit){
        Bundle args = new Bundle();
        args.putSerializable("habit", habit);
        HabitFragments fragment = new HabitFragments();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Used to fill out habit information in the fragment fields
     * @param savedInstanceState
     * @return
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_habit_fragments, null);
        habitTitle = view.findViewById(R.id.habitTitleEditText);
        startDate = view.findViewById(R.id.reasonEditText);
        tableLayout = view.findViewById(R.id.customSelector);
        frequencyButton = view.findViewById(R.id.frequencyButton);
        dateButton = view.findViewById(R.id.dateButton);
        calendarView = view.findViewById(R.id.calendarView2);
        calendarView.setVisibility(View.GONE);
        tableLayout.setVisibility(View.GONE);
        dateToSend = new Date(calendarView.getDate());
        publicSwitcher = view.findViewById(R.id.privateSwitch);
        monday = view.findViewById(R.id.mondayBox);
        tuesday = view.findViewById(R.id.tuesdayBox);
        wednesday = view.findViewById(R.id.wednesdayBox);
        thursday = view.findViewById(R.id.thursdayBox);
        friday = view.findViewById(R.id.fridayBox);
        saturday = view.findViewById(R.id.saturdayBox);
        sunday = view.findViewById(R.id.sundayBox);
        weekday = 0;
        Dialog test;



        // Toggles frequency view
        frequencyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tableLayout.setVisibility(tableLayout.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
            }
        });
        // Toggles date view
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (calendarView.getVisibility() == View.GONE)
                    calendarView.setVisibility(View.VISIBLE);
                else
                    calendarView.setVisibility(View.GONE);
            }
        });

        // Updates the date we need to send eventually when OK button is clicked
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                dateToSend = new Date(i-1900, i1, i2);
            }
        });


        Bundle args = getArguments();

        // Handles an edit, that is if we get a habit to edit via bundle
        if (args != null) {
            Habit retrievedHabit = (Habit) args.getSerializable("habit");
            // Setting text and calendar according to retrievedHabit
            habitTitle.setText(retrievedHabit.getHabitName());
            startDate.setText(retrievedHabit.getReason());
            publicSwitcher.setChecked(retrievedHabit.getPublic());
            long toConvert = retrievedHabit.getStartDate().getTime();
            calendarView.setDate(toConvert);

            // We check which check boxes need to be checked off.
            CheckBox checkBox = view.findViewById(R.id.mondayBox);
            if (retrievedHabit.getIsOnDayOfWeek(0))
                checkBox.setChecked(true);
            checkBox = view.findViewById(R.id.tuesdayBox);
            if (retrievedHabit.getIsOnDayOfWeek(1))
                checkBox.setChecked(true);
            checkBox = view.findViewById(R.id.wednesdayBox);
            if (retrievedHabit.getIsOnDayOfWeek(2))
                checkBox.setChecked(true);
            checkBox = view.findViewById(R.id.thursdayBox);
            if (retrievedHabit.getIsOnDayOfWeek(3))
                checkBox.setChecked(true);
            checkBox = view.findViewById(R.id.fridayBox);
            if (retrievedHabit.getIsOnDayOfWeek(4))
                checkBox.setChecked(true);
            checkBox = view.findViewById(R.id.saturdayBox);
            if (retrievedHabit.getIsOnDayOfWeek(5))
                checkBox.setChecked(true);
            checkBox = view.findViewById(R.id.sundayBox);
            if (retrievedHabit.getIsOnDayOfWeek(6))
                checkBox.setChecked(true);

            // Build Dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            test = builder
                    .setView(view)
                    .setTitle("Edit Habit")
                    .setNegativeButton("Cancel", null)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {

                            String name = habitTitle.getText().toString();
                            String reason = startDate.getText().toString();
                            retrievedHabit.setHabitName(habitTitle.getText().toString());
                            Date date = dateToSend;
                            int weekdays = 0;
                            boolean publicHabit = false;
                            CheckBox checkBox = view.findViewById(R.id.mondayBox);

                            // add to weekday bit wise to reflect the checked boxes
                            if (checkBox.isChecked() == true)
                                weekdays = weekdays + 1;
                            checkBox = view.findViewById(R.id.tuesdayBox);
                            if (checkBox.isChecked() == true)
                                weekdays = weekdays + 2;
                            checkBox = view.findViewById(R.id.wednesdayBox);
                            if (checkBox.isChecked() == true)
                                weekdays = weekdays + 4;
                            checkBox = view.findViewById(R.id.thursdayBox);
                            if (checkBox.isChecked() == true)
                                weekdays = weekdays + 8;
                            checkBox = view.findViewById(R.id.fridayBox);
                            if (checkBox.isChecked() == true)
                                weekdays = weekdays + 16;
                            checkBox = view.findViewById(R.id.saturdayBox);
                            if (checkBox.isChecked() == true)
                                weekdays = weekdays + 32;
                            checkBox = view.findViewById(R.id.sundayBox);
                            if (checkBox.isChecked() == true)
                                weekdays = weekdays + 64;

                            if (publicSwitcher.isChecked())
                                publicHabit = true;
                            else
                                publicHabit = false;
                            // Check if fields are empty
                            listener.onOkPressed(retrievedHabit, name, reason, date, weekdays, publicHabit);
                        }
                    }).create();
            test.show();
            ((AlertDialog) test).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
        }
            else{
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            test = builder
                    .setView(view)
                    .setTitle("Add Habit")
                    .setNegativeButton("Cancel", null)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {

                            String name = habitTitle.getText().toString();
                            String reason = startDate.getText().toString();
                            Date date = dateToSend;
                            int weekdays = 0;
                            boolean publicHabit = false;
                            CheckBox checkBox = view.findViewById(R.id.mondayBox);

                            // add to weekday bit wise to reflect the checked boxes
                            if (checkBox.isChecked() == true)
                                weekdays = weekdays + 1;
                            checkBox = view.findViewById(R.id.tuesdayBox);
                            if (checkBox.isChecked() == true)
                                weekdays = weekdays + 2;
                            checkBox = view.findViewById(R.id.wednesdayBox);
                            if (checkBox.isChecked() == true)
                                weekdays = weekdays + 4;
                            checkBox = view.findViewById(R.id.thursdayBox);
                            if (checkBox.isChecked() == true)
                                weekdays = weekdays + 8;
                            checkBox = view.findViewById(R.id.fridayBox);
                            if (checkBox.isChecked() == true)
                                weekdays = weekdays + 16;
                            checkBox = view.findViewById(R.id.saturdayBox);
                            if (checkBox.isChecked() == true)
                                weekdays = weekdays + 32;
                            checkBox = view.findViewById(R.id.sundayBox);
                            if (checkBox.isChecked() == true)
                                weekdays = weekdays + 64;

                            if (publicSwitcher.isChecked())
                                publicHabit = true;
                            else
                                publicHabit = false;
                            // Check if fields are empty
                            listener.onOkPressed(new Habit(name, date, reason, weekdays, publicHabit));
                        }
                    }).create();
            test.show();
            ((AlertDialog) test).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
        }
            startDate.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    boolean test2 = checkFields();
                    if (test2)
                        ((AlertDialog) test).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                    else
                        ((AlertDialog) test).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            habitTitle.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    boolean test2 = checkFields();
                    if (test2)
                        ((AlertDialog) test).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                    else
                        ((AlertDialog) test).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            monday.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean test2 = checkFields();
                    if (test2)
                        ((AlertDialog) test).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                    else
                        ((AlertDialog) test).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                }
            });
            tuesday.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean test2 = checkFields();
                    if (test2)
                        ((AlertDialog) test).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                    else
                        ((AlertDialog) test).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                }
            });
            wednesday.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean test2 = checkFields();
                    if (test2)
                        ((AlertDialog) test).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                    else
                        ((AlertDialog) test).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                }
            });
            thursday.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean test2 = checkFields();
                    if (test2)
                        ((AlertDialog) test).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                    else
                        ((AlertDialog) test).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                }
            });
            friday.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean test2 = checkFields();
                    if (test2)
                        ((AlertDialog) test).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                    else
                        ((AlertDialog) test).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                }
            });
            saturday.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean test2 = checkFields();
                    if (test2)
                        ((AlertDialog) test).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                    else
                        ((AlertDialog) test).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                }
            });
            sunday.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean test2 = checkFields();
                    if (test2)
                        ((AlertDialog) test).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                    else
                        ((AlertDialog) test).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                }
            });

            return test;
        }
    }



