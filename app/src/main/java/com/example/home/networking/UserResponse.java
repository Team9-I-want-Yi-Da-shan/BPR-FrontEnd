package com.example.home.networking;

import com.example.home.model.User;

public class UserResponse {
    private int id;
    private String name;

    public User getUser() {
        return new User();
    }
}
