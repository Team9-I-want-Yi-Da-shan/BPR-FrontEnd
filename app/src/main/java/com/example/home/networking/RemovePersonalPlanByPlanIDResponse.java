package com.example.home.networking;

import com.google.gson.annotations.SerializedName;

public class RemovePersonalPlanByPlanIDResponse {

    @SerializedName("message")
    private String message;

    public RemovePersonalPlanByPlanIDResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
