/**
 * Account data structure unique per user
 */
package com.example.cmput_301_project;

import android.graphics.Bitmap;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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

    /**
     * Hashes password and stores it
     * @param rawPassword
     * @throws NoSuchAlgorithmException
     */
    public void updatePassword(String rawPassword) throws NoSuchAlgorithmException {
        MessageDigest hasher = MessageDigest.getInstance("SHA-256");
        String toHash = rawPassword + this.id;
        byte[] digest = hasher.digest(toHash.getBytes());
        this.password = new String(digest);
    }

    /**
     * Check if password is correct by matching hash
     * @param password
     * @return
     */
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


    /**
     * Forces a Firestore update for the active account
     */
    public void updateFirestore() {
        AccountData.create().modifyAccount(this);
    }

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

    public void updateHabit(int position, Habit updatedHabit) {
        this.habitTable.set(position, updatedHabit);
    }

    /**
     * Gets the current day of the week
     * @return
     */
    private int getWeekday() {
        // Day of week getter from: https://stackoverflow.com/questions/5574673/what-is-the-easiest-way-to-get-the-current-day-of-the-week-in-android
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        switch (day) {
            case Calendar.MONDAY:
                return 0;
            case Calendar.TUESDAY:
                return 1;
            case Calendar.WEDNESDAY:
                return 2;
            case Calendar.THURSDAY:
                return 3;
            case Calendar.FRIDAY:
                return 4;
            case Calendar.SATURDAY:
                return 5;
            case Calendar.SUNDAY:
                return 6;
        }
        return 7;
    }

    /**
     * Gets a string of the current date
     * @return
     */
    private String getToday() {
        // Day getter from: https://stackoverflow.com/questions/8654990/how-can-i-get-current-date-in-android
        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        return df.format(c);
    }

    /**
     * Gets a list of habit events for today, both completed and todo.
     * @return
     */
    public void getTodayHabitEvents(ArrayList<HabitEvent> todoHabits, ArrayList<HabitEvent> completedHabits) {
        int weekday = getWeekday();
        String today = getToday();

        for (Habit currentHabit : this.habitTable) {
            if (currentHabit.getIsOnDayOfWeek(weekday)) {
                boolean createTodayEvent = true;
                for(HabitEvent event : currentHabit.getHabitEventTable()) {
                    if (event.getDate().equals(today)) {
                        if (event.isCompleted()) {
                            completedHabits.add(new HabitEvent(event));
                        } else {
                            todoHabits.add(new HabitEvent(event));
                        }
                        createTodayEvent = false;
                        break;
                    }
                }
                if (createTodayEvent) {
                    HabitEvent newEvent = new HabitEvent(today, currentHabit.getHabitName());
                    currentHabit.addHabitEvent(newEvent);
                    todoHabits.add(new HabitEvent(newEvent));
                }
            }
        }
        this.updateFirestore();
        return;
    }

    /**
     * Getter for habit events, for editing.
     * @param id
     * @param habitName
     * @return
     */
    public HabitEvent getHabitEvent(String id, String habitName) {
        for (Habit currentHabit : this.habitTable) {
            if (currentHabit.getHabitName().equals(habitName)) {
                for(HabitEvent event : currentHabit.getHabitEventTable()) {
                    if (event.getId() == id) {
                        return event;
                    }
                }
            }
        }
        return null;
    }

    public Bitmap getPfp() {
        return pfp;
    }

    public void setPfp(Bitmap pfp) {
        this.pfp = pfp;
    }
}
