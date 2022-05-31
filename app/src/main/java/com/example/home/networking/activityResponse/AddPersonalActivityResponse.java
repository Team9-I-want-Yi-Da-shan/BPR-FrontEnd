package com.example.home.networking.activityResponse;

import com.google.gson.annotations.SerializedName;

public class AddPersonalActivityResponse {
    private int activity_id;
    private String message;

    public AddPersonalActivityResponse(int activity_id, String message) {
        this.activity_id = activity_id;
        this.message = message;
    }

    public int getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(int activity_id) {
        this.activity_id = activity_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
