package com.example.brianmote.expensemanager.listeners;

import com.firebase.client.FirebaseError;

/**
 * Created by Brian Mote on 5/13/2016.
 */
public interface OnDataInsertedListener {
    void onComplete();

    void onError(FirebaseError error);
}
