/**
 * Adapter for recycler view used in habits
 */
package com.example.cmput_301_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * RecyclerViewAdpater helps create the custom list, also handles edits and deletions
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemVH> {
    private static final String TAG="Adapter";
    Account userAccount;
    List<Habit> habitList;
    Activity context;
    boolean delMode;
    String userId;

    /**
     * Constructor, Activity and delMode are essential for handling edits and deletions
     * @param fm
     * @param delMode
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public RecyclerViewAdapter(Activity fm, boolean delMode) {
        Bundle extras = fm.getIntent().getExtras();
        AccountData accountData = AccountData.create();
        userId = extras.getString("userId");
        for (Account ua: accountData.getAccountData().values()) {
            if (ua.getId().equals(userId)) {
                userAccount = ua;
            }
        }
        if (!userAccount.getId().equals(AccountData.create().getActiveUserId())) {
            this.habitList = userAccount.getHabitTable().stream().filter(h -> h.getPublic()).collect(Collectors.toList());
        } else {
            this.habitList = userAccount.getHabitTable();
        }
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
    public RecyclerViewAdapter.ItemVH onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.habits_custom_list, parent, false);

        return new ItemVH(view);
    }

    /**
     * Used to translate habit stats to the list item holder
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ItemVH holder, int position) {
        Habit habit = habitList.get(position);
        holder.habitTitleView.setText(habit.getHabitName());
        holder.startDateView.setText(habit.getStartDate().toString());
        holder.startDateView.setText(DateFormat.getDateInstance().format(habit.getStartDate()));
        holder.reasonView.setText(habit.getReason());
        boolean isExpanded=habitList.get(position).isExpanded();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE:View.GONE);
        if (!userId.equals(AccountData.create().getActiveUserId())) {
            holder.editButton.setVisibility(View.GONE);
            holder.historyButton.setVisibility(View.GONE);
        }

        holder.progressRate = userAccount.getHabitCompletionRateInLastThirtyDays(habit.getId());
        if (holder.progressRate[0] != 0 && holder.progressRate[1] != 0) {
            holder.progress = (int) (100 * holder.progressRate[1] / holder.progressRate[0]);
        } else {
            holder.progress = 0;
        }
        // Progress bar library bug fix (not our fault)
        if (holder.progress < 100) {
            holder.updateProgress(holder.progress + 1);
        } else {
            holder.updateProgress(holder.progress - 1);
        }
        holder.updateProgress(holder.progress);
    }

    /**
     * Gets count of items
     * @return Size of habit list
     */
    @Override
    public int getItemCount() {
        return habitList.size();
    }

    class ItemVH extends RecyclerView.ViewHolder {
        private static final String TAG = "Item";
        TextView habitTitleView, reasonView, startDateView, frequencyView, progressView;
        LinearLayout expandableLayout;
        Button editButton;
        Button historyButton;
        ProgressBar progressPercentage;
        private int progress = 0;
        int[] progressRate = {0, 0};

        /**
         * Constructor that finds the textviews and buttons needed to assign habit attributes to
         * @param itemView
         */
        public ItemVH(View itemView) {
            super(itemView);
            habitTitleView = itemView.findViewById(R.id.habitTitle);
            reasonView = itemView.findViewById(R.id.reason);
            startDateView = itemView.findViewById(R.id.startdate);
            frequencyView = itemView.findViewById(R.id.frequency);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);
            editButton = itemView.findViewById(R.id.editButton);
            historyButton = itemView.findViewById(R.id.historyButton);
            progressView = itemView.findViewById(R.id.prg_value6);
            progressPercentage = itemView.findViewById(R.id.progress_bar5);

            // Give itemView a listener for expansion and deletion
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Habit habit = habitList.get(getAdapterPosition());
                    if (isDelMode())
                    {
                        habitList.remove(getAdapterPosition());
                        userAccount.deleteHabit(habit);
                        userAccount.updateFirestore();
                    }
                    else {
                        habit.setExpanded(!habit.isExpanded());
                    }
                    notifyDataSetChanged();
                }
            });

            // Give editButton a listener for editing habits.
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Habit habit = habitList.get(getAdapterPosition());
                    FragmentActivity activity = (FragmentActivity)(context);
                    FragmentManager fm = activity.getSupportFragmentManager();
                    HabitFragments alertDialog = new HabitFragments().newInstance(habit);
                    alertDialog.show(fm, "fragment_alert");
                }
            });

            // Give historyButton a placeholder interaction
            historyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentActivity activity = (FragmentActivity)(context);
                    Intent switchActivityIntent = new Intent(activity, HabitEventHistory.class);
                    Habit habit = habitList.get(getAdapterPosition());
                    switchActivityIntent.putExtra("habitId", habit.getId());
                    context.startActivity(switchActivityIntent);
                }
            });
        }

        private void updateProgress(int progress) {
            progressPercentage.setProgress(progress);
            progressView.setText(progress + "%");
        }
    }
}
