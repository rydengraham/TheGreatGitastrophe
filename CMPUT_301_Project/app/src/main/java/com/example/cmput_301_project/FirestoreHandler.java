/**
 * Singleton class used for firestore I/O
 */
package com.example.cmput_301_project;

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

/**
 * Account Data is responsible for storing Habits, friends, and habit events for each account
 */

// Singleton Global Variable Access From:
// https://stackoverflow.com/questions/1944656/android-global-variable
public class FirestoreHandler {
    private static final String CLASSTAG = "AccountData";
    private static FirestoreHandler singletonFirestoreHandler;
    private HashMap<String, Account> accountData = new HashMap<String, Account>();
    private String activeUserId = "";

    private final String TAG = "Sample";
    private FirebaseFirestore db;
    private final CollectionReference collectionReference;

    private FirestoreHandler() {
        accountData = new HashMap<String, Account>();

        db = FirebaseFirestore.getInstance();
        collectionReference = db.collection("Users");
    }

    public static FirestoreHandler create() {
        if (singletonFirestoreHandler == null) {
            singletonFirestoreHandler = new FirestoreHandler();
        }
        return singletonFirestoreHandler;
    }

    /**
     * UNUSED: For future pfp saving/conversion since firestore does not support bitmaps
     * @param encodedString
     * @return
     */
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

    public String getActiveUserId() {
        return activeUserId;
    }

    public void setActiveUserId(String activeUserId) {
        this.activeUserId = activeUserId;
    }

    public Account getActiveUserAccount() {
        return accountData.get(activeUserId);
    }

    /**
     * Deletes active user account
     */
    public void deleteActiveUserAccount() {
        accountData.remove(activeUserId);
        collectionReference
                .document(activeUserId)
                .delete()
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
        setActiveUserId("");
        return;
    }

    /**
     * Queries firestore for account info
     * @return
     */
    public HashMap<String, Account> getAccountData() {
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
        return accountData;
    }

    /**
     * Either adds or overrides a given account
     * @param newAccount
     */
    public void modifyAccount(Account newAccount) {
        // TODO: check for duplicate emails, usernames, and ids
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
}
