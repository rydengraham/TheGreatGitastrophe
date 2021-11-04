package com.example.cmput_301_project;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen_page);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        usernameField = (EditText) view.findViewById(R.id.createUsernameEV);
        passwordField = (EditText) view.findViewById(R.id.createPasswordEV);

        return view;
    }

    public void onRegisterClick(View view) {
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
        // TODO: add functionality to check user credentials
        HashMap<String, Account> accountData = AccountData.create().getAccountData();

        usernameField = (EditText) view.findViewById(R.id.createUsernameEV);
        passwordField = (EditText) view.findViewById(R.id.createPasswordEV);

        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        System.out.println("login attempt: " + username + " " + password);

        boolean validated = false;

        for (Account existingAccount : accountData.values()) {
            System.out.println(existingAccount.getUserName() + " " + existingAccount.getEmail());
            if (existingAccount.getUserName().equals(username) && existingAccount.checkPassword(password)) {
                // TODO: open UI fragment to mention issue
                validated = true;
                break;
            }
        }

        // when 'sign in' button is pressed, open the main page after verification
        if (validated) {
            Intent switchToMainPage = new Intent(LoginScreenPage.this, MainPage.class);
            startActivity(switchToMainPage);
        }
    }
}