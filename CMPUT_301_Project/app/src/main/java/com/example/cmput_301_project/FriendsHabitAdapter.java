package com.example.cmput_301_project;

import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FriendsHabitAdapter extends RecyclerView.Adapter<FriendsHabitAdapter.ViewHolder>{

    private ArrayList<String> habitTitles = new ArrayList<>();
    private Context context;

    /**
     * Contructor for the adapter class
     * @param context context of the adapter
     * @param habitTitles ArrayList of all habit titles for each friend
     */
    public FriendsHabitAdapter(Context context, ArrayList<String> habitTitles) {
        this.habitTitles = habitTitles;
    }

    /**
     * Called when adapter is created, creates viewHolder that contains friend_habit layout
     * @param viewGroup collection of current views (implemented automatically
     * @param i current index of element the adapter is representing in the ArrayList
     * @return viewHolder of the friend_habit view that corresponds to current index in the ArrayList
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // set up the ViewHolder containing the friend_habit layout for the adapter to use
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.friend_habit, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    /**
     * Called when viewHolder is bound to data at index i in ArrayList
     * @param viewHolder viewHolder containing friend_habit layout as defined in {@link #onCreateViewHolder(ViewGroup, int)}
     * @param i current index of element the adapter is representing in the ArrayList
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        // change the name of the habit to the value from the ArrayList that corresponds to the current index
        viewHolder.habitName.setText(habitTitles.get(i));
    }

    /**
     * @return total size of ArrayList being adapted
     */
    @Override
    public int getItemCount() {
        return habitTitles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView habitName;
        RelativeLayout habitLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            habitName = itemView.findViewById(R.id.friendHabitTitle);
            habitLayout = itemView.findViewById(R.id.friendHabitLayout);
        }
    }
}
