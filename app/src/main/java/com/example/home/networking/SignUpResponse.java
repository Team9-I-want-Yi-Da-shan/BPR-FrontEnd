package com.example.home.networking;

import com.example.home.model.User;
import com.google.gson.annotations.SerializedName;

public class SignUpResponse {

    @SerializedName("message")
    private String message;
    private User user;

    public SignUpResponse(){
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


