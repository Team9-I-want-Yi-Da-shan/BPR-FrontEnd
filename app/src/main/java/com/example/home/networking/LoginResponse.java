package com.example.home.networking;

import com.example.home.model.User;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponse {
    @SerializedName("message")
    private String message;
    @SerializedName("user")
    private User user;

    public LoginResponse(){
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public User getUser(){return user;}

    public void setUser(User user) {
        this.user = user;
    }

}
