package com.example.brianmote.expensemanager.firebase;

import com.example.brianmote.expensemanager.listeners.OnAuthListener;
import com.example.brianmote.expensemanager.listeners.OnCreateAccountListener;
import com.example.brianmote.expensemanager.pojos.User;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

/**
 * Created by Brian Mote on 5/12/2016.
 */
public class FirebaseAuthHandler {
    private static final String TAG = FirebaseAuthHandler.class.getSimpleName();
    private Firebase ref = new Firebase(FirebaseInit.BASE_REF);
    private Firebase userRef = ref.child("users");
    private OnCreateAccountListener createAccountListener;
    private OnAuthListener authListener;

    public FirebaseAuthHandler() {

    }

    /**
     * Creates a new User Account
     * @param user
     * @param createAccountListener
     */
    public void createAccount(final User user, final OnCreateAccountListener createAccountListener) {
        this.createAccountListener = createAccountListener;

        ref.createUser(user.getEmail(), user.getPassword(), new Firebase.ResultHandler() {
            @Override
            public void onSuccess() {
                ref.authWithPassword(user.getEmail(), user.getPassword(),
                        new Firebase.AuthResultHandler() {
                            @Override
                            public void onAuthenticated(AuthData authData) {
                                userRef.child(authData.getUid()).updateChildren(user.getUserMap());
                                createAccountListener.onComplete();
                            }

                            @Override
                            public void onAuthenticationError(FirebaseError firebaseError) {
                                createAccountListener.onError(firebaseError);
                            }
                        });
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                createAccountListener.onError(firebaseError);
            }
        });
    }

    public void login(final User user, final OnAuthListener authListener) {
        this.authListener = authListener;

        ref.authWithPassword(user.getEmail(), user.getPassword(), new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                authListener.onComplete();
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                authListener.onError(firebaseError);
            }
        });
    }

}
