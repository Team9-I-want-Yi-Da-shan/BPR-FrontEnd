package com.example.home.networking;

import com.example.home.model.PersonalPlan;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPersonPlanListByPersonIDResponse {

    @SerializedName("results")
    private List<PersonalPlan>results;

    public GetPersonPlanListByPersonIDResponse(List<PersonalPlan> data) {
        this.results = results;
    }

    public List<PersonalPlan> getResults() {
        return results;
    }

    public void setResults(List<PersonalPlan> results) {
        this.results = results;
    }
}
