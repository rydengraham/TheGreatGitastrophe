package com.example.cmput_301_project.pages;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cmput_301_project.fragments.HabitFragmentHelper;
import com.example.cmput_301_project.R;
import com.example.cmput_301_project.adapters.RecyclerViewAdapter;

/**
 * Class responsible for displaying recycle view of habits, and deleting habit items. Extends {@link AppCompatActivity} and implements {@link HabitFragmentHelper.OnFragmentInteractionListener}}
 */
public class FriendHabitsPage extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recycleAdapter;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_habits_page);
        // Find our components here
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // Initialize adapter and set recycleView to it
        recycleAdapter = new RecyclerViewAdapter(this, false);
        recyclerView.setAdapter(recycleAdapter);
    }
}
