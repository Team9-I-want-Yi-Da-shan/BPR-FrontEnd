package com.example.home.networking.activityResponse;

import com.example.home.model.dataTransferObject.FamilyActivityDTO;

import java.util.ArrayList;

public class GetFamilyActivityByDateResponse {
    private ArrayList<FamilyActivityDTO> familyActivityDTOS;

    public ArrayList<FamilyActivityDTO> getFamilyActivityDAOS() {
        return familyActivityDTOS;
    }

    public void setFamilyActivityDAOS(ArrayList<FamilyActivityDTO> familyActivityDTOS) {
        this.familyActivityDTOS = familyActivityDTOS;
    }
}
