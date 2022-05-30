package com.example.home.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PersonalPlan {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("user_id")
    @Expose
    private int user_id;

    @SerializedName("description")
    @Expose

    private String description;

    @SerializedName("comment")
    @Expose
    private String comment;

    @SerializedName("plan_id")
    @Expose
    private int plan_id;

    @SerializedName("results")
    @Expose
    private List<PersonalPlan> results = null;

    public PersonalPlan(String name, int user_id, String description, String comment) {
        this.name = name;
        this.user_id = user_id;
        this.description = description;
        this.comment = comment;
    }

    public PersonalPlan(String name, String description, String comment) {
        this.name = name;
        this.description = description;
        this.comment = comment;
    }

    public PersonalPlan(int plan_id){
        this.plan_id = plan_id;

    }
    public PersonalPlan(int plan_id, String name, int user_id, String description, String comment) {
        this.name = name;
        this.user_id = user_id;
        this.description = description;
        this.comment = comment;
        this.plan_id = plan_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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
        return results;
    }

    public void setPlanList(List<PersonalPlan> planList) {
        this.results = planList;
    }
}
