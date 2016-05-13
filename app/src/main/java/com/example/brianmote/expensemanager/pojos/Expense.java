package com.example.brianmote.expensemanager.pojos;

import java.util.HashMap;

/**
 * Created by Brian Mote on 5/13/2016.
 */
public class Expense {
    private String name;
    private String amount;
    private boolean isPaid;
    private HashMap<String, Object> expMap;

    public Expense() {

    }

    public Expense(String name, String amount) {
        this.name = name;
        this.amount = amount;
    }

    public Expense(String name, String amount, boolean isPaid) {
        this(name, amount);
        this.isPaid = isPaid;
    }

    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }

    public HashMap<String, Object> getExpMap() {
        expMap = new HashMap<>();
        expMap.put("name", name);
        expMap.put("amount", amount);
        return expMap;
    }

}
