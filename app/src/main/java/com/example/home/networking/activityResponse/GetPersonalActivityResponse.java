package com.example.home.networking.activityResponse;

import com.example.home.model.dataTransferObject.PersonalActivityDTO;

import java.util.ArrayList;

public class GetPersonalActivityResponse {


    private ArrayList<PersonalActivityDTO> data;

    public ArrayList<PersonalActivityDTO> getPersonalActivityDAOS() {
        return data;
    }

    public void setPersonalActivityDAOS(ArrayList<PersonalActivityDTO> personalActivityDTOS) {
        this.data = personalActivityDTOS;
    }

    @Override
    public String toString() {
        return "GetPersonalActivityByDateResponse{" +
                "personalActivityDTOS=" + data +
                '}';
    }
}
