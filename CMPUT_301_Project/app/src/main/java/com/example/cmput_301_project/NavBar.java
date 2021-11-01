package com.example.cmput_301_project;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sagarkoli.chetanbottomnavigation.chetanBottomNavigation;


public class NavBar extends Fragment {

    public NavBar() {
        // Required empty public constructor
    }
    static final int home = 0;
    static final int habit = 1;
    static final int friends = 2;
    static final int settings = 3;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MainPage mainActivity = (MainPage) getActivity();


        View view = inflater.inflate(R.layout.fragment_nav_bar, container, false);
        chetanBottomNavigation  bottomNavigation = view.findViewById(R.id.navibar);
        bottomNavigation.add(new chetanBottomNavigation.Model(home, R.mipmap.ic_home_foreground));
        bottomNavigation.add(new chetanBottomNavigation.Model(habit, R.mipmap.ic_friend_foreground));
        bottomNavigation.add(new chetanBottomNavigation.Model(friends, R.mipmap.ic_habit_foreground));
        bottomNavigation.add(new chetanBottomNavigation.Model(settings, R.mipmap.ic_settings_foreground));
        bottomNavigation.setOnShowListener(new chetanBottomNavigation.ShowListener(){

            @Override
            public void onShowItem(chetanBottomNavigation.Model item) {
                String name;
                switch (item.getId()) {
                    case home:
                        name = "HOME";
                        mainActivity.textView.setText(name);
                        break;
                    case habit:
                        name = "FRIENDS";
                        mainActivity.textView.setText(name);
                        break;
                    case friends:
                        name = "HABITS";
                        mainActivity.textView.setText(name);
                        break;
                    case settings:
                        name = "SETTINGS";
                        mainActivity.textView.setText(name);
                        break;
                    default:
                        name = "";
                }
            }
        });

        bottomNavigation.setOnClickMenuListener(new chetanBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(chetanBottomNavigation.Model item) {
                Toast.makeText(view.getContext(), "Item clicked", Toast.LENGTH_SHORT).show();

            }
        });

        bottomNavigation.show(home,true);



        return view;

    }

}
