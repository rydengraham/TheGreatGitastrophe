/**
 * Nav bar fragment
 */
package com.example.cmput_301_project.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.cmput_301_project.FirestoreHandler;
import com.example.cmput_301_project.R;
import com.example.cmput_301_project.pages.AccountSettingsPage;
import com.example.cmput_301_project.pages.MainPage;
import com.example.cmput_301_project.pages.MyFriendsPage;
import com.example.cmput_301_project.pages.MyHabitsPage;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * Navbar object that is used as a footer to allow navigation to various activities
 * A simple {@link Fragment} subclass.
 * create an instance of the navbar fragment.
 */
public class NavBarFragment extends Fragment {
    public NavBarFragment() { /* Required empty public constructor */ }

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
        } else if (getActivity().getClass() == AccountSettingsPage.class) {
            bottomNavigationView.setSelectedItemId(R.id.settings);
        } else if (getActivity().getClass() == MyHabitsPage.class) {
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
                    if (getActivity().getClass() != MyHabitsPage.class) {
                        Intent switchToHabitsPage = new Intent(getActivity(), MyHabitsPage.class);
                        switchToHabitsPage.putExtra("userId", FirestoreHandler.create().getActiveUserId());
                        startActivity(switchToHabitsPage);
                    }
                    break;

                case R.id.friends:
                    // Switch to Friends Page
                    Intent switchToAddFriendsPage = new Intent(view.getContext(), MyFriendsPage.class);
                    startActivity(switchToAddFriendsPage);
                    break;

                case R.id.settings:
                    if (getActivity().getClass() != AccountSettingsPage.class) {
                        Intent switchToSettingsPage = new Intent(getActivity(), AccountSettingsPage.class);
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
