package com.example.brianmote.expensemanager.listeners;

import com.firebase.client.FirebaseError;

/**
 * Created by Brian Mote on 5/12/2016.
 */
public interface OnAuthListener {
    void onComplete();

    void onError(FirebaseError error);
}
