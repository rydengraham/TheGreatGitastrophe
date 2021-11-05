package com.example.cmput_301_project;

import android.graphics.Bitmap;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
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

    private List<Habit> habitTable;

    // Account Information
    public Account(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.id = UUID.randomUUID().toString();
        this.habitTable = new ArrayList<>();

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

    // TODO: Change to internally check password
    public Boolean checkPassword(String password) {
        String candidatePassword = "";
        try {
            MessageDigest hasher = MessageDigest.getInstance("SHA-256");
            String toHash = password + this.id;
            byte[] digest = hasher.digest(toHash.getBytes());
            candidatePassword = new String(digest);
        } catch (NoSuchAlgorithmException e) {}

        return this.password.equals(candidatePassword);
    }

    // TODO: Add User Habit Items

    public String getPassword() {
        // Getter required only for firestore
        return password;
    }

    public void setPassword(String password) {
        // Setter required only for firestore
        this.password = password;
    }

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

    public String getId() {
        return id;
    }

    public List<Habit> getHabitTable() {
        return this.habitTable;
    }

    public void setHabitTable(List<Habit> habitTable) {
        this.habitTable = habitTable;
    }

    public void addHabit(Habit newHabit) {
        this.habitTable.add(newHabit);
    }

    public void deleteHabit(Habit newHabit) {
        this.habitTable.remove(newHabit);
    }

//    public void updateHabit(Habit newHabit) {
//        this.habitTable.add(newHabit);
//    }

    public Bitmap getPfp() {
        return pfp;
    }

    public void setPfp(Bitmap pfp) {
        this.pfp = pfp;
    }
}
