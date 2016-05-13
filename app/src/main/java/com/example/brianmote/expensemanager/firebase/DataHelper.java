package com.example.brianmote.expensemanager.firebase;

import com.example.brianmote.expensemanager.listeners.OnDataInsertedListener;

/**
 * Created by Brian Mote on 5/13/2016.
 */
public interface DataHelper<T> {
    void insert(T t, OnDataInsertedListener dataInsertedListener);
}
