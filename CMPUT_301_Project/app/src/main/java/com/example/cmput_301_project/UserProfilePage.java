package com.example.cmput_301_project;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class UserProfilePage extends AppCompatActivity {

    // TODO: random values chosen need to be replaced w real complete/incomplete habits
    int totalHabits = 16;
    int completedHabits = 5;
    double habitRatio = (double) completedHabits/totalHabits;
    // define fragment manager and transaction for opening/closing settings fragment
    FragmentManager manager = getSupportFragmentManager();
    FragmentTransaction transaction;
    TextView usernameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_page);

        usernameTextView = findViewById(R.id.usernameText);
        Account activeUserAccount = AccountData.create().getActiveUserAccount();
        usernameTextView.setText(activeUserAccount.getUserName());

        // calculate the % of habits completed this month and update the habit complete TV
        DecimalFormat df = new DecimalFormat("#");
        String habitPercent = df.format(habitRatio*100) + "% of habits completed this month";
        TextView habitPercentText = (TextView) findViewById(R.id.completionPercText);
        habitPercentText.setText(habitPercent);
        /*
         * set the background of the completion percentage drawable as a hue between red & green
         * depending on the ratio of completed habits/total habits
        */
        habitPercentText.getBackground().setTint(setHabitColour(habitRatio));

        // create a list of completed habits and add them to the habit LV
        ListView habitList = findViewById(R.id.habitList);

        // TODO: need to use a list of real recently-completed habits instead of examples here
        String []habits ={"Habit 1", "Habit 2", "Habit 3", "Habit 4", "Habit 5",
                "Habit 6" ,"Habit 7", "Habit 8", "Habit 9", "Habit 10"};
        // set up the dataList & adapter for converting habit array to ListView
        ArrayList<String> dataList = new ArrayList<>(Arrays.asList(habits));
        ArrayAdapter<String> habitAdapter = new ArrayAdapter<>(this, R.layout.habit_list_textview, dataList);

        habitList.setAdapter(habitAdapter);
    }

    public void onSettingsClick(View view) {
        // create a new settings fragment and display it on the appropriate frame
        Fragment userSettings = new UserSettingsFragment();
        // begin fragment transaction and add current activity to backstack
        transaction = manager.beginTransaction();
        transaction.add(R.id.settingsFrame, userSettings);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    /**
     * @param habitRatio ratio of habits completed/total habits
     * @return colour of background depending on habitRatio cast as an int
     */
    public int setHabitColour(double habitRatio) {
        // set the HSB step (0 = red, 120 = green) and define the color based on the ratio of habits completed
        double step = 120*habitRatio;
        /*
         * set the colour of the completion drawable depending on the ratio of habits completed
         * 0% = red, 100% = green, x% = hue between red and green
         */
        return Color.HSVToColor(new float[]{(float) step, 1f, 1f});
    }

}