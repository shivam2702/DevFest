package com.shivam.devfestblr;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.shivam.devfestblr.helpers.AppDatabase;
import com.shivam.devfestblr.helpers.SharedPreferenceHelperKt;

public class MyApplication extends Application {
    public static AppDatabase database;
    public static MyApplication instance;
    public static DocumentReference firebaseDatabase;
    public FirebaseAuth mAuth;


    public static MyApplication getInstance() {
        if (instance == null)
            instance = new MyApplication();
        return instance;
    }

    static AppDatabase getDatabase() {
        return database;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        changeTheme();
        database = AppDatabase.Companion.getInstance(getApplicationContext());
        firebaseDatabase = FirebaseFirestore.getInstance().collection("devfest").document("2019");
        mAuth = FirebaseAuth.getInstance();

        mAuth.signInAnonymously()
                .addOnCompleteListener(task -> {
                    checkOnlineDatabaseVersion();
                });
    }

    private void checkOnlineDatabaseVersion() {
        firebaseDatabase.collection("version").document("DatabaseVersion").get()
                .addOnSuccessListener(documentSnapshot -> {
                    long version = documentSnapshot.getLong("VersionNo");
                    long current = SharedPreferenceHelperKt.getOnlineDatabaseVersion();
                    if (current < version) {
                        database.syncAll();
                        SharedPreferenceHelperKt.setOnlineDatabaseVersion(version);
                    }
                    database.essentialSync();
                });
    }

    public void changeTheme() {
        AppCompatDelegate.setDefaultNightMode(SharedPreferenceHelperKt.getDarkModeSetting());
    }
}
