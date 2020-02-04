package com.physics.pojo;

public class CourseVo {
    private int id;
    private int number;
    private int selected;
    private int weekly;
    private int lesson_id;

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getWeekly() {
        return weekly;
    }

    public void setWeekly(int weekly) {
        this.weekly = weekly;
    }
}
