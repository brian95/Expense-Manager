package com.example.brianmote.expensemanager.firebase;

import com.example.brianmote.expensemanager.listeners.OnDataInsertedListener;
import com.example.brianmote.expensemanager.pojos.Expense;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;

/**
 * Created by Brian Mote on 5/13/2016.
 */
public class ExpenseHandler implements DataHelper<Expense> {
    private static final String TAG = ExpenseHandler.class.getSimpleName();
    private Firebase ref = new Firebase(FirebaseInit.BASE_REF);
    private Firebase expRef = ref.child("expenses");
    private OnDataInsertedListener dataInsertedListener;
    private HashMap<String, Object> map;

    public ExpenseHandler() {

    }


    @Override
    public void insert(Expense expense, final OnDataInsertedListener dataInsertedListener) {
        this.dataInsertedListener = dataInsertedListener;
        map = new HashMap<>();
        map.put("name", expense.getName());
        map.put("amount", expense.getAmount());
        map.put("createdBy", Utils.getCurrentUsername());
        map.put("isPaid", false);

        expRef.push().updateChildren(map, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                if (firebaseError != null) {
                    dataInsertedListener.onError(firebaseError);
                } else {
                    dataInsertedListener.onComplete();
                }
            }
        });
    }
}
