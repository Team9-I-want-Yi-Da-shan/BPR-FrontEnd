package com.example.home.networking.activityResponse;

import com.example.home.model.dataTransferObject.PersonalActivityDTO;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetPersonalActivityByDateResponse {
    @SerializedName("results")
    private ArrayList<PersonalActivityDTO> personalActivityDTOS;

    public ArrayList<PersonalActivityDTO> getPersonalActivityDAOS() {
        return personalActivityDTOS;
    }

    public void setPersonalActivityDAOS(ArrayList<PersonalActivityDTO> personalActivityDTOS) {
        this.personalActivityDTOS = personalActivityDTOS;
    }

    @Override
    public String toString() {
        return "GetPersonalActivityByDateResponse{" +
                "personalActivityDTOS=" + personalActivityDTOS +
                '}';
    }
}
