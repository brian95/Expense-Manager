package com.example.brianmote.expensemanager.firebase;

import com.example.brianmote.expensemanager.pojos.User;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by Brian Mote on 5/13/2016.
 */
public class Utils {
    private static Firebase ref = new Firebase(FirebaseInit.BASE_REF);
    private static Firebase userRef = ref.child("users");
    private static Firebase expRef = ref.child("expenses");
    private static String currentUsername;
    public Utils() {

    }

    public static String getCurrentUsername() {
        userRef.child(ref.getAuth().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentUsername = (String) dataSnapshot.child("username").getValue();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        return currentUsername;
    }
}
