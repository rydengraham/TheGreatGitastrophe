package com.example.cmput_301_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Class responsible for displaying recycle view of habits, and deleting habit items. Extends {@link AppCompatActivity} and implements {@link HabitFragments.OnFragmentInteractionListener}}
 */
public class MyFriends extends AppCompatActivity {
    private Account userAccount = AccountData.create().getActiveUserAccount();
    private Button removeButton;
    private Button cancelButton;
    private Button addHabitButton;
    private Button viewAllHabitsButton;
    private TextView deleteText;
    private ListView friendsListView;
    private static ArrayList<String> friendsList;
    private static FriendsListAdapter friendsListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_friends);
        // Find our components here
        removeButton = (Button) findViewById(R.id.removeFriendButton);
        cancelButton = (Button) findViewById(R.id.cancelDeleteButton);
        viewAllHabitsButton = findViewById(R.id.dailyFriendsHabitsButton);
        deleteText = (TextView) findViewById(R.id.deleteFriendText);
        addHabitButton = findViewById(R.id.addApproveButton);
        friendsListView = (ListView) findViewById(R.id.friendList);

        friendsList = new ArrayList<>();

        // Temporary list filling variables
        friendsList.add("Friend 1");
        friendsList.add("Friend 2");
        friendsList.add("Friend 3");
        friendsList.add("Friend 4");
        friendsList.add("Friend 5");
        friendsList.add("Friend 6");
        friendsList.add("Friend 7");
        friendsList.add("Friend 8");
        friendsList.add("Friend 9");

        friendsListAdapter = new FriendsListAdapter(this, R.layout.friend_custom_list, friendsList, false);
        friendsListView.setAdapter(friendsListAdapter);

        // If remove button is clicked enable delete mode
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelButton.setVisibility(View.VISIBLE);
                deleteText.setVisibility(View.VISIBLE);
                addHabitButton.setVisibility(View.GONE);
                removeButton.setVisibility(View.GONE);
                friendsListAdapter.setDelMode(true);
            }
        });

        // If cancel button is clicked disable delete mode
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelButton.setVisibility(View.GONE);
                deleteText.setVisibility(View.GONE);
                addHabitButton.setVisibility(View.VISIBLE);
                removeButton.setVisibility(View.VISIBLE);
                friendsListAdapter.setDelMode(false);
            }
        });

        addHabitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchToAddFriendsPage = new Intent(MyFriends.this, AddFriends.class);
                startActivity(switchToAddFriendsPage);
            }
        });

        // If viewAllHabits button is pressed, navigate to FriendsHabitEventsPage
        viewAllHabitsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchToFriendsHabitEventsPage = new Intent(MyFriends.this, FriendsHabitEventsPage.class);
                startActivity(switchToFriendsHabitEventsPage);
            }
        });

    }

    /**
     * Returns the ToDo array list
     * @return Array list containing ToDo events
     */
    public static ArrayList<String> getFriendsList(){
        return friendsList;
    }

    /**
     * Returns the ToDo adapter
     * @return Adapter for ToDo list
     */
    public static FriendsListAdapter getFriendsListAdapter(){
        return friendsListAdapter;
    }
}
