/**
 * User profile activity
 */
package com.example.cmput_301_project.pages;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cmput_301_project.Account;
import com.example.cmput_301_project.FirestoreHandler;
import com.example.cmput_301_project.Habit;
import com.example.cmput_301_project.HabitEvent;
import com.example.cmput_301_project.adapters.ProfileHabitAdapter;
import com.example.cmput_301_project.R;
import com.example.cmput_301_project.fragments.UserSettingsFragment;
import com.example.cmput_301_project.fragments.VerifyPasswordFragment;

import java.util.ArrayList;

/**
 * Activity that showcases the user's account profile
 */

public class UserProfilePage extends AppCompatActivity implements VerifyPasswordFragment.OnPasswordVerify {
    boolean passwordVerified = false;
    // Define fragment manager and transaction for opening/closing settings fragment
    FragmentManager manager = getSupportFragmentManager();
    FragmentTransaction transaction;
    TextView usernameTextView;
    TextView progressText;
    ProgressBar progressBar;
    private int progress = 0;
    int progressMaxCounter = 0, progressCurrentCounter = 0;
    int[] progressRate = new int[2];
    Account userAccount;
    private RecyclerView recyclerView;
    private ProfileHabitAdapter profileHabitAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_page);

        usernameTextView = findViewById(R.id.usernameText);
        Account activeUserAccount = FirestoreHandler.create().getActiveUserAccount();
        usernameTextView.setText(activeUserAccount.getUserName());
        progressBar = findViewById(R.id.progress_bar);
        progressText = findViewById(R.id.prg_value);
        userAccount = FirestoreHandler.create().getActiveUserAccount();
        recyclerView = (RecyclerView) findViewById(R.id.habitList);

        // Percent completed habits per month graphic update
        for (Habit habit : userAccount.getHabitTable()) {
            progressRate = userAccount.getHabitCompletionRateInLastThirtyDays(habit.getId());
            progressCurrentCounter += progressRate[1];
            progressMaxCounter += progressRate[0];
        }
        if (progressMaxCounter != 0) {
            progress = 100 * progressCurrentCounter / progressMaxCounter;
        } else {
            progress = 0;
        }
        // Progress bar library bug fix (not our fault)
        if (progress < 100) {
            updateProgress(progress + 1);
        } else {
            updateProgress(progress - 1);
        }
        updateProgress(progress);
        
        ArrayList<HabitEvent> eventList = new ArrayList<HabitEvent>();
        userAccount.getRecentHabitEvents(eventList, true);
        profileHabitAdapter = new ProfileHabitAdapter(eventList,this);
        recyclerView.setAdapter(profileHabitAdapter);
    }

    public void onSettingsClick(View view) {
        passwordVerified = false;
        new VerifyPasswordFragment().show(getSupportFragmentManager(),"VERIFY_PASSWORD");
    }

    public void onPasswordVerify(boolean verified, Context context) {
        if (verified) {
            Fragment userSettings = new UserSettingsFragment();
            // Begin fragment transaction and add current activity to backstack
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
    public void updateProgress(int progress) {
        progressBar.setProgress(progress);
        progressText.setText(progress + "%");
    }
}
