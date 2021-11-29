/**
 * Fragment for user sign up
 */
package com.example.cmput_301_project;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
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
import java.util.regex.Pattern;

/**
 * Fragment used to allow user to sign up by creating an acccount
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment extends Fragment  implements View.OnClickListener {

    EditText emailField;
    EditText usernameField;
    EditText passwordField;
    EditText reenterPasswordField;

    HashMap<String, Account> accountData;

    public SignUpFragment() { /* Required empty public constructor */ }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment SignUpFragment.
     */
    public static SignUpFragment newInstance(String param1, String param2) {
        SignUpFragment fragment = new SignUpFragment();
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
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        emailField = (EditText) view.findViewById(R.id.createEmailEV);
        usernameField = (EditText) view.findViewById(R.id.createUsernameEV);
        passwordField = (EditText) view.findViewById(R.id.createPasswordEV);
        reenterPasswordField = (EditText) view.findViewById(R.id.reenterPasswordEV);

        // add buttons for sign-up and exit and set their onClick listeners to current class
        Button signUpButton = (Button) view.findViewById(R.id.signUpButton);
        Button exitButton = (Button) view.findViewById(R.id.cancelButton);
        signUpButton.setOnClickListener(this);
        exitButton.setOnClickListener(this);

        accountData = AccountData.create().getAccountData();

        return view;
    }

    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setCancelable(true);
        // override onClick depending on if the 'sign-up' or 'cancel' button is pressed
        switch(view.getId()) {
            case R.id.signUpButton:
                String username = usernameField.getText().toString();
                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();
                String reenterPassword = reenterPasswordField.getText().toString();

                if (username.length() == 0 || password.length() == 0 || reenterPasswordField.length() == 0 || email.length() == 0) {
                    builder.setTitle("Missing Required Field");
                    builder.setMessage("");
                    builder.setNegativeButton("OK", null);
                    AlertDialog alertBox = builder.create();
                    alertBox.show();
                    return;
                }

                // Regex from: https://howtodoinjava.com/java/regex/java-regex-validate-email-address/
                String emailRegex = "^(.+)@(.+)$";
                Pattern pattern = Pattern.compile(emailRegex);

                if (!pattern.matcher(email).matches()) {
                    builder.setTitle("Invalid Email");
                    builder.setMessage("Emails must contain an '@' sign surrounded by characters");
                    builder.setNegativeButton("OK", null);
                    AlertDialog alertBox = builder.create();
                    alertBox.show();
                    return;
                }

                for (Account existingAccount : accountData.values()) {
                    if (existingAccount.getEmail().equals(email)) {
                        builder.setTitle("Invalid Email");
                        builder.setMessage("This email is already paired to an existing account");
                        builder.setNegativeButton("OK", null);
                        AlertDialog alertBox = builder.create();
                        alertBox.show();
                        return;
                    }
                    if (existingAccount.getUserName().equals(username)) {
                        builder.setTitle("Invalid Username");
                        builder.setMessage("This username is already taken");
                        builder.setNegativeButton("OK", null);
                        AlertDialog alertBox = builder.create();
                        alertBox.show();
                        return;
                    }
                }

                if (!password.equals(reenterPassword)) {
                    builder.setTitle("Passwords Do Not Match");
                    builder.setNegativeButton("OK", null);
                    AlertDialog alertBox = builder.create();
                    alertBox.show();
                    return;
                }
                AccountData.create().modifyAccount(new Account(username, email, password));
                builder.setTitle("Account Creation Successful");
                builder.setNegativeButton("OK", null);
                AlertDialog alertBox = builder.create();
                alertBox.show();

                getActivity().onBackPressed();
                break;
            case R.id.cancelButton:
                // if the cancel button is pressed, warn the user before exiting
                builder.setTitle("Exit Without Saving?");
                builder.setMessage("Any changes you've made will be lost.");
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