package com.example.brianmote.expensemanager.firebase;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Brian Mote on 5/12/2016.
 */
public class FirebaseInit extends Application {
    public static final String BASE_REF = "https://personal-budget.firebaseio.com/";

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
