/**
 * Main home page activity
 */
package com.example.cmput_301_project;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class MainPage extends AppCompatActivity {

    // textview represents text that changes depending on menu item clicked
    TextView textView;
    // progressText is a textview represents the % of progress bar filled out
    TextView progressText;
    // amount of circular progress bar filled out
    private int progress = 0;
    // progressBar references progress bar object
    ProgressBar progressBar;
    //used to be zero set it to ONE
    int progressMaxCounter = 1, progressCurrentCounter = 0;
    int[] progressRate = new int[2];

    Account userAccount;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        // initialize circular progress bar
        progressBar= findViewById(R.id.progress_bar);
        // initialize updatebutton and friendbutton
        Button updateButton= findViewById(R.id.updateButton);
        Button friendButton= findViewById(R.id.friendbutton);
        textView= findViewById(R.id.btName);
        progressText= findViewById(R.id.prg_value);
        userAccount = AccountData.create().getActiveUserAccount();

        // Percent completed habits per month graphic update
        for (Habit habit : userAccount.getHabitTable()) {
            progressRate = userAccount.getHabitCompletionRateInLastThirtyDays(habit.getId());
            progressCurrentCounter += progressRate[1];
            progressMaxCounter += progressRate[0];
            System.out.println(progressCurrentCounter);
            System.out.println(progressMaxCounter);
        }

        progress = 100 * progressCurrentCounter / progressMaxCounter;
        updateProgress();
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchToTodayHabitsPage = new Intent(MainPage.this, TodayHabits.class);
                updateProgress();
                startActivity(switchToTodayHabitsPage);
            }
        });
        progressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { }
        });

        friendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Switch to Friends Page
                Intent switchToAddFriendsPage = new Intent(MainPage.this, MyFriends.class);
                startActivity(switchToAddFriendsPage);
            }
        });

    }

    /**
     * Method to set the progress value i.e % of progress
     * bar filled out */
    public void updateProgress()
    {
        progressBar.setProgress(progress);
        progressText.setText(progress + "%");
    }




}
