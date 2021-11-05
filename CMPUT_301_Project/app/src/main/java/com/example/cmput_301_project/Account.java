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

    private String getToday() {
        // Day getter from: https://stackoverflow.com/questions/8654990/how-can-i-get-current-date-in-android
        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        return df.format(c);
    }

    public ArrayList<HabitEvent> getTodoHabitEvents() {
        ArrayList<HabitEvent> todoHabits = new ArrayList<>();
        int weekday = getWeekday();
        String today = getToday();
        System.out.println("week: "+weekday);

        for (Habit currentHabit : this.habitTable) {
            System.out.println("day: "+currentHabit.getIsOnDayOfWeek(weekday));
            if (currentHabit.getIsOnDayOfWeek(weekday)) {
                boolean createTodayEvent = true;
                for(HabitEvent event : currentHabit.getHabitEventTable()) {
                    System.out.println("name: "+event.getTitle());
                    if (event.getDate().equals(today) && !event.isCompleted()) {
                        todoHabits.add(event);
                        createTodayEvent = false;
                        break;
                    }
                }
                if (createTodayEvent) {
                    HabitEvent newEvent = new HabitEvent(today, currentHabit.getHabitName());
                    currentHabit.addHabitEvent(newEvent);
                    todoHabits.add(newEvent);
                }
            }
        }
        return todoHabits;
    }

    public ArrayList<HabitEvent> getCompletedHabitEvents() {
        ArrayList<HabitEvent> completedHabits = new ArrayList<>();
        int weekday = getWeekday();
        String today = getToday();

        for (Habit currentHabit : this.habitTable) {
            if (currentHabit.getIsOnDayOfWeek(weekday)) {
                for(HabitEvent event : currentHabit.getHabitEventTable()) {
                    if (event.getDate().equals(today) && event.isCompleted()) {
                        completedHabits.add(event);
                        break;
                    }
                }
            }
        }
        return completedHabits;
    }

    public HabitEvent getHabitEvent(String date, String habitName) {
        for (Habit currentHabit : this.habitTable) {
            if (currentHabit.getHabitName().equals(habitName)) {
                for(HabitEvent event : currentHabit.getHabitEventTable()) {
                    if (event.getDate().equals(date)) {
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
