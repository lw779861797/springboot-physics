package com.physics.pojo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class Teacher implements Serializable {
    @NotNull
    private int id;
    @Size(min = 6,max = 6,message = "学工号只能为6位")
    private String user_name;
    @Size(min = 6,max = 6,message = "学工号密码只能为6位")
    private String pass_word;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
