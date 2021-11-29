/**
 * Menu page for additional account functions.
 */
package com.example.cmput_301_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class AccountSettings extends AppCompatActivity {

    FragmentTransaction transaction;
    FragmentManager manager = getSupportFragmentManager();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_settings);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        /**
         * Link to profile page
         */
        final Button viewProfileButton = findViewById(R.id.viewProfile);
        viewProfileButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent switchActivityIntent = new Intent(AccountSettings.this, UserProfilePage.class);
                startActivity(switchActivityIntent);
            }
        });

        /**
         * Password change page link
         */
        final Button changePassButton = findViewById(R.id.changePassword);
        changePassButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // create a new change password fragment and display it on the appropriate frame
                Fragment changePassword = new ChangePasswordFragment();
                // begin fragment transaction and add current activity to backstack
                transaction = manager.beginTransaction();
                transaction.add(R.id.changePasswordFrame, changePassword);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        /**
         * Allows for account deletion
         */
        final Button deleteAccountButton = findViewById(R.id.delete);
        deleteAccountButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Standard TBD Alert Dialogue
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setCancelable(true);
                builder.setTitle("Are You Sure You Want To Delete Your Account?");
                builder.setMessage("This action cannot be undone");
                // If the user chooses to exit, return to the user profile activity
                builder.setPositiveButton("Delete Account", (dialog, which) -> {
                    AccountData accountData = AccountData.create();
                    Account activeFriendAccount;
                    // Deletion of account from other user's friend and pending list
                    for (String id : accountData.getActiveUserAccount().getFriendList()) {
                        activeFriendAccount = accountData.getAccountData().get(id);
                        activeFriendAccount.removeFriend(accountData.getActiveUserId());
                        activeFriendAccount.updateFirestore();
                    }
                    for (Account friendAccount : accountData.getAccountData().values()) {
                        if (friendAccount.getFriendPendingList().contains(accountData.getActiveUserId())) {
                            friendAccount.removePendingFriend(accountData.getActiveUserId());
                            friendAccount.updateFirestore();
                        }
                    }
                    accountData.deleteActiveUserAccount();
                    Intent switchActivityIntent = new Intent(AccountSettings.this, LoginScreenPage.class);
                    startActivity(switchActivityIntent);
                });
                // If the user chooses to stay on the fragment, simply close the dialog
                builder.setNegativeButton("Cancel", null);
                // Create the alert dialog and display it over the fragment
                AlertDialog confirmDeleteDialogue = builder.create();
                confirmDeleteDialogue.show();
            }
        });

        final Button logoutButton = findViewById(R.id.logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AccountData.create().setActiveUserId("");
                Intent switchActivityIntent = new Intent(AccountSettings.this, LoginScreenPage.class);
                startActivity(switchActivityIntent);
            }
        });
    }
}
