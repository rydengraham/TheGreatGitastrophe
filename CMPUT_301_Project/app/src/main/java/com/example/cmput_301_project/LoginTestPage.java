package com.example.cmput_301_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.UUID;

public class LoginTestPage extends AppCompatActivity {
    HashMap<String, Account> accountData = new HashMap<String, Account>();

    final String TAG = "Sample";
    Button addButton;
    EditText usernameEditText;
    EditText passwordEditText;
    EditText emailEditText;
    FirebaseFirestore db;

    //From: https://stackoverflow.com/questions/23005948/convert-string-to-bitmap
    public Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte = Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }
        catch(Exception e){
            e.getMessage();
            return null;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_test);

        addButton = findViewById(R.id.add_account_button);
        usernameEditText = findViewById(R.id.username_field);
        passwordEditText = findViewById(R.id.password_field);
        emailEditText = findViewById(R.id.email_field);

        db = FirebaseFirestore.getInstance();
        final CollectionReference collectionReference = db.collection("Users");

        addButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(accountData.size());
                final String username = usernameEditText.getText().toString();
                final String password = passwordEditText.getText().toString();
                final String email = emailEditText.getText().toString();

                // TODO: check for duplicate emails, usernames, and ids
                if (username.length() > 0 && password.length() > 0 && email.length() > 0) {
                    Account newAccount = new Account(username, email, password);
                    System.out.println(newAccount.getId());
                    System.out.println(newAccount == null);
                    accountData.put(newAccount.getId(), newAccount);
                    collectionReference
                            .document(newAccount.getId())
                            .set(newAccount)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    // These are a method which gets executed when the task is succeeded
                                    Log.d(TAG, "Data has been added successfully!");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // These are a method which gets executed if there’s any problem
                                    Log.d(TAG, "Data could not be added!" + e.toString());
                                }
                            });
                    usernameEditText.setText("");
                    passwordEditText.setText("");
                    emailEditText.setText("");

                }
            }
        });

        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable
                    FirebaseFirestoreException error) {
                accountData.clear();
                for(QueryDocumentSnapshot doc: queryDocumentSnapshots)
                {
                    Account account = doc.toObject(Account.class);
                    accountData.put(account.getId(), account);
                }
            }
        });

    }
}