package com.example.home.networking;

import com.example.home.model.PersonalPlan;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPersonPlanListByPersonIDResponse {

    @SerializedName("data")
    private List<PersonalPlan>data;


    public GetPersonPlanListByPersonIDResponse(List<PersonalPlan> data) {
        this.data = data;
    }

    public List<PersonalPlan> getData() {
        return data;
    }

    public void setData(List<PersonalPlan> data) {
        this.data = data;
    }
}
