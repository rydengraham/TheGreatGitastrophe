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

import java.util.ArrayList;

public class FriendDetailedHabitsAdapter extends RecyclerView.Adapter<FriendDetailedHabitsAdapter.ViewHolder> {

    private final ArrayList<HabitEvent> habits;
    private Context context;

    public FriendDetailedHabitsAdapter(Context context, ArrayList<HabitEvent> habits) {
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

        HabitEvent habit = habits.get(i);
        viewHolder.friendHabitTitle.setText(habit.getTitle());
        viewHolder.expandedTV.setText(habit.getComment());

        boolean isExpanded = habit.isExpanded();
        viewHolder.expandedLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return habits.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView friendHabitTitle;
        RelativeLayout habitLayout;
        TextView expandedTV;

        ConstraintLayout expandedLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            friendHabitTitle = itemView.findViewById(R.id.friendDetailedHabitTitle);
            habitLayout = itemView.findViewById(R.id.friendsHabitLayout);
            expandedTV = itemView.findViewById(R.id.expandedTV);

            expandedLayout = itemView.findViewById(R.id.expandedLayout);
            friendHabitTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HabitEvent habit = habits.get(getAdapterPosition());
                    habit.setExpanded(!habit.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
