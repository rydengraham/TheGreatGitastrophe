package com.example.cmput_301_project;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adapter for holding Habits of profiles
 */

public class ProfileHabitAdapter extends RecyclerView.Adapter<ProfileHabitAdapter.ItemVH>{
    private static final String TAG="Adapter";
    List<HabitEvent> habitEventList;
    Activity context;

    /**
     * Constructor, Activity and delMode are essential for handling edits and deletions
     * @param fm
     */
    public ProfileHabitAdapter(List<HabitEvent> habitEventList, Activity fm) {
        this.habitEventList = habitEventList;
        this.context = fm;
    }

    /**
     * Returns a view holder
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ProfileHabitAdapter.ItemVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.habit_list_textview, parent, false);

        return new ProfileHabitAdapter.ItemVH(view);
    }

    /**
     * Used to translate habit stats to the list item holder
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ProfileHabitAdapter.ItemVH holder, int position) {
        HabitEvent habitEvent = habitEventList.get(position);
        holder.habitNameView.setText(habitEvent.getTitle());
        holder.commentView.setText(habitEvent.getComment());
        holder.DateView.setText(habitEvent.getDate());
        boolean isExpanded = habitEventList.get(position).isExpanded();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE:View.GONE);
    }

    /**
     * Gets count of items
     * @return Size of habit list
     */
    @Override
    public int getItemCount() {
        return habitEventList.size();
    }


    class ItemVH extends RecyclerView.ViewHolder {
        private static final String TAG = "Item";
        TextView habitNameView, DateView, commentView;
        LinearLayout expandableLayout;
        Button iconButton;
        Button locationButton;

        /**
         *
         * Constructor that finds the textviews and buttons needed to assign habit attributes to
         * @param itemView
         */
        public ItemVH(View itemView) {
            super(itemView);
            habitNameView = itemView.findViewById(R.id.habitNameTextView);
            DateView = itemView.findViewById(R.id.habitEventDateTextView);
            commentView = itemView.findViewById(R.id.commentTextView);
            expandableLayout = itemView.findViewById(R.id.expandableHELayout);
            // Give itemView a listener for expansion and deletion
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HabitEvent currentEvent = habitEventList.get(getAdapterPosition());
                    currentEvent.setExpanded(!currentEvent.isExpanded());
                    commentView.setText(currentEvent.getComment());
                    notifyDataSetChanged();

                }
            });
        }
    }

}
