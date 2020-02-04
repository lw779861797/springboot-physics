package com.physics.pojo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

//描述课程的大范围
public class Lesson implements Serializable {
    private static final long serialVersionUID = 5782931420483096025L;
    private int id;
    private String name;
    private String  address;
//    描述周次
    private String time;
//    备注
    private String remarks;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
