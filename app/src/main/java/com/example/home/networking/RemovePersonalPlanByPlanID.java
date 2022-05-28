package com.example.home.networking;

import com.google.gson.annotations.SerializedName;

public class RemovePersonalPlanByPlanID {

    @SerializedName("message")
    private String message;

    public RemovePersonalPlanByPlanID(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
