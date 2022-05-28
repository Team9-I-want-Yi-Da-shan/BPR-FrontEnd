package com.example.home.networking;

import com.example.home.model.PersonalPlan;
import com.google.gson.annotations.SerializedName;

public class GetPersonalPlanByPlanIDResponse {


    @SerializedName("plan")
    private PersonalPlan personalPlan;

    public GetPersonalPlanByPlanIDResponse(PersonalPlan personalPlan) {
        this.personalPlan = personalPlan;
    }

    public PersonalPlan getPersonalPlan() {
        return personalPlan;
    }

    public void setPersonalPlan(PersonalPlan personalPlan) {
        this.personalPlan = personalPlan;
    }
}
