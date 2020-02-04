package com.physics.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

//留言类
public class Answer implements Serializable {
    private int id;
    private int student_id;
    private String context;
//    留言的创建日期
    private Date date;
//    留言是否被回复
    private int status = 0;
    private String message;
    private Reply reply;
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Reply getReply() {
        return reply;
    }

    public void setReply(Reply reply) {
        this.reply = reply;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
