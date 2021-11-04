package com.example.cmput_301_project;

import android.graphics.Bitmap;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.UUID;

public class Account {
    // Identification Information
    private String username;
    private String email;
    private String password;
    private String id; // Used as password salt
    // this might change later
    // TODO: set to default pfp
    private Bitmap pfp;

    private HashMap<String, Habit> habitTable = new HashMap<String, Habit>();

    // Account Information
    public Account(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.id = UUID.randomUUID().toString();

        try {
            updatePassword(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public Account() { /* Required empty public constructor */ }

    public void updatePassword(String rawPassword) throws NoSuchAlgorithmException {
        MessageDigest hasher = MessageDigest.getInstance("SHA-256");
        String toHash = rawPassword + this.id;
        byte[] digest = hasher.digest(toHash.getBytes());
        this.password = new String(digest);
    }

    // TODO: Add User Habit Items

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // TODO: Change to internally check password
    public boolean checkPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest hasher = MessageDigest.getInstance("SHA-256");
        byte[] digest = hasher.digest(password.getBytes());
        String candidatePassword = new String(digest);

        return this.password.equals(candidatePassword);
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
