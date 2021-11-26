package com.example.cmput_301_project;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EventHabitAdapter extends RecyclerView.Adapter<EventHabitAdapter.ItemVH>{
    private static final String TAG="Adapter";
    //Account userAccount = AccountData.create().getActiveUserAccount();
    List<HabitEvent> habitEventList;
    Activity context;
    boolean delMode;

    /**
     * Constructor, Activity and delMode are essential for handling edits and deletions
     * @param fm
     * @param delMode
     */
    public EventHabitAdapter(List<HabitEvent> habitEventList, Activity fm, boolean delMode) {
        this.habitEventList = habitEventList;
        this.context = fm;
        this.delMode = delMode;
    }

    /**
     * Returns whether or not deletion mode is used
     * @return True if delete mode
     */
    public boolean isDelMode() {
        return delMode;
    }

    /**
     * Sets deletion mode
     * @return
     */
    public void setDelMode(boolean delMode) {
        this.delMode = delMode;
    }

    /**
     * Returns a view holder
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public EventHabitAdapter.ItemVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.habit_event_custom_list, parent, false);

        return new EventHabitAdapter.ItemVH(view);
    }

    /**
     * Used to translate habit stats to the list item holder
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(EventHabitAdapter.ItemVH holder, int position) {
        HabitEvent habitEvent = habitEventList.get(position);
        holder.habitNameView.setText(habitEvent.getTitle());
        holder.commentView.setText(habitEvent.getComment());
        holder.DateView.setText(habitEvent.getDate());
        boolean isExpanded=habitEventList.get(position).isExpanded();
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
        TextView habitNameView, DateView;
        EditText commentView;
        LinearLayout expandableLayout;
        Button iconButton;
        Button locationButton;
        Account userAccount;

        /**
         *
         * Constructor that finds the textviews and buttons needed to assign habit attributes to
         * @param itemView
         */
        public ItemVH(View itemView) {
            super(itemView);
            habitNameView = itemView.findViewById(R.id.habitNameTextView);
            DateView = itemView.findViewById(R.id.habitEventDateTextView);
            commentView = itemView.findViewById(R.id.editTextTextPassword);
            iconButton = itemView.findViewById(R.id.photoIconButton);
            locationButton = itemView.findViewById(R.id.editLocationButton);
            expandableLayout = itemView.findViewById(R.id.expandableHELayout);
            userAccount = AccountData.create().getActiveUserAccount();
            // Give itemView a listener for expansion and deletion
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HabitEvent currentEvent = habitEventList.get(getAdapterPosition());
                    if (isDelMode())
                    {

                        habitEventList.remove(habitEventList.get(getAdapterPosition()));
                        Bundle extras = context.getIntent().getExtras();
                        ArrayList<HabitEvent> events = new ArrayList<HabitEvent>();
                        userAccount.deleteHabitEvent(currentEvent, extras.getString("habitId"));
                        userAccount.updateFirestore();
                    }
                    else {
                        currentEvent.setExpanded(!currentEvent.isExpanded());
                        commentView.setText(currentEvent.getComment());
                    }
                    notifyDataSetChanged();

                }
            });
            commentView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    HabitEvent habit = habitEventList.get(getAdapterPosition());
                    habit.setComment(charSequence.toString());
                    userAccount.updateFirestore();
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            // TODO: Connect this to the habit event list (does not exist for part 3)
            // Give historyButton a placeholder interaction

        }
    }

}
