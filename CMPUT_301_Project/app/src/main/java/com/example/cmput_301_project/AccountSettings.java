/**
 * Menu page for additional account functions.
 */
package com.example.cmput_301_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AccountSettings extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_settings);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        final Button viewToggleButton = findViewById(R.id.privViewToggle);
        viewToggleButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO: CHANGE SECOND CLASS TO REDIRECT PAGE
                // Standard TBD Alert Dialogue
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setCancelable(true);
                builder.setTitle("Page Does Not Exist");
                builder.setMessage("This will be added in project part 4.");
                builder.setNegativeButton("OK", null);
                // create the alert dialog and display it over the fragment
                AlertDialog alertBox = builder.create();
                alertBox.show();
            }
        });

        final Button viewProfileButton = findViewById(R.id.viewProfile);
        viewProfileButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent switchActivityIntent = new Intent(AccountSettings.this, UserProfilePage.class);
                startActivity(switchActivityIntent);
            }
        });

        final Button changePassButton = findViewById(R.id.changePassword);
        changePassButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO: CHANGE SECOND CLASS TO REDIRECT PAGE
                // Standard TBD Alert Dialogue
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setCancelable(true);
                builder.setTitle("Page Does Not Exist");
                builder.setMessage("This will be added in project part 4.");
                builder.setNegativeButton("OK", null);
                // create the alert dialog and display it over the fragment
                AlertDialog alertBox = builder.create();
                alertBox.show();
            }
        });

        final Button deleteAccountButton = findViewById(R.id.delete);
        deleteAccountButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO: CHANGE SECOND CLASS TO REDIRECT PAGE
                // Standard TBD Alert Dialogue
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setCancelable(true);
                builder.setTitle("Are You Sure You Want To Delete Your Account?");
                builder.setMessage("This action cannot be undone");
                // if the user chooses to exit, return to the user profile activity
                builder.setPositiveButton("Delete Account", (dialog, which) -> {
                    AccountData.create().deleteActiveUserAccount();
                    Intent switchActivityIntent = new Intent(AccountSettings.this, LoginScreenPage.class);
                    startActivity(switchActivityIntent);
                });
                // if the user chooses to stay on the fragment, simply close the dialog
                builder.setNegativeButton("Cancel", null);
                // create the alert dialog and display it over the fragment
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

    public boolean onOptionsItemSelected (MenuItem item){
        finish();
        return true;
    }
}
