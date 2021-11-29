package com.example.cmput_301_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for displaying recycle view of habits, and deleting habit items. Extends {@link AppCompatActivity} and implements {@link HabitFragments.OnFragmentInteractionListener}}
 */
public class MyFriends extends AppCompatActivity {
    private Button removeButton;
    private Button cancelButton;
    private Button addFriendButton;
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
        addFriendButton = findViewById(R.id.addApproveButton);
        friendsListView = (ListView) findViewById(R.id.friendList);
        AccountData accountData;

        friendsList = new ArrayList<>();

        // Temporary list filling variables
        accountData = AccountData.create();
        List<String> friendIdList = accountData.getActiveUserAccount().getFriendList();
        for (String id : friendIdList) {
            friendsList.add(accountData.getAccountData().get(id).getUserName());
        }

        friendsListAdapter = new FriendsListAdapter(this, R.layout.friend_custom_list, friendsList, false);
        friendsListView.setAdapter(friendsListAdapter);

        // If remove button is clicked enable delete mode
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelButton.setVisibility(View.VISIBLE);
                deleteText.setVisibility(View.VISIBLE);
                addFriendButton.setVisibility(View.GONE);
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
                addFriendButton.setVisibility(View.VISIBLE);
                removeButton.setVisibility(View.VISIBLE);
                friendsListAdapter.setDelMode(false);
            }
        });

        addFriendButton.setOnClickListener(new View.OnClickListener() {
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
