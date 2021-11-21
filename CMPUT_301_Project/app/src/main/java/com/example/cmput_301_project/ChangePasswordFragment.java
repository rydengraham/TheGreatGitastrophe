/**
 * Fragment to show user settings
 */
package com.example.cmput_301_project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChangePasswordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChangePasswordFragment extends Fragment implements View.OnClickListener {

    private AccountData accountDataClass = AccountData.create();

    EditText oldPasswordField;
    EditText newPasswordField;
    EditText reenterPasswordField;

    public ChangePasswordFragment() { /* Required empty public constructor */ }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment ChangePasswordFragment.
     */
    public static ChangePasswordFragment newInstance(String param1, String param2) {
        ChangePasswordFragment fragment = new ChangePasswordFragment();
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
        View view = inflater.inflate(R.layout.fragment_change_password, container, false);

        // setup save, exit, and edit photo buttons to use onClickListeners
        // referencing: https://stackoverflow.com/questions/18711433/button-listener-for-button-in-fragment-in-android
        Button saveButton = (Button) view.findViewById(R.id.saveButton);
        Button exitButton = (Button) view.findViewById(R.id.exitButton);
        oldPasswordField = (EditText) view.findViewById(R.id.oldPasswordET);
        newPasswordField = (EditText) view.findViewById(R.id.newPasswordET);
        reenterPasswordField = (EditText) view.findViewById(R.id.reenterPasswordET);
        saveButton.setOnClickListener(this);
        exitButton.setOnClickListener(this);

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
//                HashMap<String, Account> accountData = accountDataClass.getAccountData();
////                String newUsername = usernameField.getText().toString();
//                Account updatedAccount = accountDataClass.getActiveUserAccount();
//                // TODO: add check if pfp was unmodified
//                if (updatedAccount.getUserName().equals(newUsername)) {
//                    getActivity().onBackPressed();
//                    break;
//                }
//                for (Account existingAccount : accountData.values()) {
//                    if (existingAccount.getUserName().equals(newUsername)) {
//                        builder.setTitle("Invalid Username");
//                        builder.setMessage("This username is already taken");
//                        builder.setNegativeButton("OK", null);
//                        AlertDialog alertBox = builder.create();
//                        alertBox.show();
//                        return;
//                    }
//                }
//                builder.setTitle("Update Account Info?");
//                builder.setMessage("Your old username could get taken");
//                // if the user chooses to exit, return to the user profile activity
//                builder.setPositiveButton("Yes", (dialog, which) -> {
//                    updatedAccount.setUserName(newUsername);
//                    accountDataClass.modifyAccount(updatedAccount);
//                    // TODO: also update pfp on parent
//                    TextView usernameTextView = getActivity().findViewById(R.id.usernameText);
//                    Account activeUserAccount = AccountData.create().getActiveUserAccount();
//                    usernameTextView.setText(activeUserAccount.getUserName());
//                    getActivity().onBackPressed();
//                });
//                // if the user chooses to stay on the fragment, simply close the dialog
//                builder.setNegativeButton("Go Back", null);
//                // create the alert dialog and display it over the fragment
//                AlertDialog confirmUpdatedialog = builder.create();
//                confirmUpdatedialog.show();
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
        }
    }

}