/**
 * Log in screen page activity
 */
package com.example.cmput_301_project;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class LoginScreenPage extends AppCompatActivity {
    FragmentManager manager = getSupportFragmentManager();
    FragmentTransaction transaction;

    EditText usernameField;
    EditText passwordField;

    HashMap<String, Account> accountData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen_page);

        usernameField = findViewById(R.id.usernameEV);
        passwordField = findViewById(R.id.passwordEV);

        accountData = AccountData.create().getAccountData();
    }

    public void onRegisterClick(View view) {
        usernameField.setText("");
        passwordField.setText("");
        // Create a new settings fragment and display it on the appropriate frame
        Fragment registerFragment = new SignUpFragment();
        // Begin fragment transaction and add current activity to backstack
        transaction = manager.beginTransaction();
        transaction.add(R.id.signUpLayout, registerFragment);
        transaction.addToBackStack(null);
        // Execute transaction and open sign-up fragment
        transaction.commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onSignInClick(View view) throws NoSuchAlgorithmException {
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();

        boolean validated = false;
        for (Account existingAccount : accountData.values()) {
            if (existingAccount.getUserName().equals(username) && existingAccount.checkPassword(password)) {
                AccountData accountData = AccountData.create();
                accountData.setActiveUserId(existingAccount.getId());
                accountData.getActiveUserAccount().backfillHabitEvents();
                validated = true;
                break;
            }
        }

        // When 'sign in' button is pressed, open the main page after verification
        if (validated) {
            usernameField.setText("");
            passwordField.setText("");
            Intent switchToMainPage = new Intent(LoginScreenPage.this, MainPage.class);
            startActivity(switchToMainPage);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setCancelable(true);
            builder.setTitle("Username/Password combination does not exist");
            // If the user chooses to stay on the fragment, simply close the dialog
            builder.setNegativeButton("OK", null);
            // Create the alert dialog and display it over the fragment
            AlertDialog confirmDialogue = builder.create();
            confirmDialogue.show();
        }
    }
}
