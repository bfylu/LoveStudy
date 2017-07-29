package com.example.bfy.lovestudy.entity;

/**
 * Created by bfy on 17-5-27.
 */

public class MuKeCourseList extends MuKeStatus {
    /**
     * status 状态
     */
    private int status;
    /**
     * data 数据
     */
    //private String data;
    /**
     * erorCode
     */
    private int errorCode;
    /**
     * errorDesc
     */
    private String errorDesc;
    /**
     * timestamp
     */
    private long timestamp;
    /**
     * hash
     */
    private String hash;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    /*
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    */
    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
