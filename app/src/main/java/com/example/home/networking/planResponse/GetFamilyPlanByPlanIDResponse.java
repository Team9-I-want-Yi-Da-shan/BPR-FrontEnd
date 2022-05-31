package com.example.home.networking.planResponse;

import com.example.home.model.FamilyPlan;
import com.example.home.model.PersonalPlan;
import com.google.gson.annotations.SerializedName;

public class GetFamilyPlanByPlanIDResponse {


    @SerializedName("plan")
    private FamilyPlan familyPlan;

    public GetFamilyPlanByPlanIDResponse(FamilyPlan familyPlan) {
        this.familyPlan = familyPlan;
    }

    public FamilyPlan getFamilyPlan() {
        return familyPlan;
    }

    public void setFamilyPlan(FamilyPlan familyPlan) {
        this.familyPlan = familyPlan;
    }
}
