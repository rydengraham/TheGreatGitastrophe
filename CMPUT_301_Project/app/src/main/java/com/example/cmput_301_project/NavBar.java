/**
 * Nav bar fragment
 */
package com.example.cmput_301_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;


/**
 * Navbar object that is used as a footer to allow navigation to various activities
 * A simple {@link Fragment} subclass.
 * create an instance of the navbar fragment.
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
        } else if (getActivity().getClass() == MyHabits.class) {
            bottomNavigationView.setSelectedItemId(R.id.habits);
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
                    if (getActivity().getClass() != MyHabits.class) {
                        Intent switchToHabitsPage = new Intent(getActivity(), MyHabits.class);
                        switchToHabitsPage.putExtra("userId", AccountData.create().getActiveUserId());
                        startActivity(switchToHabitsPage);
                    }
                    break;

                case R.id.friends:
                    // Switch to Friends Page
                    Intent switchToAddFriendsPage = new Intent(view.getContext(), MyFriends.class);
                    startActivity(switchToAddFriendsPage);
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


