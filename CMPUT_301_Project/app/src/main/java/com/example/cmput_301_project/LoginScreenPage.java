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

public class LoginScreenPage extends AppCompatActivity {

    FragmentManager manager = getSupportFragmentManager();
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen_page);
    }

    public void onRegisterClick(View view) {
        // create a new settings fragment and display it on the appropriate frame
        Fragment registerFragment = new SignUpFragment();
        //begin fragment transaction and add current activity to backstack
        transaction = manager.beginTransaction();
        transaction.add(R.id.signUpLayout, registerFragment);
        transaction.addToBackStack(null);
        // execute transaction and open sign-up fragment
        transaction.commit();
    }

    public void onSignInClick(View view) {
        // TODO: add functionality to check user credentials
        // when 'sign in' button is pressed, open the main page after verification
        Intent switchToMainPage = new Intent(LoginScreenPage.this, MainPage.class);
        startActivity(switchToMainPage);
    }
}