package com.example.cmput_301_project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FriendDetailedHabitsAdapter extends RecyclerView.Adapter<FriendDetailedHabitsAdapter.ViewHolder> {

    private final ArrayList<Habit> habits;
    private Context context;

    public FriendDetailedHabitsAdapter(Context context, ArrayList<Habit> habits) {
        this.context = context;
        this.habits = habits;
    }

    @NonNull
    @Override
    public FriendDetailedHabitsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.friend_detailed_habit, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

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

    // from: https://stackoverflow.com/questions/8151435/integer-to-binary-array
    private static boolean[] toBinary(int number, int base) {
        final boolean[] booleanArr = new boolean[base];
        for (int i = 0; i < base; i++) {
            booleanArr[base - 1 - i] = (1 << i & number) != 0;
        }
        return booleanArr;
    }

    private String getDays(int weekdays) {
        boolean[] isOnDays = toBinary(weekdays, 7);
        String days = "MTWTFSS";
        for(int i = 0; i < isOnDays.length; i++) {
            // TODO: doesn't work for number == 1
            if(!isOnDays[i]) {
                days = days.substring(i-1, i+1);
            }
        }
        return days;
    }
}
