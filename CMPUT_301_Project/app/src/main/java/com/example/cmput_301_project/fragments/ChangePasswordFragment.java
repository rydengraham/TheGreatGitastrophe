/**
 * Fragment to show user settings
 */
package com.example.cmput_301_project.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.cmput_301_project.FirestoreHandler;
import com.example.cmput_301_project.R;

import java.security.NoSuchAlgorithmException;

/**
 * Creates a fragment responsible for changing passwords for an account.
 * A simple {@link Fragment} subclass.
 * Use the {@link ChangePasswordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChangePasswordFragment extends Fragment implements View.OnClickListener {
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
        // Depending on the view passed to onClick, execute some action
        switch (view.getId()) {
            case R.id.saveButton:
                String oldPassword = oldPasswordField.getText().toString();
                String newPassword = newPasswordField.getText().toString();
                String reenterPassword = reenterPasswordField.getText().toString();

                // Old password verification
                if (!FirestoreHandler.create().getActiveUserAccount().checkPassword(oldPassword)) {
                    builder.setTitle("Old Password Is Incorrect");
                    builder.setMessage("");
                    builder.setNegativeButton("OK", null);
                    AlertDialog alertBox = builder.create();
                    alertBox.show();
                    return;
                }

                // Empty field verification
                if (oldPasswordField.length() == 0 || newPasswordField.length() == 0 || reenterPasswordField.length() == 0) {
                    builder.setTitle("Missing Required Field");
                    builder.setMessage("");
                    builder.setNegativeButton("OK", null);
                    AlertDialog alertBox = builder.create();
                    alertBox.show();
                    return;
                }

                // Distinct old and new password verification
                if (oldPassword.equals(newPassword)) {
                    builder.setTitle("New Password Cannot Be The Same As Old Password");
                    builder.setMessage("");
                    builder.setNegativeButton("OK", null);
                    AlertDialog alertBox = builder.create();
                    alertBox.show();
                    return;
                }

                if (newPassword.equals(reenterPassword)) {
                    try {
                        // Password update + Firestore
                        FirestoreHandler.create().getActiveUserAccount().updatePassword(newPassword);
                        FirestoreHandler.create().getActiveUserAccount().updateFirestore();

                        builder.setTitle("Password Updated Successfully");
                        builder.setMessage("");
                        builder.setNegativeButton("OK", null);
                        AlertDialog alertBox = builder.create();
                        alertBox.show();

                        getActivity().onBackPressed();
                    } catch (NoSuchAlgorithmException e) {
                        System.err.println("Error: Password failed to update");
                        builder.setTitle("Password Failed To Update");
                        builder.setMessage("");
                        builder.setNegativeButton("OK", null);
                        AlertDialog alertBox = builder.create();
                        alertBox.show();
                        return;
                    }
                } else {
                    // New passwords match verification
                    builder.setTitle("New Passwords Do Not Match");
                    builder.setMessage("");
                    builder.setNegativeButton("OK", null);
                    AlertDialog alertBox = builder.create();
                    alertBox.show();
                    return;
                }
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
                AlertDialog confirmExitDialog = builder.create();
                confirmExitDialog.show();
                break;
        }
    }
}
