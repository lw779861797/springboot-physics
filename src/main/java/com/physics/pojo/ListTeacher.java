package com.physics.pojo;

import java.io.Serializable;

public class ListTeacher implements Serializable {
    private int weekly;
//    周天
    private String sunday;
//    周一
    private String monday;
//    周二
    private String tuesday;
//    周三
    private String wednesday;
//    周四
    private String thursday;
//    周五
    private String friday;
//    周六
    private String saturday;

    public int getWeekly() {
        return weekly;
    }

    public void setWeekly(int weekly) {
        this.weekly = weekly;
    }

    public String getSunday() {
        return sunday;
    }

    public void setSunday(String sunday) {
        this.sunday = sunday;
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }

    public String getSaturday() {
        return saturday;
    }

    public void setSaturday(String saturday) {
        this.saturday = saturday;
    }
}
