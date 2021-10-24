package com.example.cmput_301_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Hashtable;

public class LoginTestPage extends AppCompatActivity {
    Hashtable<String, String> accountData = new Hashtable<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_test);
    }
}