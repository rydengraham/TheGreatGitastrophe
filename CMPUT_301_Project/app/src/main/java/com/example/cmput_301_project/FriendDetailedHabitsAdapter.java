package com.example.cmput_301_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Class serves as an adapter between an ArrayList of Habits and the friend's habit's RecyclerView.
 */
public class FriendDetailedHabitsAdapter extends RecyclerView.Adapter<FriendDetailedHabitsAdapter.ViewHolder> {

    private final ArrayList<Habit> habits;
    private Context context;

    /**
     * Constructor for the adapter.
     * @param context the context of the adapter (class it is operating within)
     * @param habits an ArrayList of all of a friend's public Habits
     */
    public FriendDetailedHabitsAdapter(Context context, ArrayList<Habit> habits) {
        this.context = context;
        this.habits = habits;
    }

    /**
     * Method called on creation of the view holder, builds the view.
     * @param viewGroup the context group the view to be created is within
     * @param i the size of the ArrayList of Habits that needs to be adapted to the RecyclerLayout
     * @return ViewHolder using the inflated view
     */
    @NonNull
    @Override
    public FriendDetailedHabitsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.friend_detailed_habit, viewGroup, false);
        return new ViewHolder(view);
    }

    /**
     * Method called when the ViewHolder is bound and implemented by the RecyclerLayout.
     * @param viewHolder the ViewHolder previously created
     * @param i the size of the ArrayList of Habits that needs to be adapted to the RecyclerLayout
     */
    @Override
    public void onBindViewHolder(@NonNull FriendDetailedHabitsAdapter.ViewHolder viewHolder, int i) {

        Habit habit = habits.get(i);

        viewHolder.friendHabitTitle.setText(habit.getHabitName());
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        viewHolder.startDate.setText(df.format(habit.getStartDate()));
        viewHolder.expandedTV.setText(habit.getReason());

        String days = getDays(habit.getWeekdays());
        viewHolder.frequency.setText(days);

        boolean isExpanded = habit.isExpanded();
        viewHolder.expandedLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
    }

    /**
     * Get the total number of items in the Habit ArrayList.
     * @return the size of the Habit ArrayList
     */
    @Override
    public int getItemCount() {
        return habits.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView friendHabitTitle;
        TextView startDate;
        TextView frequency;
        RelativeLayout habitLayout;
        TextView expandedTV;

        ConstraintLayout expandedLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            friendHabitTitle = itemView.findViewById(R.id.friendDetailedHabitTitle);

            habitLayout = itemView.findViewById(R.id.friendsHabitLayout);
            expandedTV = itemView.findViewById(R.id.expandedTV);
            startDate = itemView.findViewById(R.id.dateTV);
            frequency = itemView.findViewById(R.id.frequencyTV);
            expandedLayout = itemView.findViewById(R.id.expandedLayout);
            friendHabitTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Habit habit = habits.get(getAdapterPosition());
                    habit.setExpanded(!habit.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }

    /**
     * Converts an integer to a base 7 array of booleans.
     * @param number weekdays param from {@link Habit} class
     * @param base the number of digits in the binary number (7 since 7 days of the week)
     * @return boolean array where each entry is true if the Habit takes place on that day
     */
    private static boolean[] toBinary(int number, int base) {
        // from: https://stackoverflow.com/questions/8151435/integer-to-binary-array
        final boolean[] booleanArr = new boolean[base];
        for (int i = 0; i < base; i++) {
            booleanArr[base - 1 - i] = (1 << i & number) != 0;
        }
        return booleanArr;
    }

    /**
     * Get a String representing the weekly frequency of the Habit.
     * @param weekdays the integer param from {@link Habit} representing weekly frequency
     * @return String representing the frequency of the Habit to be displayed in the layout
     */
    private String getDays(int weekdays) {
        // create a boolean array correspoonding to whether the Habit occurs that day or not
        boolean[] isOnDays = toBinary(weekdays, 7);
        StringBuilder days = new StringBuilder();
        String[] allDays = {"Sn", "S", "F", "Th", "W", "T", "M"};
        // iterate through each day and check if the habit occurs according to the boolean array
        for(int i = isOnDays.length-1; i >= 0; i--) {
            if(isOnDays[i]) {
                days.append(" ").append(allDays[i]);
            }
        }

        return days.toString();
    }
}
