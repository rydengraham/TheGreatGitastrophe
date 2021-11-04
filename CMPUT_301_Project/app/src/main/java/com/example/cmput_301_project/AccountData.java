package com.example.cmput_301_project;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;

// Singleton Global Variable Access From:
// https://stackoverflow.com/questions/1944656/android-global-variable
public class AccountData {
    private static final String CLASSTAG = "AccountData";
    private static AccountData singletonAccountData;
    HashMap<String, Account> accountData = new HashMap<String, Account>();


    private final String TAG = "Sample";
    private FirebaseFirestore db;
    private final CollectionReference collectionReference;

    private AccountData() {
        accountData = new HashMap<String, Account>();

        db = FirebaseFirestore.getInstance();
        collectionReference = db.collection("Users");
    }

    public static AccountData create() {
        if (singletonAccountData == null) {
            singletonAccountData = new AccountData();
        }
        return singletonAccountData;
    }

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

    public HashMap<String, Account> getAccountData() {
        if (accountData.isEmpty()) {
            // fetch from firestore

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
        return accountData;
    }

    public void modifyAccount(Account newAccount) {
//        this.accountData.put(modifiedAccount.getId(), modifiedAccount);
        // TODO: check for duplicate emails, usernames, and ids
//        Account newAccount = new Account(username, email, password);
//        System.out.println(newAccount.getId());
//        System.out.println(newAccount == null);
        this.accountData.put(newAccount.getId(), newAccount);
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
    }

    //    public HashMap<String, Account> getAccountData() {
//
//    }
//
//    public void setAccountData(HashMap<String, Account> accountData) {
//        this.accountData = accountData;
//    }
}
