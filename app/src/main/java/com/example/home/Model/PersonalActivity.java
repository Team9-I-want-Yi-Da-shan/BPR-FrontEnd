package com.example.home.Model;


public class PersonalActivity {
    private String title;
    private String description;
    private String startTime;
    private String endTime;
    private String reminder;
    private String repeat;

    public PersonalActivity(String title, String description, String startTime, String endTime, String reminder, String repeat) {
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
}
