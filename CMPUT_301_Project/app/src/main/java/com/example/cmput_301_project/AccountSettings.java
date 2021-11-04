package com.example.cmput_301_project;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
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
                Intent switchActivityIntent = new Intent(AccountSettings.this, AccountSettings.class);
                startActivity(switchActivityIntent);
            }
        });

        final Button viewProfileButton = findViewById(R.id.viewProfile);
        viewProfileButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO: CHANGE SECOND CLASS TO REDIRECT PAGE
                Intent switchActivityIntent = new Intent(AccountSettings.this, AccountSettings.class);
                startActivity(switchActivityIntent);
            }
        });

        final Button changePassButton = findViewById(R.id.changePassword);
        changePassButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO: CHANGE SECOND CLASS TO REDIRECT PAGE
                Intent switchActivityIntent = new Intent(AccountSettings.this, AccountSettings.class);
                startActivity(switchActivityIntent);
            }
        });

        final Button logoutButton = findViewById(R.id.logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO: CHANGE SECOND CLASS TO REDIRECT PAGE
                Intent switchActivityIntent = new Intent(AccountSettings.this, AccountSettings.class);
                startActivity(switchActivityIntent);
            }
        });
    }

    public boolean onOptionsItemSelected (MenuItem item){
        finish();
        return true;
    }
}
