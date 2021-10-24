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

        final Button button1 = findViewById(R.id.privViewToggle);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // CHANGE SECOND CLASS TO REDIRECT PAGE
                Intent switchActivityIntent = new Intent(AccountSettings.this, AccountSettings.class);
                startActivity(switchActivityIntent);
            }
        });

        final Button button2 = findViewById(R.id.viewProfile);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // CHANGE SECOND CLASS TO REDIRECT PAGE
                Intent switchActivityIntent = new Intent(AccountSettings.this, AccountSettings.class);
                startActivity(switchActivityIntent);
            }
        });

        final Button button3 = findViewById(R.id.changePassword);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // CHANGE SECOND CLASS TO REDIRECT PAGE
                Intent switchActivityIntent = new Intent(AccountSettings.this, AccountSettings.class);
                startActivity(switchActivityIntent);
            }
        });

        final Button button4 = findViewById(R.id.logout);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // CHANGE SECOND CLASS TO REDIRECT PAGE
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
