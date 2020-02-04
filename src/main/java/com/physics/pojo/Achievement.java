package com.physics.pojo;

public class Achievement {
    private int id;
    private int course_id;
    private float achievement;
    private int student_id;
    private String course_name;
//    周次
    private String course_time;
//    座位号
    private int course_seat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public float getAchievement() {
        return achievement;
    }

    public void setAchievement(float achievement) {
        this.achievement = achievement;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_time() {
        return course_time;
    }

    public void setCourse_time(String course_time) {
        this.course_time = course_time;
    }

    public int getCourse_seat() {
        return course_seat;
    }

    public void setCourse_seat(int course_seat) {
        this.course_seat = course_seat;
    }
}
