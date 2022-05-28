package com.example.home.model;

public class FamilyActivity {
    private String title;
    private String description;
    private String startTime;
    private String endTime;
    private String reminder;
    private String repeat;


    public FamilyActivity(String title, String description, String startTime, String endTime, String reminder, String repeat) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reminder = reminder;
        this.repeat = repeat;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getReminder() {
        return reminder;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }
}
