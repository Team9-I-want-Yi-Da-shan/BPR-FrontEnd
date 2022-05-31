package com.example.home.model.dataTransferObject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FamilyPlanDTO {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("family_id")
    @Expose
    private int family_id;

    @SerializedName("description")
    @Expose

    private String description;

    @SerializedName("comment")
    @Expose
    private String comment;

    @SerializedName("plan_id")
    @Expose
    private int plan_id;

    @SerializedName("user_id")
    @Expose
    private int user_id;

    public FamilyPlanDTO(){

    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFamily_id() {
        return family_id;
    }

    public void setFamily_id(int family_id) {
        this.family_id = family_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }
}
