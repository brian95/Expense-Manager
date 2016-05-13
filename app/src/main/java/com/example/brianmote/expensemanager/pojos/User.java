package com.example.brianmote.expensemanager.pojos;

import com.firebase.client.annotations.NotNull;

import java.util.HashMap;

/**
 * Created by Brian Mote on 5/12/2016.
 */
public class User {
    private String username;
    private String email;
    private String password;
    private HashMap<String, Object> userMap;

    public User() {

    }

    public User(@NotNull String email, @NotNull String password) {
        this.email = email;
        this.password = password;
    }

    public User(@NotNull String username, @NotNull  String email, @NotNull  String password) {
        this(email, password);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public HashMap<String, Object> getUserMap() {
        userMap = new HashMap<>();
        userMap.put("username", username);
        userMap.put("email", email);
        return userMap;
    }
}
