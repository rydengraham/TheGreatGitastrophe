/**
 * Adapter for today's habits
 */
package com.example.cmput_301_project.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.cmput_301_project.Account;
import com.example.cmput_301_project.FirestoreHandler;
import com.example.cmput_301_project.R;
import com.example.cmput_301_project.pages.FriendProfilePage;
import com.example.cmput_301_project.pages.MyFriendsPage;

import java.util.ArrayList;

/**
 * Custom adapter class for Today's Habits
 */
public class FriendsListAdapter extends BaseAdapter {
    // Variable Declaration
    int resource;
    Context context;
    ArrayList<String> friendList;
    boolean delMode;

    /**
     * Constructor for the adapter
     * @param context
     * @param resource
     * @param myList
     */
    public FriendsListAdapter(Context context, int resource, ArrayList<String> myList, boolean delMode){
        this.resource = resource;
        this.context = context;
        friendList = myList;
        this.delMode = delMode;
    }

    /**
     * Returns whether or not deletion mode is used
     * @return True if delete mode
     */
    public boolean isDelMode() {
        return delMode;
    }

    /**
     * Sets deletion mode
     * @return
     */
    public void setDelMode(boolean delMode) {
        this.delMode = delMode;
    }

    /**
     * Returns the list size
     * @return List size
     */
    @Override
    public int getCount() {
        return friendList.size();
    }

    /**
     * Returns the item at i
     * @param i index
     * @return Habit event at index
     */
    @Override
    public String getItem(int i) {
        return friendList.get(i);
    }

    /**
     * Returns the id of item i
     * @param i index
     * @return Id at index
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * Main function to set the list item data
     * @param i
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = null;
        View v = view;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(context);
            v = vi.inflate(resource, null);
        }
        // Reference each item
        LinearLayout textHolder = v.findViewById(R.id.text_wrapper);
        TextView friendsName = v.findViewById(R.id.friendName);
        String friendName = getItem(i);
        // Expand the view
        textHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirestoreHandler firestoreHandler = FirestoreHandler.create();
                if (delMode){
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setCancelable(true);
                    builder.setTitle("Remove Friend?");
                    builder.setMessage("Are you sure you want to remove this friend?");
                    builder.setPositiveButton("Remove", ((dialog, which) -> {
                        // Change status of follow button to prompt user to follow
                        MyFriendsPage.getFriendsList().remove(friendName);
                        for (Account friendAccount : firestoreHandler.getAccountData().values()) {
                            if (friendAccount.getUserName().equals(friendName)) {
                                firestoreHandler.getActiveUserAccount().removeFriend(friendAccount.getId());
                                friendAccount.removeFriend(firestoreHandler.getActiveUserId());
                                firestoreHandler.getActiveUserAccount().updateFirestore();
                                friendAccount.updateFirestore();
                                break;
                            }
                        }
                        MyFriendsPage.getFriendsListAdapter().notifyDataSetChanged();
                    }));
                    builder.setNegativeButton("Cancel", null);
                    // Create the alert dialog and display it over the fragment
                    AlertDialog alertBox = builder.create();
                    alertBox.show();
                } else{
                    Intent switchToFriendProfile = new Intent(context, FriendProfilePage.class);
                    for (Account friendAccount : firestoreHandler.getAccountData().values()) {
                        if (friendAccount.getUserName().equals(friendName)) {
                            switchToFriendProfile.putExtra("friendId", friendAccount.getId());
                            break;
                        }
                    }
                    context.startActivity(switchToFriendProfile);
                }
            }
        });

        if (friendName != null){
            friendsName.setText(friendName);
        }

        return v;
    }

    /**
     * Used to make items interactive
     * @return N/A
     */
    @Override
    public boolean areAllItemsEnabled()
    {
        return true;
    }

    /**
     * Used to make items interactive
     * @param arg0
     * @return N/A
     */
    @Override
    public boolean isEnabled(int arg0)
    {
        return true;
    }
}
