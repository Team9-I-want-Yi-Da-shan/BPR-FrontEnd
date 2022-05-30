package com.example.home.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FamilyPlan {

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

    @SerializedName("data")
    @Expose
    private List<PersonalPlan> data = null;

    public FamilyPlan(String name, int family_id, String description, String comment) {
        this.name = name;
        this.description = description;
        this.comment = comment;
        this.family_id = family_id;
    }

    public FamilyPlan(String name, String description, String comment) {
        this.name = name;
        this.description = description;
        this.comment = comment;
    }
    public FamilyPlan(int plan_id, String name, int family_id, String description, String comment) {
        this.name = name;
        this.description = description;
        this.comment = comment;
        this.plan_id = plan_id;
        this.family_id = family_id;
    }

    public FamilyPlan(int family_id) {
        this.family_id = family_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<PersonalPlan> getPlanList() {
        return data;
    }

    public void setPlanList(List<PersonalPlan> planList) {
        this.data = planList;
    }

    public int getFamily_id() {return family_id;}

    public void setFamily_id(int family_id) {this.family_id = family_id;}
}
