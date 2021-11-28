package com.example.cmput_301_project;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FriendProfilePage extends AppCompatActivity {

    // TODO: random values chosen need to be replaced w real complete/incomplete habits
    private String friendName = "";
    int totalHabits = 16;
    int completedHabits = 5;
    double habitRatio = (double) completedHabits/totalHabits;
    /* TODO: isFollowing should be a real value corresponding to if user is
        following friend's habits, currently set to false to allow follow button to work
     */
    boolean isFollowing = false;
    TextView friendUsernameTV;
    private RecyclerView recyclerView;
    private ProfileHabitAdapter profileHabitAdapter;
    AccountData accountData;
    TextView progressText;
    ProgressBar progressBar;
    private int progress = 0;
    int progressMaxCounter = 0, progressCurrentCounter = 0;
    int[] progressRate = new int[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile_page);

        // TODO: add code to change friend profile photo here
        
        TextView friendNameView = (TextView) findViewById(R.id.friendUsernameTV);


        recyclerView = (RecyclerView) findViewById(R.id.habitList);
        progressBar = findViewById(R.id.progress_bar);
        progressText = findViewById(R.id.prg_value);

        Bundle extras = getIntent().getExtras();
        accountData = AccountData.create();
        Account friendAccount = accountData.getAccountData().get(extras.getString("friendId"));
        friendName = friendAccount.getUserName();
        friendNameView.setText(friendName);

        // Percent completed habits per month graphic update
        for (Habit habit : friendAccount.getHabitTable()) {
            progressRate = friendAccount.getHabitCompletionRateInLastThirtyDays(habit.getId());
            progressCurrentCounter += progressRate[1];
            progressMaxCounter += progressRate[0];
        }
        if (progressMaxCounter != 0) {
            progress = 100 * progressCurrentCounter / progressMaxCounter;
        } else {
            progress = 0;
        }
        updateProgress(progress);

        ArrayList<HabitEvent> eventList = new ArrayList<HabitEvent>();
        friendAccount.getRecentHabitEvents(eventList);
        profileHabitAdapter = new ProfileHabitAdapter(eventList,this);
        recyclerView.setAdapter(profileHabitAdapter);
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

    /**
     * This function is called when the 'Detailed Habit List' button is pressed,
     * taking user to FriendDetailedHabitListPage
     * @param view current view containing habitListButton
     */
    public void onDetailedHabitsClick(View view) {
        // TODO: add code to go to detailed habits list here
    }

    /**
     * This function is called when the 'Follow Habits' button is pressed, updating following status.
     * @param view current view containing followHabitsButton
     */
    public void onFollowHabitClick(View view) {
        RadioButton followButton = view.findViewById(R.id.followHabitsButton);

        // change status of isFollowing bool and update radio button state
        // if user is not already following, update button and text
        if(!isFollowing) {
            followButton.setText("Following");
            isFollowing = true;
            followButton.setChecked(true);
            // TODO: add code to start following friends habits here
        }
        else {
            // create an alert dialog to confirm the user wants to unfollow friends habits
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setCancelable(true);
            builder.setTitle("Unfollow Friends Habits?");
            builder.setMessage("Are you sure you want to unfollow " + friendName +"'s daily habits?");
            builder.setPositiveButton("Unfollow", ((dialog, which) -> {
                // change status of follow button to prompt user to follow
                followButton.setText("Follow Daily Habits");
                isFollowing = false;
                followButton.setChecked(false);
                // TODO: add code to stop following friends habits here
            }));
            builder.setNegativeButton("No", null);
            // create the alert dialog and display it over the fragment
            AlertDialog alertBox = builder.create();
            alertBox.show();
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