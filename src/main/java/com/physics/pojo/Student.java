package com.physics.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Student implements Serializable {
//    @NotNull(message = "id不能为空")
    private int id ;
    private String name;
    private String user_name;
    private String pass_word;
    //    用户是否可用
    private int status;
//    用户的创建时间
    private Date date;
//    哪个学院
    private String collage;
//    哪个专业
    private String major;
    //    选课记录
    private List<CourseCode> course_Codes;
//    需要选的课程数
    private int number;
//    已经选的课程数
    private int selected;
    private String weekday;
    private String week_day_section;

    public List<CourseCode> getCourse_Codes() {
        return course_Codes;
    }

    public void setCourse_Codes(List<CourseCode> course_Codes) {
        this.course_Codes = course_Codes;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getWeek_day_section() {
        return week_day_section;
    }

    public void setWeek_day_section(String week_day_section) {
        this.week_day_section = week_day_section;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
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

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPass_word() {
        return pass_word;
    }

    public void setPass_word(String pass_word) {
        this.pass_word = pass_word;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCollage() {
        return collage;
    }

    public void setCollage(String collage) {
        this.collage = collage;
    }
}
