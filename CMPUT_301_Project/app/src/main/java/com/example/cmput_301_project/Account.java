/**
 * Account data structure unique per user
 */
package com.example.cmput_301_project;

import android.graphics.Bitmap;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * Accounts are essential for operating the app, accounts are used for register and sign in, as well as finding friends
 */

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
    // Friend Account IDs
    private List<String> friendList;
    private List<String> friendPendingList;

    // Account Information
    public Account(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.id = UUID.randomUUID().toString();
        this.habitTable = new ArrayList<>();
        this.friendPendingList = new ArrayList<String>();
        this.friendList = new ArrayList<String>();

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
     * Gets the total events and completed events of a given habit in the last 30 days
     * @param habitId
     * @return
     */
    public int[] getHabitCompletionRateInLastThirtyDays(String habitId) {
        // Index 0 = Total, 1 = Completed
        int[] completionRate = {0, 0};

        // Date variables
        Date today = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH, -30);
        Date thirtyDaysAgo = calendar.getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

        for (Habit habit : habitTable) {
            if (habit.getId().equals(habitId)) {
                for (HabitEvent event : habit.getHabitEventTable()) {
                    if (!event.isDeleted()) {
                        try {
                            Date eventDate = df.parse(event.getDate());
                            if (eventDate.before(today) && eventDate.after(thirtyDaysAgo)) {
                                completionRate[0]++;
                                if (event.isCompleted()) {
                                    completionRate[1]++;
                                }
                            }
                        } catch (ParseException e) {
                            System.err.println("Error!");
                        }
                    }
                }
                return completionRate;
            }
        }
        return completionRate;
    }

    /**
     * Adds a friend to the friend list
     * @param id
     */
    public void addFriend(String id) {
        if (!friendList.contains(id)) {
            this.friendList.add(id);
        }
    }

    /**
     * Removes a friend from the friend list
     * @param id
     */
    public void removeFriend(String id) {
        if (friendList.contains(id)) {
            this.friendList.remove(id);
        }
    }

    /**
     * Adds a friend to the pending friend list
     * @param id
     */
    public int addPendingFriend(String id) {
        if (friendList.contains(id)) {
            return 1;
        }
        if (friendPendingList.contains(id)) {
            return 2;
        }
        this.friendPendingList.add(id);
        return 0;
    }

    /**
     * Removes a friend from the pending friend list
     * @param id
     */
    public void removePendingFriend(String id) {
        if (friendPendingList.contains(id)) {
            this.friendPendingList.remove(id);
        }
    }

    /**
     * Adds a habit to the habit table
     * @param newHabit
     */
    public void addHabit(Habit newHabit) {
        this.habitTable.add(newHabit);
    }

    /**
     * Deletes a habit form the habit table
     * @param newHabit
     */
    public void deleteHabit(Habit newHabit) {
        this.habitTable.remove(newHabit);
    }

    /**
     * Updates a habit in the habit table at a given position
     * @param position
     * @param updatedHabit
     */
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
    public void getTodayHabitEvents(ArrayList<HabitEvent> todoHabits, ArrayList<HabitEvent> completedHabits, boolean includePrivate, boolean showUserNames) {
        int weekday = getWeekday();
        String today = getToday();

        for (Habit currentHabit : this.habitTable) {
            if (currentHabit.getPublic() || includePrivate) {
                if (currentHabit.getIsOnDayOfWeek(weekday)) {
                    for(HabitEvent event : currentHabit.getHabitEventTable()) {
                        if (event.getDate().equals(today)) {
                            if (!event.isDeleted()) {
                                HabitEvent newEvent = new HabitEvent(event);
                                if (showUserNames) {
                                    newEvent.setDate(this.getUserName());
                                }
                                if (event.isCompleted()) {
                                    completedHabits.add(newEvent);
                                } else {
                                    todoHabits.add(newEvent);
                                }
                            }
                            break;
                        }
                    }
                }
            }
        }
        return;
    }

    /**
     * Gets a list of habit events completed in the past week
     * @return
     */
    public void getRecentHabitEvents(ArrayList<HabitEvent> recentHabits, boolean includePrivate) {
        Calendar oldestDay = Calendar.getInstance();
        oldestDay.add(Calendar.DATE, -7);
        Date sevenDaysAgo = oldestDay.getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());

        for (Habit currentHabit : this.habitTable) {
            if (currentHabit.getPublic() || includePrivate) {
                for(HabitEvent event : currentHabit.getHabitEventTable()) {
                    if (!event.isDeleted() && event.isCompleted()) {
                        try {
                            Date eventDate = df.parse(event.getDate());
                            if (eventDate.after(sevenDaysAgo)) {
                                recentHabits.add(new HabitEvent(event));
                            }
                        } catch (ParseException e) {
                            System.err.println("Error!");
                        }
                    }
                }
            }
        }
        return;
    }

    /**
     * Gets a list of habit events for a particular habit.
     * @return
     */
    public void getHabitEventsForHabit(ArrayList<HabitEvent> eventList, String habitId) {
        for (Habit currentHabit : this.habitTable) {
            if (currentHabit.getId().equals(habitId)) {
                for(HabitEvent event : currentHabit.getHabitEventTable()) {
                    if (!event.isDeleted()) {
                        eventList.add(event);
                    }
                }
                break;
            }
        }
        return;
    }

    /**
     * Creates habit events for previous days the user missed.
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void backfillHabitEvents() {
        Date today = Calendar.getInstance().getTime();
        for (Habit currentHabit : this.habitTable) {
            Calendar start = Calendar.getInstance();
            start.setTime(currentHabit.getStartDate());

            Calendar end = Calendar.getInstance();
            end.setTime(today);
            List<HabitEvent> eventList = currentHabit.getHabitEventTable();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());

            while( !start.after(end)){
                String targetDay = df.format(start.getTime());
                int weekday = start.get(Calendar.DAY_OF_WEEK);
                if (currentHabit.getIsOnDayOfWeek((weekday + 5) % 7)) {
                    if (!eventList.stream().anyMatch(e -> e.getDate().equals(targetDay))) {
                        HabitEvent newEvent = new HabitEvent(targetDay, currentHabit.getHabitName());
                        currentHabit.addHabitEvent(newEvent);
                    }
                }

                start.add(Calendar.DATE, 1);
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
                    if (event.getId().equals(id) && !event.isDeleted()) {
                        return event;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Removes a habit event.
     * @return
     */
    public void deleteHabitEvent(HabitEvent eventToDelete, String habitId) {
        for (Habit currentHabit : this.habitTable) {
            if (currentHabit.getId().equals(habitId)) {
                for(HabitEvent event : currentHabit.getHabitEventTable()) {
                    if (event.equals(eventToDelete)) {
                        event.setDeleted(true);
                        return;
                    }
                }
                break;
            }
        }
        return;
    }

    /**
     * Forces a Firestore update for the active account
     */
    public void updateFirestore() {
        FirestoreHandler.create().modifyAccount(this);
    }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public List<String> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<String> friendList) {
        this.friendList = friendList;
    }

    public List<String> getFriendPendingList() {
        return friendPendingList;
    }

    public void setFriendPendingList(List<String> friendPendingList) {
        this.friendPendingList = friendPendingList;
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

    public Bitmap getPfp() {
        return pfp;
    }

    public void setPfp(Bitmap pfp) {
        this.pfp = pfp;
    }
}
