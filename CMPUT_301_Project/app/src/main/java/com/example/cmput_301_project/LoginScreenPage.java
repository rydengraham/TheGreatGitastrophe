package com.example.cmput_301_project;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        // create a new settings fragment and display it on the appropriate frame
        Fragment registerFragment = new SignUpFragment();
        // begin fragment transaction and add current activity to backstack
        transaction = manager.beginTransaction();
        transaction.add(R.id.signUpLayout, registerFragment);
        transaction.addToBackStack(null);
        // execute transaction and open sign-up fragment
        transaction.commit();
    }

    public void onSignInClick(View view) throws NoSuchAlgorithmException {
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();

        boolean validated = false;
        for (Account existingAccount : accountData.values()) {
            if (existingAccount.getUserName().equals(username) && existingAccount.checkPassword(password)) {
                // TODO: Set this account to be the active user
                AccountData accountData = AccountData.create();
                accountData.setActiveUserId(existingAccount.getId());
                validated = true;
                break;
            }
        }

        // when 'sign in' button is pressed, open the main page after verification
        if (validated) {
            usernameField.setText("");
            passwordField.setText("");
            Intent switchToMainPage = new Intent(LoginScreenPage.this, MainPage.class);
            startActivity(switchToMainPage);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setCancelable(true);
            builder.setTitle("Username/Password combination does not exist");
            // if the user chooses to stay on the fragment, simply close the dialog
            builder.setNegativeButton("OK", null);
            // create the alert dialog and display it over the fragment
            AlertDialog confirmDialogue = builder.create();
            confirmDialogue.show();
        }
    }
}