package com.example.cmput_301_project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;


/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class NavBar extends Fragment {



    public NavBar() {
        // Required empty public constructor
    }

    BottomNavigationView bottomNavigationView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        MainPage mainActivity = (MainPage) getActivity();
        View view = inflater.inflate(R.layout.fragment_nav_bar, container, false);
        //Initialize bottomNavigation view
        bottomNavigationView = view.findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            String name;
            //Switch clause that sets alternates text depending on menu item selected

            switch (item.getItemId()) {
                case R.id.home:
                    name = "HOME";
                    mainActivity.textView.setText(name);
                    break;

                case R.id.habits:
                    name = "HABITS";
                    mainActivity.textView.setText(name);
                    break;

                case R.id.friends:
                    name = "FRIENDS";
                    mainActivity.textView.setText(name);
                    break;

                case R.id.settings:
                    name = "SETTINGS";
                    mainActivity.textView.setText(name);
                    break;

                default:
                    name = "";
            }
            return true;

        });


        return view;


    }

}


