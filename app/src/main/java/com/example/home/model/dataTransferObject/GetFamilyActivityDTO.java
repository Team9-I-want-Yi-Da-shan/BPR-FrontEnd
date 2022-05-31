package com.example.home.model.dataTransferObject;

public class GetFamilyActivityDTO {
    private int family_id;
    private long date;

    public GetFamilyActivityDTO(int family_id, long date) {
        this.family_id = family_id;
        this.date = date;
    }
}
