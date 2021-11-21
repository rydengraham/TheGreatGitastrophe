package com.example.cmput_301_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddFriends extends AppCompatActivity {

    private ListView pendingFriendsView;
    private Button addFriendButton;
    private static ArrayList<String> pendingFriendsList;
    private static PendingFriendsAdapter pendingFriendsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friends);
        // Find our components here
        addFriendButton = (Button) findViewById(R.id.addFriendButton);

        pendingFriendsView = (ListView) findViewById(R.id.pendingFriendList);

        pendingFriendsList = new ArrayList<>();

        // Temporary list filling variables
        pendingFriendsList.add("Friend 1");
        pendingFriendsList.add("Friend 2");
        pendingFriendsList.add("Friend 3");
        pendingFriendsList.add("Friend 4");
        pendingFriendsList.add("Friend 5");
        pendingFriendsList.add("Friend 6");
        pendingFriendsList.add("Friend 7");
        pendingFriendsList.add("Friend 8");
        pendingFriendsList.add("Friend 9");

        pendingFriendsAdapter = new PendingFriendsAdapter(this, R.layout.add_friend_custom_list, pendingFriendsList);
        pendingFriendsView.setAdapter(pendingFriendsAdapter);

        // If cancel button is clicked disable delete mode
        addFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    /**
     * Returns the ToDo array list
     * @return Array list containing ToDo events
     */
    public static ArrayList<String> getPendingList(){
        return pendingFriendsList;
    }

    /**
     * Returns the ToDo adapter
     * @return Adapter for ToDo list
     */
    public static PendingFriendsAdapter getPendingListAdapter(){
        return pendingFriendsAdapter;
    }
}
