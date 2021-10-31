package com.example.cmput_301_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class UserProfilePage extends AppCompatActivity {

    // TODO: random values chosen need to be replaced w real complete/incomplete habits
    int totalHabits = 14;
    int completedHabits = 6;
    FragmentManager manager = getSupportFragmentManager();
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_page);

        // calculate the % of habits completed this month and update the habit complete TV
        DecimalFormat df = new DecimalFormat("#");
        double habitsCompleted = ((double) completedHabits/totalHabits)*100;
        String habitPercent = df.format(habitsCompleted) + "% of habits completed this month";

        ((TextView)findViewById(R.id.completionPercText)).setText(
                habitPercent);
        // TODO: changes gradient of habit completion box depending on % of habits completed

        // create a list of completed habits and add them to the habit LV
        /*ListView habitList = findViewById(R.id.habitList);

        String []habits ={"Wash dishes", "Homework", "Study", "Walk dog", "Make dinner"};

        ArrayList<String> dataList = new ArrayList<>(Arrays.asList(habits));
        ArrayAdapter<String> habitAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);

        habitList.setAdapter(habitAdapter);*/
    }

    public void onSettingsClick(View view) {
        ImageButton settingsBtn = findViewById(R.id.settingsButton);
        // TODO: should probably add an animation when button is pressed using drawables
        // create a new settings fragment and display it on the appropriate frame
        Fragment userSettings = new UserSettingsFragment();
        // begin fragment transaction and add current fragment to backstack
        transaction = manager.beginTransaction();
        transaction.add(R.id.settingsFrame, userSettings);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}