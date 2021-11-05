package com.example.cmput_301_project;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserSettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserSettingsFragment extends Fragment implements View.OnClickListener {

    AccountData accountDataClass = AccountData.create();
    EditText usernameField;
    public UserSettingsFragment() { /* Required empty public constructor */ }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment UserSettingsFragment.
     */
    public static UserSettingsFragment newInstance(String param1, String param2) {
        UserSettingsFragment fragment = new UserSettingsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        usernameField = (EditText) view.findViewById(R.id.editUsernamePT);
        usernameField.setText(accountDataClass.getActiveUserAccount().getUserName());
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
                    return true;
                }
                return false;
            }
        });

        return view;
    }

    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setCancelable(true);
        // depending on the view passed to onClick, execute some action
        switch (view.getId()) {
            case R.id.saveButton:
                // if the save button is pressed, save changes and exit the fragment
                // TODO: add code to update user profile data here
                HashMap<String, Account> accountData = accountDataClass.getAccountData();
                String newUsername = usernameField.getText().toString();
                Account updatedAccount = accountDataClass.getActiveUserAccount();
                // TODO: add check if pfp was unmodified
                if (updatedAccount.getUserName().equals(newUsername)) {
                    getActivity().onBackPressed();
                    break;
                }
                for (Account existingAccount : accountData.values()) {
                    if (existingAccount.getUserName().equals(newUsername)) {
                        builder.setTitle("Invalid Username");
                        builder.setMessage("This username is already taken");
                        builder.setNegativeButton("OK", null);
                        AlertDialog alertBox = builder.create();
                        alertBox.show();
                        return;
                    }
                }
                builder.setTitle("Update Account Info?");
                builder.setMessage("Your old username could get taken");
                // if the user chooses to exit, return to the user profile activity
                builder.setPositiveButton("Yes", (dialog, which) -> {
                    updatedAccount.setUserName(newUsername);
                    accountDataClass.modifyAccount(updatedAccount);
                    getActivity().onBackPressed();
                });
                // if the user chooses to stay on the fragment, simply close the dialog
                builder.setNegativeButton("Go Back", null);
                // create the alert dialog and display it over the fragment
                AlertDialog confirmUpdatedialog = builder.create();
                confirmUpdatedialog.show();
                break;
            case R.id.exitButton:
                // if the exit button is pressed, simply exit the fragment
                builder.setTitle("Exit Without Saving?");
                builder.setMessage("Any changes you've made will be lost");
                // if the user chooses to exit, return to the user profile activity
                builder.setPositiveButton("Exit", (dialog, which) -> {
                    getActivity().onBackPressed();
                });
                // if the user chooses to stay on the fragment, simply close the dialog
                builder.setNegativeButton("Go Back", null);
                // create the alert dialog and display it over the fragment
                AlertDialog confirmExitdialog = builder.create();
                confirmExitdialog.show();
                break;
            case R.id.editProfileTV:
                // if the 'edit profile' textView is pressed, allow user to change their profile pic
                System.out.println("edit photo");
                // TODO: add code to change profile photo here
                break;
        }
    }

}