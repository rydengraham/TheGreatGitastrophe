package com.example.cmput_301_project;

import android.content.Context;
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

    public FriendsHabitAdapter(Context context, ArrayList<String> habitTitles) {
        this.habitTitles = habitTitles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.friend_habit, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        // TODO: probably make the completion indicator change from red -> green here
    }

    @Override
    public int getItemCount() {
        return habitTitles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        Button completionIndicator;
        TextView habitName;
        RelativeLayout habitLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            completionIndicator = itemView.findViewById(R.id.completedHabitIndicator);
            habitName = itemView.findViewById(R.id.friendHabitTitle);
            habitLayout = itemView.findViewById(R.id.friendHabitLayout);
        }
    }
}
