package com.example.cmput_301_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class TodayHabitsAdapter extends BaseAdapter {

    int resource;
    Context context;
    ArrayList<HabitEvent> habitList;

    public TodayHabitsAdapter(Context context, int resource, ArrayList<HabitEvent> myList){
        this.resource = resource;
        this.context = context;
        habitList = myList;
    }

    @Override
    public int getCount() {
        return habitList.size();
    }

    @Override
    public HabitEvent getItem(int i) {
        return habitList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=null;
        TodayHabitViewHolder holder = null;
        View v = view;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(context);
            v = vi.inflate(resource, null);
        }
        LinearLayout commentHolder = v.findViewById(R.id.commentHolder);
        LinearLayout iconLocationHolder = v.findViewById(R.id.iconLocationHolder);
        holder = new TodayHabitViewHolder(commentHolder, iconLocationHolder);
        HabitEvent selectedHabit = getItem(i);
        selectedHabit.setHolder(holder);
        if (selectedHabit != null) {
            TextView habitName = (TextView) v.findViewById(R.id.habitName);
            EditText comment = (EditText) v.findViewById(R.id.comment);
            if (habitName != null) {
                habitName.setText(selectedHabit.getTitle());
            }

            if (comment != null) {
                comment.setText(selectedHabit.getComment());
            }
        }
        return v;
    }

    @Override
    public boolean areAllItemsEnabled()
    {
        return true;
    }

    @Override
    public boolean isEnabled(int arg0)
    {
        return true;
    }
}
