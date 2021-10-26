package com.example.cmput_301_project;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;

public class TodayHabits extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.today_habits);

        cityList = findViewById(R.id.toDoList);

        String[] names = {"Tim", "Kevin", "Bob", "John","Evan", "Roy", "Tristan", "Ronald"};
        String[] company = {"Apple", "Microsoft", "China", "Xiaomi","Facebook", "Unemployed", "CCP", "Government"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(names));
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);
    }

}
