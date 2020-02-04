package com.physics.pojo;

import java.util.Date;

public class GoodsReply {
    private int id;
    private int goods_id;
    private String context;
    private int admin_id;
    private Date date;
    private String time;
    private String admin_message;


    @Override
    public String toString() {
        return "GoodsReply{" +
                "id=" + id +
                ", goods_id=" + goods_id +
                ", context='" + context + '\'' +
                ", admin_id=" + admin_id +
                ", date=" + date +
                ", time='" + time + '\'' +
                ", admin_message='" + admin_message + '\'' +
                '}';
    }

    public String getAdmin_message() {
        return admin_message;
    }

    public void setAdmin_message(String admin_message) {
        this.admin_message = admin_message;
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }
}
