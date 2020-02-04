package com.physics.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

//实验课程类
public class Course implements Serializable {
    private int id;
    private String name;
//    该实验一个班多少人
    private int number;
//    已经选了多少个位置
    private int selected;
    private String address;
    private int teacher_id;
//    周次具体描述
    private String weekly_time;
//    描述哪一周
    private String week;
//    描述星期几
    private String week_day;
//    描述是第几节
    private String  week_day_section;
//    描述具体日期

    private Date date;
//    描述该实验是否过期
    private int status = 0;
//    课程的id
    private int lesson_id;;
//    预约该实验的记录
    private List<CourseCode> course_Codes;

    public List<CourseCode> getCourseCodes() {
        return course_Codes;
    }

    public void setCourseCodes(List<CourseCode> courseCodes) {
        this.course_Codes = courseCodes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getWeekly_time() {
        return weekly_time;
    }

    public void setWeekly_time(String weekly_time) {
        this.weekly_time = weekly_time;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getWeek_day() {
        return week_day;
    }

    public void setWeek_day(String week_day) {
        this.week_day = week_day;
    }

    public String getWeek_day_section() {
        return week_day_section;
    }

    public void setWeek_day_section(String week_day_section) {
        this.week_day_section = week_day_section;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    public List<CourseCode> getCourse_Codes() {
        return course_Codes;
    }

    public void setCourse_Codes(List<CourseCode> course_Codes) {
        this.course_Codes = course_Codes;
    }
}
