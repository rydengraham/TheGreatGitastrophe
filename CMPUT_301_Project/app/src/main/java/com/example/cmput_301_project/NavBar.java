package com.example.cmput_301_project;

import android.content.Intent;
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

    public NavBar() { /* Required empty public constructor */ }

    BottomNavigationView bottomNavigationView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nav_bar, container, false);

        // Initialize bottomNavigation view
        bottomNavigationView = view.findViewById(R.id.bottomNavigationView);
        if (getActivity().getClass() == MainPage.class) {
            bottomNavigationView.setSelectedItemId(R.id.home);
        } else if (getActivity().getClass() == AccountSettings.class) {
            bottomNavigationView.setSelectedItemId(R.id.settings);
        } else {
            bottomNavigationView.getMenu().findItem(R.id.uncheckedItem).setChecked(true);
        }
        bottomNavigationView.setOnItemSelectedListener(item -> {
            String name;

            // Switch clause that sets alternates text depending on menu item selected
            switch (item.getItemId()) {
                case R.id.home:
                    if (getActivity().getClass() != MainPage.class) {
                        Intent switchToMainPage = new Intent(getActivity(), MainPage.class);
                        startActivity(switchToMainPage);
                    }
                    break;

                case R.id.habits:
//                    Intent switchToHabitPage = new Intent(getActivity(), MainPage.class);
//                    startActivity(switchToMainPage);
                    break;

                case R.id.friends:
                    break;

                case R.id.settings:
                    if (getActivity().getClass() != AccountSettings.class) {
                        Intent switchToSettingsPage = new Intent(getActivity(), AccountSettings.class);
                        startActivity(switchToSettingsPage);
                    }
                    break;

                default:
                    name = "";
            }
            return true;
        });
        return view;
    }
}


