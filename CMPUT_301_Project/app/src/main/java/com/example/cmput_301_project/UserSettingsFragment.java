package com.example.cmput_301_project;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserSettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserSettingsFragment extends Fragment implements View.OnClickListener {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public UserSettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserSettingsFragment.
     */
    public static UserSettingsFragment newInstance(String param1, String param2) {
        UserSettingsFragment fragment = new UserSettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_settings, container, false);

        // setup save, exit, and edit photo buttons to use onClickListeners
        // referencing: https://stackoverflow.com/questions/18711433/button-listener-for-button-in-fragment-in-android
        Button saveButton = (Button) view.findViewById(R.id.saveButton);
        Button exitButton = (Button) view.findViewById(R.id.exitButton);
        TextView editProfileTV = (TextView) view.findViewById(R.id.editProfileTV);
        saveButton.setOnClickListener(this);
        exitButton.setOnClickListener(this);
        editProfileTV.setOnClickListener(this);

        // add functionality to the editName EditText to allow user to update their username
        EditText editName = (EditText) view.findViewById(R.id.editUsernamePT);
        editName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                // if the event is a key-down on the 'Enter' button, if statement is true
                if((keyEvent.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    String newName = editName.getText().toString();
                    // TODO: add code to change username using 'newName' as updated username
                    /*
                    TODO: probably best-practice to lower keyboard after 'Enter' is pressed,
                     but android has some weird rules regarding this (relevant SO:
                     https://stackoverflow.com/questions/1109022/how-do-you-close-hide-the-android-soft-keyboard-programmatically)
                    */
                    return true;
                }
                return false;
            }
        });

        return view;
    }

    @Override
    public void onClick(View view) {
        // depending on the view passed to onClick, execute some action
        switch (view.getId()) {
            // TODO: this switch statement may have issues w Gradle8 (check warning for more info)
            case R.id.saveButton:
                // if the save button is pressed, save changes and exit the fragment
                // TODO: add code to update user profile data here
                getActivity().onBackPressed();
                break;
            case R.id.exitButton:
                // if the exit button is pressed, simply exit the fragment
                // TODO: might be good to have a dialogue fragment prompt user to discard changes
                getActivity().onBackPressed();
                break;
            case R.id.editProfileTV:
                // if the 'edit profile' textView is pressed, allow user to change their profile pic
                // TODO: add code to change profile photo here
                break;
        }
    }

}