package com.example.home.model.dataTransferObject;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class PersonalActivityDTO implements Serializable {

    private int activity_id;
    private int user_id;
    private String title;
    private String description;
    private long start_at;
    private long finish_at;
    private int reminder;
    private int is_repeat;
    private int repeat_interval;
    private int isAlarm;
    private int isFinish;

    private final int secondsPerDay = 86400;

    public PersonalActivityDTO(){ }



    public LocalDateTime getStartTimeInLocalDateTime(){
        LocalDateTime startTime = Instant.ofEpochMilli(this.start_at).atZone(ZoneId.systemDefault()).toLocalDateTime();
        return startTime;
    }

    public LocalDateTime getEndTimeInLocalDateTime(){
        LocalDateTime endTime = Instant.ofEpochMilli(this.finish_at).atZone(ZoneId.systemDefault()).toLocalDateTime();
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
        return repeat_interval /secondsPerDay;
    }


    public int getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(int activity_id) {
        this.activity_id = activity_id;
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

    public long getFinish_at() {
        return finish_at;
    }

    public void setFinish_at(long finish_at) {
        this.finish_at = finish_at;
    }

    public int getReminder() {
        return reminder;
    }

    public void setReminder(int reminder) {
        this.reminder = reminder;
    }

    public int getIs_repeat() {
        return is_repeat;
    }

    public void setIs_repeat(int is_repeat) {
        this.is_repeat = is_repeat;
    }

    public int getRepeat_interval() {
        return repeat_interval;
    }

    public void setRepeat_interval(int repeat_interval) {
        this.repeat_interval = repeat_interval;
    }

    public int getIsAlarm() {
        return isAlarm;
    }

    public void setIsAlarm(int isAlarm) {
        this.isAlarm = isAlarm;
    }

    public int getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(int isFinish) {
        this.isFinish = isFinish;
    }

    @Override
    public String toString() {
        return "PersonalActivityDTO{" +
                "activity_id=" + activity_id +
                ", user_id=" + user_id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", start_at=" + start_at +
                ", finish_at=" + finish_at +
                ", reminder=" + reminder +
                ", repeat=" + is_repeat +
                ", repeat_interval=" + repeat_interval +
                ", isAlarm=" + isAlarm +
                ", isFinish=" + isFinish +
                '}';
    }
}
