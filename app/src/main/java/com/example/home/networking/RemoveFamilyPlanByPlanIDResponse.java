package com.example.home.networking;

import com.google.gson.annotations.SerializedName;

public class RemoveFamilyPlanByPlanIDResponse {


    @SerializedName("message")
    private String message;

    public RemoveFamilyPlanByPlanIDResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
