package com.example.home.model.dataTransferObject;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class PersonalActivityDTO {
    private final int secondsPerDay = 86400;


    private int user_id;
    private String title;
    private String description;
    private long start_at;
    private long end_at;
    private int reminder;
    private int repeat;
    private int interval;
    private int is_alarm;
    private int isFinish;

    public PersonalActivityDTO(){ }


    //    public PersonalActivity convertToPersonalActivity(){
//        String title = this.title;
//        String description = this.description;
//        LocalDateTime startTime = Instant.ofEpochMilli(this.start_at).atZone(ZoneId.systemDefault()).toLocalDateTime();
//        LocalDateTime endTime = Instant.ofEpochMilli(this.end_at).atZone(ZoneId.systemDefault()).toLocalDateTime();
//        int isFinish = this.isFinish;
//        int reminder = this.reminder;
//        int isAlarm = this.is_alarm;
//        //TODO deal with repeat interval
//        int repeat = (int)this.repeat_interval;
//
//        return new PersonalActivity(title,description,startTime,endTime,reminder,isAlarm,repeat,isFinish);
//    }



    public LocalDateTime getStartTimeInLocalDateTime(){
        LocalDateTime startTime = Instant.ofEpochMilli(this.start_at).atZone(ZoneId.systemDefault()).toLocalDateTime();
        return startTime;
    }

    public LocalDateTime getEndTimeInLocalDateTime(){
        LocalDateTime endTime = Instant.ofEpochMilli(this.end_at).atZone(ZoneId.systemDefault()).toLocalDateTime();
        return endTime;
    }

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    public String getStartTimeString(){
        LocalDateTime localDateTime = getStartTimeInLocalDateTime();
        String timeString = localDateTime.format(formatter);
        return timeString;
    }
    public String getEndTimeString(){
        LocalDateTime localDateTime = getEndTimeInLocalDateTime();
        String timeString = localDateTime.format(formatter);
        return timeString;
    }

    public int getRepeatInDay(){
        return interval /secondsPerDay;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public long getStart_at() {
        return start_at;
    }

    public void setStart_at(long start_at) {
        this.start_at = start_at;
    }

    public long getEnd_at() {
        return end_at;
    }

    public void setEnd_at(long end_at) {
        this.end_at = end_at;
    }

    public int getReminder() {
        return reminder;
    }

    public void setReminder(int reminder) {
        this.reminder = reminder;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getIs_alarm() {
        return is_alarm;
    }

    public void setIs_alarm(int is_alarm) {
        this.is_alarm = is_alarm;
    }

    public int getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(int isFinish) {
        this.isFinish = isFinish;
    }
}
