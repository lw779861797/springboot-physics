package com.physics.pojo;


import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
//课程记录类 对应学生选了什么课程
public class CourseCode implements Serializable {
    private int id;
    private int student_id;
    private int course_id;

    private Date course_code_time;
    private String  course_name;
    private String  course_weekly;
//    座位
    private int seat;
//    判断该课程是否已经过日期
//    0为未过期
    private int status = 0;

    public String getCourse_weekly() {
        return course_weekly;
    }

    public void setCourse_weekly(String course_weekly) {
        this.course_weekly = course_weekly;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public Date getCourse_code_time() {
        return course_code_time;
    }

    public void setCourse_code_time(Date course_code_time) {
        this.course_code_time = course_code_time;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
