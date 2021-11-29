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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cmput_301_project.Account;
import com.example.cmput_301_project.FirestoreHandler;
import com.example.cmput_301_project.R;
import com.example.cmput_301_project.pages.AddFriendsPage;
import com.example.cmput_301_project.pages.FriendProfilePage;
import com.example.cmput_301_project.pages.MyFriendsPage;

import java.util.ArrayList;

/**
 * Custom adapter class for Pending Friends
 */
public class PendingFriendsAdapter extends BaseAdapter {
    // Variable Declaration
    int resource;
    Context context;
    ArrayList<String> pendingFriendList;

    /**
     * Constructor for the adapter
     * @param context
     * @param resource
     * @param myList
     */
    public PendingFriendsAdapter(Context context, int resource, ArrayList<String> myList){
        this.resource = resource;
        this.context = context;
        pendingFriendList = myList;
    }

    /**
     * Returns the list size
     * @return List size
     */
    @Override
    public int getCount() {
        return pendingFriendList.size();
    }

    /**
     * Returns the item at i
     * @param i index
     * @return Habit event at index
     */
    @Override
    public String getItem(int i) {
        return pendingFriendList.get(i);
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
        view=null;
        View v = view;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(context);
            v = vi.inflate(resource, null);
        }
        // Reference each item
        LinearLayout textHolder = v.findViewById(R.id.pendingFriendHolder);
        Button confirmButton = v.findViewById(R.id.confirmAddButton);
        Button declineButton = v.findViewById(R.id.declineAddButton);
        TextView pendingFriendName = v.findViewById(R.id.pendingFriendName);

        // Expand the view
        textHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchToFriendProfile = new Intent(context, FriendProfilePage.class);
                context.startActivity(switchToFriendProfile);
            }
        });

        FirestoreHandler firestoreHandler = FirestoreHandler.create();

        String friendName = getItem(i);
        if (friendName != null){
            pendingFriendName.setText(friendName);
            confirmButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    MyFriendsPage.getFriendsList().add(friendName);
                    MyFriendsPage.getFriendsListAdapter().notifyDataSetChanged();
                    AddFriendsPage.getPendingList().remove(friendName);
                    AddFriendsPage.getPendingListAdapter().notifyDataSetChanged();
                    for (Account friendAccount : firestoreHandler.getAccountData().values()) {
                        if (friendAccount.getUserName().equals(friendName)) {
                            String friendID = friendAccount.getId();
                            firestoreHandler.getActiveUserAccount().removePendingFriend(friendID);
                            firestoreHandler.getActiveUserAccount().addFriend(friendID);
                            friendAccount.addFriend(firestoreHandler.getActiveUserId());
                            friendAccount.removePendingFriend(firestoreHandler.getActiveUserId());
                            friendAccount.updateFirestore();
                            firestoreHandler.getActiveUserAccount().updateFirestore();
                            break;
                        }
                    }
                }
            });
            declineButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    AddFriendsPage.getPendingList().remove(friendName);
                    AddFriendsPage.getPendingListAdapter().notifyDataSetChanged();
                    for (Account friendAccount : firestoreHandler.getAccountData().values()) {
                        if (friendAccount.getUserName().equals(friendName)) {
                            String friendID = friendAccount.getId();
                            firestoreHandler.getActiveUserAccount().removePendingFriend(friendID);
                            firestoreHandler.getActiveUserAccount().updateFirestore();
                            break;
                        }
                    }
                }
            });
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
