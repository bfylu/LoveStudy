package com.example.bfy.lovestudy.entity;

/**
 * Created by bfy on 17-6-6.
 */

public class User {
    //用户id
    private int uid;
    //用户名
    private String userName;
    //昵称
    private String compel;
    //
    private String number;
    //密码
    private String password;
    //0学生1老师
    private int type;
    //性别,0男，1女
    private int sex;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCompel() {
        return compel;
    }

    public void setCompel(String compel) {
        this.compel = compel;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
