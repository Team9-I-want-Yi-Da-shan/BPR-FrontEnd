package com.example.home.networking.planResponse;

import com.example.home.model.FamilyPlan;
import com.example.home.model.PersonalPlan;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetFamilyPlanListByFamilyIDResponse {


    public List<FamilyPlan> getResults() {
        return results;
    }

    public void setResults(List<FamilyPlan> results) {
        this.results = results;
    }

    @SerializedName("results")
    private List<FamilyPlan> results;

    public GetFamilyPlanListByFamilyIDResponse(List<FamilyPlan> results) {
        this.results = results;
    }

}
