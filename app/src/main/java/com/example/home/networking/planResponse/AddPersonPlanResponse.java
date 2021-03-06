package com.example.home.networking.planResponse;

import com.google.gson.annotations.SerializedName;

public class AddPersonPlanResponse {

    @SerializedName("message")
    private String message;

    @SerializedName("plan_id")
    private int plan_id;


    public AddPersonPlanResponse(String message, int plan_id) {
        this.message = message;
        this.plan_id = plan_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }
}
