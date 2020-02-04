package com.physics.pojo;

import java.io.Serializable;

public class Code implements Serializable {
    private int id;
    private String context;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
