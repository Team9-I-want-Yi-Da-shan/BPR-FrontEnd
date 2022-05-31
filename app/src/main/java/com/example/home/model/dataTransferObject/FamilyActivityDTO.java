package com.example.home.model.dataTransferObject;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class FamilyActivityDTO {
    private final int secondsPerDay = 86400;
    private int family_id;
    private String title;
    private String description;
    private long start_at;
    private long end_at;
    private int isFinish;
    private int reminder;
    private int repeat;
    private int repeat_interval;
    private int is_alarm;
    private int[] users;

    public FamilyActivityDTO(){}


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
        return repeat_interval/secondsPerDay;
    }

    public int getFamily_id() {
        return family_id;
    }

    public void setFamily_id(int family_id) {
        this.family_id = family_id;
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

    public int getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(int isFinish) {
        this.isFinish = isFinish;
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

    public int getRepeat_interval() {
        return repeat_interval;
    }

    public void setRepeat_interval(int repeat_interval) {
        this.repeat_interval = repeat_interval;
    }

    public int getIs_alarm() {
        return is_alarm;
    }

    public void setIs_alarm(int is_alarm) {
        this.is_alarm = is_alarm;
    }
}
