package com.example.cmput_301_project;

import android.graphics.Bitmap;

import java.util.HashMap;
import java.util.UUID;

public class Account {
    // Identification Information
    private String username;
    private String email;
    private String password;
    private String id;
    // this might change later
    // TODO: set to default pfp
    private Bitmap pfp;

    private HashMap<String, Habit> habitTable = new HashMap<String, Habit>();

    // Account Information
    public Account(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.id = UUID.randomUUID().toString();
    }

    public Account() {}

    // TODO: Add User Habit Items

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public HashMap<String, Habit> getHabitTable() {
        return habitTable;
    }

    public void setHabitTable(HashMap<String, Habit> habitTable) {
        this.habitTable = habitTable;
    }

    public Bitmap getPfp() {
        return pfp;
    }

    public void setPfp(Bitmap pfp) {
        this.pfp = pfp;
    }
}
