/**
 * Adapter for recycler view used in habits
 */
package com.example.cmput_301_project;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.List;

/**
 * RecyclerViewAdpater helps create the custom list, also handles edits and deletions
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemVH> {
    private static final String TAG="Adapter";
    Account userAccount = AccountData.create().getActiveUserAccount();
    List<Habit> habitList;
    Activity context;
    boolean delMode;

    /**
     * Constructor, Activity and delMode are essential for handling edits and deletions
     * @param fm
     * @param delMode
     */
    public RecyclerViewAdapter(Activity fm, boolean delMode) {
        this.habitList = userAccount.getHabitTable();
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
        TextView habitTitleView, reasonView, startDateView, frequencyView;
        LinearLayout expandableLayout;
        Button editButton;
        Button historyButton;

        /**
         *
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
            // Give itemView a listener for expansion and deletion
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Habit habit = habitList.get(getAdapterPosition());
                    if (isDelMode())
                    {
//                        habitList.remove(getAdapterPosition());
                        userAccount.deleteHabit(habit);
                        userAccount.updateFirestore();
                    }
                    else {
                        habit.setExpanded(!habit.isExpanded());
                    }
                    notifyItemChanged(getAdapterPosition());

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

            // TODO: Connect this to the habit event list (does not exist for part 3)
            // Give historyButton a placeholder interaction
            historyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Standard TBD Alert Dialogue
                    FragmentActivity activity = (FragmentActivity)(context);
                    Intent switchActivityIntent = new Intent(activity, HabitEventHistory.class);
                    context.startActivity(switchActivityIntent);
                    /*
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setCancelable(true);
                    builder.setTitle("Page Does Not Exist");
                    builder.setMessage("This will be added in project part 4.");
                    builder.setNegativeButton("OK", null);
                    // create the alert dialog and display it over the fragment
                    AlertDialog alertBox = builder.create();
                    alertBox.show();
                    */
                }
            });
            
        }
    }


}
