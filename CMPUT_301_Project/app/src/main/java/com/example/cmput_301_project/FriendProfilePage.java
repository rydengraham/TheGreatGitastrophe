package com.example.cmput_301_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

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

        // create a list of completed habits and add them to the habit LV
        ListView friendHabitList = findViewById(R.id.friendProfileHabitList);
        // TODO: need to use a list of real recently-completed habits instead of examples here
        String[] testHabits ={"Habit 1", "Habit 2", "Habit 3", "Habit 4", "Habit 5",
                "Habit 6" ,"Habit 7", "Habit 8", "Habit 9", "Habit 10"};
        // set up the dataList & adapter for converting habit array to ListView
        ArrayList<String> dataList = new ArrayList<>(Arrays.asList(testHabits));
        /*
            TODO: commit on habit_list_textview.xml broke this since it used the same layout as the
                user profile page, if we're changing the layout for this one as well, I'll just leave
                it commented out until changes are made.
         */
        ArrayAdapter recentHabitsAdapter = new ArrayAdapter(this, R.layout.habit_list_textview, dataList);

        //friendHabitList.setAdapter(recentHabitsAdapter);
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
        // create new intent to navigate to FriendDetailedHabitsPage
        Intent switchToFriendHabitPage = new Intent(FriendProfilePage.this, FriendDetailedHabitsPage.class);
        startActivity(switchToFriendHabitPage);
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