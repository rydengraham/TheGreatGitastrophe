/**
 * User profile activity
 */
package com.example.cmput_301_project;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class UserProfilePage extends AppCompatActivity implements VerifyPasswordFragment.OnPasswordVerify {

    // TODO: random values chosen need to be replaced w real complete/incomplete habits
    int totalHabits = 16;
    int completedHabits = 5;
    int habitRatio = 100 * completedHabits/totalHabits;

    boolean passwordVerified = false;
    // define fragment manager and transaction for opening/closing settings fragment
    FragmentManager manager = getSupportFragmentManager();
    FragmentTransaction transaction;
    TextView usernameTextView;
    TextView progressText;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_page);

        usernameTextView = findViewById(R.id.usernameText);
        Account activeUserAccount = AccountData.create().getActiveUserAccount();
        usernameTextView.setText(activeUserAccount.getUserName());
        progressBar = findViewById(R.id.progress_bar);
        progressText = findViewById(R.id.prg_value);

        updateProgress(habitRatio);

        // calculate the % of habits completed this month and update the habit complete TV
        DecimalFormat df = new DecimalFormat("#");
        String habitPercent = df.format(habitRatio*100) + "% of habits completed this month";

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
        passwordVerified = false;
        new VerifyPasswordFragment().show(getSupportFragmentManager(),"VERIFY_PASSWORD");
    }

    public void onPasswordVerify(boolean verified, Context context) {
        if (verified) {
            Fragment userSettings = new UserSettingsFragment();
            // begin fragment transaction and add current activity to backstack
            transaction = manager.beginTransaction();
            transaction.add(R.id.settingsFrame, userSettings);
            transaction.addToBackStack(null);
            transaction.commit();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setCancelable(true);
            builder.setTitle("Password Is Incorrect");
            builder.setMessage("");
            builder.setNegativeButton("OK", null);
            AlertDialog alertBox = builder.create();
            alertBox.show();
            return;
        }
    }

    /**
     * Method to set the progress value i.e % of progress
     * bar filled out */
    public void updateProgress(int progress)
    {
        progressBar.setProgress(progress);
        progressText.setText(progress + "%");
    }

}