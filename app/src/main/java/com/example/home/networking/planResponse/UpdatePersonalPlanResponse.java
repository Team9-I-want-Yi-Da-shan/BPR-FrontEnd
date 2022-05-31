package com.example.home.networking.planResponse;

import com.google.gson.annotations.SerializedName;

public class UpdatePersonalPlanResponse {


    @SerializedName("mesage")
    private String message;

    public UpdatePersonalPlanResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
