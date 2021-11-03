package com.example.cmput_301_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemVH> {
    private static final String TAG="Adapter";
    List<Habit> habitList;

    public RecyclerViewAdapter(List<Habit> habitList) {
        this.habitList = habitList;
    }

    @Override
    public RecyclerViewAdapter.ItemVH onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.habits_custom_list, parent, false);

        return new ItemVH(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ItemVH holder, int position) {
        Habit habit = habitList.get(position);
        holder.habitTitleView.setText(habit.getHabitName());
        holder.startDateView.setText(habit.getStartDate().toString());
        holder.reasonView.setText(habit.getReason());
        boolean isExpanded=habitList.get(position).isExpanded();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE:View.GONE);


    }

    @Override
    public int getItemCount() {
        return habitList.size();
    }

    class ItemVH extends RecyclerView.ViewHolder {
        private static final String TAG = "Item";
        TextView habitTitleView, reasonView, startDateView, frequencyView;
        LinearLayout expandableLayout;
        Button editButton;
        public ItemVH(View itemView) {
            super(itemView);
            habitTitleView = itemView.findViewById(R.id.habitTitle);
            reasonView = itemView.findViewById(R.id.reason);
            startDateView = itemView.findViewById(R.id.startdate);
            frequencyView = itemView.findViewById(R.id.frequency);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);
            editButton = itemView.findViewById(R.id.editButton);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Habit habit = habitList.get(getAdapterPosition());
                    habit.setExpanded(!habit.isExpanded());
                    notifyItemChanged(getAdapterPosition());

                }
            });
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Habit habit = habitList.get(getAdapterPosition());
                    HabitFragments Edit_frag = new HabitFragments().newInstance(habit);
                    //Edit_frag.show(this,"EDIT_CITY");
                }
            });
            
        }
    }


}
