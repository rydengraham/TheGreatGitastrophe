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

import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment extends Fragment  implements View.OnClickListener {

    EditText emailField;
    EditText usernameField;
    EditText passwordField;
    EditText reenterPasswordField;

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

        return view;
    }

    @Override
    public void onClick(View view) {
        // override onClick depending on if the 'sign-up' or 'cancel' button is pressed
        switch(view.getId()) {
            case R.id.signUpButton:
                // TODO: add code to create a new user account here
                HashMap<String, Account> accountData = AccountData.create().getAccountData();

                String username = usernameField.getText().toString();
                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();
                String reenterPassword = reenterPasswordField.getText().toString();

                if (username.length() == 0 || password.length() == 0 || email.length() == 0) {
                    // TODO: open UI fragment to mention issue
                    System.out.println("0 length");
                    return;
                }

                for (Account existingAccount : accountData.values()) {
                    System.out.println(existingAccount.getUserName() + " " + existingAccount.getEmail());
                    if (existingAccount.getUserName().equals(username) || existingAccount.getEmail().equals(email)) {
                        // TODO: open UI fragment to mention issue
                        System.out.println("dupe name/mail");
                        return;
                    }
                }

                if (!password.equals(reenterPassword)) {
                    // TODO: open UI fragment to mention issue
                    System.out.println("no pass match");
                    return;
                }
                // Regex from: https://howtodoinjava.com/java/regex/java-regex-validate-email-address/
                String emailRegex = "^(.+)@(.+)$";
                Pattern pattern = Pattern.compile(emailRegex);

                if (!pattern.matcher(email).matches()) {
                    // TODO: open UI fragment to mention issue
                    System.out.println("invalid email");
                    return;
                }

                AccountData.create().modifyAccount(new Account(username, email, password));

                getActivity().onBackPressed();
                break;
            case R.id.cancelButton:
                // if the cancel button is pressed, close the fragment and return to login page
                getActivity().onBackPressed();
                break;
        }
    }
}