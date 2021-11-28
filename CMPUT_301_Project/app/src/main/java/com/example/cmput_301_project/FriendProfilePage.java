package com.example.cmput_301_project;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class FriendProfilePage extends AppCompatActivity {

    // TODO: random values chosen need to be replaced w real complete/incomplete habits
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile_page);

        // TODO: add code to change friend profile photo and username here

        // calculate the % of habits completed this month and update the habit complete TV
        DecimalFormat df = new DecimalFormat("#");
        String habitPercent = df.format(habitRatio*100) + "% of habits completed this month";
        TextView friendCompletionPerc = (TextView) findViewById(R.id.friendCompletionPercTV);
        friendCompletionPerc.setText(habitPercent);
        /*
         * set the background of the completion percentage drawable as a hue between red & green
         * depending on the ratio of completed habits/total habits
         */
        friendCompletionPerc.getBackground().setTint(setHabitColour(habitRatio));

        ArrayList<HabitEvent> eventList = new ArrayList<HabitEvent>();
        Bundle extras = getIntent().getExtras();
        Account friendAccount = AccountData.create().getAccountData().get(extras.getString("friendId"));
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
            builder.setMessage("Are you sure you want to unfollow user's habits?");
            builder.setPositiveButton("Unfollow", ((dialog, which) -> {
                // change status of follow button to prompt user to follow
                followButton.setText("Follow Habits");
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
}