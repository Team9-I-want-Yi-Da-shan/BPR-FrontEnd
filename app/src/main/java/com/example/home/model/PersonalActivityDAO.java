package com.example.home.model;

public class PersonalActivityDAO {
    private String title;
    private String description;
    private long startTime;
    private long endTime;
    private String reminder;
    private String repeat;

    public PersonalActivityDAO(){}


    public PersonalActivityDAO(String title, String description, long startTime, long endTime) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public PersonalActivityDAO(String title, String description, long startTime, long endTime, String reminder) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reminder = reminder;
    }

    public PersonalActivityDAO(String title, String description, long startTime, long endTime, String reminder, String repeat) {
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

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
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

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }
}
