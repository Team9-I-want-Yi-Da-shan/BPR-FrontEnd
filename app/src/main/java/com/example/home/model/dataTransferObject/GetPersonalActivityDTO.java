package com.example.home.model.dataTransferObject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetPersonalActivityDTO {

    private int user_id;
    private long date;

    public GetPersonalActivityDTO(int user_id, long date) {
        this.user_id = user_id;
        this.date = date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "GetPersonalActivityDTO{" +
                "user_id=" + user_id +
                ", date=" + date +
                '}';
    }
}
