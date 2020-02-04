package com.physics.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LessonVo implements Serializable {
//    private static final long serialVersionUID = 1631280650588763177L;
    private int id;
    private String name;
    private String  address;
    //    描述周次
    private String time;
    //    备注
    private String remarks;
    private String type;
    private List<Integer> weeklys =new ArrayList<>();

    public List<Integer> getWeeklys() {
        return weeklys;
    }

    public void setWeeklys(List<Integer> weeklys) {
        this.weeklys = weeklys;
    }

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
