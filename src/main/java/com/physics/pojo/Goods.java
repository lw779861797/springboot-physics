package com.physics.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//丢失，或者认领的物品
public class Goods implements Serializable {
    private int id;
    private int admin_id;
    private Date date;
    private String title;
    private String context;
//    0为认领，1为找回
    private int status;
    private String type;
    private String time;
    private List<GoodsReply> goodsReplyList;
    private String admin_message;

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", admin_id=" + admin_id +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", context='" + context + '\'' +
                ", status=" + status +
                ", type='" + type + '\'' +
                ", time='" + time + '\'' +
                ", goodsReplyList=" + goodsReplyList +
                ", admin_message='" + admin_message + '\'' +
                '}';
    }

    public String getAdmin_message() {
        return admin_message;
    }

    public void setAdmin_message(String admin_message) {
        this.admin_message = admin_message;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public List<GoodsReply> getGoodsReplyList() {
        return goodsReplyList;
    }

    public void setGoodsReplyList(List<GoodsReply> goodsReplyList) {
        this.goodsReplyList = goodsReplyList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
