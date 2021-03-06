package com.example.home.model;


import java.time.LocalDateTime;


public class PersonalActivity {
    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int reminder;
    private int isAlarm;
    private int repeat;
    private int isFinish;

    public PersonalActivity(String title, String description, LocalDateTime startTime, LocalDateTime endTime, int reminder, int isAlarm, int repeat, int isFinish) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reminder = reminder;
        this.isAlarm = isAlarm;
        this.repeat = repeat;
        this.isFinish = isFinish;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getReminder() {
        return reminder;
    }

    public void setReminder(int reminder) {
        this.reminder = reminder;
    }

    public int getIsAlarm() {
        return isAlarm;
    }

    public void setIsAlarm(int isAlarm) {
        this.isAlarm = isAlarm;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public int getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(int isFinish) {
        this.isFinish = isFinish;
    }
}
